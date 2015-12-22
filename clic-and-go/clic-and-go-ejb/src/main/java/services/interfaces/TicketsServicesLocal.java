package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Ticket;

@Local
public interface TicketsServicesLocal {
	Boolean addTicket(Ticket ticket);

	Boolean updateTicket(Ticket ticket);

	Ticket findTicketById(Integer ticketId);

	List<Ticket> findAllTickets();

	List<Ticket> findAllTicketsByUserId(Integer userId);
}
