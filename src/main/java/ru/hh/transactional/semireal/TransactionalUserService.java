package ru.hh.transactional.semireal;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

public class TransactionalUserService {

  private final UserDao userDao;

  TransactionalUserService(final UserDao userDao) {
    this.userDao = userDao;
  }

  @Transactional
  public Optional<User> getById(final int userId) {
    return userDao.getById(userId);
  }
}
