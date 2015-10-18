package ru.hh.transactional.semireal;

import java.util.Optional;

class UserController {

  private final TransactionalUserServiceInterface transactionalUserServiceInterface;
  private final TransactionalUserService transactionalUserService;
  private final NonTransactionalUserService nonTransactionalService;

  UserController(final TransactionalUserServiceInterface transactionalUserServiceInterface,
                 final TransactionalUserService transactionalUserService,
                 final NonTransactionalUserService nonTransactionalService) {
    this.transactionalUserServiceInterface = transactionalUserServiceInterface;
    this.transactionalUserService = transactionalUserService;
    this.nonTransactionalService = nonTransactionalService;
  }

  String getUserTransactionalInterface(final int userId) {
    final Optional<User> optionalUser = transactionalUserServiceInterface.getById(userId);
    return optionalUserToString(optionalUser);
  }

  String getUserTransactionalService(final int userId) {
    final Optional<User> optionalUser = transactionalUserService.getById(userId);
    return optionalUserToString(optionalUser);
  }

  String getUserNonTransactional(final int userId) {
    final Optional<User> optionalUser = nonTransactionalService.getById(userId);
    return optionalUserToString(optionalUser);
  }

  private static String optionalUserToString(final Optional<User> optionalUser) {
    return optionalUser.isPresent() ? optionalUser.get().toString() : "No user";
  }
}
