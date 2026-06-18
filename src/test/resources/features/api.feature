@api

Feature: Posts API
Validating the JSONPlaceholder posts endpoints

@smoke
Scenario:Get all posts
When a request is made to get all posts
Then the API response status is 200
And the response contains 100 posts


@regression
Scenario:Get a post by id
When a request is made to get the post with id 1
Then the API response status is 200
And the returned post id is 1

@regression
Scenario:Create a new post
When a request is made to create a new post
Then the API response status is 201
And the created post title matches the test data

@negative
Scenario:Non-existent post returns 404
When a request is made to get the post with id 999999
Then the API response status is 404