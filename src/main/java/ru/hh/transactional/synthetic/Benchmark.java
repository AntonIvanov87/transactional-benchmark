package ru.hh.transactional.synthetic;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Benchmark {

  private static final TransactionalServiceInterface transactionalServiceInterface;
  private static final TransactionalService transactionalService;
  private static final NonTransactionalService nonTransactionalService;

  static {
    final BeanFactory beanFactory = new AnnotationConfigApplicationContext(SynthethicDependencies.class);
    transactionalServiceInterface = beanFactory.getBean(TransactionalServiceInterface.class);
    transactionalService = beanFactory.getBean(TransactionalService.class);
    nonTransactionalService = beanFactory.getBean(NonTransactionalService.class);
  }

  @org.openjdk.jmh.annotations.Benchmark
  public int interfaceTransactionalMethod() {
    return transactionalServiceInterface.method();
  }

  @org.openjdk.jmh.annotations.Benchmark
  public int nonInterfaceTransactionalMethod() {
    return transactionalService.method();
  }

  @org.openjdk.jmh.annotations.Benchmark
  public int nonTransactionalMethod() {
    return nonTransactionalService.method();
  }
}
