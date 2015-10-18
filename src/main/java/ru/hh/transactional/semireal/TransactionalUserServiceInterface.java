package ru.hh.transactional.semireal;

import java.util.Optional;

public interface TransactionalUserServiceInterface {
  Optional<User> getById(final int userId);
}
