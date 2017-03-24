package org.test;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception> {

  private static final String ERROR_CODE = "code";
  private static final String DESCRIPTION = "description";
  private static final String TITLE = "title";

  public static final String ERROR_FORMAT = "{\"" + ERROR_CODE + "\":%d,\"" + TITLE + "\":\"%s\",\"" + DESCRIPTION + "\":\"%s\"}";

  private static final String FATAL_ERROR = "Fatal error";
  @Override
  public Response toResponse(Exception exception) {
    System.out.println("error is "+exception.getMessage());
    exception.printStackTrace();
    return Response.status(500).entity(String.format(ERROR_FORMAT, 1337, FATAL_ERROR,
      String.format("Unexpected error: %s", exception.getMessage()))).type("application/json").build();
  }
}
