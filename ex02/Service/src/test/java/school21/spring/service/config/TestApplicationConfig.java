package school21.spring.service.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.spring.repositories.UsersRepository;
import edu.school21.spring.repositories.UsersRepositoryJdbcImpl;
import edu.school21.spring.repositories.UsersRepositoryJdbcTemplateImpl;
import edu.school21.spring.services.UsersService;
import edu.school21.spring.services.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
public class TestApplicationConfig {
  @Bean
  public DataSource embeddedDatabase() {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    return builder
      .setType(EmbeddedDatabaseType.H2)
      .addScript("shema.sql")
      .build();
  }

  @Bean
  public UsersRepository usersRepositoryJdbc(DataSource dataSource) {
    return new UsersRepositoryJdbcImpl(dataSource);
  }

  @Bean
  public UsersRepository usersRepositoryJdbcTemplate(DataSource dataSource) {
    return new UsersRepositoryJdbcTemplateImpl(dataSource);
  }

  @Bean
  public UsersService usersService() {
    return new UsersServiceImpl();
  }
}
