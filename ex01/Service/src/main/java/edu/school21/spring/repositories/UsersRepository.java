package edu.school21.spring.repositories;

import edu.school21.spring.models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
  Optional<User> findByEmail(String email) throws SQLException;
}
