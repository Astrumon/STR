package com.course_project.support.creator;

import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.data_access.model.train.Train;
import com.course_project.support.AlertGenerator;
import com.course_project.support.Checker;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.manager.TrainManager;

import java.util.ArrayList;
import java.util.List;

public class RouteCreator {
    public static Long idRoute;
    private RouteManager routeManager;
    private TrainManager trainManager;


    private RouteSet routeSet;
    private TicketCreator ticketCreator;

    public RouteCreator() {
        routeManager = new RouteManager();
        trainManager = new TrainManager();
        ticketCreator = new TicketCreator();
        routeSet = new RouteSet();
    }

    public RouteSet getRouteSet() {
        return routeSet;
    }

    public void setRouteSet(RouteSet routeSet) {
        this.routeSet = routeSet;
    }

    public TrainManager getTrainManager() {
        return trainManager;
    }

    public List<String> getTrainWithoutRoute() {
        List<String> trainListWithoutRoute = new ArrayList<>();
        for (Train train : trainManager.getTrains()) {

            if (train.getIdRoute() == 0 && train.getType() == Train.PASSENGER_TYPE) {
                trainListWithoutRoute.add(train.getName());
            }
        }
        return trainListWithoutRoute;
    }

    public void create(RouteSet routeSet) {
        if (!routeManager.createRouteSet(routeSet)) {
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
            idRoute = routeSets.get(lastIndex - 1).getIdRoute() + 1;
        }
    }

    public void create(Route route) {
        if (routeManager.createRoute(route)) {
            ticketCreator.createTicket(ticketCreator.fillTicket(route));
            AlertGenerator.info("Маршрут успішно створено");
        } else {
            AlertGenerator.error("Виникла помилка при створені маршрута");
        }
    }

    public RouteSet getFillRouteSet(String trainName, int price) {
        routeSet.setIdRoute(RouteCreator.idRoute);

        routeSet.setPrice(price);
        routeSet.setTrainName(trainName);
        return routeSet;
    }

    public void setIdRouteToTrain(String name) {
        Train train = trainManager.getTrain(name);
        train.setIdRoute(RouteCreator.idRoute);
        trainManager.updateIdRoute(train);
    }

    public Route getFillRoute(String trainName) {
        Route route = new Route();
        route.setTrainName(trainName);
        route.setIdRoute(RouteCreator.idRoute - 1);
        int count = 0;

        int price = 0;
        for (RouteSet routeSet : routeManager.getRouteSets()) {
            System.out.println(routeSet);
            if (routeSet.getIdRoute().equals(RouteCreator.idRoute)) {
                count++;
                price += routeSet.getPrice();

                if (count == 1) {
                    route.setFromTown(routeSet.getFromTown());
                    route.setTimeStart(routeSet.getSendTime());
                }

                if (count == getLastIndexIdRoute()) {
                    route.setToTown(routeSet.getToTown());
                    route.setTimeEnd(routeSet.getArriveTime());
                }
            }

        }
        route.setPrice(price);
        return route;
    }

    private int getLastIndexIdRoute() {
        int last = 0;
        for (RouteSet routeSet1 : routeManager.getRouteSets()) {
            if (routeSet1.getIdRoute().equals(RouteCreator.idRoute)) {
                last++;
            }
        }
        return last;
    }

    public boolean isValidPoint(String currentValue) {
        if (!Checker.checkEmptyValue(currentValue)
                && Checker.checkStringValue(currentValue)) {
            return true;
        } else {
            AlertGenerator.error("Точка вказана невірно");
            return false;
        }
    }

    public boolean isValidDate(String currentDate) {
        System.out.println("CURRENTDATE: " + currentDate);
        if (!Checker.checkEmptyValue(currentDate)) {
            return true;
        } else {
            AlertGenerator.error("Дата вказана невірно");
            return false;
        }
    }

    public boolean isValidTime(String currentDate) {
        if (!Checker.checkEmptyValue(currentDate) && Checker.isValidTime(currentDate)) {
            return true;
        } else {
            AlertGenerator.error("Час вказан невірно");
            return false;
        }
    }

    public boolean isValidPrice(String price) {
        if (!Checker.checkEmptyValue(price) && Checker.checkPositiveIntValue(price)) {
            return true;
        } else {
            AlertGenerator.error("Час вказан невірно");
            return false;
        }
    }


}
