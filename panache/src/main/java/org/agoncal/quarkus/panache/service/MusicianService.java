package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.Musician;
import org.agoncal.quarkus.panache.repository.MusicianRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MusicianService {

  @Inject
  MusicianRepository repository;

  public Musician persistMusician(Musician musician) {
    repository.persist(musician);
    return musician;
  }

  public Musician findMusicianById(Long id) {
    return repository.findById(id);
  }

  public void deleteMusician(Long id) {
    repository.deleteById(id);
  }

  public Long countMusicians() {
    return repository.count();
  }
}
