package org.agoncal.quarkus.jpa.rest;

import org.agoncal.quarkus.jpa.model.Artist;
import org.agoncal.quarkus.jpa.repository.PanacheArtistRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ArtistResource {

  @Inject
  PanacheArtistRepository repository;

  @GET
  @Path("{id}")
  public Artist getArtist(@PathParam("id") Long id) {
    System.out.println("££££££££££££££££££££");
    System.out.println(id);
    return repository.findById(id);
  }

  @POST
  @Transactional
  public Response createArtist(Artist artist) {
    repository.persist(artist);
    System.out.println("@@@@@@@@@@@@@@@@@@");
    System.out.println(artist);
    return Response.status(Response.Status.CREATED).entity(artist).build();
  }
}
