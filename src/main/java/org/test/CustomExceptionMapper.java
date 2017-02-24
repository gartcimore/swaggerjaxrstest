package org.test;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception> {

  @Override
  public Response toResponse(Exception exception) {

    return Response.status(500).entity(String.format("error occured : %s", exception.getMessage())).type("application/json").build();
  }
}
