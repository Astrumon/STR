package com.course_project.data_access.dao.warehouse_dao;

import com.course_project.data_access.model.warehouse.Warehouse;

import java.util.List;

public interface WarehouseDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Warehouse.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Warehouse.ID_COLUMN + " = ?";
    String SQL_FIND_BY_NAME = SQL_FIND_ALL + " WHERE " + Warehouse.NAME_WAREHOUSE_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + Warehouse.TABLE_NAME + "("  + Warehouse.NAME_WAREHOUSE_COLUMN  + ") VALUES ( ?)";
    String SQL_UPDATE = "UPDATE " + Warehouse.TABLE_NAME + " SET "
            + Warehouse.CAPACITY_COLUMN + "= ?,"
            + Warehouse.COUNT_WAGONS_COLUMN + " = ? "
            + " WHERE " + Warehouse.NAME_WAREHOUSE_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Warehouse.TABLE_NAME + " WHERE "  + Warehouse.ID_COLUMN + " = ?";
    String SQL_DELETE_BY_NAME = "DELETE FROM " + Warehouse.TABLE_NAME + " WHERE "  + Warehouse.NAME_WAREHOUSE_COLUMN + " = ?";
    String SQL_UPDATE_COUNT_WAGON = "UPDATE " + Warehouse.TABLE_NAME + " SET "
            + Warehouse.COUNT_WAGONS_COLUMN + " = ? WHERE "
            + Warehouse.NAME_WAREHOUSE_COLUMN + " = ?";

    List<Warehouse> findAll();

    Warehouse findById(Long id);

    Warehouse findByName(String name);

    void update(Warehouse warehouse);

    void updateCountWagon(Warehouse warehouse);

    void delete(Warehouse warehouse);

    boolean deleteByName(Warehouse warehouse);

    boolean insert(Warehouse warehouse);

}
