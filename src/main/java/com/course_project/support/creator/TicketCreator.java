package com.course_project.support.creator;

import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.manager.TicketManager;

public class TicketCreator {
    private TicketManager ticketManager;
    private RouteManager routeManager;

    private Ticket ticket;

    public TicketCreator() {
        ticketManager = new TicketManager();
        routeManager = new RouteManager();
        ticket = new Ticket();
    }

    public boolean createTicket(Ticket ticket) {
        return ticketManager.createTicket(ticket);
    }

    public TicketManager getTicketManager() {
        return ticketManager;
    }

    public Ticket fillTicket(Route route) {
        System.out.println("ROUTETEST: " + route);
        RouteSet routeSet = routeManager.getRouteSetsByRouteId(route.getIdRoute()+1).get(0);
        ticket.setIdRoute(route.getIdRoute());
        ticket.setStatus(Ticket.STATUS_FREE);
        ticket.setLinen(false);
        ticket.setFromTown(route.getFromTown());
        ticket.setToTown(route.getToTown());
        ticket.setIdTicket(route.getIdRoute());
        ticket.setTimeStart(route.getTimeStart());
        ticket.setTimeEnd(route.getTimeEnd());
        ticket.setPrice(route.getPrice());
        ticket.setDateSend(routeSet.getDateSend());
        ticket.setDateArrive(routeSet.getDateArrive());
        return ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
