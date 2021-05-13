package org.agoncal.quarkus.panache.repository;

import org.agoncal.quarkus.jdbc.model.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.SqlResultSetMapping;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

@ApplicationScoped
@SqlResultSetMapping(
  name = "CustomerMapping",
  entities = {
    @EntityResult(
      entityClass = org.agoncal.quarkus.jdbc.model.Customer.class
//      fields = {
//        @FieldResult(name = "id", column = "employeeNumber"),
//        @FieldResult(name = "name", column = "name")}
    )})
public class CustomerRepository {

  @Inject
  EntityManager em;

  /**
   * https://github.com/quarkusio/quarkus/issues/2628
   *
   * @param customer
   * @throws SQLException
   */
  @Transactional
  public void persist(Customer customer) throws SQLException {
    String sql = "INSERT INTO T_CUSTOMER (id, first_name, last_name, email, created_date) VALUES (?, ?, ?, ?, ?)";
    customer.setId(Math.abs(new Random().nextInt()));

    em.joinTransaction();
    em.createNativeQuery(sql)
      .setParameter(1, customer.getId())
      .setParameter(2, customer.getFirstName())
      .setParameter(3, customer.getLastName())
      .setParameter(4, customer.getEmail())
      .setParameter(5, Timestamp.from(customer.getCreatedDate()))
      .executeUpdate();
  }

  public Customer findById(Integer id) throws SQLException {
    String sql = "SELECT id, first_name, last_name, email, created_date FROM T_CUSTOMER WHERE id = ?";

    Customer customer = (Customer) em.createNativeQuery(sql, "CustomerMapping").getSingleResult();

    return customer;
  }
}
