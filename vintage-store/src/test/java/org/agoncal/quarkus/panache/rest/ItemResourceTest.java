package org.agoncal.quarkus.panache.rest;
//@formatter:off

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.Language;
import org.junit.jupiter.api.Disabled;import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import java.math.BigDecimal;
import java.time.LocalDate;
import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static org.hamcrest.Matchers.hasKey;

@QuarkusTest @Disabled
public class ItemResourceTest {

  private Faker faker = new Faker();

  @Test
  public void shouldCreateAndFindAnBook() {
    Book book = new Book();
    book.title = faker.book().title();
    book.description = faker.lorem().paragraph();
    book.price = BigDecimal.TEN;
    book.isbn = faker.code().isbn13();
    book.nbOfPages = faker.number().numberBetween(10, 800);
    book.publicationDate = LocalDate.now();
    book.language = Language.ENGLISH;

    book =
      given()
        .body(book, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, MediaType.APPLICATION_JSON).
      when()
        .post("/api/items/books").
      then()
        .statusCode(201)
        .extract().body().as(Book.class);

    given()
      .pathParam("id", book.id).
    when()
      .get("/api/items/books/{id}").
    then()
      .statusCode(200)
      .body("$", hasKey("id"))
      .body("$", hasKey("title"))
      .body("$", hasKey("description"))
      .body("$", hasKey("price"))
      .body("$", hasKey("isbn"))
      .body("$", hasKey("nbOfPages"))
      .body("$", hasKey("publicationDate"))
      .body("$", hasKey("language"))
      .body("$", hasKey("createdDate"));
  }
}
