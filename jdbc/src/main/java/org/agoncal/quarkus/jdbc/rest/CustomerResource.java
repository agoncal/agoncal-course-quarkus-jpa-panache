package org.agoncal.quarkus.jdbc.rest;

import org.agoncal.quarkus.jdbc.model.Customer;
import org.agoncal.quarkus.jdbc.repository.CustomerRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

  @Inject
  CustomerRepository repository;

  @GET
  @Path("{id}")
  public Customer getCustomer(@PathParam("id") Integer id) throws SQLException {
    return repository.findById(id);
  }

  @POST
  public Response createCustomer(Customer customer) throws SQLException {
    repository.persist(customer);
    return Response.status(Response.Status.CREATED).entity(customer).build();
  }
}
