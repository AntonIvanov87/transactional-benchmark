package ru.hh.transactional.synthetic;

import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

class TransactionalServiceImpl implements TransactionalServiceInterface {

  private static final Random random = new Random();

  @Override
  @Transactional
  public int method() {
    return random.nextInt();
  }
}
