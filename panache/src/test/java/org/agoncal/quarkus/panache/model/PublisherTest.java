package org.agoncal.quarkus.panache.model;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestTransaction
public class PublisherTest {

  @Test
  void shouldCreateAPublisher() {

    Publisher publisher = new Publisher("AGoncal Fascicles");
    assertNull(publisher.id);

    Publisher.persist(publisher);

    assertNotNull(publisher.id);
    assertEquals("AGoncal Fascicles", publisher.name);
  }
}
