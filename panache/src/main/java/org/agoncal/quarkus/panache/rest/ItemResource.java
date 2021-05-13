package org.agoncal.quarkus.panache.rest;

import org.agoncal.quarkus.panache.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ItemResource {

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
