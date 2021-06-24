package org.agoncal.quarkus.panache.resource;


import org.agoncal.quarkus.panache.model.Publisher;

import javax.enterprise.context.ApplicationScoped;
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

@Path("/api/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional(SUPPORTS)
public class PublisherResource {

  @GET
  @Path("/{id: \\d+}")
  public Publisher findPublisherById(@PathParam("id") Long id) {
    return (Publisher) Publisher.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @GET
  @Path("/{name: \\D+}")
  public Publisher findPublisherByName(@PathParam("name") String name) {
    return Publisher.findByName(name).orElseThrow(NotFoundException::new);
  }

  @GET
  @Path("/like/{name}")
  public List<Publisher> findPublisherContainingName(@PathParam("name") String name) {
    return Publisher.findContainingName(name);
  }

  @GET
  public List<Publisher> listAllPublishers() {
    return Publisher.listAll();
  }

  @POST
  @Transactional
  public Response persistPublisher(Publisher publisher, @Context UriInfo uriInfo) {
    Publisher.persist(publisher);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(publisher.id));
    return Response.created(builder.build()).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void deletePublisher(@PathParam("id") Long id) {
    Publisher.deleteById(id);
  }
}
