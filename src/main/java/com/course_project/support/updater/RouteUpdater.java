package com.course_project.support.updater;

import com.course_project.data_access.model.Ticket;
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


    public RouteUpdater() {
        routeManager = new RouteManager();
        ticketManager = new TicketManager();
        route = new Route();
        routeSet = new RouteSet();
        ticketUpdater = new TicketUpdater();
    }

    public RouteManager getRouteManager() {
        return routeManager;
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
            AlertGenerator.error("Ціна вказана невірно");
            return false;
        }

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
        if (!Checker.checkEmptyValue(currentDate)) {
            return true;
        } else {
            AlertGenerator.error("Дата вказана невірно");
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

    public void updateRouteSet(RouteSet routeSet) {

        if (!routeManager.updateRouteSet(routeSet)) {
            AlertGenerator.error("Виникла помилка при зміні даних");
        } else {

            Route route = getUpdatedRoute(routeSet);
            if (routeManager.updateRoute(route)) {
                Ticket ticket = ticketManager.getTicketByIdRoute(routeSet.getIdRoute()-1);
                ticket.setFromTown(route.getFromTown());
                ticket.setToTown(route.getToTown());
                ticket.setPrice(route.getPrice());
                ticket.setTimeStart(route.getTimeStart());
                ticket.setTimeEnd(route.getTimeEnd());
                ticket.setDateSend(routeSet.getDateSend());
                ticket.setDateArrive(routeSet.getDateArrive());
                System.out.println("TICKET " + ticket);
                if (ticketManager.updateTicket(ticket)) {
                    AlertGenerator.info("Зміни успішно внесені");
                }
            }
        }
//            AlertGenerator.info("Зміни в маршруті успішно внесені");

//            AlertGenerator.error("Виникла помилка при зміні даних");

    }

    private Route getUpdatedRoute(RouteSet routeSet) {
        Route route = new Route();
        route.setTrainName(routeSet.getTrainName());
        route.setIdRoute(routeSet.getIdRoute() - 1);
        int count = 0;

        int price = 0;
        for (RouteSet value : routeManager.getRouteSetsByRouteId(routeSet.getIdRoute())) {
            System.out.println("TEST routeset " + value);
            count++;
            price += routeSet.getPrice();

            if (count == 1) {
                route.setFromTown(value.getFromTown());
                route.setTimeStart(value.getSendTime());
            }

            if (count == routeManager.getRouteSetsByRouteId(routeSet.getIdRoute()).size()) {
                System.out.println("SIZE: count= " + count + " index= " + value);
                route.setToTown(value.getToTown());
                route.setTimeEnd(value.getArriveTime());
            }
        }

        route.setPrice(price);
        return route;
    }


}
