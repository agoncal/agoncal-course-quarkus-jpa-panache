package org.agoncal.quarkus.panache.service;

import org.agoncal.quarkus.panache.repository.ArtistRepository;

import javax.inject.Inject;

public class ArtistService {

  @Inject
  ArtistRepository repository;
}
