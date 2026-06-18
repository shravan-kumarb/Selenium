package com.saucedemo.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.saucedemo.api.models.Post;

/** API test data, source from externalize JSON ({@code data/testdata.json}). */
public final class ApiData {
	
	public static final int EXISTING_POST_ID = TestData.number("api","existingPostId");
	public static final int NON_EXISTING_POST_ID = TestData.number("api","nonExistentPostId");
	public static final int EXPECTED_POST_COUNT = TestData.number("api","expectedPostCount");
	
	/**Builds the create post payload from the externalized test data. */
	public static Post newPost() {
		JsonNode node = TestData.node("api").path("newPost");
		return new Post(
				node.path("userId").asInt(),
				node.path("title").asText(),
				node.path("body").asText());
	}
	
	private ApiData() {}

}
