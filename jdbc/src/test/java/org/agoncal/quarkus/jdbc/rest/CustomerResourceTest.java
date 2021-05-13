package org.agoncal.quarkus.jdbc.rest;
//@formatter:off

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jdbc.model.Customer;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static org.hamcrest.Matchers.hasKey;

@QuarkusTest
public class CustomerResourceTest {

  private Faker faker = new Faker();

  @Test
  public void shouldCreateAndFindACustomer() {
    Customer customer = new Customer();
    customer.setFirstName(faker.name().firstName());
    customer.setLastName(faker.name().lastName());
    customer.setEmail(faker.internet().emailAddress());

    customer =
      given()
        .body(customer, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, MediaType.APPLICATION_JSON).
      when()
        .post("/api/customers").
      then()
        .statusCode(201)
        .extract().as(Customer.class);

    given()
      .pathParam("id", customer.getId()).
    when()
      .get("/api/customers/{id}").
    then()
      .statusCode(200)
      .body("$", hasKey("id"))
      .body("$", hasKey("firstName"))
      .body("$", hasKey("lastName"))
      .body("$", hasKey("email"))
      .body("$", hasKey("createdDate"));
  }
}
