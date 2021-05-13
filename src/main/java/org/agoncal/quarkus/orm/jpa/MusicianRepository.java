package org.agoncal.quarkus.orm.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MusicianRepository implements PanacheRepository<Musician> {
}
