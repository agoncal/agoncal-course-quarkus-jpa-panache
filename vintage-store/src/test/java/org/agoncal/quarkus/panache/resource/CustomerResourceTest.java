package org.agoncal.quarkus.panache.resource;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.jpa.Customer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.hasKey;import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerResourceTest {

  private static int nbCustomers;
  private static String customerId;

  @Test
  void shouldNotGetUnknownCustomer() {
    Long randomId = new Random().nextLong();
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", randomId).
    when()
      .get("/api/customers/{id}").
    then()
      .statusCode(NOT_FOUND.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldGetInitialCustomers() {
    nbCustomers =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/customers").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();
  }

  @Test
  @Order(2)
  void shouldCreateANewCustomer() {
    Customer customer = new Customer();
    customer.setFirstName("first name");
    customer.setLastName("last name");
    customer.setEmail("email");

    String location =
      given()
        .body(customer, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .post("/api/customers").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the id
    assertTrue(location.contains("/api/customers"));
    String[] segments = location.split("/");
    customerId = segments[segments.length - 1];
    assertNotNull(customerId);
  }

  @Test
  @Order(3)
  void shouldGetAnExtraCustomer() {
    int customers =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/customers").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbCustomers + 1, customers);
  }

  @Test
  @Order(4)
  void shouldCheckTheExtraCustomer() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", customerId).
    when()
      .get("/api/customers/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("firstName", is("first name"))
      .body("lastName", is("last name"))
      .body("email", is("email"))
      .body("$", hasKey("id"))
      .body("$", hasKey("createdDate"));  }

  @Test
  @Order(5)
  void shouldDeleteTheExtraCustomer() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", customerId).
    when()
      .delete("/api/customers/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldGetLessAnCustomer() {
    int customers =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/customers").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbCustomers, customers);
  }
}
