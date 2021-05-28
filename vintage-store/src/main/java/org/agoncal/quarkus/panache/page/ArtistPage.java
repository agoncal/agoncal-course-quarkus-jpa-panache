package org.agoncal.quarkus.panache.page;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.panache.repository.ArtistRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/page/artists")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class ArtistPage {

  @Inject
  ArtistRepository repository;

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance artist(Artist artist);

    public static native TemplateInstance artists(List<Artist> artists);
  }

  @GET
  @Path("{id}")
  public TemplateInstance showArtistById(@PathParam("id") Long id) {
    return Templates.artist(repository.findById(id));
  }

  @GET
  public TemplateInstance showAllArtists(@QueryParam("query") String query, @QueryParam("sort") @DefaultValue("id") String sort, @QueryParam("page") @DefaultValue("0") Integer pageIndex, @QueryParam("size") @DefaultValue("1000") Integer pageSize) {
    return Templates.artists(repository.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
      .data("query", query)
      .data("sort", sort)
      .data("pageIndex", pageIndex)
      .data("pageSize", pageSize);
  }
}
