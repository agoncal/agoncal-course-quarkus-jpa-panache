package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PublisherRepositoryTest {

  @Test
  @TestTransaction
  public void shouldCreateAndFindAPublisher() {
    long nbPublishers = Publisher.count();

    // Creates a Publisher
    Publisher publisher = new Publisher();
    publisher.name = "name";

    // Persists the Publisher
    Publisher.persist(publisher);
    assertNotNull(publisher.id);

    assertEquals(nbPublishers + 1, Publisher.count());

    // Gets the Publisher
    publisher = Publisher.findById(publisher.id);
    assertEquals("name", publisher.name);

    // Deletes the Artist
    Publisher.deleteById(publisher.id);
    assertEquals(nbPublishers, Publisher.count());
  }
}
