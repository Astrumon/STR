package com.course_project.support.updater;

import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.support.AlertGenerator;
import com.course_project.support.Checker;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.manager.TicketManager;

public class RouteUpdater {
    private RouteManager routeManager;
    private TicketManager ticketManager;
    private Route route;
    private RouteSet routeSet;
    private TicketUpdater ticketUpdater;


    public RouteManager getRouteManager() {
        return routeManager;
    }

    public RouteUpdater() {
        routeManager = new RouteManager();
        ticketManager = new TicketManager();
        route = new Route();
        routeSet = new RouteSet();
        ticketUpdater = new TicketUpdater();
    }

    public void delete(Route route) {
        if (routeManager.deleteRoute(route) & deleteRouteSet(route.getIdRoute()) & deleteRouteFromTrain(route.getTrainName(), route.getIdRoute()) & deleteTicket(route.getIdRoute())) {

            AlertGenerator.info("Шлях успішно видалений");
        } else {
            AlertGenerator.error("Виникла помилка при видаленні шляху");
        }

    }

    private boolean deleteRouteSet(Long idRoute) {
        return routeManager.deleteRouteSetByIdRoute(idRoute);
    }

    private boolean deleteRouteFromTrain(String nameTrain, Long idRoute) {
        return routeManager.deleteRouteFromTrain(nameTrain, idRoute);
    }

    private boolean deleteTicket(Long idRoute) {
        return ticketUpdater.delete(idRoute);
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public boolean isValidPrice(String currentPrice) {
        if (!Checker.checkEmptyValue(currentPrice)
                && Checker.checkPositiveIntValue(currentPrice)) {
            return true;
        } else {
            AlertGenerator.error("Ціна вказана невірно або цей запис вже існує");
            return false;
        }

    }

    public boolean isValidPoint(String currentValue) {
        if (!Checker.checkEmptyValue(currentValue)
                && Checker.checkStringValue(currentValue)) {
            return true;
        } else {
            AlertGenerator.error("Точка вказана невірно або цей запис вже існує");
            return false;
        }
    }

    public boolean isValidDate(String currentDate) {
        if (!Checker.checkEmptyValue(currentDate)) {
            return true;
        } else {
            AlertGenerator.error("Дата вказана невірно або цей запис вже існує");
            return false;
        }
    }

    public boolean isValidTime(String currentTime) {
        if (!Checker.checkEmptyValue(currentTime)
                && Checker.isValidTime(currentTime)) {
            return true;
        } else {
            AlertGenerator.error("Час вказан невірно");
            return false;
        }
    }







}
