package ru.hh.transactional;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

public final class TransactionFactory {

  private final TransactionTemplate transactionTemplate;

  public TransactionFactory(final PlatformTransactionManager transactionManager) {
    this.transactionTemplate = new TransactionTemplate(transactionManager);
  }

  public <T> T inTransaction(final Supplier<T> block) {
    return transactionTemplate.execute(transactionStatus -> block.get());
  }
}