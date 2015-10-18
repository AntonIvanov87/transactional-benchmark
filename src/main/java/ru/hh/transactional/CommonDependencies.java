package ru.hh.transactional;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class CommonDependencies {

  private static PGSimpleDataSource pgSimpleDataSource() {
    final PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
    pgSimpleDataSource.setDatabaseName("jdbc_example");
    pgSimpleDataSource.setUser("jdbc_example");
    pgSimpleDataSource.setPassword("123");
    return pgSimpleDataSource;
  }

  private static HikariConfig hikariConfig(final DataSource dataSource) {
    final HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDataSource(dataSource);
    return hikariConfig;
  }

  @Bean
  public DataSource dataSource() {
    final DataSource postgresqlDataSource = pgSimpleDataSource();
    final HikariConfig hikariConfig = hikariConfig(postgresqlDataSource);
    return new HikariDataSource(hikariConfig);
  }

  @Bean
  public PlatformTransactionManager platformTransactionManager(final DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public TransactionFactory transactionFactory(final PlatformTransactionManager platformTransactionManager) {
    return new TransactionFactory(platformTransactionManager);
  }
}
