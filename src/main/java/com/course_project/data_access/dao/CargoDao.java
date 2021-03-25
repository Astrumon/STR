package com.course_project.data_access.dao;

import com.course_project.data_access.model.Cargo;

import java.util.List;

/**
 * Интерфейс который содержит основные SQL-запросы таблицы cargo
 */
public interface CargoDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Cargo.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Cargo.ID_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Cargo.TABLE_NAME + " WHERE " + Cargo.ID_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + Cargo.TABLE_NAME
            + "(" + Cargo.FROM_TOWN_COlUMN
            + "," + Cargo.TO_TOWN_COlUMN
            + "," + Cargo.DATE_SEND_COLUMN
            + "," + Cargo.DATE_ARRIVE_COLUMN
            + "," + Cargo.EMAIL_COLUMN
            + "," + Cargo.TEXT_COLUMN
            + ") VALUES(?, ?, ?, ?, ?, ?)";
    String SQL_UPDATE = "UPDATE " + Cargo.TABLE_NAME + " SET "
            + Cargo.FROM_TOWN_COlUMN + " = ?, "
            + Cargo.TO_TOWN_COlUMN + " = ?, "
            + Cargo.DATE_SEND_COLUMN + " = ?, "
            + Cargo.DATE_ARRIVE_COLUMN + " = ?, "
            + Cargo.EMAIL_COLUMN + " = ?, "
            + Cargo.TEXT_COLUMN + " = ? "
            + " WHERE " + Cargo.ID_COLUMN + " = ?";

    List<Cargo> findByAll();

    Cargo findById(Long id);


    boolean delete(Cargo cargo);

    boolean insert(Cargo cargo);

    boolean update(Cargo cargo);
}
