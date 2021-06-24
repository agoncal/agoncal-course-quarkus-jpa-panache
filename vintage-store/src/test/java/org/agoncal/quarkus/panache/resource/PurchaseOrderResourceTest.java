package org.agoncal.quarkus.panache.resource;
//@formatter:off

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.mapper.ObjectMapperType;
import org.agoncal.quarkus.panache.model.PurchaseOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
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
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PurchaseOrderResourceTest {

  private static int nbPurchaseOrders;
  private static String purchaseOrderId;

  @Test
  void shouldNotGetUnknownPurchaseOrder() {
    Long randomId = new Random().nextLong();
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", randomId).
    when()
      .get("/api/purchase-orders/{id}").
    then()
      .statusCode(NOT_FOUND.getStatusCode());
  }

  @Test
  @Order(1)
  void shouldGetInitialPurchaseOrders() {
    nbPurchaseOrders =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/purchase-orders").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();
  }

  @Test
  @Order(2)
  void shouldCreateANewPurchaseOrder() {
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.date = LocalDate.now();

    String location =
      given()
        .body(purchaseOrder, ObjectMapperType.JSONB)
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .post("/api/purchase-orders").
      then()
        .statusCode(CREATED.getStatusCode())
        .extract().header("Location");

    // Extracts the Location and stores the id
    assertTrue(location.contains("/api/purchase-orders"));
    String[] segments = location.split("/");
    purchaseOrderId = segments[segments.length - 1];
    assertNotNull(purchaseOrderId);
  }

  @Test
  @Order(3)
  void shouldGetAnExtraPurchaseOrder() {
    int purchaseOrders =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/purchase-orders").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbPurchaseOrders + 1, purchaseOrders);
  }

  @Test
  @Order(4)
  void shouldCheckTheExtraPurchaseOrder() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", purchaseOrderId).
    when()
      .get("/api/purchase-orders/{id}").
    then()
      .statusCode(OK.getStatusCode())
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .body("$", hasKey("id"))
      .body("$", hasKey("date"))
      .body("$", hasKey("createdDate"));
  }

  @Test
  @Order(5)
  void shouldDeleteTheExtraPurchaseOrder() {
    given()
      .header(ACCEPT, APPLICATION_JSON)
      .pathParam("id", purchaseOrderId).
    when()
      .delete("/api/purchase-orders/{id}").
    then()
      .statusCode(NO_CONTENT.getStatusCode());
  }

  @Test
  @Order(6)
  void shouldGetLessAnPurchaseOrder() {
    int purchaseOrders =
      given()
        .header(ACCEPT, APPLICATION_JSON).
      when()
        .get("/api/purchase-orders").
      then()
        .statusCode(OK.getStatusCode())
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .extract().body().as(List.class).size();

    assertEquals(nbPurchaseOrders, purchaseOrders);
  }
}
