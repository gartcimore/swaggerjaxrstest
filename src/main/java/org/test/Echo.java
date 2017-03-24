package org.test;

import org.restlet.resource.Get;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path(value = "/echo")
public class Echo {


  @PUT
  @Consumes({"application/json"})
  @Produces({"application/json"})
  public String process(String request, @Context UriInfo info) throws Exception {
        System.out.println(info);
    if (request != null && request.contains("error")) {
      throw new Exception("error occured ");
    }
    return request;

  }

  @POST
  @Consumes({"application/json"})
  @Produces({"application/json"})
  public String process(String request) throws Exception {
    if (request != null && request.contains("error")) {
      throw new Exception("error occured ");
    }
    return request;

  }

  @Get
  @Consumes({"text/plain", "application/json"})
  public String get() {
    return "echo?";

  }
}
