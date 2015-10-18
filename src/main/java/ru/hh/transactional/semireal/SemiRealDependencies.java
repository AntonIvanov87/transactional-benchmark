package ru.hh.transactional.semireal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.hh.transactional.CommonDependencies;
import ru.hh.transactional.TransactionFactory;

import javax.sql.DataSource;

@Configuration
@Import(CommonDependencies.class)
@EnableTransactionManagement
// @EnableAspectJAutoProxy
class SemiRealDependencies {

  @Bean
  UserDao userDao(final DataSource dataSource) {
    return new UserDao(dataSource);
  }

  @Bean
  TransactionalUserServiceInterface transactionalServiceInterface(final UserDao userDao) {
    return new TransactionalUserServiceImpl(userDao);
  }

  @Bean
  TransactionalUserService transactionalService(final UserDao userDao) {
    return new TransactionalUserService(userDao);
  }

  @Bean
  NonTransactionalUserService nonTransactionalService(final TransactionFactory transactionFactory,
                                                      final UserDao userDao) {
    return new NonTransactionalUserService(transactionFactory, userDao);
  }

  @Bean
  UserController userController(final TransactionalUserServiceInterface transactionalUserServiceInterface,
                                final TransactionalUserService transactionalUserService,
                                final NonTransactionalUserService nonTransactionalUserService) {
    return new UserController(transactionalUserServiceInterface, transactionalUserService, nonTransactionalUserService);
  }
}
