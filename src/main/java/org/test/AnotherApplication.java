package org.test;

import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

public class AnotherApplication extends Application {


  public Set<Class<?>> getClasses() {
    HashSet<Class<?>> classes = new HashSet<>();
    classes.add(Echo.class);
    return classes;
  }
}
