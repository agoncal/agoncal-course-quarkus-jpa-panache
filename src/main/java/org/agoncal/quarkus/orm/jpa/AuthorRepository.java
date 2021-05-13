package org.agoncal.quarkus.orm.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.agoncal.quarkus.orm.jpa.Author;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthorRepository implements PanacheRepository<Author> {
}
