package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Card;
import entities.Line;
import entities.Ticket;

@Local
public interface TicketsServicesLocal {
	Boolean addTicket(Ticket ticket);

	Boolean updateTicket(Ticket ticket);

	Boolean chooseLineForTicket(Ticket ticket, Line line);

	Boolean payTicket(Ticket ticket, Card card);

	Ticket findTicketById(Integer ticketId);

	List<Ticket> findAllTickets();

	List<Ticket> findAllTicketsByUserId(Integer userId);

	Card authentificateCard(String cardId, Integer pwd);

	Double CheckCardAmount(Card card);

	Double CalculateTotalPrice(Line line, Integer qt);

}
