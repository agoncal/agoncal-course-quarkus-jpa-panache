package org.agoncal.quarkus.jdbc;

import java.time.Instant;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
public class Artist {

  // ======================================
  // =             Attributes             =
  // ======================================

  private Long id;
  private String name;
  private String bio;
  private Instant createdDate = Instant.now();

  // ======================================
  // =            Constructors            =
  // ======================================

  public Artist() {
  }

  public Artist(String name, String bio) {
    this.name = name;
    this.bio = bio;
  }

  public Artist(String name) {
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

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public String toString() {
    return "Artist{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", bio='" + bio + '\'' +
      ", createdDate=" + createdDate +
      '}';
  }
}
