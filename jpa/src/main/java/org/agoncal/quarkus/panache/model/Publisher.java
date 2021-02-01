package org.agoncal.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Publisher.FIND_ALL, query = "select p from Publisher p"),
})
public class Publisher {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id = null;

  @Column(length = 30)
  @NotNull
  @Size(max = 30)
  private String name;

  // ======================================
  // =              Constant              =
  // ======================================

  public static final String FIND_ALL = "Publisher.findAll";

  // ======================================
  // =            Constructors            =
  // ======================================

  public Publisher() {
  }

  public Publisher(String name) {
    this.name = name;
  }

// ======================================
  // =          Getters & Setters         =
  // ======================================

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // ======================================
  // =    hashcode, equals & toString     =
  // ======================================

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Publisher publisher = (Publisher) o;

    if (id != null ? !id.equals(publisher.id) : publisher.id != null) return false;
    if (!name.equals(publisher.name)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + name.hashCode();
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(name);
    return sb.toString();
  }
}
