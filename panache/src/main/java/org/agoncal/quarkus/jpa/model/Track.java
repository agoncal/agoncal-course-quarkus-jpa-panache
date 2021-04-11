package org.agoncal.quarkus.jpa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Duration;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
@Table(name = "t_tracks")
public class Track extends PanacheEntity {

  // ======================================
  // =             Attributes             =
  // ======================================

  public String title;
  public Duration duration;

  // ======================================
  // =            Constructors            =
  // ======================================

  public Track() {
  }

  public Track(String title, Duration duration) {
    this.title = title;
    this.duration = duration;
  }
}
