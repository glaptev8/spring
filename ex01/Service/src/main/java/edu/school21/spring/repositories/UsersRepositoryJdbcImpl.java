package edu.school21.spring.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.spring.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

  private HikariDataSource dataSource;

  public UsersRepositoryJdbcImpl(HikariDataSource dataSource) {

    this.dataSource = dataSource;
  }

  @Override
  public Optional<User> findByEmail(String email) throws SQLException {
    String sql = "SELECT * FROM users WHERE email = '" + email + "'";
    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      Long id = resultSet.getLong("id");
      String emaill = resultSet.getString("email");
      preparedStatement.close();
      return Optional.of(new User(id, emaill));
    }
    preparedStatement.close();
    return Optional.empty();
  }

  @Override
  public User findById(Long id) throws SQLException {
    String sql = "SELECT * FROM users WHERE id = " + id;
    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      Long idd = resultSet.getLong("id");
      String emaill = resultSet.getString("email");
      preparedStatement.close();
      return new User(idd, emaill);
    }
    preparedStatement.close();
    return null;
  }

  @Override
  public List<User> findAll() throws SQLException {
    String sql = "SELECT * FROM users";
    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    List<User> users = new ArrayList<>();
    while (resultSet.next()) {

      Long idd = resultSet.getLong("id");
      String emaill = resultSet.getString("email");
      users.add(new User(idd, emaill));
    }
    preparedStatement.close();
    return users;
  }

  @Override
  public void save(User entity) throws SQLException {
    String sql = "INSERT INTO users (email) VALUES ('" + entity.getEmail() +"')";
    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
    preparedStatement.executeUpdate();
    preparedStatement.close();
  }

  @Override
  public void update(User entity) throws SQLException {
    String sql = "update users set email=? where id=?";
    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
    preparedStatement.setString(1, entity.getEmail());
    preparedStatement.setLong(2, entity.getId());
    preparedStatement.executeUpdate();
    preparedStatement.close();
  }

  @Override
  public void delete(Long id) throws SQLException {
    String sql = "delete from users where id = " + id;
    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
    preparedStatement.execute();
    preparedStatement.close();
  }
}
