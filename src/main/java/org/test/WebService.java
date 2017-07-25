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

    jaxRsApplicationSwaggerSpecificationRestlet.setBasePath("doc1");
    restletComponent.getServers().add(Protocol.HTTP, port);
//        restletComponent.getDefaultHost().attach(jaxRsApplication);
        restletComponent.getDefaultHost().attach("doc",jaxRsApplicationSwaggerSpecificationRestlet);


    challengeAuthenticator.setVerifier(myVerifier);
//    challengeAuthenticator.setNext(jaxRsApplicationSwaggerSpecificationRestlet);
    challengeAuthenticator.setNext(jaxRsApplication);
    restletComponent.getDefaultHost().attach(challengeAuthenticator);
    restletComponent.start();
    started = true;
  }

}
