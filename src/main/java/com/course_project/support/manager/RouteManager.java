package com.course_project.support.manager;

import com.course_project.data_access.dao.impl.route_dao_impl.RouteDaoImpl;
import com.course_project.data_access.dao.impl.route_dao_impl.RouteSetDaoImpl;
import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;

import java.util.List;

public class RouteManager extends Manager {
    public static Route transfer;
    private RouteDaoImpl routeDao;
    private RouteSetDaoImpl routeSetDao;

    private Route route;

    public RouteManager() {
        routeDao = new RouteDaoImpl(dataSource);
        routeSetDao = new RouteSetDaoImpl(dataSource);
        route = new Route();
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
}
