package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends PanacheEntity {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(length = 100)
  public String title;

  @Column(length = 3000)
  public String description;

  @Column(name = "unit_cost")
  public Float unitCost;
}
