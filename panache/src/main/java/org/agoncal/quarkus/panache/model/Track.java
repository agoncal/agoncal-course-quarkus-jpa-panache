package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.Duration;

@Entity
public class Track extends PanacheEntity {

  public String title;
  public Duration duration;
}
