package org.agoncal.quarkus.panache.service;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestTransaction
public class PublisherServiceTest {

  @Inject
  PublisherService service;

  @Test
  void shouldManageAPublisher() {

    Long initialCount = service.countPublishers();

    Publisher publisher = new Publisher("AGoncal Fascicles");
    assertNull(publisher.id);

    publisher = service.persistPublisher(publisher);

    assertNotNull(publisher.id);
    assertEquals(initialCount + 1, service.countPublishers());

    publisher = service.findPublisherById(publisher.id);
    assertEquals("AGoncal Fascicles", publisher.name);

    service.deletePublisher(publisher.id);

    assertEquals(initialCount, service.countPublishers());
  }
}
