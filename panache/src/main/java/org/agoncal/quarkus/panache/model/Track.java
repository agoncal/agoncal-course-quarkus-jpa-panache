package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.Duration;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@Entity
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
