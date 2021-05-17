package org.agoncal.quarkus.panache.rest;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jpa.Artist;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.OK;

@QuarkusTest @Disabled
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArtistResourceTest {

  private static final String FIRST_NAME = "First name";
  private static final String LAST_NAME = "Last name";
  private static final String BIO = "Bio";

  private static int nbAuthors;
  private static String authorId;

  @Test
  void shouldNotAddInvalidAuthor() {
    Artist artist = new Artist();
    artist.setFirstName(null);

    RestAssured.given()
      .body(artist, ObjectMapperType.JSONB)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
      when()
      .post("/api/authors").
      then()
      .statusCode(INTERNAL_SERVER_ERROR.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldCountInitialAuthors() {
    nbAuthors =
      RestAssured.given().
        when()
        .get("/api/authors/count").
        then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(Integer.class);

    List<Artist> listAuthors =
      RestAssured.given().
        when()
        .get("/api/authors").
        then()
        .statusCode(Response.Status.OK.getStatusCode())
        .extract().body().as(getAuthorTypeRef());

    Assertions.assertEquals(nbAuthors, listAuthors.size());
  }

  private TypeRef<List<Artist>> getAuthorTypeRef() {
    return new TypeRef<List<Artist>>() {
    };
  }

  @Test
  @Order(2)
  void shouldAddANewAuthor() {
    Artist artist = new Artist();
    artist.setFirstName(FIRST_NAME);
    artist.setLastName(LAST_NAME);
    artist.setBio(BIO);

    // Persists a new artist
    String location =
      RestAssured.given()
        .body(artist, ObjectMapperType.JSONB)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
        when()
        .post("/api/authors").
        then()
        .statusCode(Response.Status.CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the book id
    Assertions.assertTrue(location.contains("/api/authors"));
    String[] segments = location.split("/");
    authorId = segments[segments.length - 1];
    Assertions.assertNotNull(authorId);
  }

  @Test
  @Order(3)
  void shouldFindCreatedAuthor() {
    RestAssured.given()
      .pathParam("id", authorId).
      when()
      .get("/api/authors/{id}").
      then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body("firstName", Is.is(FIRST_NAME))
      .body("lastName", Is.is(LAST_NAME))
      .body("bio", Is.is(BIO));
  }

  @Test
  @Order(4)
  void shouldCountCreatedAuthor() {
    int authors =
      RestAssured.given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
        when()
        .get("/api/authors/count").
        then()
        .statusCode(Response.Status.OK.getStatusCode())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .extract().body().as(Integer.class);

    Assertions.assertEquals(nbAuthors + 1, authors);

    List<Artist> listAuthors =
      RestAssured.given().
        when()
        .get("/api/authors").
        then()
        .statusCode(Response.Status.OK.getStatusCode())
        .extract().body().as(getAuthorTypeRef());

    Assertions.assertEquals(authors, listAuthors.size());
  }

  @Test
  @Order(5)
  void shouldRemoveCreatedAuthor() {
    // Deletes the previously created book
    RestAssured.given()
      .pathParam("id", authorId).
      when()
      .delete("/api/authors/{id}").
      then()
      .statusCode(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldCountRemovedAuthor() {
    int authors =
      RestAssured.given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
        when()
        .get("/api/authors/count").
        then()
        .statusCode(Response.Status.OK.getStatusCode())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .extract().body().as(Integer.class);

    Assertions.assertEquals(nbAuthors, authors);

    List<Artist> listAuthors =
      RestAssured.given().
        when()
        .get("/api/authors").
        then()
        .statusCode(Response.Status.OK.getStatusCode())
        .extract().body().as(getAuthorTypeRef());

    Assertions.assertEquals(authors, listAuthors.size());
  }
}
