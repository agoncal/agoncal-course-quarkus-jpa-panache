package org.agoncal.quarkus.panache.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
@Entity
@NamedQueries({
        @NamedQuery(name = CD.FIND_ALL, query = "select c from CD c"),
})
public class CD extends Item {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(name = "total_duration")
  private Float totalDuration;

  @Column(name = "music_company")
  private String musicCompany;

  private String genre;

  // ======================================
  // =              Constant              =
  // ======================================

  public static final String FIND_ALL = "CD.findAll";

  // ======================================
  // =            Constructors            =
  // ======================================

  public CD() {
  }

  public CD(String title, String description, Float unitCost, Float totalDuration, String genre) {
    this.title = title;
    this.description = description;
    this.unitCost = unitCost;
    this.totalDuration = totalDuration;
    this.genre = genre;
  }

  // ======================================
  // =          Getters & Setters         =
  // ======================================

  public Float getTotalDuration() {
    return totalDuration;
  }

  public void setTotalDuration(Float totalDuration) {
    this.totalDuration = totalDuration;
  }

  public String getMusicCompany() {
    return musicCompany;
  }

  public void setMusicCompany(String musicCompany) {
    this.musicCompany = musicCompany;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }
}
