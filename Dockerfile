# Maven + JDK 21 base, with Google Chrome for headless UI tests.
# WebDriverManager resolves the matching chromedriver at runtime.
FROM maven:3.9-eclipse-temurin-21

# Install Google Chrome (stable)
RUN apt-get update\
	&& apt-get install -y --no-install-recommends wget gnupg ca-certificates \
	&& wget -q -0 - https://dl.google.com/linux/linux_signing_key.pub \
		| gpg --dearmor -o /urs/share/keyrings/google-chrome.gpg \
	&& echo "deb [arch=amd64 signed by=/usr/share/keyrings/google-chrome.gpg] http://dl.google.com/linux/chrome/deb/ stable main"\
		> /etc/apt/sources.list.d/google-chrome.list \
	&& apt-get update \
	&& apt-get install -y --no-install-recommends google-chrome-stable \
	&& rm -rf /var/lib/apt/lists/*
	
WORKDIR /app


# Cache dependencies first for faster rebuilds.
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# Copy the rest of the framework.
COPY . .


# Default: run the full suite headless on Chrome. Override at runtime, e.g.:
#   docker run --rm --shm-size=2g saucedemo mvn test -Dcucumber.filter.tags="@smoke"
CMD ["mvn","-B","test","-Dheadless=true","-Dbrowser=chrome"]