package com.course_project.data_access.dao.impl;

import com.course_project.data_access.dao.CargoDao;
import com.course_project.data_access.model.Cargo;
import com.course_project.data_access.model.Operator;
import com.course_project.database.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс CargoDaoImpl служит для добавления/обновления/удаления информации про грузоперевозки
 *
 */
public class CargoDaoImpl implements CargoDao {
    private DataSource dataSource;

    public CargoDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Cargo> findByAll() {
        Connection connection = null;
        List<Cargo> cargos = new ArrayList<Cargo>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultSet.getLong(Cargo.ID_COLUMN));
                cargo.setFromTown(resultSet.getString(Cargo.FROM_TOWN_COlUMN));
                cargo.setToTown(resultSet.getString(Cargo.TO_TOWN_COlUMN));
                cargo.setDateSend(resultSet.getString(Cargo.DATE_SEND_COLUMN));
                cargo.setDateArrive(resultSet.getString(Cargo.DATE_ARRIVE_COLUMN));
                cargo.setEmail(resultSet.getString(Cargo.EMAIL_COLUMN));
                cargo.setText(resultSet.getString(Cargo.TEXT_COLUMN));


                cargos.add(cargo);
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
        return cargos;
    }

    @Override
    public Cargo findById(Long id) {
        Connection connection = null;
        Cargo cargo = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cargo = new Cargo();
                cargo.setId(resultSet.getLong(Cargo.ID_COLUMN));
                cargo.setFromTown(resultSet.getString(Cargo.FROM_TOWN_COlUMN));
                cargo.setToTown(resultSet.getString(Cargo.TO_TOWN_COlUMN));
                cargo.setDateSend(resultSet.getString(Cargo.DATE_SEND_COLUMN));
                cargo.setDateArrive(resultSet.getString(Cargo.DATE_ARRIVE_COLUMN));
                cargo.setEmail(resultSet.getString(Cargo.EMAIL_COLUMN));
                cargo.setText(resultSet.getString(Cargo.TEXT_COLUMN));
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
        return cargo;
    }


    @Override
    public boolean delete(Cargo cargo) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, cargo.getId());
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
    public boolean insert(Cargo cargo) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cargo.getFromTown());
            preparedStatement.setString(2, cargo.getToTown());
            preparedStatement.setString(3, cargo.getDateSend());
            preparedStatement.setString(4, cargo.getDateArrive());
            preparedStatement.setString(5, cargo.getEmail());
            preparedStatement.setString(6, cargo.getText());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                cargo.setId(resultSet.getLong(1));
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
    public boolean update(Cargo cargo) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, cargo.getFromTown());
            preparedStatement.setString(2, cargo.getToTown());
            preparedStatement.setString(3, cargo.getDateSend());
            preparedStatement.setString(4, cargo.getDateArrive());
            preparedStatement.setString(5, cargo.getEmail());
            preparedStatement.setString(6, cargo.getText());
            preparedStatement.setLong(7, cargo.getId());

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
