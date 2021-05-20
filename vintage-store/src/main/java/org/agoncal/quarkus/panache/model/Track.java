package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "t_tracks")
public class Track extends PanacheEntity {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Column(nullable = false)
  @NotNull
  public String title;

  @Column(nullable = false)
  @NotNull
  public Duration duration;

  @JoinColumn(name = "cd_fk")
  @ManyToOne(fetch = FetchType.LAZY)
  public CD cd;

  @Column(name = "created_date", nullable = false)
  @NotNull
  public Instant createdDate = Instant.now();

  // ======================================
  // =            Constructors            =
  // ======================================

  public Track() {
  }

  public Track(String title, Duration duration) {
    this.title = title;
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "Track{" +
      "title='" + title + '\'' +
      ", duration=" + duration +
      ", createdDate=" + createdDate +
      ", id=" + id +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Track track = (Track) o;
    return id.equals(track.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
