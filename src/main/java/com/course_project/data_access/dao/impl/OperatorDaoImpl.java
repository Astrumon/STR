package com.course_project.data_access.dao.impl;

import com.course_project.data_access.dao.OperatorDao;
import com.course_project.data_access.model.Operator;
import com.course_project.data_access.model.Ticket;
import com.course_project.database.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс OperatorDaoImpl служит для добавления/обновления/удаления информации про операторов
 *
 */
public class OperatorDaoImpl implements OperatorDao {
    private DataSource dataSource;

    public OperatorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<Operator> findByAll() {
        Connection connection = null;
        List<Operator> operators = new ArrayList<Operator>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Operator operator = new Operator();
                operator.setId(resultSet.getLong(Operator.ID_COLUMN));
                operator.setLogin(resultSet.getString(Operator.LOGIN_COLUMN));
                operator.setPassword(resultSet.getString(Operator.PASSWORD_COLUMN));

                operators.add(operator);
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }
        return operators;
    }

    @Override
    public Operator findById(Long id) {
        Connection connection = null;
        Operator operator = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                operator = new Operator();
                operator.setId(resultSet.getLong(Operator.ID_COLUMN));
                operator.setLogin(resultSet.getString(Operator.LOGIN_COLUMN));
                operator.setPassword(resultSet.getString(Operator.PASSWORD_COLUMN));
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }
        return operator;
    }

    @Override
    public Operator findByLogin(String login) {
        Connection connection = null;
        Operator operator = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                operator = new Operator();
                operator.setId(resultSet.getLong(Operator.ID_COLUMN));
                operator.setLogin(resultSet.getString(Operator.LOGIN_COLUMN));
                operator.setPassword(resultSet.getString(Operator.PASSWORD_COLUMN));
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }
        return operator;
    }

    @Override
    public boolean delete(Operator operator) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setString(1, operator.getLogin());
            preparedStatement.execute();
            return true;
        } catch (SQLException exc) {
            System.out.println(exc);
            return false;
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }

    @Override
    public boolean insert(Operator operator) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, operator.getLogin());
            preparedStatement.setString(2, operator.getPassword());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                operator.setId(resultSet.getLong(1));
            }
            return true;
        } catch (SQLException exc) {
            System.out.println(exc);
            return false;
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }

    @Override
    public boolean update(Operator operator) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, operator.getLogin());
            preparedStatement.setString(2, operator.getPassword());
            preparedStatement.setString(3, operator.getLogin());

            preparedStatement.execute();
            return true;
        } catch (SQLException exc) {
            System.out.println(exc);
            return false;
        } finally {
            try {
                connection.close();
            }catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }
}
