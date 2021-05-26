package org.agoncal.quarkus.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.agoncal.quarkus.jpa.Customer;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
}
