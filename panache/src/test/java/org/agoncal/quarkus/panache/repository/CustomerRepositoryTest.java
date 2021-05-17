package org.agoncal.quarkus.panache.repository;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.Customer;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerRepositoryTest {

  @Inject
  CustomerRepository repository;

  private Faker faker = new Faker();

  @Test
  @Transactional
  public void shouldCreateAndFindACustomer() throws SQLException {
    Customer customer = new Customer();
    customer.setFirstName(faker.name().firstName());
    customer.setLastName(faker.name().lastName());
    customer.setEmail(faker.internet().emailAddress());

    repository.persist(customer);

    customer = repository.findById(customer.getId());

    assertNotNull(customer.getId());
    assertNotNull(customer.getFirstName());
    assertNotNull(customer.getLastName());
    assertNotNull(customer.getEmail());
    assertNotNull(customer.getCreatedDate());
  }
}
