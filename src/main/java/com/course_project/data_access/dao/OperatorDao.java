package com.course_project.data_access.dao;

import com.course_project.data_access.model.Operator;
import com.course_project.data_access.model.route.Route;

import java.util.List;

public interface OperatorDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Operator.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Operator.ID_COLUMN + " = ?";
    String SQL_FIND_BY_LOGIN = SQL_FIND_ALL + " WHERE " + Operator.LOGIN_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Operator.TABLE_NAME + " WHERE " + Operator.LOGIN_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + Operator.TABLE_NAME
            + "(" + Operator.LOGIN_COLUMN
            + "," + Operator.PASSWORD_COLUMN
            + ") VALUES(?, ?)";
    String SQL_UPDATE = "UPDATE " + Operator.TABLE_NAME + " SET "
            + Operator.LOGIN_COLUMN + " = ?, "
            + Operator.PASSWORD_COLUMN + " = ?"
            + " WHERE " + Operator.LOGIN_COLUMN + " = ?";

    List<Operator> findByAll();

    Operator findById(Long id);

    Operator findByLogin(String login);

    boolean delete(Operator operator);

    boolean insert(Operator operator);

    boolean update(Operator operator);

}
