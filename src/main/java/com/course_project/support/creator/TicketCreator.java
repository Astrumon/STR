package com.course_project.support.creator;

import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;
import com.course_project.support.manager.TicketManager;

public class TicketCreator {
    private TicketManager ticketManager;
    private Ticket ticket;

    public TicketCreator() {
        ticketManager = new TicketManager();
        ticket = new Ticket();
    }

    public boolean createTicket(Ticket ticket) {
        return ticketManager.createTicket(ticket);
    }

    public TicketManager getTicketManager() {
        return ticketManager;
    }

    public void fillTicket(Route route) {
        System.out.println("ROUTETEST: " + route);
        ticket.setIdRoute(route.getId());
        ticket.setStatus(Ticket.STATUS_FREE);
        ticket.setLinen(false);
        ticket.setFromTown(route.getFromTown());
        ticket.setToTown(route.getToTown());
        ticket.setIdTicket(route.getIdRoute());
        ticket.setTimeStart(route.getTimeStart());
        ticket.setTimeEnd(route.getTimeEnd());
        ticket.setPrice(route.getPrice());
    }

    public Ticket getTicket() {
        return ticket;
    }
}
