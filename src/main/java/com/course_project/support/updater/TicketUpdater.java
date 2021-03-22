package com.course_project.support.updater;

import com.course_project.data_access.model.Ticket;
import com.course_project.support.manager.TicketManager;

public class TicketUpdater {
    private TicketManager ticketManager;
    private Ticket ticket;

    public TicketUpdater() {
        ticketManager = new TicketManager();
        ticket = new Ticket();
    }

    public boolean delete(Long idRoute) {
        ticket = ticketManager.getTicketByIdRoute(idRoute);
        System.out.println(ticket);
        return ticketManager.deleteTicket(ticket);
    }
}
