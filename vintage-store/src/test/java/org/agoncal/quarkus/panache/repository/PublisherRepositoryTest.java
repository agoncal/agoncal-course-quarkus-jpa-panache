package org.agoncal.quarkus.panache.repository;

import com.github.javafaker.Faker;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PublisherRepositoryTest {

  private static Faker faker = new Faker();

  @Test
  @TestTransaction
  public void shouldCreateAndFindACustomer() {
    // Creates a Publisher
    Publisher publisher = new Publisher();
    publisher.name = faker.book().publisher();

    // Persists the Publisher
    Publisher.persist(publisher);

    // Gets the Publisher
    publisher = Publisher.findById(publisher.id);

    // Checks the Publisher
    assertNotNull(publisher.id);
    assertNotNull(publisher.name);
    assertNotNull(publisher.createdDate);
  }

  @Test
  @Disabled
  @Transactional
  public void shouldFillUpTheDatabase() {
    for (int i = 0; i < 1000; i++) {
      Publisher publisher = new Publisher();
      publisher.name = faker.book().publisher();
      Publisher.persist(publisher);
    }
  }
}
