package com.course_project.support.manager;

import com.course_project.data_access.dao.impl.route_dao_impl.RouteDaoImpl;
import com.course_project.data_access.model.route.Route;

import java.util.List;

public class RouteManager extends Manager {
    private RouteDaoImpl routeDao;

    private Route route;

    public RouteManager() {
        routeDao = new RouteDaoImpl(dataSource);
        route = new Route();
    }

    public Route getRoute(Long idRoute) {
        return routeDao.findById(idRoute);
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
}
