package ru.hh.transactional.synthetic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.hh.transactional.CommonDependencies;
import ru.hh.transactional.TransactionFactory;

@Configuration
@Import(CommonDependencies.class)
@EnableTransactionManagement
// @EnableAspectJAutoProxy
class SynthethicDependencies {

  @Bean
  TransactionalServiceInterface transactionalServiceInterface() {
    return new TransactionalServiceImpl();
  }

  @Bean
  TransactionalService transactionalService() {
    return new TransactionalService();
  }

  @Bean
  NonTransactionalService nonTransactionalService(final TransactionFactory transactionFactory) {
    return new NonTransactionalService(transactionFactory);
  }
}
