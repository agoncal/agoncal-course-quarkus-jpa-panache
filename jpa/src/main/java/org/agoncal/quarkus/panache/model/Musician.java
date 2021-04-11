package org.agoncal.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
@Table(name = "t_musicians")
public class Musician extends Artist {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(name = "preferred_instrument")
  private String preferredInstrument;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Musician() {
  }

  public Musician(String firstName, String lastName, String preferredInstrument) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.preferredInstrument = preferredInstrument;
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public String getPreferredInstrument() {
    return preferredInstrument;
  }

  public void setPreferredInstrument(String preferredInstrument) {
    this.preferredInstrument = preferredInstrument;
  }

}
