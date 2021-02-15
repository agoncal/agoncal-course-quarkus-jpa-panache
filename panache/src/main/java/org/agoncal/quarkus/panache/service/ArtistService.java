package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.Author;
import org.agoncal.quarkus.panache.model.Musician;
import org.agoncal.quarkus.panache.repository.ArtistRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ArtistService {

  @Inject
  ArtistRepository repository;

  public Author createAuthor(Author author) {
    repository.persist(author);
    return author;
  }

  public Musician createMusician(Musician musician) {
    repository.persist(musician);
    return musician;
  }
}
