package com.tomspencerlondon;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class RestAssuredTest {

  // curl --location --request POST 'localhost:8080/oauth/token' \
  //      --header 'Authorization: Basic amF2YWludXNlLWNsaWVudDpqYXZhaW51c2Utc2VjcmV0' \
  //      --header 'Content-Type: application/x-www-form-urlencoded' \
  //      --data-urlencode 'grant_type=client_credentials'

  @Test
  void queryToOauthThenTestWorks() {
    given().header("Authorization", "Bearer " + getAccessToken())
        .when()
        .get("http://localhost:9090/test")
        .then().statusCode(200)
        .assertThat().body(is("Hello World"));
  }

  public String getAccessToken() {
    Response response = given().auth()
        .preemptive()
        .basic("javainuse-client", "javainuse-secret")
        .formParam("grant_type", "client_credentials")
        .when().post("http://localhost:8080/oauth/token")
        .then().extract().response();
    return response.body().jsonPath().get("access_token").toString();
  }
}
