package org.agoncal.quarkus.jdbc.model;

import java.time.Instant;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class Customer {

  // ======================================
  // =             Attributes             =
  // ======================================

  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private Instant createdDate = Instant.now();

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public String toString() {
    return "Customer{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", createdDate=" + createdDate +
      '}';
  }
}
