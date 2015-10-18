package ru.hh.transactional.synthetic;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class Main {

  public static void main(String[] args) throws RunnerException {

    final BeanFactory beanFactory = new AnnotationConfigApplicationContext(SynthethicDependencies.class);
    beanFactory.getBean(TransactionalServiceInterface.class).method();
    beanFactory.getBean(TransactionalService.class).method();
    beanFactory.getBean(NonTransactionalService.class).method();

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
