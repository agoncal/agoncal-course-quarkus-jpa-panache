package org.agoncal.quarkus.jdbc.rest;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jdbc.model.Book;
import org.agoncal.quarkus.jdbc.model.Language;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.Response;

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
public class BookResourceTest {

  private static final String TITLE = "Title";
  private static final String DESCRIPTION = "Description";
  private static final String ISBN = "Isbn";
  private static final Integer NB_OF_PAGES = 123;
  private static final Language LANGUAGE  = Language.PORTUGUESE;

  private static int nbBooks;
  private static String bookId;

  @Test
  public void shouldFindAnExistingBook() {
    given()
      .pathParam("id", 4000).
    when()
      .get("/api/books/{id}").
    then()
      .statusCode(Response.Status.OK.getStatusCode())
      .body("title", Is.is("Beginning Java EE 7"));
  }

  @Test
  public void shouldNotFindUnknownBook() {
    given()
      .pathParam("id", 9999).
    when()
      .get("/api/books/{id}").
    then()
      .statusCode(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  void shouldNotAddInvalidBook() {
    Book book = new Book();
    book.title = null;

    given()
      .body(book, ObjectMapperType.JSONB)
      .header(CONTENT_TYPE, APPLICATION_JSON).
    when()
      .post("/api/books").
    then()
      .statusCode(INTERNAL_SERVER_ERROR.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldCountInitialBooks() {
    nbBooks =
      given().
      when()
        .get("/api/books/count").
      then()
        .statusCode(OK.getStatusCode())
        .extract().body().as(Integer.class);
  }

  @Test
  @Order(2)
  void shouldAddANewBook() {
    Book book = new Book();
    book.title = TITLE;
    book.description= DESCRIPTION;
    book.isbn = ISBN;
    book.nbOfPages = NB_OF_PAGES;
    book.language = LANGUAGE;

    // Persists a new book
    String location =
      given()
        .body(book, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON).
      when()
        .post("/api/books").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the book id
    assertTrue(location.contains("/api/books"));
    String[] segments = location.split("/");
    bookId = segments[segments.length - 1];
    assertNotNull(bookId);
  }

  @Test
  @Order(3)
  void shouldFindCreatedBook() {
    given()
      .pathParam("id", bookId).
    when()
      .get("/api/books/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .body("title", is(TITLE))
      .body("description", is(DESCRIPTION))
      .body("isbn", is(ISBN))
      .body("nbOfPages", is(NB_OF_PAGES))
      .body("language", is(LANGUAGE.toString()));
  }

  @Test
  @Order(4)
  void shouldCountCreatedBook() {
    int books =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/books/count").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(Integer.class);

    assertEquals(nbBooks + 1, books);
  }

  @Test
  @Order(5)
  void shouldRemoveCreatedBook() {
    // Deletes the previously created book
    given()
      .pathParam("id", bookId).
    when()
      .delete("/api/books/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldCountRemovedBook() {
    int books =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/books/count").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(Integer.class);

    assertEquals(nbBooks, books);
  }
}
