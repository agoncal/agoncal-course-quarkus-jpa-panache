package org.agoncal.quarkus.orm.jdbc;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jdbc.model.Customer;
import org.agoncal.quarkus.jdbc.repository.CustomerRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerTest {

  @Inject
  CustomerRepository customerRepository;

  private Faker faker = new Faker();

  @Test
  void shouldCreateAndFindACustomer() throws SQLException {
    Customer customer = new Customer();
    Integer id = Math.abs(new Random().nextInt());
    customer.setId(id);
    customer.setFirstName(faker.name().firstName());
    customer.setLastName(faker.name().lastName());
    customer.setEmail(faker.internet().emailAddress());
    customer.setCreatedDate(Instant.now());

    customerRepository.persist(customer);

    customer = customerRepository.findById(id);

    assertNotNull(customer.getFirstName());
    assertNotNull(customer.getLastName());
    assertNotNull(customer.getEmail());
  }
}
