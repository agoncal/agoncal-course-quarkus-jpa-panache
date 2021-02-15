package org.agoncal.quarkus.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.agoncal.quarkus.panache.model.Musician;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MusicianRepository implements PanacheRepository<Musician> {
}
