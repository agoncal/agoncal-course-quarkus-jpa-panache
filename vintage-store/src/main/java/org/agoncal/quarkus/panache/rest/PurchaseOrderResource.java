package org.agoncal.quarkus.panache.rest;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.agoncal.quarkus.panache.model.Publisher;
import org.agoncal.quarkus.panache.model.PurchaseOrder;
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

@Path("/api/purchase-orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class PurchaseOrderResource {

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance purchaseOrder(PurchaseOrder purchaseOrder);

    public static native TemplateInstance purchaseOrders(List<PurchaseOrder> purchaseOrders);
  }

  @GET
  @Path("show/{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public TemplateInstance showPurchaseOrderById(@PathParam("id") Long id) {
    return Templates.purchaseOrder(PurchaseOrder.findById(id));
  }

  @GET
  @Path("show")
  @Produces(MediaType.TEXT_PLAIN)
  public TemplateInstance showAllPurchaseOrder() {
    return Templates.purchaseOrders(PurchaseOrder.listAll());
  }


  @Inject
  Logger logger;

  @POST
  @Transactional
  public Response persistPublisher(Publisher publisher, @Context UriInfo uriInfo) {
    Publisher.persist(publisher);
    URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(publisher.id)).build();
    logger.info("New publisher created with URI " + uri.toString());
    return Response.created(uri).build();
  }

  @GET
  @Path("/{id}")
  public Response findPublisherById(@PathParam("id") Long id) {
    return Publisher
      .findByIdOptional(id)
      .map(publisher -> Response.ok(publisher).build())
      .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @GET
  public List<Publisher> findPublishers() {
    return Publisher.listAll();
  }

  @GET
  @Path("/count")
  public Long countPublishers() {
    return Publisher.count();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void deletePublisher(@PathParam("id") Long id) {
    Publisher.deleteById(id);
  }
}
