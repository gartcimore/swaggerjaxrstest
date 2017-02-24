package org.test;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main(String[] args) {

    ClassPathXmlApplicationContext context = null;

    try {
      context = new ClassPathXmlApplicationContext("spring.xml");
    } catch (BeansException exception) {
      System.out.println("Error loading spring : "+exception.getMessage());
      exception.printStackTrace();
    }
    WebService bean = context.getBean(WebService.class);

    try {
      bean.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
