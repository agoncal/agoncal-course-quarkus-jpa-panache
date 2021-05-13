package org.agoncal.quarkus.panache.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

//  @Inject
//  CustomerRepository repository;
//
//  @GET
//  @Path("{id}")
//  public Customer getCustomer(@PathParam("id") Integer id) throws SQLException {
//    return repository.findById(id);
//  }
//
//  @POST
//  public Response createCustomer(Customer customer) throws SQLException {
//    repository.persist(customer);
//    return Response.status(Response.Status.CREATED).entity(customer).build();
//  }
}
