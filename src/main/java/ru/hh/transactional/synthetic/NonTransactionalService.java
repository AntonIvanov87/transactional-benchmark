package ru.hh.transactional.synthetic;

import ru.hh.transactional.TransactionFactory;

import java.util.Random;

class NonTransactionalService {

  private static final Random random = new Random();

  private final TransactionFactory transactionFactory;

  NonTransactionalService(final TransactionFactory transactionFactory) {
    this.transactionFactory = transactionFactory;
  }

  int method() {
    return transactionFactory.inTransaction(() -> random.nextInt());
  }
}
