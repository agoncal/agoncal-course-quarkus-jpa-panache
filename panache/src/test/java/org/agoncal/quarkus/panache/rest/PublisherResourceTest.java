package org.agoncal.quarkus.panache.rest;
// @formatter:off

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class PublisherResourceTest {

  @Test
  public void shouldFindPublisherById() {
    given()
      .pathParam("id", 1000).
    when()
      .get("/api/publishers/{id}").
    then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body("name", is("AGoncal Fascicles"));
  }

  @Test
  public void shouldNotFindUnknownPublisher() {
    given()
      .pathParam("id", 9999).
    when()
      .get("/api/publishers/{id}").
    then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode());
  }
}
