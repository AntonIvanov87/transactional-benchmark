package ru.hh.transactional.semireal;

import ru.hh.transactional.TransactionFactory;

import java.util.Optional;
import java.util.Random;

class NonTransactionalUserService {

  private final TransactionFactory transactionFactory;
  private final UserDao userDao;

  NonTransactionalUserService(final TransactionFactory transactionFactory, final UserDao userDao) {
    this.transactionFactory = transactionFactory;
    this.userDao = userDao;
  }

  Optional<User> getById(final int userId) {
    return transactionFactory.inTransaction(() -> userDao.getById(userId));
  }
}
