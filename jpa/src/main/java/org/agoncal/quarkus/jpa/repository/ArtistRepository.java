package org.agoncal.quarkus.jpa.repository;

import org.agoncal.quarkus.jpa.model.Artist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ArtistRepository {

  @Inject
  EntityManager em;

  public void persist(Artist artist) {
    em.persist(artist);
  }

  public Artist findById(Long id) {
    return em.find(Artist.class, id);
  }
}
