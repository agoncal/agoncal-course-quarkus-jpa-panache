package org.agoncal.quarkus.panache.rest;

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

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

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
