package org.agoncal.quarkus.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import org.agoncal.quarkus.jpa.Customer;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

  public List<Customer> listAllDans() {
    return list("firstName = 'Dan'", Sort.by("lastName"));
  }
}
