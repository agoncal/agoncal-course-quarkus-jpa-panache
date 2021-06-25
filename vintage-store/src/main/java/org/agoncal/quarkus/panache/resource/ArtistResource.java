package org.agoncal.quarkus.panache.resource;

import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.panache.repository.ArtistRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.transaction.Transactional.TxType.SUPPORTS;

@Path("/api/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional(SUPPORTS)
public class ArtistResource {

  @Inject
  ArtistRepository repository;

  /**
   * curl http://localhost:8080/api/artists/1
   */
  @GET
  @Path("{id}")
  public Artist findArtistById(@PathParam("id") Long id) {
    return repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  /**
   * curl http://localhost:8080/api/artists
   */
  @GET
  public List<Artist> listAllArtists() {
    return repository.listAllArtistsSorted();
  }

  /**
   * curl -X POST http://localhost:8080/api/artists -H 'Content-Type: application/json' -d '{ "bio": "artist bi", "name": "artist name" }' -v
   */
  @POST
  @Transactional
  public Response persistArtist(Artist artist, @Context UriInfo uriInfo) {
    repository.persist(artist);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(artist.getId()));
    return Response.created(builder.build()).build();
  }

  /**
   * curl -X DELETE http://localhost:8080/api/artists/1
   */
  @DELETE
  @Transactional
  @Path("/{id}")
  public void deleteArtist(@PathParam("id") Long id) {
    repository.deleteById(id);
  }

}
