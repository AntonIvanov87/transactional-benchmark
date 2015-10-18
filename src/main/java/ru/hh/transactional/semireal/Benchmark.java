package ru.hh.transactional.semireal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public class Benchmark {

  private static final Random random = new Random();
  private static final UserController userController;

  static {
    final BeanFactory beanFactory = new AnnotationConfigApplicationContext(SemiRealDependencies.class);
    userController = beanFactory.getBean(UserController.class);
  }

  @org.openjdk.jmh.annotations.Benchmark
  public String interfaceTransactionalMethod() {
    return userController.getUserTransactionalInterface(random.nextInt());
  }

  @org.openjdk.jmh.annotations.Benchmark
  public String nonInterfaceTransactionalMethod() {
    return userController.getUserTransactionalService(random.nextInt());
  }

  @org.openjdk.jmh.annotations.Benchmark
  public String nonTransactionalMethod() {
    return userController.getUserNonTransactional(random.nextInt());
  }
}
