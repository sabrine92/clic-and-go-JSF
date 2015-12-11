package services.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.UserServicesLocal;
import services.interfaces.UserServicesRemote;
import entities.User;

/**
 * Session Bean implementation class UserServices
 */
@Stateless
public class UserServices implements UserServicesRemote, UserServicesLocal {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UserServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean addUser(User u) {
		Boolean b = false;
		try {
			entityManager.persist(u);
			b = true;
		} catch (Exception e) {
			System.err.println("A problem occured while adding " + u);
		}
		return b;
	}

	@Override
	public Boolean deleteUserById(int id) {
		Boolean b = false;
		try {
			entityManager.remove(findUserById(id));
			b = true;
		} catch (Exception e) {
			System.err.println("A problem occured while deleting MoT number"
					+ id);
		}
		return b;
	}

	@Override
	public User updateUser(int id) {
		User u = findUserById(id);
		try {

			entityManager.merge(u);

		} catch (Exception e) {
			System.err.println("A problem occured while updating " + u);
		}
		return u;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		String jpql = "select u from User u";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public User findUserById(int id) {
		try {
			return entityManager.find(User.class, id);
		} catch (Exception e) {
			System.err
					.println("A problem occured while trying to find MoT by  "
							+ id);

		}
		return null;

	}

	@Override
	public User authenticate(String name, String password) {

		User found = new User();
		Query query = entityManager
				.createQuery("select u from User u where u.name=:name and u.password=:password");
		query.setParameter("name", name);
		query.setParameter("password", password);
		try {
			found = (User) query.getSingleResult();
			System.out.println("The authentified user is:" + found);
			return found;
		} catch (NoResultException e) {
			Logger.getLogger(getClass().getName()).log(
					Level.WARNING,
					"auth attempt failed with login=" + name + " and password="
							+ password);
			return null;
		}

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByName(String name) {
		Query query = entityManager.createQuery("select p from User p where UPPER(p.name) LIKE:pname");
		query.setParameter("pname", "%" + name + "%");
return query.getResultList();
	}

	@Override
	public User updateReadingSpeed(int id,int speed) {
		User user = entityManager.find(User.class, id);
		try {
			user.setNbOfWordsPerMinute(speed);
			entityManager.merge(user);

		} catch (Exception e) {
			System.err.println("A problem occured while updating " + user);
		}
		return user;
	}
}