package org.agoncal.quarkus.panache.rest;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.CD;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ItemResource {

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance book(Book book);

    public static native TemplateInstance books(List<Book> books);

    public static native TemplateInstance cd(CD cd);

    public static native TemplateInstance cds(List<CD> cds);
  }

  @GET
  @Path("/books/show/{id}")
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance showBookById(@PathParam("id") Long id) {
    return Templates.book(Book.findById(id));
  }

  @GET
  @Path("/books/show")
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance showAllBooks(@QueryParam("query") String query, @QueryParam("sort") @DefaultValue("id") String sort, @QueryParam("page") @DefaultValue("0") Integer pageIndex, @QueryParam("size") @DefaultValue("1000") Integer pageSize) {
    return Templates.books(Book.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
      .data("query", query)
      .data("sort", sort)
      .data("pageIndex", pageIndex)
      .data("pageSize", pageSize);
  }

  @GET
  @Path("/cds/show/{id}")
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance showCDById(@PathParam("id") Long id) {
    return Templates.cd(CD.findById(id));
  }

  @GET
  @Path("/cds/show")
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance showAllCDs(@QueryParam("query") String query, @QueryParam("sort") @DefaultValue("id") String sort, @QueryParam("page") @DefaultValue("0") Integer pageIndex, @QueryParam("size") @DefaultValue("1000") Integer pageSize) {
    return Templates.cds(CD.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
      .data("query", query)
      .data("sort", sort)
      .data("pageIndex", pageIndex)
      .data("pageSize", pageSize);
  }

  @GET
  @Path("/books/{id}")
  public Book getBook(@PathParam("id") Long id) {
    return Book.findById(id);
  }

  @POST
  @Path("/books")
  @Transactional
  public Response createBook(Book book) {
    Book.persist(book);
    return Response.status(Response.Status.CREATED).entity(book).build();
  }
}
