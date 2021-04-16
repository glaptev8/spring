package edu.school21.spring.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.spring.repositories.UsersRepository;
import edu.school21.spring.repositories.UsersRepositoryJdbcImpl;
import edu.school21.spring.repositories.UsersRepositoryJdbcTemplateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("edu.school21.spring")
@PropertySource("classpath:/db.properties")
public class ApplicationConfig {

  @Autowired
  private Environment env;

  @Bean
  public HikariConfig hikariConfig() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.url"));
    hikariConfig.setUsername(env.getProperty("spring.datasource.username"));
    hikariConfig.setPassword(env.getProperty("spring.datasource.password"));
    hikariConfig.setUsername(env.getProperty("spring.datasource.username"));
    return hikariConfig;
  }

  @Bean
  public HikariDataSource hikariDataSource(HikariConfig hikariConfig) {
    return new HikariDataSource(hikariConfig);
  }

  @Bean
  public UsersRepository usersRepositoryJdbc(HikariDataSource hikariDataSource) {
    return new UsersRepositoryJdbcImpl(hikariDataSource);
  }

  @Bean
  public UsersRepository usersRepositoryJdbcTemplate(HikariDataSource hikariDataSource) {
    return new UsersRepositoryJdbcTemplateImpl(hikariDataSource);
  }


}