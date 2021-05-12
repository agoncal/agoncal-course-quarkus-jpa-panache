package org.agoncal.quarkus.orm.rest;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jpa.model.Author;
import org.graalvm.compiler.debug.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.net.http.HttpHeaders;
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
public class AuthorResourceTest {

  private static final String FIRST_NAME = "First name";
  private static final String LAST_NAME = "Last name";
  private static final String BIO = "Bio";

  private static int nbAuthors;
  private static String authorId;

  @Test
  void shouldNotAddInvalidAuthor() {
    Author author = new Author();
    author.setFirstName(null);

    RestAssured.given()
      .body(author, ObjectMapperType.JSONB)
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
    when()
      .post("/api/authors").
    then()
      .statusCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldCountInitialAuthors() {
    nbAuthors =
      RestAssured.given().
      when()
        .get("/api/authors/count").
      then()
        .statusCode(Status.OK.getStatusCode())
        .extract().body().as(Integer.class);

    List<Author> listAuthors =
      RestAssured.given().
      when()
        .get("/api/authors").
      then()
        .statusCode(Status.OK.getStatusCode())
        .extract().body().as(getAuthorTypeRef());

    Assertions.assertEquals(nbAuthors, listAuthors.size());
  }

  private TypeRef<List<Author>> getAuthorTypeRef() {
    return new TypeRef<List<Author>>() {
    };
  }

  @Test
  @Order(2)
  void shouldAddANewAuthor() {
    Author author = new Author();
    author.setFirstName(FIRST_NAME);
    author.setLastName(LAST_NAME);
    author.setBio(BIO);

    // Persists a new author
    String location =
      RestAssured.given()
        .body(author, ObjectMapperType.JSONB)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
      when()
        .post("/api/authors").
      then()
        .statusCode(Status.CREATED.getStatusCode())
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
      .statusCode(Status.OK.getStatusCode())
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
        .statusCode(Status.OK.getStatusCode())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .extract().body().as(Integer.class);

    Assertions.assertEquals(nbAuthors + 1, authors);

    List<Author> listAuthors =
      RestAssured.given().
      when()
        .get("/api/authors").
      then()
        .statusCode(Status.OK.getStatusCode())
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
      .statusCode(Status.NO_CONTENT.getStatusCode());
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
        .statusCode(Status.OK.getStatusCode())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        .extract().body().as(Integer.class);

    Assertions.assertEquals(nbAuthors, authors);

    List<Author> listAuthors =
      RestAssured.given().
      when()
        .get("/api/authors").
      then()
        .statusCode(Status.OK.getStatusCode())
        .extract().body().as(getAuthorTypeRef());

    Assertions.assertEquals(authors, listAuthors.size());
  }
}
