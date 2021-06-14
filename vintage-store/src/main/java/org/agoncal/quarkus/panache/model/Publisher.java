package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
@Table(name = "t_publishers")
public class Publisher extends PanacheEntity {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(length = 50, nullable = false)
  public String name;

  @Column(name = "created_date", nullable = false)
  public Instant createdDate = Instant.now();

  // ======================================
  // =           Constructors             =
  // ======================================

  public Publisher(String name) {
    this.name = name;
  }

  public Publisher() {
  }

  // ======================================
  // =              Methods               =
  // ======================================

  public static List<Publisher> findContainingName(String name) {
    return Publisher.list("name like ?1", "%" + name + "%");
  }

  public static Optional<Publisher> findByName(String name) {
    return Publisher.find("name", name).firstResultOptional();
  }

  @Override
  public String toString() {
    return "Publisher{" +
      "name='" + name + '\'' +
      ", createdDate=" + createdDate +
      ", id=" + id +
      '}';
  }
}
