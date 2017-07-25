package org.test;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.ext.jaxrs.JaxRsApplication;
import org.restlet.ext.swagger.JaxRsApplicationSwaggerSpecificationRestlet;
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

    HelloApplication helloApplication;
    EchoApplication echoApplication;

    helloApplication = new HelloApplication();


    echoApplication = new EchoApplication();

    // create JAX-RS runtime environment
    JaxRsApplication jaxRsApplication = new JaxRsApplication(restletComponent.getContext().createChildContext());
//    jaxRsApplication.getJaxRsRestlet().addClass(CustomExceptionMapper.class);



    jaxRsApplication.add(helloApplication);
    jaxRsApplication.add(echoApplication);

    JaxRsApplicationSwaggerSpecificationRestlet jaxRsApplicationSwaggerSpecificationRestlet =
      new JaxRsApplicationSwaggerSpecificationRestlet(echoApplication);

    restletComponent.getServers().add(Protocol.HTTP, port);

    // access to docs on all url
//    restletComponent.getDefaultHost().attach(jaxRsApplicationSwaggerSpecificationRestlet);
//    restletComponent.getDefaultHost().attach(jaxRsApplication);
//
//    challengeAuthenticator.setVerifier(myVerifier);
//    challengeAuthenticator.setNext(jaxRsApplicationSwaggerSpecificationRestlet);


    // access to resources but no api doc
//    restletComponent.getDefaultHost().attach(jaxRsApplication);
//    restletComponent.getDefaultHost().attach(jaxRsApplicationSwaggerSpecificationRestlet);
//
//    challengeAuthenticator.setVerifier(myVerifier);
//    challengeAuthenticator.setNext(jaxRsApplicationSwaggerSpecificationRestlet);


    // access to docs on all url
    restletComponent.getDefaultHost().attach(jaxRsApplicationSwaggerSpecificationRestlet);
    restletComponent.getDefaultHost().attach(jaxRsApplication);

    challengeAuthenticator.setVerifier(myVerifier);
    challengeAuthenticator.setNext(jaxRsApplication);

    restletComponent.getDefaultHost().attach(challengeAuthenticator);
    restletComponent.start();
    started = true;
  }

}
