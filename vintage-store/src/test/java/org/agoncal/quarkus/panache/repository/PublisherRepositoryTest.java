package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PublisherRepositoryTest {

  @Test
  @TestTransaction
  public void shouldCreateAndFindACustomer() {
    // Creates a Publisher
    Publisher publisher = new Publisher();
    publisher.name = "name";

    // Persists the Publisher
    Publisher.persist(publisher);

    // Gets the Publisher
    publisher = Publisher.findById(publisher.id);

    // Checks the Publisher
    assertNotNull(publisher.id);
  }
}
