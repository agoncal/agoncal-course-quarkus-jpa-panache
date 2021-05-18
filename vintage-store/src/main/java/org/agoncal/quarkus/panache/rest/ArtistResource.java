package org.agoncal.quarkus.panache.rest;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.panache.repository.ArtistRepository;

import javax.enterprise.context.ApplicationScoped;
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

@Path("/api/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class ArtistResource {

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance artist(Artist artist);

    public static native TemplateInstance artists(List<Artist> artists);
  }

  @Inject
  ArtistRepository repository;

  @GET
  @Path("show/{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public TemplateInstance showArtistById(@PathParam("id") Long id) {
    return Templates.artist(repository.findById(id));
  }

  @GET
  @Path("show")
  @Produces(MediaType.TEXT_PLAIN)
  public TemplateInstance showAllArtists() {
    return Templates.artists(repository.listAll());
  }

  @GET
  @Path("{id}")
  public Artist findArtistById(@PathParam("id") Long id) {
    return repository.findById(id);
  }

  @GET
  public List<Artist> listAllArtists() {
    return repository.listAll();
  }

  @GET
  @Path("/count")
  public Long countArtist() {
    return repository.count();
  }

  @POST
  public Response persistArtist(Artist artist, @Context UriInfo uriInfo) {
    repository.persist(artist);
    URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(artist.getId())).build();
    return Response.created(uri).build();
  }

  @DELETE
  @Path("{id}")
  public void deleteArtistById(@PathParam("id") Long id) {
    repository.deleteById(id);
  }
}
