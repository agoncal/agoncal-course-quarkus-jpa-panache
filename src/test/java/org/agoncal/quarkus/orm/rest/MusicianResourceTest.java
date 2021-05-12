package org.agoncal.quarkus.orm.rest;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jpa.model.Musician;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MusicianResourceTest {

  private static final String FIRST_NAME = "First name";
  private static final String LAST_NAME = "Last name";
  private static final String PREFERRED_INSTRUMENT = "Preferred instrument";

  private static int nbMusicians;
  private static String musicianId;

  @Test
  void shouldNotAddInvalidMusician() {
    Musician musician = new Musician();
    musician.setFirstName(null);

    RestAssured.given()
      .body(musician, ObjectMapperType.JSONB)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
    when()
      .post("/api/musicians").
    then()
      .statusCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldCountInitialMusicians() {
    nbMusicians =
      RestAssured.given().
      when()
        .get("/api/musicians/count").
      then()
        .statusCode(Status.OK.getStatusCode())
        .extract().body().as(Integer.class);

    List<Musician> listMusicians =
      RestAssured.given().
      when()
        .get("/api/musicians").
      then()
        .statusCode(Status.OK.getStatusCode())
        .extract().body().as(getMusicianTypeRef());

    Assertions.assertEquals(nbMusicians, listMusicians.size());
  }

  private TypeRef<List<Musician>> getMusicianTypeRef() {
    return new TypeRef<List<Musician>>() {
    };
  }

  @Test
  @Order(2)
  void shouldAddANewMusician() {
    Musician musician = new Musician();
    musician.setFirstName(FIRST_NAME);
    musician.setLastName(LAST_NAME);
    musician.setPreferredInstrument(PREFERRED_INSTRUMENT);

    // Persists a new musician
    String location =
      RestAssured.given()
        .body(musician, ObjectMapperType.JSONB)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
      when()
        .post("/api/musicians").
      then()
        .statusCode(Status.CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the book id
    Assertions.assertTrue(location.contains("/api/musicians"));
    String[] segments = location.split("/");
    musicianId = segments[segments.length - 1];
    Assertions.assertNotNull(musicianId);
  }

  @Test
  @Order(3)
  void shouldFindCreatedMusician() {
    RestAssured.given()
      .pathParam("id", musicianId).
    when()
      .get("/api/musicians/{id}").
    then()
      .statusCode(Status.OK.getStatusCode())
      .body("firstName", Is.is(FIRST_NAME))
      .body("lastName", Is.is(LAST_NAME))
      .body("preferredInstrument", Is.is(PREFERRED_INSTRUMENT));
  }

  @Test
  @Order(4)
  void shouldCountCreatedMusician() {
    int musicians =
      RestAssured.given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
      when()
        .get("/api/musicians/count").
      then()
        .statusCode(Status.OK.getStatusCode())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .extract().body().as(Integer.class);

    Assertions.assertEquals(nbMusicians + 1, musicians);

    List<Musician> listMusicians =
      RestAssured.given().
      when()
        .get("/api/musicians").
      then()
        .statusCode(Status.OK.getStatusCode())
        .extract().body().as(getMusicianTypeRef());

    Assertions.assertEquals(musicians, listMusicians.size());
  }

  @Test
  @Order(5)
  void shouldRemoveCreatedMusician() {
    // Deletes the previously created book
    RestAssured.given()
      .pathParam("id", musicianId).
    when()
      .delete("/api/musicians/{id}").
    then()
      .statusCode(Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldCountRemovedMusician() {
    int musicians =
      RestAssured.given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
      when()
        .get("/api/musicians/count").
      then()
        .statusCode(Status.OK.getStatusCode())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .extract().body().as(Integer.class);

    Assertions.assertEquals(nbMusicians, musicians);

    List<Musician> listMusicians =
      RestAssured.given().
      when()
        .get("/api/musicians").
      then()
        .statusCode(Status.OK.getStatusCode())
        .extract().body().as(getMusicianTypeRef());

    Assertions.assertEquals(musicians, listMusicians.size());
  }
}
