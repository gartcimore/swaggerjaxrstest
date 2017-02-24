package org.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author Laurent Rustuel
 */
@Path("/hello/")
public class OneAppHelloResource {

  @GET
  @Produces({"application/json"})
  public Response get() {
        return Response.ok("hello").build();
  }
}
