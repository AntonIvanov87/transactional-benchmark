package ru.hh.transactional.semireal;

final class User {

  final int userId;
  final String firstName;
  final String lastName;

  User(final int userId, final String firstName, final String lastName) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format("%s{%d, %s %s}", getClass().getSimpleName(), userId, lastName, firstName);
  }
}
