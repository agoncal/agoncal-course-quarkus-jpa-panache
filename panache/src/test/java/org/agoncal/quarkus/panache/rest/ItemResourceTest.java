package org.agoncal.quarkus.panache.rest;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.CD;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
public class ItemResourceTest {

  @Test
  public void shouldCRUDBooks() {
    given()
      .header(ACCEPT, TEXT_PLAIN).
    when()
      .get("/api/books/count").
    then()
      .statusCode(OK.getStatusCode())
      .body(is("5"));

    Book book = new Book("Understanding Quarkus", "Best Quarkus book ever", 9.99f, "13-235-8764", 500);

    given()
      .body(book, ObjectMapperType.JSONB)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(ACCEPT, APPLICATION_JSON).
    when()
      .post("/api/books").
    then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("title", is("Understanding Quarkus"))
      .body("description", startsWith("Best Quarkus book ever"))
      .body("unitCost", is(9.99F))
      .body("isbn", is("13-235-8764"))
      .body("nbOfPages", is(500));


    given()
      .header(ACCEPT, TEXT_PLAIN).
    when()
      .get("/api/books/count").
    then()
      .statusCode(OK.getStatusCode())
      .body(is("6"));

    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", 1000).
    when()
      .get("/api/books/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("id", is(1000))
      .body("title", is("Beginning Java EE 7"))
      .body("description", startsWith("Java Enterprise Edition (Java EE) continues to be one of the leading"))
      .body("unitCost", is(49.99F))
      .body("isbn", is("143024626X"))
      .body("publicationDate", is("2014-02-05"))
      .body("language", is("ENGLISH"))
      .body("nbOfPages", is(608))
      .body("publisher.id", is(1001))
      .body("publisher.name", is( "APress"));

    given()
      .pathParam("id", 1000).
    when()
      .delete("/api/books/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());

    given()
      .header(ACCEPT, TEXT_PLAIN).
    when()
      .get("/api/books/count").
    then()
      .statusCode(OK.getStatusCode())
      .body(is("5"));
  }


  @Test
  public void shouldCRUDCDs() {
    given()
      .header(ACCEPT, TEXT_PLAIN).
      when()
      .get("/api/cds/count").
      then()
      .statusCode(OK.getStatusCode())
      .body(is("5"));

    CD cd = new CD("Quarkus Lonely Heart", "Impired by the Beatles", 9.99f, 13.5F, "Geek Pop");

    given()
      .body(cd, ObjectMapperType.JSONB)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(ACCEPT, APPLICATION_JSON).
      when()
      .post("/api/cds").
      then()
      .statusCode(OK.getStatusCode());

    given()
      .header(ACCEPT, TEXT_PLAIN).
      when()
      .get("/api/cds/count").
      then()
      .statusCode(OK.getStatusCode())
      .body(is("6"));

    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", 100).
      when()
      .get("/api/cds/{id}").
      then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("title", is("Let It Be"))
      .body("description", startsWith("Let It Be is the 12th and final studio album released by the English rock band"))
      .body("unitCost", is(12.99F))
      .body("totalDuration", is(123.0F))
      .body("musicCompany", is("EMI"))
      .body("genre", is("Pop Rock"));

    given()
      .pathParam("id", 100).
      when()
      .delete("/api/cds/{id}").
      then()
      .statusCode(NO_CONTENT.getStatusCode());

    given()
      .header(ACCEPT, TEXT_PLAIN).
      when()
      .get("/api/cds/count").
      then()
      .statusCode(OK.getStatusCode())
      .body(is("5"));
  }


}
