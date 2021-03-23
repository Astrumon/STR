package com.course_project.data_access.dao.impl;

import com.course_project.data_access.dao.ticket_dao.TicketDao;
import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;
import com.course_project.database.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.course_project.data_access.dao.route_dao.RouteSetDao.SQL_FIND_BY_ID_ROUTE;

public class TicketDaoImpl implements TicketDao {
    private DataSource dataSource;

    public TicketDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Ticket> findByAll() {
        Connection connection = null;
        List<Ticket> tickets = new ArrayList<Ticket>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong(Ticket.ID_COLUMN));
                ticket.setFromTown(resultSet.getString(Ticket.FROM_TOWN_COLUMN));
                ticket.setToTown(resultSet.getString(Ticket.TO_TOWN_COLUMN));
                ticket.setIdTicket(resultSet.getLong(Ticket.ID_TICKET_COLUMN));
                ticket.setTimeStart(resultSet.getString(Ticket.TIME_START_COLUMN));
                ticket.setTimeEnd(resultSet.getString(Ticket.TIME_END_COLUMN));
                ticket.setLinen(resultSet.getBoolean(Ticket.LINEN_COLUMN));
                ticket.setPrice(resultSet.getInt(Ticket.PRICE_COLUMN));
                ticket.setStatus(resultSet.getInt(Ticket.STATUS_COLUMN));
                ticket.setIdRoute(resultSet.getLong(Ticket.ID_ROUTE_COLUMN));
                tickets.add(ticket);
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
        return tickets;
    }

    @Override
    public Ticket findById(Long id) {
        Connection connection = null;
        Ticket ticket = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ticket = new Ticket();
                ticket.setId(resultSet.getLong(Ticket.ID_COLUMN));
                ticket.setFromTown(resultSet.getString(Ticket.FROM_TOWN_COLUMN));
                ticket.setToTown(resultSet.getString(Ticket.TO_TOWN_COLUMN));
                ticket.setIdTicket(resultSet.getLong(Ticket.ID_TICKET_COLUMN));
                ticket.setTimeStart(resultSet.getString(Ticket.TIME_START_COLUMN));
                ticket.setTimeEnd(resultSet.getString(Ticket.TIME_END_COLUMN));
                ticket.setLinen(resultSet.getBoolean(Ticket.LINEN_COLUMN));
                ticket.setPrice(resultSet.getInt(Ticket.PRICE_COLUMN));
                ticket.setStatus(resultSet.getInt(Ticket.STATUS_COLUMN));
                ticket.setIdRoute(resultSet.getLong(Ticket.ID_ROUTE_COLUMN));
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
        return ticket;
    }

    @Override
    public Ticket findByIdRoute(Long idRoute) {
        Connection connection = null;
        Ticket ticket = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_ROUTE);
            preparedStatement.setLong(1, idRoute);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ticket = new Ticket();
                ticket.setId(resultSet.getLong(Ticket.ID_COLUMN));
                ticket.setFromTown(resultSet.getString(Ticket.FROM_TOWN_COLUMN));
                ticket.setToTown(resultSet.getString(Ticket.TO_TOWN_COLUMN));
                ticket.setIdTicket(resultSet.getLong(Ticket.ID_TICKET_COLUMN));
                ticket.setTimeStart(resultSet.getString(Ticket.TIME_START_COLUMN));
                ticket.setTimeEnd(resultSet.getString(Ticket.TIME_END_COLUMN));
                ticket.setLinen(resultSet.getBoolean(Ticket.LINEN_COLUMN));
                ticket.setPrice(resultSet.getInt(Ticket.PRICE_COLUMN));
                ticket.setStatus(resultSet.getInt(Ticket.STATUS_COLUMN));
                ticket.setIdRoute(resultSet.getLong(Ticket.ID_ROUTE_COLUMN));
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
        return ticket;
    }

    @Override
    public boolean delete(Ticket ticket) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, ticket.getIdRoute());
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
    public boolean insert(Ticket ticket) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ticket.getFromTown());
            preparedStatement.setString(2, ticket.getToTown());
            preparedStatement.setString(3, ticket.getTimeEnd());
            preparedStatement.setString(4, ticket.getTimeStart());
            preparedStatement.setBoolean(5, ticket.isLinen());
            preparedStatement.setInt(6, ticket.getPrice());
            preparedStatement.setInt(7, ticket.getStatus());
            preparedStatement.setLong(8, ticket.getIdRoute());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                ticket.setId(resultSet.getLong(1));
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
    public boolean update(Ticket ticket) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, ticket.getFromTown());
            preparedStatement.setString(2, ticket.getToTown());
            preparedStatement.setString(3, ticket.getTimeStart());
            preparedStatement.setString(4, ticket.getTimeEnd());
            preparedStatement.setBoolean(5, ticket.isLinen());
            preparedStatement.setInt(6, ticket.getPrice());
            preparedStatement.setInt(7, ticket.getStatus());
            preparedStatement.setLong(8, ticket.getIdRoute());
            preparedStatement.setLong(9, ticket.getId());
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
