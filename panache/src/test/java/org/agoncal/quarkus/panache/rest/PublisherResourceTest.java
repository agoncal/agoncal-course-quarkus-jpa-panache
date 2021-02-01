package org.agoncal.quarkus.panache.rest;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.panache.model.Publisher;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PublisherResourceTest {

  @Test
  public void shouldCRUDPublishers() {
    given()
      .header(ACCEPT, TEXT_PLAIN).
    when()
      .get("/api/publishers/count").
    then()
      .statusCode(OK.getStatusCode())
      .body(is("11"));

    Publisher publisher = new Publisher("AGoncal Fascicles");

    given()
      .body(publisher, ObjectMapperType.JSONB)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(ACCEPT, APPLICATION_JSON).
    when()
      .post("/api/publishers").
    then()
      .statusCode(OK.getStatusCode());

    given()
      .header(ACCEPT, TEXT_PLAIN).
    when()
      .get("/api/publishers/count").
    then()
      .statusCode(OK.getStatusCode())
      .body(is("12"));

    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", 1000).
    when()
      .get("/api/publishers/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("name", Is.is("AGoncal Fascicles"));

    given()
      .pathParam("id", 1000).
    when()
      .delete("/api/publishers/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());

    given()
      .header(ACCEPT, TEXT_PLAIN).
    when()
      .get("/api/publishers/count").
    then()
      .statusCode(OK.getStatusCode())
      .body(is("11"));
  }
}
