package com.saucedemo.stepdefs;

import static org.testng.Assert.assertEquals;

import java.util.List;

import com.saucedemo.api.ApiClient;
import com.saucedemo.data.ApiData;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ApiSteps {
	
	private final ApiClient api = new ApiClient();
	private Response response;
	
	@When("a request is made to get all posts")
	public void aRequestIsMadeToGetAllPosts() {
		response = api.getAllPosts();
	}
	
	@When("a request is made to get the post with id {int}")
	public void aRequestIsMadeToGetThePostWithId(int id) {
		response = api.getPostsById(id);
	}
	
	@When("a request is made to create a new post")
	public void aRequestIsMadeToCreateANewPost() {
		response = api.createPost(ApiData.newPost());
	}
	
	@Then("the API response status is {int}")
	public void theApiResponseStatusIs(int status) {
		assertEquals(response.statusCode(),(status));
	}
	
	@Then("the response contains {int} posts")
	public void theResponseContainsPosts(int count) {
		List<?> posts = response.jsonPath().getList("$");
		assertEquals(posts.size(), count);
	}
	
	@Then("the returned post id is {int}")
	public void theReturnedPostIdIs(int id) {
		assertEquals(response.jsonPath().getInt("id"),id);
	}
	
	@Then("the created post title matches the test data")
	public void theCreatedPostTitleMatchesTheTestData() {
		assertEquals(response.jsonPath().getString("title"),ApiData.newPost().getTitle());
	}

}
