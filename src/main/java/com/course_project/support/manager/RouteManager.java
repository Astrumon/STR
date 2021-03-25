package com.course_project.support.manager;

import com.course_project.data_access.dao.impl.route_dao_impl.RouteDaoImpl;
import com.course_project.data_access.dao.impl.route_dao_impl.RouteSetDaoImpl;
import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.data_access.model.train.Train;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс который взаимодействует с DAO маршрутов и точек маршрута
 */
public class RouteManager extends Manager {
    public static Route transfer;
    public static RouteSet transferRouteSet;
    private RouteDaoImpl routeDao;
    private RouteSetDaoImpl routeSetDao;
    private TrainManager trainManager;

    private Route route;

    public RouteManager() {
        routeDao = new RouteDaoImpl(dataSource);
        routeSetDao = new RouteSetDaoImpl(dataSource);
        trainManager = new TrainManager();
        route = new Route();
    }

    public List<RouteSet> getRouteSetsByRouteId(Long idRoute) {
        List<RouteSet> routeSetsByIdRoute = new ArrayList<>();
        for (RouteSet routeSet : getRouteSets()) {
            if (routeSet.getIdRoute().equals(idRoute)) {
                routeSetsByIdRoute.add(routeSet);
            }
        }
        return routeSetsByIdRoute;
    }

    public Route getRoute(Long idRoute) {
        return routeDao.findById(idRoute);
    }

    public RouteSet getRouteSet(Long idRoute) {
        return routeSetDao.findByIdRoute(idRoute);
    }

    public List<RouteSet> getRouteSets() {
        return routeSetDao.findByAll();
    }

    public boolean createRouteSet(RouteSet routeSet) {
        return routeSetDao.insert(routeSet);
    }

    public List<Route> getRoutes() {
        return routeDao.findByAll();
    }

    public boolean createRoute(Route route) {
        return routeDao.insert(route);
    }

    public boolean deleteRoute(Route route) {
        return routeDao.delete(route);
    }

    public boolean deleteRouteSetByIdRoute(Long idRoute) {
        boolean result = false;
        for (RouteSet routeSet : getRouteSetsByRouteId(idRoute + 1)) {
            result = routeSetDao.delete(routeSet);
        }
        return result;
    }

    public boolean deleteRouteFromTrain(String nameTrain, Long idRoute) {
        boolean result = false;
        Train train = trainManager.getTrain(nameTrain);

        if (train.getIdRoute().equals(idRoute + 1)) {
            train.setIdRoute(null);
            result = trainManager.updateIdRoute(train);
        }
        return result;
    }

    public boolean updateRouteSet(RouteSet routeSet) {
      return routeSetDao.update(routeSet);
    }

    public boolean updateRoute(Route route) {
       return routeDao.update(route);
    }

    public List<RouteSet> getRouteSetsByFromToDate(RouteSet routeSet) {
        return routeSetDao.findByFromToDate(routeSet);
    }
}
