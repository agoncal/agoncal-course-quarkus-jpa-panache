package org.agoncal.quarkus.orm.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.agoncal.quarkus.orm.jpa.Musician;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MusicianRepository implements PanacheRepository<Musician> {
}
