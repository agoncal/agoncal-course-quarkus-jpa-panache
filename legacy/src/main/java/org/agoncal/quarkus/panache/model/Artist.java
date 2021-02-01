package org.agoncal.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@MappedSuperclass
public abstract class Artist {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  protected Long id = null;

  @Column(name = "first_name", length = 50)
  @NotNull
  @Size(min = 2, max = 50)
  protected String firstName;

  @Column(name = "last_name", length = 50)
  @NotNull
  @Size(min = 2, max = 50)
  protected String lastName;
}
