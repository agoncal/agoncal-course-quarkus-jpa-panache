package org.agoncal.quarkus.panache.rest;
//@formatter:off

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jdbc.Artist;
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

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.OK;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArtistResourceTest {

  private static Faker faker = new Faker();
  private static final String NAME = faker.book().author();
  private static final String BIO = faker.lorem().paragraph(1);

  private static Long nbAuthors;
  private static String authorId;

  @Test @Disabled
  void shouldNotAddInvalidAuthor() {
    Artist artist = new Artist();
    artist.setName(null);

    given()
      .body(artist, ObjectMapperType.JSONB)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
    when()
      .post("/api/artists").
    then()
      .statusCode(INTERNAL_SERVER_ERROR.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldCountInitialAuthors() {
    nbAuthors =
      given().
        when()
          .get("/api/artists/count").
        then()
          .statusCode(OK.getStatusCode())
          .extract().body().as(Long.class);

    List<Artist> listAuthors =
      given().
        when()
          .get("/api/artists").
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
    artist.setName(NAME);
    artist.setBio(BIO);

    // Persists a new artist
    String location =
      given()
        .body(artist, ObjectMapperType.JSONB)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
      when()
        .post("/api/artists").
      then()
        .statusCode(Response.Status.CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the book id
    Assertions.assertTrue(location.contains("/api/artists"));
    String[] segments = location.split("/");
    authorId = segments[segments.length - 1];
    Assertions.assertNotNull(authorId);
  }

  @Test
  @Order(3)
  void shouldFindCreatedAuthor() {
    given()
      .pathParam("id", authorId).
    when()
    .get("/api/artists/{id}").
      then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body("name", Is.is(NAME))
      .body("bio", Is.is(BIO));
  }

  @Test
  @Order(4)
  void shouldCountCreatedAuthor() {
    Long authors =
      given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
      when()
        .get("/api/artists/count").
      then()
        .statusCode(Response.Status.OK.getStatusCode())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .extract().body().as(Long.class);

    Assertions.assertEquals(nbAuthors + 1, authors);

    List<Artist> listAuthors =
      given().
        when()
          .get("/api/artists").
        then()
         .statusCode(Response.Status.OK.getStatusCode())
         .extract().body().as(getAuthorTypeRef());

    Assertions.assertEquals(authors, listAuthors.size());
  }

  @Test
  @Order(5)
  void shouldRemoveCreatedAuthor() {
    // Deletes the previously created book
    given()
      .pathParam("id", authorId).
    when()
      .delete("/api/artists/{id}").
    then()
      .statusCode(Response.Status.NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldCountRemovedAuthor() {
    Long authors =
      given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
        when()
        .get("/api/artists/count").
        then()
        .statusCode(Response.Status.OK.getStatusCode())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .extract().body().as(Long.class);

    Assertions.assertEquals(nbAuthors, authors);

    List<Artist> listAuthors =
      given().
        when()
        .get("/api/artists").
        then()
        .statusCode(Response.Status.OK.getStatusCode())
        .extract().body().as(getAuthorTypeRef());

    Assertions.assertEquals(authors, listAuthors.size());
  }
}
