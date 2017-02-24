package org.test;

import org.restlet.security.SecretVerifier;
import org.springframework.stereotype.Component;


@Component
public class CustomVerifier extends SecretVerifier {

  @Override
  public int verify(String identifier, char[] secret) {
    System.out.println(String.format("Verifying secret for user %s", identifier));
    return RESULT_VALID;
  }
}
