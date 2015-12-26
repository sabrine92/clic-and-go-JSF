package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Card;
import entities.Ticket;

@Remote
public interface TicketsServicesRemote {
	Boolean addTicket(Ticket ticket);

	Boolean updateTicket(Ticket ticket);

	Boolean payTicket(Ticket ticket, Card card);

	Ticket findTicketById(Integer ticketId);

	List<Ticket> findAllTickets();

	List<Ticket> findAllTicketsByUserId(Integer userId);
}
