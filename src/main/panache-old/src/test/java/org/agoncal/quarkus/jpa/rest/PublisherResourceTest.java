package org.agoncal.quarkus.jpa.rest;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jpa.model.Publisher;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.Response;
import java.util.List;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PublisherResourceTest {

  private static final String NAME = "Name";

  private static int nbPublishers;
  private static String publisherId;

  @Test
  public void shouldFindAnExistingPublisher() {
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

  @Test
  void shouldNotAddInvalidPublisher() {
    Publisher publisher = new Publisher();
    publisher.name = null;

    given()
      .body(publisher, ObjectMapperType.JSONB)
      .header(CONTENT_TYPE, APPLICATION_JSON).
    when()
      .post("/api/publishers").
    then()
      .statusCode(INTERNAL_SERVER_ERROR.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldCountInitialPublishers() {
    nbPublishers =
      given().
      when()
        .get("/api/publishers/count").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(Integer.class);

    List<Publisher> listPublishers =
      given().
      when()
        .get("/api/publishers").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getPublisherTypeRef());

    assertEquals(nbPublishers, listPublishers.size());
  }

  private TypeRef<List<Publisher>> getPublisherTypeRef() {
    return new TypeRef<List<Publisher>>() {
    };
  }

  @Test
  @Order(2)
  void shouldAddANewPublisher() {
    Publisher publisher = new Publisher();
    publisher.name = NAME;

    // Persists a new publisher
    String location =
      given()
        .body(publisher, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON).
      when()
        .post("/api/publishers").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the book id
    assertTrue(location.contains("/api/publishers"));
    String[] segments = location.split("/");
    publisherId = segments[segments.length - 1];
    assertNotNull(publisherId);
  }

  @Test
  @Order(3)
  void shouldFindCreatedPublisher() {
    given()
      .pathParam("id", publisherId).
    when()
      .get("/api/publishers/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .body("name", Is.is(NAME));
  }

  @Test
  @Order(4)
  void shouldCountCreatedPublisher() {
    int publishers =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/publishers/count").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(Integer.class);

    assertEquals(nbPublishers + 1, publishers);

    List<Publisher> listPublishers =
      given().
      when()
        .get("/api/publishers").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getPublisherTypeRef());

    assertEquals(publishers, listPublishers.size());
  }

  @Test
  @Order(5)
  void shouldRemoveCreatedPublisher() {
    // Deletes the previously created book
    given()
      .pathParam("id", publisherId).
    when()
      .delete("/api/publishers/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldCountRemovedPublisher() {
    int publishers =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/publishers/count").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(Integer.class);

    assertEquals(nbPublishers, publishers);

    List<Publisher> listPublishers =
      given().
      when()
        .get("/api/publishers").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getPublisherTypeRef());

    assertEquals(publishers, listPublishers.size());
  }
}
