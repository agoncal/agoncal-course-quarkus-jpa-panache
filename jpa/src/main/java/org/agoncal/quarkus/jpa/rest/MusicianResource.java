package org.agoncal.quarkus.jpa.rest;

import org.agoncal.quarkus.jpa.model.Musician;
import org.jboss.logging.Logger;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/jpa/musicians")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MusicianResource {

  @Inject
  EntityManager em;
  @Inject
  Logger logger;


  @POST
  @Transactional
  public Response persistMusician(Musician musician, @Context UriInfo uriInfo) {
    em.persist(musician);
    URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(musician.getId())).build();
    logger.info("New musician created with URI " + uri.toString());
    return Response.created(uri).build();
  }

  @GET
  @Path("/{id}")
  public Musician findMusicianById(@PathParam("id") Long id) {
    return em.find(Musician.class, id);
  }

  @GET
  public List<Musician> findMusicians() {
    return em.createQuery("select m from Musician m", Musician.class).getResultList();
  }

  @GET
  @Path("/count")
  public Long countMusicians() {
    return em.createQuery("select count(a) from Musician a", Long.class).getSingleResult();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void deleteMusician(@PathParam("id") Long id) {
    em.remove(em.getReference(Musician.class, id));
  }
}
