package com.course_project.data_access.dao.impl.train_dao_impl;

import com.course_project.data_access.dao.impl.wagon_dao_impl.WagonDaoImpl;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.data_access.model.warehouse.WarehouseSet;
import com.course_project.database.DataSource;
import com.course_project.data_access.dao.train_dao.TrainSetDao;
import com.course_project.data_access.model.train.Train;
import com.course_project.data_access.model.train.TrainSet;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.support.manager.TrainManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс RouteSetDaoImpl служит для добавления/обновления/удаления информации про  состав поезда
 *
 */
public class TrainSetDaoImpl implements TrainSetDao {

    private DataSource dataSource;


    public TrainSetDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addWagon(String trainName, Wagon wagon, int position) {
        Connection connection = null;
        assert wagon != null;

        TrainManager trainManager = new TrainManager();
        TrainSet trainSet = trainManager.getFilledTrainSet(wagon, position);

            try {
                connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_WAGON);
                preparedStatement.setLong(1, trainSet.getIdWagon());
                preparedStatement.setString(2, trainSet.getName());
                preparedStatement.setInt(3, trainSet.getPosWagon());
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
    public List<TrainSet> findAll() {
        Connection connection = null;
        List<TrainSet> trainSets = new ArrayList<TrainSet>();

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                TrainSet trainSet = new TrainSet();
                trainSet.setId(rs.getLong(TrainSet.ID_COLUMN));
                trainSet.setName(rs.getString(TrainSet.NAME_COLUMN));
                trainSet.setIdWagon(rs.getLong(TrainSet.ID_WAGON_COLUMN));
                trainSet.setPosWagon(rs.getInt(TrainSet.POS_WAGON_COLUMN));
                trainSet.setIdTrain(rs.getLong(TrainSet.ID_TRAIN_COLUMN));
                trainSets.add(trainSet);
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

        return trainSets;
    }

    @Override
    public TrainSet findById(Long id) {
        Connection connection = null;
        TrainSet trainSet = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                trainSet = new TrainSet();
                trainSet.setId(rs.getLong(TrainSet.ID_COLUMN));
                trainSet.setName(rs.getString(TrainSet.NAME_COLUMN));
                trainSet.setIdWagon(rs.getLong(TrainSet.ID_WAGON_COLUMN));
                trainSet.setIdTrain(rs.getLong(TrainSet.ID_TRAIN_COLUMN));
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
        return trainSet;
    }

    @Override
    public TrainSet findByName(String name) {
        Connection connection = null;
        TrainSet trainSet = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                trainSet = new TrainSet();
                trainSet.setId(rs.getLong(TrainSet.ID_COLUMN));
                trainSet.setName(rs.getString(TrainSet.NAME_COLUMN));
                trainSet.setIdWagon(rs.getLong(TrainSet.ID_WAGON_COLUMN));
                trainSet.setIdTrain(rs.getLong(TrainSet.ID_TRAIN_COLUMN));
                trainSet.setPosWagon(rs.getInt(TrainSet.POS_WAGON_COLUMN));
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
        return trainSet;
    }

    @Override
    public void delete(TrainSet trainSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, trainSet.getId());
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
    public void deleteByTrainName(Train train) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_TRAIN_NAME);
            preparedStatement.setString(1, train.getName());
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
    public void insert(TrainSet trainSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, trainSet.getName());
            preparedStatement.setInt(2, trainSet.getPosWagon());
            preparedStatement.setLong(3, trainSet.getIdTrain());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                trainSet.setId(rs.getLong(1));
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
    }

    @Override
    public void update(TrainSet trainSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, trainSet.getName());
            preparedStatement.setLong(2, trainSet.getIdWagon());
            preparedStatement.setLong(3, trainSet.getIdTrain());
            preparedStatement.setLong(4, trainSet.getId());
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
    public boolean updateWagon(TrainSet trainSet) {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_WAGON);
            if (trainSet.getIdWagon() == null) {
                preparedStatement.setNull(1, 0);
            } else {
                preparedStatement.setLong(1, trainSet.getIdWagon());
            }
            preparedStatement.setLong(2, trainSet.getId());
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
