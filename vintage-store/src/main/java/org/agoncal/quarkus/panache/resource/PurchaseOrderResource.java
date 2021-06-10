package org.agoncal.quarkus.panache.resource;


import org.agoncal.quarkus.panache.model.PurchaseOrder;

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

@Path("/api/purchase-orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PurchaseOrderResource {

  @GET
  @Path("{id}")
  public PurchaseOrder findPurchaseOrderById(@PathParam("id") Long id) {
    return (PurchaseOrder) PurchaseOrder.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @GET
  public List<PurchaseOrder> listAllPurchaseOrders() {
    return PurchaseOrder.listAll();
  }

  @POST
  @Transactional
  public Response persistPurchaseOrder(PurchaseOrder purchaseOrder, @Context UriInfo uriInfo) {
    PurchaseOrder.persist(purchaseOrder);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(purchaseOrder.id));
    return Response.created(builder.build()).build();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public Response deletePurchaseOrder(@PathParam("id") Long id) {
    PurchaseOrder.deleteById(id);
    return Response.noContent().build();
  }
}
