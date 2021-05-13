package org.agoncal.quarkus.jpa.rest;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jpa.model.Artist;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static org.hamcrest.Matchers.hasKey;

@QuarkusTest
public class ArtistResourceTest {

  private Faker faker = new Faker();

  @Test
  public void shouldCreateAndFindAnArtist() {
    Artist artist = new Artist();
    artist.setFirstName(faker.name().firstName());
    artist.setLastName(faker.name().lastName());
    artist.setBio(faker.lorem().paragraph());

    artist =
      given()
        .body(artist, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, MediaType.APPLICATION_JSON).
      when()
        .post("/api/artists").
      then()
        .statusCode(201)
        .extract().body().as(Artist.class);

    System.out.println("$$$$$$$$$$$$$$$$$$$$$$");
    System.out.println(artist);

    given()
      .pathParam("id", artist.getId()).
    when()
      .get("/api/artists/{id}").
    then()
      .statusCode(200)
      .body("$", hasKey("id"))
      .body("$", hasKey("firstName"))
      .body("$", hasKey("lastName"))
      .body("$", hasKey("bio"))
      .body("$", hasKey("createdDate"));
  }
}
