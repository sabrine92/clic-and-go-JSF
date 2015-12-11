package services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.LineServicesLocal;
import services.interfaces.LineServicesRemote;
import entities.Line;

/**
 * Session Bean implementation class LineServices
 */
@Stateless
public class LineServices implements LineServicesRemote, LineServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public LineServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean addLine(Line line) {
		Boolean b = false;
		try {
			entityManager.persist(line);
			b = true;
		} catch (Exception e) {
			System.err.println("A problem occured while adding " + line);
		}
		return b;
	}

	@Override
	public List<Line> findAllLines() {
		String jpql = "select s from Line s";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public Line findLineByName(String name) {
		Line found = new Line();
		Query query = entityManager
				.createQuery("select u from Line u where u.name=:name");
		query.setParameter("name", name);
		try {
			found = (Line) query.getSingleResult();
			System.out.println("The line found is:" + found);
			return found;
		} catch (NoResultException e) {

			return null;
		}
	}

}
