package ru.hh.transactional.semireal;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public final class Main {

  public static void main(String[] args) throws RunnerException {

    final BeanFactory beanFactory = new AnnotationConfigApplicationContext(SemiRealDependencies.class);
    final UserController userController = beanFactory.getBean(UserController.class);
    final int userId = new Random().nextInt();
    System.out.println("Check user by id " + userId + ':');
    System.out.println(userController.getUserTransactionalInterface(userId));
    System.out.println(userController.getUserTransactionalService(userId));
    System.out.println(userController.getUserNonTransactional(userId));

    final Options options = new OptionsBuilder()
            .include(Benchmark.class.getName())
            .forks(3)
            .warmupIterations(10)
            .build();

    new Runner(options).run();
  }

  private Main() {
  }
}
