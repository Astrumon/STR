package com.course_project.data_access.dao.route_dao;

import com.course_project.data_access.model.route.Route;

import java.util.List;

public interface RouteDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Route.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Route.ID_ROUTE_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + Route.TABLE_NAME + " WHERE " + Route.ID_ROUTE_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + Route.TABLE_NAME
            + "(" + Route.FROM_TOWN_COLUMN
            + "," + Route.TO_TOWN_COLUMN
            + "," + Route.TIME_END_COLUMN
            + "," + Route.TIME_START_COLUMN
            + "," + Route.ID_ROUTE_COLUMN
            + "," + Route.ALL_TICKETS_COLUMN
            + "," + Route.SOLD_TICKETS_COLUMN
            + "," + Route.PRICE_COLUMN
            + "," + Route.TRAIN_NAME_COLUMN
            + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SQL_UPDATE = "UPDATE " + Route.TABLE_NAME + " SET "
            + Route.FROM_TOWN_COLUMN + " = ?, "
            + Route.TO_TOWN_COLUMN + " = ?, "
            + Route.TIME_START_COLUMN + " = ?, "
            + Route.TIME_END_COLUMN + " = ?, "
            + Route.SOLD_TICKETS_COLUMN + " = ?,"
            + Route.ALL_TICKETS_COLUMN + " = ?, "
            + Route.PRICE_COLUMN + " = ?, "
            + Route.TRAIN_NAME_COLUMN + " = ? WHERE " + Route.ID_ROUTE_COLUMN + " = ?";

    List<Route> findByAll();

    Route findById(Long id);

    boolean delete(Route route);

    boolean insert(Route route);

    boolean update(Route route);
}
