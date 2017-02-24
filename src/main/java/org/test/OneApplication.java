package org.test;

import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

public class OneApplication extends Application {

  public Set<Class<?>> getClasses() {

    HashSet classes = new HashSet();
    classes.add(OneAppHelloResource.class);
    return classes;
  }
}
