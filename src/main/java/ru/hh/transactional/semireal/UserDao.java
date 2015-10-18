package ru.hh.transactional.semireal;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class UserDao {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  UserDao(final DataSource dataSource) {
    namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  Optional<User> getById(final int userId) {
    final Map<String, Object> params = new HashMap<>();
    params.put("user_id", userId);

    final User user;
    try {
      user = namedParameterJdbcTemplate.queryForObject(userByIdQuery, params, rowToUser);
    } catch (final EmptyResultDataAccessException ignored) {
      return Optional.empty();
    }
    return Optional.of(user);
  }

  private static final String userByIdQuery =
          "SELECT user_id, first_name, last_name FROM users WHERE user_id = :user_id";
  private static final RowMapper<User> rowToUser = (resultSet, rowNum) ->
          new User(resultSet.getInt("user_id"),resultSet.getString("first_name"), resultSet.getString("last_name"));
}
