package org.agoncal.quarkus.panache.resource;

import org.agoncal.quarkus.jpa.Customer;
import org.agoncal.quarkus.panache.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional(SUPPORTS)
public class CustomerResource {

  @Inject
  CustomerRepository repository;

  @GET
  @Path("{id}")
  public Customer findCustomerById(@PathParam("id") Long id) {
    return repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
  }

  @GET
  public List<Customer> listAllCustomers() {
    return repository.listAll();
  }

  @POST
  @Transactional
  public Response persistCustomer(Customer customer, @Context UriInfo uriInfo) {
    repository.persist(customer);
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(customer.getId()));
    return Response.created(builder.build()).build();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void deleteCustomer(@PathParam("id") Long id) {
    repository.deleteById(id);
  }
}
