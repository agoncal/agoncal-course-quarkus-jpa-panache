package org.agoncal.quarkus.jpa.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.agoncal.quarkus.jpa.model.Musician;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MusicianRepository implements PanacheRepository<Musician> {
}