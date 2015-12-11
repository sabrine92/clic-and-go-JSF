package services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Ticket;
import services.interfaces.TicketsServicesLocal;
import services.interfaces.TicketsServicesRemote;

/**
 * Session Bean implementation class TicketsServices
 */
@Stateless
public class TicketsServices implements TicketsServicesRemote, TicketsServicesLocal {

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
	public Boolean updateTicket(Ticket ticket) {
		
		Boolean b = false;
		try {
			entityManager.merge(ticket);
			b = true;
		} catch (Exception e) {
			System.err.println("A problem occured while updating "
					+ ticket);
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

}
