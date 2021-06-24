package org.agoncal.quarkus.panache.resource;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.CD;
import org.agoncal.quarkus.panache.model.Language;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.hasKey;import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemResourceTest {

  private static int nbBooks;
  private static String bookId;
  private static int nbCDs;
  private static String cdId;

  @Test
  void shouldNotGetUnknownBook() {
    Long randomId = new Random().nextLong();
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", randomId).
    when()
      .get("/api/items/books/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldGetInitialBooks() {
    nbBooks =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/items/books").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();
  }

  @Test
  @Order(2)
  void shouldCreateANewBook() {
    Book book = new Book();
    book.title = "title";
    book.description = "description";
    book.price = new BigDecimal(10);
    book.isbn = "ISBN";
    book.nbOfPages = 500;
    book.publicationDate = LocalDate.now().minusDays(100);
    book.language = Language.ENGLISH;

    String location =
      given()
        .body(book, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .post("/api/items/books").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the id
    assertTrue(location.contains("/api/items/books"));
    String[] segments = location.split("/");
    bookId = segments[segments.length - 1];
    assertNotNull(bookId);
  }

  @Test
  @Order(3)
  void shouldGetAnExtraBook() {
    int books =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/items/books").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbBooks + 1, books);
  }

  @Test
  @Order(4)
  void shouldCheckTheExtraBook() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", bookId).
    when()
      .get("/api/items/books/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("title", is("title"))
      .body("description", is("description"))
      .body("isbn", is("ISBN"))
      .body("nbOfPages", is(500))
      .body("$", hasKey("id"))
      .body("$", hasKey("createdDate"));
  }

  @Test
  @Order(5)
  void shouldDeleteTheExtraBook() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", bookId).
    when()
      .delete("/api/items/books/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldGetLessABook() {
    int books =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/items/books").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbBooks, books);
  }

  @Test
  void shouldNotGetUnknownCD() {
    Long randomId = new Random().nextLong();
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", randomId).
    when()
      .get("/api/items/cds/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldGetInitialCDs() {
    nbCDs =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/items/cds").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();
  }

  @Test
  @Order(2)
  void shouldCreateANewCD() {
    CD cd = new CD();
    cd.title = "title";
    cd.description = "description";
    cd.price = new BigDecimal(10);
    cd.musicCompany = "music company";
    cd.genre = "genre";

    String location =
      given()
        .body(cd, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .post("/api/items/cds").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the id
    assertTrue(location.contains("/api/items/cds"));
    String[] segments = location.split("/");
    cdId = segments[segments.length - 1];
    assertNotNull(cdId);
  }

  @Test
  @Order(3)
  void shouldGetAnExtraCD() {
    int cds =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/items/cds").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbCDs + 1, cds);
  }

  @Test
  @Order(4)
  void shouldCheckTheExtraCD() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", cdId).
    when()
      .get("/api/items/cds/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("title", is("title"))
      .body("description", is("description"))
      .body("musicCompany", is("music company"))
      .body("genre", is("genre"))
      .body("$", hasKey("id"))
      .body("$", hasKey("createdDate"));
  }

  @Test
  @Order(5)
  void shouldDeleteTheExtraCD() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", cdId).
    when()
      .delete("/api/items/cds/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldGetLessACD() {
    int cds =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/items/cds").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbCDs, cds);
  }
}
