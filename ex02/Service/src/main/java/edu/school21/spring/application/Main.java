package edu.school21.spring.application;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.spring.config.ApplicationConfig;
import edu.school21.spring.models.User;
import edu.school21.spring.repositories.UsersRepositoryJdbcImpl;
import edu.school21.spring.repositories.UsersRepositoryJdbcTemplateImpl;
import edu.school21.spring.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {


  public static void main(String[] args) throws SQLException {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

    UsersServiceImpl usersService = context.getBean(UsersServiceImpl.class);
    HikariDataSource dataSource = context.getBean(HikariDataSource.class);
    createTable(dataSource);
    usersService.signUp("email");
    UsersRepositoryJdbcImpl usersRepositoryJdbc = context.getBean(UsersRepositoryJdbcImpl.class);
    System.out.println(usersRepositoryJdbc.findAll());
  }

  private static void example2(UsersRepositoryJdbcImpl usersRepositoryJdbc) throws SQLException {
    User userId = usersRepositoryJdbc.findById(3L);
    System.out.println(userId);
    User user = new User("sacvve@email.ru");
    usersRepositoryJdbc.save(user);
    System.out.println(usersRepositoryJdbc.findAll());
    System.out.println(usersRepositoryJdbc.findAll());
    userId.setEmail("updateUser");
    usersRepositoryJdbc.update(userId);
    System.out.println(usersRepositoryJdbc.findById(3L));
    System.out.println(usersRepositoryJdbc.findByEmail("updateUser"));
    System.out.println(usersRepositoryJdbc.findAll());
  }

  private static void example1(UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate) {
    User userId = usersRepositoryJdbcTemplate.findById(3L);
    System.out.println(userId);
    User user = new User("sacvve@email.ru");
    usersRepositoryJdbcTemplate.save(user);
    System.out.println(usersRepositoryJdbcTemplate.findAll());
    usersRepositoryJdbcTemplate.delete(6L);
    userId.setEmail("updateUser");
    usersRepositoryJdbcTemplate.update(userId);
    System.out.println(usersRepositoryJdbcTemplate.findById(3L));
    System.out.println(usersRepositoryJdbcTemplate.findByEmail("updateUser"));
    System.out.println(usersRepositoryJdbcTemplate.findAll());
  }

  private static void createTable(HikariDataSource dataSource) throws SQLException {
    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("DROP TABLE IF EXISTS users;\n" +
      "CREATE TABLE users (id INT AUTO_INCREMENT  PRIMARY KEY, email VARCHAR(250) NOT NULL, password VARCHAR(250));\n" +
      "INSERT INTO users (email) VALUES\n" +
      "  ('qqq@email1.ru'),\n" +
      "  ('qqq@email2.ru'),\n" +
      "  ('qqq@email3.ru'),\n" +
      "  ('qqq@email4.ru'),\n" +
      "  ('qqq@email5.ru');");
    preparedStatement.executeUpdate();
    preparedStatement.close();
  }
}
