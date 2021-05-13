package org.agoncal.quarkus.orm.rest;

import org.agoncal.quarkus.orm.jpa.Author;
import org.agoncal.quarkus.orm.repository.AuthorRepository;
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

@Path("/api/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class AuthorResource {

  @Inject
  AuthorRepository repository;
  @Inject
  Logger logger;

  @POST
  @Transactional
  public Response persistAuthor(Author author, @Context UriInfo uriInfo) {
    repository.persist(author);
    URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(author.getId())).build();
    logger.info("New author created with URI " + uri.toString());
    return Response.created(uri).build();
  }

  @GET
  @Path("/{id}")
  public Response findAuthorById(@PathParam("id") Long id) {
    return repository
      .findByIdOptional(id)
      .map(publisher -> Response.ok(publisher).build())
      .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @GET
  public List<Author> findAuthors() {
    return repository.listAll();
  }

  @GET
  @Path("/count")
  public Long countAuthors() {
    return repository.count();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void deleteAuthor(@PathParam("id") Long id) {
    repository.deleteById(id);
  }
}
