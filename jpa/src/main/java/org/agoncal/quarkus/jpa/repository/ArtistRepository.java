package org.agoncal.quarkus.jpa.repository;

import org.agoncal.quarkus.jpa.model.Artist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class ArtistRepository {

  @Inject
  EntityManager em;

  @Transactional
  public void persist(Artist artist) {
    em.persist(artist);
    System.out.println(artist);
  }

  public Artist findById(Long id) {
    return em.find(Artist.class, id);
  }
}
