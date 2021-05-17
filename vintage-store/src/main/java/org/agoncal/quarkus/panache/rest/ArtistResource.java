package org.agoncal.quarkus.panache.rest;

import org.agoncal.quarkus.panache.repository.ArtistRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ArtistResource {

  @Inject
  ArtistRepository repository;

//  @GET
//  @Path("{id}")
//  public Artist getArtist(@PathParam("id") Long id) {
//    System.out.println("££££££££££££££££££££");
//    System.out.println(id);
//    return repository.findById(id);
//  }
//
//  @POST
//  public Response createArtist(Artist artist) {
//    repository.persist(artist);
//    System.out.println("@@@@@@@@@@@@@@@@@@");
//    System.out.println(artist);
//    return Response.status(Response.Status.CREATED).entity(artist).build();
//  }
}
