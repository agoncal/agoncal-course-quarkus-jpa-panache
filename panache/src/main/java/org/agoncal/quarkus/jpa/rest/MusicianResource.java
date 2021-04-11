package org.agoncal.quarkus.jpa.rest;

import org.agoncal.quarkus.jpa.model.Musician;
import org.agoncal.quarkus.jpa.repository.MusicianRepository;
import org.jboss.logging.Logger;

import javax.inject.Inject;
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

import static javax.transaction.Transactional.TxType.SUPPORTS;

@Path("/api/musicians")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(SUPPORTS)
public class MusicianResource {

  @Inject
  MusicianRepository repository;
  @Inject
  Logger logger;

  @POST
  @Transactional
  public Response persistMusician(Musician musician, @Context UriInfo uriInfo) {
    repository.persist(musician);
    URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(musician.getId())).build();
    logger.info("New musician created with URI " + uri.toString());
    return Response.created(uri).build();
  }

  @GET
  @Path("/{id}")
  public Response findMusicianById(@PathParam("id") Long id) {
    return repository
      .findByIdOptional(id)
      .map(publisher -> Response.ok(publisher).build())
      .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @GET
  public List<Musician> findMusicians() {
    return repository.listAll();
  }

  @GET
  @Path("/count")
  public Long countMusicians() {
    return repository.count();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void deleteMusician(@PathParam("id") Long id) {
    repository.deleteById(id);
  }
}
