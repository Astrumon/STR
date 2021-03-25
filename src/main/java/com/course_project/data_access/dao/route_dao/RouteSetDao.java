package com.course_project.data_access.dao.route_dao;


import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.RouteSet;

import java.util.List;

/**
 * Интерфейс который содержит основные SQL-запросы таблицы route_set
 */
public interface RouteSetDao {

    String SQL_FIND_ALL = "SELECT * FROM " + RouteSet.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + RouteSet.ID_COLUMN + " = ?";
    String SQL_FIND_BY_ID_ROUTE = SQL_FIND_ALL + " WHERE " + RouteSet.ID_ROUTE_COLUMN + " = ?";
    String SQL_DELETE = "DELETE FROM " + RouteSet.TABLE_NAME + " WHERE " + RouteSet.ID_ROUTE_COLUMN + " = ?";
    String SQL_FIND_BY_FROM_TO_DATE = SQL_FIND_ALL + " WHERE "
            + RouteSet.FROM_TOWN_COLUMN + " = ? AND "
            + RouteSet.TO_TOWN_COLUMN  + " = ? AND "
            + RouteSet.DATE_SEND_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + RouteSet.TABLE_NAME
            + "(" + RouteSet.FROM_TOWN_COLUMN
            + "," + RouteSet.TO_TOWN_COLUMN
            + "," + RouteSet.SEND_TIME_COLUMN
            + "," + RouteSet.ARRIVE_TIME_COLUMN
            + "," + RouteSet.PRICE_COLUMN
            + "," + RouteSet.DATE_SEND_COLUMN
            + "," + RouteSet.DATE_ARRIVE_COLUMN
            + "," + RouteSet.TRAIN_NAME_COLUMN
            + "," + RouteSet.ID_ROUTE_COLUMN
            + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SQL_UPDATE = "UPDATE " + RouteSet.TABLE_NAME + " SET "
            + RouteSet.FROM_TOWN_COLUMN + " = ?, "
            + RouteSet.TO_TOWN_COLUMN + " = ?, "
            + RouteSet.SEND_TIME_COLUMN + " = ?, "
            + RouteSet.ARRIVE_TIME_COLUMN + " = ?, "
            + RouteSet.PRICE_COLUMN + " = ?,"
            + RouteSet.ID_ROUTE_COLUMN + " = ?, "
            + RouteSet.DATE_SEND_COLUMN + " = ?,"
            + RouteSet.DATE_ARRIVE_COLUMN + " = ?, "
            + RouteSet.TRAIN_NAME_COLUMN + " = ? WHERE " + RouteSet.ID_COLUMN + " = ?";

    List<RouteSet> findByAll();

    RouteSet findById(Long id);

    RouteSet findByIdRoute(Long idRoute);

    boolean delete(RouteSet routeSet);

    boolean insert(RouteSet routeSet);

    boolean update(RouteSet routeSet);

    List<RouteSet> findByFromToDate(RouteSet RouteSet);


}
