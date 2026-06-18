package com.saucedemo.api;


import com.saucedemo.api.models.Post;
import com.saucedemo.config.ConfigReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/** RestAssured-based client for the JSONPlaceholder posts end points*/

public class ApiClient {

	private static final Logger log = LoggerFactory.getLogger(ApiClient.class);
	private final String baseUri;
	
	public ApiClient() {
		this.baseUri= ConfigReader.get("api.url","https://jsonplaceholder.typicode.com");
	}
	
	private RequestSpecification request() {
		return given()
				.baseUri(baseUri)
				.header("Content-Type","application/json");
	}
	
	public Response getAllPosts() {
		log.info("GET {}/posts",baseUri);
		return request().when().get("/posts");
	}
	
	public Response getPostsById(int id) {
		log.info("GET {}/posts/{}",baseUri, id);
		return request().when().get("/posts/{id}",id);
	}
	
	public Response createPost(Post post) {
		log.info("POST {}/posts (title={})",baseUri,post.getTitle());
		return request().body(post).when().post("/posts");
	}
	
	public static void resetFilters() {RestAssured.reset();}
}
