package org.agoncal.quarkus.panache.repository;

import com.github.javafaker.Faker;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jpa.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerRepositoryTest {

  @Inject
  CustomerRepository repository;

  private Faker faker = new Faker();

  @Test
  @TestTransaction
  public void shouldCreateAndFindACustomer() {
    // Creates a Customer
    Customer customer = new Customer();
    customer.setFirstName(faker.name().firstName());
    customer.setLastName(faker.name().lastName());
    customer.setEmail(faker.internet().emailAddress());

    // Persists the Customer
    repository.persist(customer);

    // Gets the Customer
    customer = repository.findById(customer.getId());

    // Checks the Customer
    assertNotNull(customer.getId());
    assertNotNull(customer.getFirstName());
    assertNotNull(customer.getLastName());
    assertNotNull(customer.getEmail());
    assertNotNull(customer.getCreatedDate());
  }

  @Test
  @Disabled
  @Transactional
  public void shouldFillUpTheDatabase() {
    for (int i = 0; i < 1000; i++) {
      Customer customer = new Customer();
      customer.setFirstName(faker.name().firstName());
      customer.setLastName(faker.name().lastName());
      customer.setEmail(faker.internet().emailAddress());
      repository.persist(customer);
    }
  }
}
