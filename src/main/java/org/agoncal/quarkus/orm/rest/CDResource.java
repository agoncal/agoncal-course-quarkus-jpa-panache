package org.agoncal.quarkus.orm.rest;

import org.agoncal.quarkus.orm.panache.CD;
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

@Path("/api/cds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class CDResource {

  @Inject
  Logger logger;

  @POST
  @Transactional
  public Response persistCD(CD cd, @Context UriInfo uriInfo) {
    CD.persist(cd);
    URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(cd.id)).build();
    logger.info("New cd created with URI " + uri.toString());
    return Response.created(uri).build();
  }

  @GET
  @Path("/{id}")
  public Response findCDById(@PathParam("id") Long id) {
    return CD
      .findByIdOptional(id)
      .map(cd -> Response.ok(cd).build())
      .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @GET
  public List<CD> findCDs() {
    return CD.listAll();
  }

  @GET
  @Path("/count")
  public Long countCDs() {
    return CD.count();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void deleteCD(@PathParam("id") Long id) {
    CD.deleteById(id);
  }
}
