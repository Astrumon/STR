package com.course_project.support;

import com.course_project.data_access.dao.impl.TicketDaoImpl;
import com.course_project.data_access.dao.impl.route_dao_impl.RouteDaoImpl;
import com.course_project.data_access.model.Ticket;
import com.course_project.data_access.model.route.Route;

import java.util.List;

public class TicketManager extends Manager {
    private TicketDaoImpl ticketDao;

    private Ticket ticket;

    public TicketManager() {
        ticketDao = new TicketDaoImpl(dataSource);
        ticket = new Ticket();
    }

    public Ticket getTicket(Long idTicket) {
        return ticketDao.findById(idTicket);
    }

    public List<Ticket> getTickets() {
        return ticketDao.findByAll();
    }

    public boolean createRoute(Ticket ticket) {
        return ticketDao.insert(ticket);
    }

    public boolean deleteRoute(Ticket ticket) {

        return ticketDao.delete(ticket);
    }
}
