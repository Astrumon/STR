package com.course_project.data_access.dao.warehouse_dao;

import com.course_project.data_access.model.wagon.Wagon;
import com.course_project.data_access.model.warehouse.Warehouse;
import com.course_project.data_access.model.warehouse.WarehouseSet;

import java.util.List;

public interface WarehouseSetDao {

    String SQL_FIND_ALL = "SELECT * FROM " + WarehouseSet.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + WarehouseSet.ID_COLUMN + " = ?";
    String SQL_FIND_BY_NAME = SQL_FIND_ALL + " WHERE " + WarehouseSet.NAME_COLUMN + " = ?";
    String SQL_UPDATE = "UPDATE " + WarehouseSet.TABLE_NAME + " SET "
            + WarehouseSet.NAME_COLUMN + " = ?, "
            + WarehouseSet.ID_WAGON_COLUMN + " = ?, "
            + WarehouseSet.ID_WAREHOUES_COLUMN + " = ?"
            + " WHERE " + WarehouseSet.ID_COLUMN + " = ?";
    String SQL_UPDATE_WAGON = "UPDATE " + WarehouseSet.TABLE_NAME + " SET "
            + WarehouseSet.ID_WAGON_COLUMN + " = ? "
            + " WHERE " + WarehouseSet.ID_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + WarehouseSet.TABLE_NAME + " WHERE "
            + WarehouseSet.ID_COLUMN + " = ?";
    String SQL_DELETE_BY_NAME = "DELETE FROM " + WarehouseSet.TABLE_NAME + " WHERE "
            + WarehouseSet.NAME_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + WarehouseSet.TABLE_NAME
            + " (" + WarehouseSet.NAME_COLUMN  + ","
            + WarehouseSet.POSITION_COLUMN + ","
            + WarehouseSet.ID_WAREHOUES_COLUMN
            + ") VALUES(?,?,?)";
    String SQL_ADD_WAGON = "UPDATE " + WarehouseSet.TABLE_NAME + " SET "
            + WarehouseSet.ID_WAGON_COLUMN + " = ? WHERE "
            + WarehouseSet.NAME_COLUMN + " = ? AND " + WarehouseSet.POSITION_COLUMN + " = ?";

    List<WarehouseSet> findAll();

    WarehouseSet findById(Long id);

    WarehouseSet findByName(String name);

    void delete(WarehouseSet warehouseSet);

    void deleteByWarehouseName(Warehouse warehouse);

    boolean updateWagon(WarehouseSet warehouseSet);

    void update(WarehouseSet warehouseSet);

    boolean addWagon(String warehouseName, Wagon wagon, int position);

    void insert(WarehouseSet warehouseSet);




}
