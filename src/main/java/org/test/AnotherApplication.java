package org.test;

import org.restlet.ext.swagger.SwaggerApplication;

import java.util.HashSet;
import java.util.Set;

public class AnotherApplication extends SwaggerApplication {


  public Set<Class<?>> getClasses() {
    HashSet<Class<?>> classes = new HashSet<>();
    classes.add(Echo.class);
    return classes;
  }
}
