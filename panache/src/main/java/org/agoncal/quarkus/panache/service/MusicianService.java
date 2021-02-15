package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.model.Musician;
import org.agoncal.quarkus.panache.repository.MusicianRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@ApplicationScoped
@Transactional(SUPPORTS)
public class MusicianService {

  @Inject
  MusicianRepository repository;

  @Transactional(REQUIRED)
  public Musician persistMusician(Musician musician) {
    repository.persist(musician);
    return musician;
  }

  public Musician findMusicianById(Long id) {
    return repository.findById(id);
  }

  @Transactional(REQUIRED)
  public void deleteMusician(Long id) {
    repository.deleteById(id);
  }

  public Long countMusicians() {
    return repository.count();
  }
}
