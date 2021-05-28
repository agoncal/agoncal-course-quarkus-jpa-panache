package org.agoncal.quarkus.panache.page;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.agoncal.quarkus.panache.model.Publisher;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/page/publishers")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class PublisherPage {

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance publisher(Publisher publisher);

    public static native TemplateInstance publishers(List<Publisher> publishers);
  }

  @GET
  @Path("{id}")
  public TemplateInstance showPublisherById(@PathParam("id") Long id) {
    return Templates.publisher(Publisher.findById(id));
  }

  @GET
  public TemplateInstance showAllPublishers(@QueryParam("query") String query, @QueryParam("sort") @DefaultValue("id") String sort, @QueryParam("page") @DefaultValue("0") Integer pageIndex, @QueryParam("size") @DefaultValue("1000") Integer pageSize) {
    return Templates.publishers(Publisher.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
      .data("query", query)
      .data("sort", sort)
      .data("page", pageIndex)
      .data("size", pageSize);
  }
}
