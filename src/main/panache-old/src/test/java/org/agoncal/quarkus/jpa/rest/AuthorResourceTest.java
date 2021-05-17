package org.agoncal.quarkus.jdbc.rest;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jdbc.model.Author;
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
public class AuthorResourceTest {

  private static final String FIRST_NAME = "First name";
  private static final String LAST_NAME = "Last name";
  private static final String BIO = "Bio";

  private static int nbAuthors;
  private static String authorId;

  @Test
  public void shouldFindAnExistingAuthor() {
    given()
      .pathParam("id", 2001).
    when()
      .get("/api/authors/{id}").
    then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body("firstName", Is.is("Antonio"))
      .body("lastName", Is.is("Goncalves"));
  }

  @Test
  public void shouldNotFindUnknownAuthor() {
    given()
      .pathParam("id", 9999).
    when()
      .get("/api/authors/{id}").
    then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  void shouldNotAddInvalidAuthor() {
    Author author = new Author();
    author.setFirstName(null);

    given()
      .body(author, ObjectMapperType.JSONB)
      .header(CONTENT_TYPE, APPLICATION_JSON).
    when()
      .post("/api/authors").
    then()
      .statusCode(INTERNAL_SERVER_ERROR.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldCountInitialAuthors() {
    nbAuthors =
      given().
      when()
        .get("/api/authors/count").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(Integer.class);

    List<Author> listAuthors =
      given().
      when()
        .get("/api/authors").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getAuthorTypeRef());

    assertEquals(nbAuthors, listAuthors.size());
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
      given()
        .body(author, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON).
      when()
        .post("/api/authors").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the book id
    assertTrue(location.contains("/api/authors"));
    String[] segments = location.split("/");
    authorId = segments[segments.length - 1];
    assertNotNull(authorId);
  }

  @Test
  @Order(3)
  void shouldFindCreatedAuthor() {
    given()
      .pathParam("id", authorId).
    when()
      .get("/api/authors/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .body("firstName", is(FIRST_NAME))
      .body("lastName", is(LAST_NAME))
      .body("bio", is(BIO));
  }

  @Test
  @Order(4)
  void shouldCountCreatedAuthor() {
    int authors =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/authors/count").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(Integer.class);

    assertEquals(nbAuthors + 1, authors);

    List<Author> listAuthors =
      given().
      when()
        .get("/api/authors").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getAuthorTypeRef());

    assertEquals(authors, listAuthors.size());
  }

  @Test
  @Order(5)
  void shouldRemoveCreatedAuthor() {
    // Deletes the previously created book
    given()
      .pathParam("id", authorId).
    when()
      .delete("/api/authors/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldCountRemovedAuthor() {
    int authors =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/authors/count").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(Integer.class);

    assertEquals(nbAuthors, authors);

    List<Author> listAuthors =
      given().
      when()
        .get("/api/authors").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(getAuthorTypeRef());

    assertEquals(authors, listAuthors.size());
  }
}
