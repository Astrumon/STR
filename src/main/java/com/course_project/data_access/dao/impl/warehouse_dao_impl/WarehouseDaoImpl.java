package com.course_project.data_access.dao.impl.warehouse_dao_impl;

import com.course_project.database.DataSource;
import com.course_project.data_access.dao.warehouse_dao.WarehouseDao;
import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.data_access.model.warehouse.WarehouseSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс WarehouseDaoImpl служит для добавления/обновления/удаления информации про склады
 *
 */
public class WarehouseDaoImpl implements WarehouseDao {
    private DataSource dataSource;


    public WarehouseDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Warehouse> findAll() {
        Connection connection = null;
        List<Warehouse> warehouses = new ArrayList<Warehouse>();

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setId(rs.getLong(Wagon.ID_COLUMN));
                warehouse.setCapacity(rs.getInt(Warehouse.CAPACITY_COLUMN));
                warehouse.setName(rs.getString(Warehouse.NAME_WAREHOUSE_COLUMN));
                warehouse.setCountWagons(rs.getInt(Warehouse.COUNT_WAGONS_COLUMN));
                warehouses.add(warehouse);
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

        return warehouses;
    }

    @Override
    public Warehouse findById(Long id) {
        Connection connection = null;
        Warehouse warehouse = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                warehouse = new Warehouse();
                warehouse.setId(rs.getLong(Warehouse.ID_COLUMN));
                warehouse.setCapacity(rs.getInt(Warehouse.CAPACITY_COLUMN));
                warehouse.setName(rs.getString(Warehouse.NAME_WAREHOUSE_COLUMN));
                warehouse.setCountWagons(rs.getInt(Warehouse.COUNT_WAGONS_COLUMN));
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
        return warehouse;
    }

    @Override
    public Warehouse findByName(String name) {
        Connection connection = null;
        Warehouse warehouse = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                warehouse = new Warehouse();
                warehouse.setId(rs.getLong(Warehouse.ID_COLUMN));
                warehouse.setCapacity(rs.getInt(Warehouse.CAPACITY_COLUMN));
                warehouse.setName(rs.getString(Warehouse.NAME_WAREHOUSE_COLUMN));
                warehouse.setCountWagons(rs.getInt(Warehouse.COUNT_WAGONS_COLUMN));
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
        return warehouse;
    }

    @Override
    public void update(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, warehouse.getCapacity());
            preparedStatement.setInt(2, warehouse.getCountWagons());
            preparedStatement.setString(3, warehouse.getName());
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
    public void delete(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, warehouse.getId());
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
    public boolean insert(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, warehouse.getName());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                warehouse.setId(rs.getLong(1));
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
    public boolean deleteByName(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_NAME);
            preparedStatement.setString(1, warehouse.getName());
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
    public void updateCountWagon(Warehouse warehouse) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COUNT_WAGON);
            preparedStatement.setInt(1, warehouse.getCountWagons());
            preparedStatement.setString(2, warehouse.getName());
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
}
