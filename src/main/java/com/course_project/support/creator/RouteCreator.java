package com.course_project.support.creator;

import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.data_access.model.train.Train;
import com.course_project.support.AlertGenerator;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.manager.TrainManager;

import java.util.ArrayList;
import java.util.List;

public class RouteCreator {
    private RouteManager routeManager;
    private TrainManager trainManager;

    public static Long idRoute;




    public RouteCreator() {
        routeManager = new RouteManager();
        trainManager = new TrainManager();
    }

    public List<String> getTrainWithoutRoute() {

        List<String> trainListWithoutRoute = new ArrayList<>();
        for (Train train :trainManager.getTrains()) {

            if (train.getIdRoute() == 0) {

               trainListWithoutRoute.add(train.getName());
            }
        }
        return trainListWithoutRoute;
    }

    public void create(RouteSet routeSet) {
        if (routeManager.createRouteSet(routeSet)) {
            AlertGenerator.info("Маршрут успішно створено");
        } else {
            AlertGenerator.error("Виникла помилка при створені маршрута");
        }
    }

    public RouteManager getRouteManager() {
        return routeManager;
    }

    public void generateIdRoute() {

        List<RouteSet> routeSets = routeManager.getRouteSets();
        int lastIndex = routeSets.size();
        if (lastIndex == 0) {
            idRoute = 1l;
        } else {
            idRoute = routeSets.get(lastIndex-1).getIdRoute() + 1;
        }
    }

    public void create(Route route) {
        if (routeManager.createRoute(route)) {
            AlertGenerator.info("Маршрут успішно створено");
        } else {
            AlertGenerator.error("Виникла помилка при створені маршрута");
        }
    }




}
