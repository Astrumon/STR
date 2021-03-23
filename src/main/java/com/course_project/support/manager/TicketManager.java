package com.course_project.support.manager;

import com.course_project.data_access.dao.impl.TicketDaoImpl;
import com.course_project.data_access.model.Ticket;

import java.util.List;

public class TicketManager extends Manager {
    private TicketDaoImpl ticketDao;
    public static Ticket transfer;

    private Ticket ticket;

    public TicketManager() {
        ticketDao = new TicketDaoImpl(dataSource);
        ticket = new Ticket();
    }

    public Ticket getTicket(Long idTicket) {
        return ticketDao.findById(idTicket);
    }
    public Ticket getTicketByIdRoute(Long idRoute) {
        return ticketDao.findByIdRoute(idRoute);
    }

    public List<Ticket> getTickets() {
        return ticketDao.findByAll();
    }

    public boolean createTicket(Ticket ticket) {
        return ticketDao.insert(ticket);
    }

    public boolean deleteTicket(Ticket ticket) {

        return ticketDao.delete(ticket);
    }

    public boolean updateTicket(Ticket ticket) {
        return ticketDao.update(ticket);
    }
}
