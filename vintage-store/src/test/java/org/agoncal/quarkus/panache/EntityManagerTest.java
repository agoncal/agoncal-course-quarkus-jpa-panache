package org.agoncal.quarkus.panache;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.agoncal.quarkus.jpa.Customer;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.wildfly.common.Assert.assertTrue;

@QuarkusTest
public class EntityManagerTest {

  @Inject
  EntityManager em;

  @Test
  @TestTransaction
  public void shouldCreateAndFindACustomer() {
    Customer customer = new Customer("first name", "last name", "email");

    assertFalse(em.contains(customer));
    em.persist(customer);
    assertTrue(em.contains(customer));
    assertNotNull(customer.getId());

    customer = em.find(Customer.class, customer.getId());
    assertEquals("last name", customer.getLastName());

    em.remove(customer);
    assertFalse(em.contains(customer));
  }
}
