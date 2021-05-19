package org.agoncal.quarkus.panache.rest;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.agoncal.quarkus.jpa.Customer;
import org.agoncal.quarkus.panache.repository.CustomerRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance customer(Customer customer);

    public static native TemplateInstance customers(List<Customer> customers);
  }

  @GET
  @Path("show/{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public TemplateInstance showCustomerById(@PathParam("id") Long id) {
    return Templates.customer(repository.findById(id));
  }

  @GET
  @Path("show")
  @Produces(MediaType.TEXT_PLAIN)
  public TemplateInstance showAllCustomers() {
    return Templates.customers(repository.listAll());
  }

  @Inject
  CustomerRepository repository;

  @GET
  @Path("{id}")
  public Customer getCustomer(@PathParam("id") Long id){
    return repository.findById(id);
  }

  @POST
  public Response createCustomer(Customer customer) {
    repository.persist(customer);
    return Response.status(Response.Status.CREATED).entity(customer).build();
  }
}
