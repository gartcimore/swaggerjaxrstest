package org.test;

import org.restlet.resource.Get;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path(value = "/echo")
public class Echo {


  @PUT
  @Consumes({"text/plain", "application/json"})
  @Produces({"application/json; charset=UTF-8"})
  public String process(String request) {
    return request;

  }

  @Get
  @Consumes({"text/plain", "application/json"})
  public String get() {
    return "echo?";

  }
}
