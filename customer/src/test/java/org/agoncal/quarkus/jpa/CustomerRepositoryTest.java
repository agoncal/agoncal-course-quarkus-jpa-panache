package org.agoncal.quarkus.jpa;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerRepositoryTest {

  @Inject
  CustomerRepository repository;

  @Test
  @Transactional
  public void shouldCreateAndFindACustomer() {
    Customer customer = new Customer();
    customer.setFirstName("first name");
    customer.setLastName("last name");
    customer.setEmail("email");

    repository.persist(customer);

    customer = repository.findById(customer.getId());

    assertNotNull(customer.getId());
  }
}
