package ru.hh.transactional.synthetic;

import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

public class TransactionalService {

  private static final Random random = new Random();

  @Transactional
  public int method() {
    return random.nextInt();
  }
}
