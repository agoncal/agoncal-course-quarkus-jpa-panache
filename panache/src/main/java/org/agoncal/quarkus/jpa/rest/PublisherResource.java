package org.agoncal.quarkus.jpa.rest;

import org.agoncal.quarkus.jpa.model.Publisher;
import org.agoncal.quarkus.jpa.service.PublisherService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublisherResource {

  @Inject
  PublisherService publisherService;

  @POST
  public Publisher persistPublisher(Publisher publisher) {
    publisherService.persistPublisher(publisher);
    return publisher;
  }

  @Path("/{id}")
  @GET
  public Response findPublisherById(@PathParam("id") Long id) {
    return publisherService.findPublisherById(id)
      .map(publisher -> Response.ok(publisher).build())
      .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @Path("/count")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Long countPublishers() {
    return publisherService.countPublishers();
  }

  @Path("/{id}")
  @DELETE
  public void deletePublisher(@PathParam("id") Long id) {
    publisherService.deletePublisher(id);
  }
}
