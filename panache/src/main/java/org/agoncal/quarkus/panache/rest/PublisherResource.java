package org.agoncal.quarkus.panache.rest;

import org.agoncal.quarkus.panache.model.Publisher;

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

@Path("/api/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublisherResource {

  @Inject
  EntityManager em;

  @POST
  @Transactional
  public Publisher persistPublisher(Publisher publisher) {
    em.persist(publisher);
    return publisher;
  }

  @Path("/{id}")
  @GET
  public Publisher findPublisherById(@PathParam("id") Long id) {
    return em.find(Publisher.class, id);
  }

  @Path("/count")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Long countPublishers() {
    return em.createQuery("select count(p) from Publisher p", Long.class).getSingleResult();
  }

  @Path("/{id}")
  @DELETE
  @Transactional
  public void deletePublisher(@PathParam("id") Long id) {
    em.remove(em.getReference(Publisher.class, id));
  }
}
