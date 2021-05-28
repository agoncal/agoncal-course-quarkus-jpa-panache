package org.agoncal.quarkus.panache.page;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.agoncal.quarkus.panache.model.PurchaseOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/page/purchase-orders")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class PurchaseOrderPage {

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance purchaseOrder(PurchaseOrder purchaseOrder);

    public static native TemplateInstance purchaseOrders(List<PurchaseOrder> purchaseOrders);
  }

  @GET
  @Path("{id}")
  public TemplateInstance showPurchaseOrderById(@PathParam("id") Long id) {
    return Templates.purchaseOrder(PurchaseOrder.findById(id));
  }

  @GET
  public TemplateInstance showAllPurchaseOrders(@QueryParam("query") String query, @QueryParam("sort") @DefaultValue("id") String sort, @QueryParam("page") @DefaultValue("0") Integer pageIndex, @QueryParam("size") @DefaultValue("1000") Integer pageSize) {
    return Templates.purchaseOrders(PurchaseOrder.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
      .data("query", query)
      .data("sort", sort)
      .data("pageIndex", pageIndex)
      .data("pageSize", pageSize);
  }
}
