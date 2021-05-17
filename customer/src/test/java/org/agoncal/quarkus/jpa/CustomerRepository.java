package org.agoncal.quarkus.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CustomerRepository {

  @Inject
  EntityManager em;

  public void persist(Customer customer)  {
    em.persist(customer);
  }

  public Customer findById(Long id) {
    return em.find(Customer.class, id);
  }
}
