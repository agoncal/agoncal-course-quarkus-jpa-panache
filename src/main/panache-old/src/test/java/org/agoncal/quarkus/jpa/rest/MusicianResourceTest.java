package org.agoncal.quarkus.jdbc.rest;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jdbc.model.Musician;
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
  public void shouldFindAnExistingMusician() {
    given()
      .pathParam("id", 3001).
    when()
      .get("/api/musicians/{id}").
    then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body("firstName", Is.is("John"))
      .body("lastName", Is.is("Lennon"));
  }

  @Test
  public void shouldNotFindUnknownMusician() {
    given()
      .pathParam("id", 9999).
    when()
      .get("/api/musicians/{id}").
    then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  void shouldNotAddInvalidMusician() {
    Musician musician = new Musician();
    musician.setFirstName(null);

    given()
      .body(musician, ObjectMapperType.JSONB)
      .header(CONTENT_TYPE, APPLICATION_JSON).
    when()
      .post("/api/musicians").
    then()
      .statusCode(INTERNAL_SERVER_ERROR.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldCountInitialMusicians() {
    nbMusicians =
      given().
      when()
        .get("/api/musicians/count").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(Integer.class);

    List<Musician> listMusicians =
      given().
      when()
        .get("/api/musicians").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getMusicianTypeRef());

    assertEquals(nbMusicians, listMusicians.size());
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
      given()
        .body(musician, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON).
      when()
        .post("/api/musicians").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the book id
    assertTrue(location.contains("/api/musicians"));
    String[] segments = location.split("/");
    musicianId = segments[segments.length - 1];
    assertNotNull(musicianId);
  }

  @Test
  @Order(3)
  void shouldFindCreatedMusician() {
    given()
      .pathParam("id", musicianId).
    when()
      .get("/api/musicians/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .body("firstName", is(FIRST_NAME))
      .body("lastName", is(LAST_NAME))
      .body("preferredInstrument", is(PREFERRED_INSTRUMENT));
  }

  @Test
  @Order(4)
  void shouldCountCreatedMusician() {
    int musicians =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/musicians/count").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(Integer.class);

    assertEquals(nbMusicians + 1, musicians);

    List<Musician> listMusicians =
      given().
      when()
        .get("/api/musicians").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getMusicianTypeRef());

    assertEquals(musicians, listMusicians.size());
  }

  @Test
  @Order(5)
  void shouldRemoveCreatedMusician() {
    // Deletes the previously created book
    given()
      .pathParam("id", musicianId).
    when()
      .delete("/api/musicians/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldCountRemovedMusician() {
    int musicians =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/musicians/count").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(Integer.class);

    assertEquals(nbMusicians, musicians);

    List<Musician> listMusicians =
      given().
      when()
        .get("/api/musicians").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getMusicianTypeRef());

    assertEquals(musicians, listMusicians.size());
  }
}
