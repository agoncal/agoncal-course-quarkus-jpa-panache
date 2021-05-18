package org.agoncal.quarkus.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
@Table(name = "t_customers")
public class Customer {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "first_name", length = 50, nullable = false)
  @NotNull
  @Size(min = 2, max = 50)
  private String firstName;

  @Column(name = "last_name", length = 50, nullable = false)
  @NotNull
  @Size(min = 2, max = 50)
  private String lastName;

  @Column(name = "e_mail", nullable = false)
  @NotNull
  private String email;

  @Column(name = "created_date", nullable = false)
  @NotNull
  private Instant createdDate = Instant.now();

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public Long getId() {
    return id;
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
