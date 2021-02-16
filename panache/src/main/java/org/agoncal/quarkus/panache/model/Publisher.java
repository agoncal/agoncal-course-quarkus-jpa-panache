package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

  @Column(length = 30)
  public String name;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Publisher() {
  }

  public Publisher(String name) {
    this.name = name;
  }
}
