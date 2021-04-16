package edu.school21.spring.services;

import java.sql.SQLException;

public interface UsersService {

  String signUp(String email) throws SQLException;

}
