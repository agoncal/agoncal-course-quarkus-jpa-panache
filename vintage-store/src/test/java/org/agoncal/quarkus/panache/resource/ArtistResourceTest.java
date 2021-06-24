package org.agoncal.quarkus.panache.resource;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jdbc.Artist;
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
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArtistResourceTest {

  private static int nbArtists;
  private static String artistId;

  @Test
  void shouldNotGetUnknownArtist() {
    Long randomId = new Random().nextLong();
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", randomId).
    when()
      .get("/api/artists/{id}").
    then()
      .statusCode(NOT_FOUND.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldGetInitialArtists() {
    nbArtists =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/artists").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();
  }

  @Test
  @Order(2)
  void shouldCreateANewArtist() {
    Artist artist = new Artist();
    artist.setName("name");
    artist.setBio("bio");

    String location =
      given()
        .body(artist, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .post("/api/artists").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the id
    assertTrue(location.contains("/api/artists"));
    String[] segments = location.split("/");
    artistId = segments[segments.length - 1];
    assertNotNull(artistId);
  }

  @Test
  @Order(3)
  void shouldGetAnExtraArtist() {
    int artists =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/artists").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbArtists + 1, artists);
  }

  @Test
  @Order(4)
  void shouldCheckTheExtraArtist() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", artistId).
    when()
      .get("/api/artists/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("name", is("name"))
      .body("bio", is("bio"))
      .body("$", hasKey("id"))
      .body("$", hasKey("createdDate"));
  }

  @Test
  @Order(5)
  void shouldDeleteTheExtraArtist() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", artistId).
    when()
      .delete("/api/artists/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldGetLessAnArtist() {
    int artists =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/artists").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbArtists, artists);
  }
}
