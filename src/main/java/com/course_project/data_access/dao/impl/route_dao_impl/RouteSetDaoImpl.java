package com.course_project.data_access.dao.impl.route_dao_impl;

import com.course_project.data_access.dao.route_dao.RouteSetDao;
import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.database.DataSource;
import com.course_project.support.manager.RouteManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteSetDaoImpl implements RouteSetDao {


    private DataSource dataSource;

    public RouteSetDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<RouteSet> findByAll() {
        Connection connection = null;
        List<RouteSet> routeSets = new ArrayList<RouteSet>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RouteSet routeSet = new RouteSet();
                routeSet.setId(resultSet.getLong(RouteSet.ID_COLUMN));
                routeSet.setFromTown(resultSet.getString(RouteSet.FROM_TOWN_COLUMN));
                routeSet.setToTown(resultSet.getString(RouteSet.TO_TOWN_COLUMN));
                routeSet.setIdRoute(resultSet.getLong(RouteSet.ID_ROUTE_COLUMN));
                routeSet.setSendTime(resultSet.getString(RouteSet.SEND_TIME_COLUMN));
                routeSet.setArriveTime(resultSet.getString(RouteSet.ARRIVE_TIME_COLUMN));
                routeSet.setPrice(resultSet.getInt(RouteSet.PRICE_COLUMN));
                routeSet.setTrainName(resultSet.getString(RouteSet.TRAIN_NAME_COLUMN));
                routeSet.setDateSend(resultSet.getString(RouteSet.DATE_SEND_COLUMN));
                routeSet.setDateArrive(resultSet.getString(RouteSet.DATE_ARRIVE_COLUMN));
                routeSets.add(routeSet);
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
        return routeSets;
    }

    @Override
    public RouteSet findById(Long id) {
        Connection connection = null;
        RouteSet routeSet = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routeSet = new RouteSet();
                routeSet.setId(resultSet.getLong(RouteSet.ID_COLUMN));
                routeSet.setFromTown(resultSet.getString(RouteSet.FROM_TOWN_COLUMN));
                routeSet.setToTown(resultSet.getString(RouteSet.TO_TOWN_COLUMN));
                routeSet.setIdRoute(resultSet.getLong(RouteSet.ID_ROUTE_COLUMN));
                routeSet.setSendTime(resultSet.getString(RouteSet.SEND_TIME_COLUMN));
                routeSet.setArriveTime(resultSet.getString(RouteSet.ARRIVE_TIME_COLUMN));
                routeSet.setPrice(resultSet.getInt(RouteSet.PRICE_COLUMN));
                routeSet.setTrainName(resultSet.getString(RouteSet.TRAIN_NAME_COLUMN));
                routeSet.setDateSend(resultSet.getString(RouteSet.DATE_SEND_COLUMN));
                routeSet.setDateArrive(resultSet.getString(RouteSet.DATE_ARRIVE_COLUMN));
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
        return routeSet;
    }

    @Override
    public RouteSet findByIdRoute(Long idRoute) {
        Connection connection = null;
        RouteSet routeSet = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_ROUTE);
            preparedStatement.setLong(1, idRoute);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                routeSet = new RouteSet();
                routeSet.setId(resultSet.getLong(RouteSet.ID_COLUMN));
                routeSet.setFromTown(resultSet.getString(RouteSet.FROM_TOWN_COLUMN));
                routeSet.setToTown(resultSet.getString(RouteSet.TO_TOWN_COLUMN));
                routeSet.setIdRoute(resultSet.getLong(RouteSet.ID_ROUTE_COLUMN));
                routeSet.setSendTime(resultSet.getString(RouteSet.SEND_TIME_COLUMN));
                routeSet.setArriveTime(resultSet.getString(RouteSet.ARRIVE_TIME_COLUMN));
                routeSet.setPrice(resultSet.getInt(RouteSet.PRICE_COLUMN));
                routeSet.setTrainName(resultSet.getString(RouteSet.TRAIN_NAME_COLUMN));
                routeSet.setDateSend(resultSet.getString(RouteSet.DATE_SEND_COLUMN));
                routeSet.setDateArrive(resultSet.getString(RouteSet.DATE_ARRIVE_COLUMN));
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
        return routeSet;
    }

    @Override
    public boolean delete(RouteSet routeSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, routeSet.getIdRoute());
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
    public boolean insert(RouteSet routeSet) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, routeSet.getFromTown());
            preparedStatement.setString(2, routeSet.getToTown());
            preparedStatement.setString(3, routeSet.getSendTime());
            preparedStatement.setString(4, routeSet.getArriveTime());
            preparedStatement.setInt(5, routeSet.getPrice());
            preparedStatement.setString(6, routeSet.getDateSend());
            preparedStatement.setString(7, routeSet.getDateArrive());
            preparedStatement.setString(8, routeSet.getTrainName());
            preparedStatement.setLong(9, routeSet.getIdRoute());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                routeSet.setId(resultSet.getLong(1));
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
    public boolean update(RouteSet routeSet) {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, routeSet.getFromTown());
            preparedStatement.setString(2, routeSet.getToTown());
            preparedStatement.setString(3, routeSet.getSendTime());
            preparedStatement.setString(4, routeSet.getArriveTime());
            preparedStatement.setInt(5, routeSet.getPrice());
            preparedStatement.setLong(6, routeSet.getIdRoute());
            preparedStatement.setString(7, routeSet.getDateSend());
            preparedStatement.setString(8, routeSet.getDateArrive());
            preparedStatement.setString(9, routeSet.getTrainName());
            preparedStatement.setLong(10, routeSet.getId());
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
    public List<RouteSet> findByFromToDate(RouteSet routeSet) {
        Connection connection = null;
        List<RouteSet> routeSets = new ArrayList<RouteSet>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_FROM_TO_DATE);
            preparedStatement.setString(1, routeSet.getFromTown());
            preparedStatement.setString(2, routeSet.getToTown());
            preparedStatement.setString(3, routeSet.getDateSend());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RouteSet fillRouteSet = new RouteSet();
                fillRouteSet.setId(resultSet.getLong(RouteSet.ID_COLUMN));
                fillRouteSet.setFromTown(resultSet.getString(RouteSet.FROM_TOWN_COLUMN));
                fillRouteSet.setToTown(resultSet.getString(RouteSet.TO_TOWN_COLUMN));
                fillRouteSet.setIdRoute(resultSet.getLong(RouteSet.ID_ROUTE_COLUMN));
                fillRouteSet.setSendTime(resultSet.getString(RouteSet.SEND_TIME_COLUMN));
                fillRouteSet.setArriveTime(resultSet.getString(RouteSet.ARRIVE_TIME_COLUMN));
                fillRouteSet.setPrice(resultSet.getInt(RouteSet.PRICE_COLUMN));
                fillRouteSet.setTrainName(resultSet.getString(RouteSet.TRAIN_NAME_COLUMN));
                fillRouteSet.setDateSend(resultSet.getString(RouteSet.DATE_SEND_COLUMN));
                fillRouteSet.setDateArrive(resultSet.getString(RouteSet.DATE_ARRIVE_COLUMN));
                routeSets.add(fillRouteSet);
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
        return routeSets;
    }




}
