package com.course_project.data_access.dao.ticket_dao;

import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;

import java.util.List;

public interface TicketDao {
    String SQL_FIND_ALL = "SELECT * FROM " + Ticket.TABLE_NAME;
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE " + Ticket.ID_COLUMN + " = ?";
    String SQL_FIND_BY_ID_ROUTE = SQL_FIND_ALL + " WHERE " + Ticket.ID_ROUTE_COLUMN + " = ?";
    String SQL_FIND_BY_FROM_TO_DATE = SQL_FIND_ALL + " WHERE "
            + Ticket.FROM_TOWN_COLUMN + " = ? AND "
            + Ticket.TO_TOWN_COLUMN + " = ? AND "
            + Ticket.DATE_SEND_COLUMN;

    String SQL_DELETE = "DELETE FROM " + Ticket.TABLE_NAME + " WHERE " + Ticket.ID_ROUTE_COLUMN + " = ?";
    String SQL_DELETE_BY_TRAIN_NAME = "DELETE FROM " + Ticket.TABLE_NAME + " WHERE " + Ticket.TRAIN_NAME_COLUMN + " = ?";
    String SQL_INSERT = "INSERT INTO " + Ticket.TABLE_NAME
            + "(" + Ticket.FROM_TOWN_COLUMN
            + "," + Ticket.TO_TOWN_COLUMN
            + "," + Ticket.TIME_END_COLUMN
            + "," + Ticket.TIME_START_COLUMN
            + "," + Ticket.LINEN_COLUMN
            + "," + Ticket.PRICE_COLUMN
            + "," + Ticket.STATUS_COLUMN
            + "," + Ticket.ID_ROUTE_COLUMN
            + "," + Ticket.DATE_SEND_COLUMN
            + "," + Ticket.DATE_ARRIVE_COLUMN
            + "," + Ticket.TRAIN_NAME_COLUMN
            + "," + Ticket.ID_WAGON_COLUMN
            + "," + Ticket.PLACE_COLUMN
            + "," + Ticket.TYPE_PLACE_COLUMN
            + "," + Ticket.CONTACT_COLUMN
            + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SQL_UPDATE = "UPDATE " + Ticket.TABLE_NAME + " SET "
            + Ticket.FROM_TOWN_COLUMN + " = ?, "
            + Ticket.TO_TOWN_COLUMN + " = ?, "
            + Ticket.TIME_START_COLUMN + " = ?, "
            + Ticket.TIME_END_COLUMN + " = ?, "
            + Ticket.LINEN_COLUMN + " = ?, "
            + Ticket.PRICE_COLUMN + " = ?,"
            + Ticket.STATUS_COLUMN + " = ?,"
            + Ticket.ID_ROUTE_COLUMN + " = ?, "
            + Ticket.DATE_SEND_COLUMN + " = ?, "
            + Ticket.DATE_ARRIVE_COLUMN + " = ?,"
            + Ticket.TRAIN_NAME_COLUMN + " = ?,"
            + Ticket.ID_WAGON_COLUMN + " = ?,"
            + Ticket.PLACE_COLUMN + " = ?,"
            + Ticket.TYPE_PLACE_COLUMN + " = ?,"
            + "," + Ticket.CONTACT_COLUMN + " = ?"
            + " WHERE " + Ticket.ID_COLUMN + " = ?";

    List<Ticket> findByAll();

    Ticket findById(Long id);

    boolean delete(Ticket ticket);

    Ticket findByIdRoute(Long idRoute);

    boolean insert(Ticket ticket);

    boolean update(Ticket ticket);

    List<Ticket> findByFromToDate(Ticket ticket);

    boolean deletByTrainName(String trainName);
}
