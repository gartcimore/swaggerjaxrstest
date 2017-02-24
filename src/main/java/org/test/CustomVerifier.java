package org.test;

import org.restlet.security.SecretVerifier;
import org.springframework.stereotype.Component;


@Component
public class CustomVerifier extends SecretVerifier {

  @Override
  public int verify(String identifier, char[] secret) {
    return RESULT_VALID;
  }
}
