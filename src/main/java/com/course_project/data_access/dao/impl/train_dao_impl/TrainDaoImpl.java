package com.course_project.data_access.dao.impl.train_dao_impl;

import com.course_project.database.DataSource;
import com.course_project.data_access.dao.train_dao.TrainDao;
import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.train.TrainSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс TrainDaoImpl служит для добавления/обновления/удаления информации про поезда
 *
 */
public class TrainDaoImpl implements TrainDao {
    private DataSource dataSource;

    public TrainDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Train> findAll() {
        Connection connection = null;
        List<Train> trains = new ArrayList<Train>();

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Train train = new Train();
                train.setId(rs.getLong(Train.ID_COLUMN));
                train.setName(rs.getString(Train.NAME_COLUMN));
                train.setCapacity(rs.getInt(Train.CAPACITY_COLUMN));
                train.setCountWagon(rs.getInt(Train.COUNT_WAGON_COLUMN));
                train.setType(rs.getInt(Train.TYPE_COLUMN));
                train.setIdRoute(rs.getLong(Train.ID_ROUTE_COLUMN));
                trains.add(train);
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }

        return trains;
    }

    @Override
    public Train findById(Long id) {
        Connection connection = null;
        Train train = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                train = new Train();
                train.setId(rs.getLong(Train.ID_COLUMN));
                train.setName(rs.getString(Train.NAME_COLUMN));
                train.setCapacity(rs.getInt(Train.CAPACITY_COLUMN));
                train.setCountWagon(rs.getInt(Train.COUNT_WAGON_COLUMN));
                train.setType(rs.getInt(Train.TYPE_COLUMN));
                train.setIdRoute(rs.getLong(Train.ID_ROUTE_COLUMN));
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
        return train;
    }

    @Override
    public Train findByName(String name) {
        Connection connection = null;
        Train train = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                train = new Train();
                train.setId(rs.getLong(Train.ID_COLUMN));
                train.setName(rs.getString(Train.NAME_COLUMN));
                train.setCapacity(rs.getInt(Train.CAPACITY_COLUMN));
                train.setCountWagon(rs.getInt(Train.COUNT_WAGON_COLUMN));
                train.setType(rs.getInt(Train.TYPE_COLUMN));
                train.setIdRoute(rs.getLong(Train.ID_ROUTE_COLUMN));
            }
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
        return train;
    }

    @Override
    public boolean delete(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, train.getId());
            preparedStatement.execute();
            return true;

        } catch (SQLException exc) {
            System.out.println(exc);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }

    @Override
    public boolean deleteByName(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_NAME);
            preparedStatement.setString(1, train.getName());
            preparedStatement.execute();
            return true;

        } catch (SQLException exc) {
            System.out.println(exc);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }

    @Override
    public boolean insert(Train train) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, train.getName());
            preparedStatement.setInt(2, train.getType());
            preparedStatement.setInt(3, train.getCapacity());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();

            while (rs.next()) {
                train.setId(rs.getLong(1));
            }

            return true;
        } catch (SQLException exc) {
            System.out.println(exc);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }

    @Override
    public boolean update(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, train.getName());
            preparedStatement.setInt(2, train.getCapacity());
            preparedStatement.setInt(3, train.getCountWagon());
            preparedStatement.setInt(4, train.getType());
            preparedStatement.setLong(5, train.getIdRoute());
            preparedStatement.setString(6, train.getName());
            preparedStatement.execute();

            return true;

        } catch (SQLException exc) {
            System.out.println(exc);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }

    @Override
    public void updateTrainSet(TrainSet trainSet, Long idWarehouseSet) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatementWagon = connection.prepareStatement(SQL_UPDATE_TRAIN_SET);
            preparedStatementWagon.setLong(1, idWarehouseSet);
            preparedStatementWagon.setString(2, trainSet.getName());
            preparedStatementWagon.setLong(3, trainSet.getIdWagon());
            preparedStatementWagon.execute();
        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }

    @Override
    public void updateCountWagon(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COUNT_WAGON);
            preparedStatement.setInt(1, train.getCountWagon());
            preparedStatement.setString(2, train.getName());
            preparedStatement.execute();

        } catch (SQLException exc) {
            System.out.println(exc);
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }

    @Override
    public boolean updateRoute(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ROUTE);
            if (train.getIdRoute() == null) {
                preparedStatement.setNull(1, 0);
            } else {
                preparedStatement.setLong(1, train.getIdRoute());
            }

            preparedStatement.setString(2, train.getName());
            preparedStatement.execute();
            return true;
        } catch (SQLException exc) {
            System.out.println(exc);
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException exc) {
                System.out.println(exc);
            }
        }
    }
}
