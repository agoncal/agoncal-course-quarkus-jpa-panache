package org.agoncal.quarkus.jpa.rest;

import org.agoncal.quarkus.jpa.model.Book;
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

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(SUPPORTS)
public class BookResource {

  @Inject
  Logger logger;

  @POST
  @Transactional
  public Response persistBook(Book book, @Context UriInfo uriInfo) {
    Book.persist(book);
    URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(book.id)).build();
    logger.info("New book created with URI " + uri.toString());
    return Response.created(uri).build();
  }

  @GET
  @Path("/{id}")
  public Response findBookById(@PathParam("id") Long id) {
    return Book
      .findByIdOptional(id)
      .map(book -> Response.ok(book).build())
      .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @GET
  public List<Book> findBooks() {
    return Book.listAll();
  }

  @GET
  @Path("/count")
  public Long countBooks() {
    return Book.count();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void deleteBook(@PathParam("id") Long id) {
    Book.deleteById(id);
  }
}
