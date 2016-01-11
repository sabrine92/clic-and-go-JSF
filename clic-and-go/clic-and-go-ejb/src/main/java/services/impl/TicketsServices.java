package services.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.TicketsServicesLocal;
import services.interfaces.TicketsServicesRemote;
import entities.Card;
import entities.Ebook;
import entities.Line;
import entities.Ticket;

/**
 * Session Bean implementation class TicketsServices
 */
@Stateless
public class TicketsServices implements TicketsServicesRemote,
		TicketsServicesLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	public TicketsServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean addTicket(Ticket ticket) {

		Boolean b = false;
		try {
			entityManager.merge(ticket);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean addCard(Card card) {
		Boolean b = false;
		try {
			entityManager.merge(card);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}
	@Override
	public Boolean updateTicket(Ticket ticket) {

		Boolean b = false;
		try {
			entityManager.merge(ticket);
			b = true;
		} catch (Exception e) {
			System.err.println("A problem occured while updating " + ticket);
		}
		return b;
	}

	@Override
	public Ticket findTicketById(Integer ticketId) {
		try {
			return entityManager.find(Ticket.class, ticketId);
		} catch (Exception e) {
			System.err
					.println("A problem occured while trying to find ticket by  "
							+ ticketId);

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findAllTickets() {
		String jpql = "select t from Ticket t";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findAllTicketsByUserId(Integer userId) {
		String jpql = "select t from Ticket t where t.user.id=:param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", userId);
		return query.getResultList();
	}

	@Override
	public Boolean payTicket(Ticket ticket, Card card) {
		Boolean b = false;
		try {
			if (ticket.getPrice() < card.getAmount()) {
				card.setAmount(card.getAmount() - ticket.getPrice());
				b = true;
			} else {
				System.out.println("vous devez recharger votre carte");
				b = false;
			}
			entityManager.merge(ticket);
			entityManager.merge(card);

		} catch (Exception e) {
			System.out.println("erreur");
		}
		return b;
	}

	@Override
	public Boolean chooseLineForTicket(Ticket ticket, Line line) {
		Boolean b = false;
		try {
			ticket.setPrice(line.getPrice());
			entityManager.merge(ticket);
			b = true;
		} catch (Exception e) {
			System.out.println("A problem occured while updating " + ticket);
		}
		return b;
	}

	@Override
	public Card authentificateCard(String cardId, Integer pwd) {
		Card found = new Card();
		Query query = entityManager
				.createQuery("select c from Card c where c.cardId=:cardId and c.pwd=:pwd");
		query.setParameter("cardId", cardId);
		query.setParameter("pwd", pwd);
		try {
			found = (Card) query.getSingleResult();
			System.out.println("The authentified card is:" + found);
			return found;
		} catch (NoResultException e) {
			Logger.getLogger(getClass().getName()).log(
					Level.WARNING,
					"auth attempt failed with cardId=" + cardId
							+ " and password=" + pwd);
			return found;
		}
	}

	@Override
	public Double CheckCardAmount(Card card) {
		Double b = 0D;
		try {

			b = card.getAmount();
		} catch (Exception e) {
			System.out.println("error Amount card");
		}
		return b;
	}

	@Override
	public Double CalculateTotalPrice(Line line, Integer qt) {

		Double b = 0D;
		try {

			b = line.getPrice() * qt;
		} catch (Exception e) {
			System.out.println("error calculate Total Price");
		}
		return b;
	}

}
