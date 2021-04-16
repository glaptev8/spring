package school21.spring.service.services;

import edu.school21.spring.services.UsersService;
import edu.school21.spring.services.UsersServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import school21.spring.service.config.TestApplicationConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
public class UsersServiceImplTest {

  @Autowired
  private DataSource embeddedDatabase;

  @Autowired
  private UsersServiceImpl usersService;

  @Test
  public void usersServiceImplTest() throws SQLException {
    usersService.signUp("teeeest@mail.ru");
    String sql = "SELECT password FROM users WHERE id = 6";
    PreparedStatement preparedStatement = embeddedDatabase.getConnection().prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
      assert resultSet.getString("password") != null;
    }
  }
}
