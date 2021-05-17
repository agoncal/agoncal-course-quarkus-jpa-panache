package org.agoncal.quarkus.jdbc.rest;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jdbc.model.CD;
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
public class CDResourceTest {

  private static final String TITLE = "Title";
  private static final String DESCRIPTION = "Description";
  private static final String MUSIC_COMPANY = "Music Company";
  private static final String GENRE = "Genre";

  private static int nbCDs;
  private static String cdId;

  @Test
  public void shouldFindAnExistingCD() {
    given()
      .pathParam("id", 5000).
    when()
      .get("/api/cds/{id}").
    then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body("title", Is.is("Let It Be"));
  }

  @Test
  public void shouldNotFindUnknownCD() {
    given()
      .pathParam("id", 9999).
    when()
      .get("/api/cds/{id}").
    then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  void shouldNotAddInvalidCD() {
    CD cd = new CD();
    cd.title = null;

    given()
      .body(cd, ObjectMapperType.JSONB)
      .header(CONTENT_TYPE, APPLICATION_JSON).
    when()
      .post("/api/cds").
    then()
      .statusCode(INTERNAL_SERVER_ERROR.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldCountInitialCDs() {
    nbCDs =
      given().
      when()
        .get("/api/cds/count").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(Integer.class);

    List<CD> listCDs =
      given().
      when()
        .get("/api/cds").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getCDTypeRef());

    assertEquals(nbCDs, listCDs.size());
  }

  private TypeRef<List<CD>> getCDTypeRef() {
    return new TypeRef<List<CD>>() {
    };
  }

  @Test
  @Order(2)
  void shouldAddANewCD() {
    CD cd = new CD();
    cd.title = TITLE;
    cd.description= DESCRIPTION;
    cd.musicCompany = MUSIC_COMPANY;
    cd.genre = GENRE;

    // Persists a new cd
    String location =
      given()
        .body(cd, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON).
      when()
        .post("/api/cds").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the cd id
    assertTrue(location.contains("/api/cds"));
    String[] segments = location.split("/");
    cdId = segments[segments.length - 1];
    assertNotNull(cdId);
  }

  @Test
  @Order(3)
  void shouldFindCreatedCD() {
    given()
      .pathParam("id", cdId).
    when()
      .get("/api/cds/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .body("title", is(TITLE))
      .body("description", is(DESCRIPTION))
      .body("musicCompany", is(MUSIC_COMPANY))
      .body("genre", is(GENRE));
  }

  @Test
  @Order(4)
  void shouldCountCreatedCD() {
    int cds =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/cds/count").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(Integer.class);

    assertEquals(nbCDs + 1, cds);

    List<CD> listCDs =
      given().
        when()
        .get("/api/cds").
        then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getCDTypeRef());

    assertEquals(cds, listCDs.size());
  }

  @Test
  @Order(5)
  void shouldRemoveCreatedCD() {
    // Deletes the previously created cd
    given()
      .pathParam("id", cdId).
    when()
      .delete("/api/cds/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldCountRemovedCD() {
    int cds =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/cds/count").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(Integer.class);

    assertEquals(nbCDs, cds);

    List<CD> listCDs =
      given().
        when()
        .get("/api/cds").
        then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getCDTypeRef());

    assertEquals(cds, listCDs.size());
  }
}
