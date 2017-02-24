package org.test;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.ext.jaxrs.JaxRsApplication;
import org.restlet.security.ChallengeAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("singleton")
public class WebService {

  @Autowired
  private Component restletComponent;

  @Autowired
  private ChallengeAuthenticator challengeAuthenticator;

  @Autowired
  private CustomVerifier myVerifier;

  private boolean started;
  private int port = 8283;

  public void start() throws Exception {
    if (started) {
      return;
    }

    OneApplication oneApplication;
    AnotherApplication anotherApplication;

    oneApplication = new OneApplication();


    anotherApplication = new AnotherApplication();

    // create JAX-RS runtime environment
    JaxRsApplication jaxRsApplication = new JaxRsApplication(restletComponent.getContext());
    jaxRsApplication.getJaxRsRestlet().addClass(CustomExceptionMapper.class);


    jaxRsApplication.add(oneApplication);

//    jaxRsApplication.add(anotherApplication);

    restletComponent.getServers().add(Protocol.HTTP, port);
    restletComponent.getDefaultHost().attach(jaxRsApplication);


    challengeAuthenticator.setVerifier(myVerifier);
    challengeAuthenticator.setNext(jaxRsApplication);
    restletComponent.getDefaultHost().attach(challengeAuthenticator); //UCS-UCS-3960

    restletComponent.start();
    started = true;
  }

}
