package com.course_project.support.creator;

import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;
import com.course_project.data_access.model.route.RouteSet;
import com.course_project.support.manager.RouteManager;
import com.course_project.support.manager.TicketManager;
/**
 * Класс который реализует всю логику создания билетов
 *
 */
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

    public Ticket getTicket() {
        return ticket;
    }
}
