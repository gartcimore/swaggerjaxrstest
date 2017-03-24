package org.test;

import com.fasterxml.jackson.databind.JsonNode;

import org.junit.Test;
import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.resource.ClientResource;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.restlet.data.Protocol.HTTP;

public class EchoTest {


  @Test
  public void shouldNotHaveNullPointerWithPut() throws IOException {
    Client client = new Client(new Context(), HTTP);
    ClientResource clientResource = new ClientResource("http://localhost:8283/echo");
    clientResource.setNext(client);
    clientResource.accept(MediaType.APPLICATION_JSON);
//    clientResource.put(new JacksonRepresentation<>((JsonNode) null));
    clientResource.put(null);
    assertThat(clientResource.getResponseEntity().getText(), is(nullValue()));
    assertThat(clientResource.getStatus().getCode(), is(204));
  }

  @Test
  public void shouldNotHaveNullPointerWithPost() throws IOException {
    Client client = new Client(new Context(), HTTP);
    ClientResource clientResource = new ClientResource("http://localhost:8283/echo");
    clientResource.setNext(client);
    clientResource.accept(MediaType.APPLICATION_JSON);
    //    clientResource.put(new JacksonRepresentation<>((JsonNode) null));
    clientResource.post(null);
    assertThat(clientResource.getResponseEntity().getText(), is(nullValue()));
    assertThat(clientResource.getStatus().getCode(), is(200));
  }

}

