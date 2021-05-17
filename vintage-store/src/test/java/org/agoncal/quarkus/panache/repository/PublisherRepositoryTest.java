package org.agoncal.quarkus.panache.repository;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PublisherRepositoryTest {

  private Faker faker = new Faker();

  @Test
  @Transactional
  public void shouldCreateAndFindACustomer() {
    Publisher publisher = new Publisher();
    publisher.name = faker.name().firstName();

    Publisher.persist(publisher);

    publisher = Publisher.findById(publisher.id);

    assertNotNull(publisher.id);
    assertNotNull(publisher.name);
    assertNotNull(publisher.createdDate);
  }
}
