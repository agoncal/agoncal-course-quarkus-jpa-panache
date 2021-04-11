package org.agoncal.quarkus.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
public class CD extends Item {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(name = "music_company")
  public String musicCompany;

  public String genre;

  @JoinColumn(name = "cd_fk")
  @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
  public List<Track> tracks;

  // ======================================
  // =            Constructors            =
  // ======================================

  public CD() {
  }

  public CD(String title, String description, BigDecimal price, String genre) {
    this.title = title;
    this.description = description;
    this.price = price;
    this.genre = genre;
  }
}
