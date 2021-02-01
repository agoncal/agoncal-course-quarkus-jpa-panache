package org.agoncal.quarkus.panache.rest;

import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.CD;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

  @Inject
  EntityManager em;

  @Path("/books")
  @POST
  @Transactional
  public Book persistBook(Book book) {
    em.persist(book);
    return book;
  }

  @Path("/books/{id}")
  @GET
  public Book findBookById(@PathParam("id") Long id) {
    return em.find(Book.class, id);
  }

  @Path("/books/count")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Long countBooks() {
    return em.createQuery("select count(b) from Book b", Long.class).getSingleResult();
  }

  @Path("/books/{id}")
  @DELETE
  @Transactional
  public void deleteBook(@PathParam("id") Long id) {
    em.remove(em.getReference(Book.class, id));
  }

  @Path("/cds")
  @POST
  @Transactional
  public CD persistCD(CD cd) {
    em.persist(cd);
    return cd;
  }

  @Path("/cds/{id}")
  @GET
  public CD findCDById(@PathParam("id") Long id) {
    return em.find(CD.class, id);
  }

  @Path("/cds/count")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Long countCDs() {
    return em.createQuery("select count(c) from CD c", Long.class).getSingleResult();
  }

  @Path("/cds/{id}")
  @DELETE
  @Transactional
  public void deleteCD(@PathParam("id") Long id) {
    em.remove(em.getReference(CD.class, id));
  }
}
