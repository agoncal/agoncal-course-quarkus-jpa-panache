package org.agoncal.quarkus.panache.resource;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.hasKey;import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PublisherResourceTest {

  private static int nbPublishers;
  private static String publisherId;

  @Test @Disabled
  void shouldNotGetUnknownPublisher() {
    Long randomId = new Random().nextLong();
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", randomId).
    when()
      .get("/api/publishers/{id}").
    then()
      .statusCode(NOT_FOUND.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldGetInitialPublishers() {
    nbPublishers =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/publishers").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();
  }

  @Test
  @Order(2)
  void shouldCreateANewPublisher() {
    Publisher publisher = new Publisher();
    publisher.name = "name";

    String location =
      given()
        .body(publisher, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .post("/api/publishers").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the id
    assertTrue(location.contains("/api/publishers"));
    String[] segments = location.split("/");
    publisherId = segments[segments.length - 1];
    assertNotNull(publisherId);
  }

  @Test
  @Order(3)
  void shouldGetAnExtraPublisher() {
    int publishers =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/publishers").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbPublishers + 1, publishers);
  }

  @Test
  @Order(4)
  void shouldCheckTheExtraPublisher() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", publisherId).
    when()
      .get("/api/publishers/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("name", is("name"))
      .body("$", hasKey("id"))
      .body("$", hasKey("createdDate"));
  }

  @Test
  @Order(5)
  void shouldDeleteTheExtraPublisher() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", publisherId).
    when()
      .delete("/api/publishers/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldGetLessAnPublisher() {
    int publishers =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/publishers").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbPublishers, publishers);
  }
}
