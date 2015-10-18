package ru.hh.transactional.semireal;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

class TransactionalUserServiceImpl implements TransactionalUserServiceInterface {

  private final UserDao userDao;

  TransactionalUserServiceImpl(final UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  @Transactional
  public Optional<User> getById(final int userId) {
    return userDao.getById(userId);
  }
}
