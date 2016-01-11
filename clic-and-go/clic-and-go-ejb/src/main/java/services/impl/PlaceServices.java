package services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.PlaceServicesLocal;
import services.interfaces.PlaceServicesRemote;
import entities.Place;
import entities.Station;

/**
 * Session Bean implementation class PlaceServices
 */
@Stateless
public class PlaceServices implements PlaceServicesRemote, PlaceServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */

	public PlaceServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean addPlace(Place place) {
		Boolean b = false;
		try {
			System.out.println(place);
			entityManager.persist(place);
			b = true;
			System.out.println(b);

		} catch (Exception e) {
		}
		System.out.println(b);
		return b;
	}

	@Override
	public Boolean deletePlaceByPlaceId(Integer placeId) {
		Boolean b = false;
		try {
			entityManager.remove(findPlaceByPlaceId(placeId));
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean updatePlace(Place place) {
		Boolean b = false;
		try {
			entityManager.merge(place);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Place findPlaceByPlaceId(Integer placeId) {
		return entityManager.find(Place.class, placeId);
	}

	@Override
	public Boolean deletePlace(Place place) {
		Boolean b = false;
		try {
			place = findPlaceByPlaceId(place.getPlaceId());
			entityManager.remove(place);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Boolean assignPlaceToStation(Integer placeId, Integer stationId) {
		Boolean b = false;

		try {
			Station station = entityManager.find(Station.class, stationId);
			Place place = entityManager.find(Place.class, placeId);
			place.setStation(station);
			entityManager.merge(place);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public List<Place> findAllPlaces() {
		return entityManager.createQuery("select u from Place u")
				.getResultList();
	}

	@Override
	public List<Place> findPlacesByDestination(Integer stationId) {
		try {
			String jpql = "select m from Place m where m.station.stationId=:param";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param", stationId);
			return query.getResultList();

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Boolean ratePlace(Integer placeId, Integer rating) {
		Boolean b = false;
		try {
			Place place = findPlaceByPlaceId(placeId);
			Integer currentRating = place.getRating();
			Integer currentNbRaters = place.getNbRaters();
			Integer currentRate=place.getRate();
			Integer newRating;
			Integer newNbRaters;
			Integer newRate;
			if ((currentRating != null) && (currentNbRaters != null)) {
				newNbRaters = currentNbRaters + 1;
				newRating = currentRating+rating;
				newRate=(int)(newRating/newNbRaters);
				
				System.out.println("old rate= " + currentRating + " new rate= "
						+ newRating);
				System.out.println("old nbR= " + currentNbRaters
						+ " new nbRaters= " + newNbRaters);
				System.out.println("old rate= " + currentRate + " new rate= "
						+ newRate);
			} else {
				newRating = rating;
				newNbRaters = 1;
				newRate=(int)(newRating/newNbRaters);
			}
			place.setRating(newRating);
			place.setNbRaters(newNbRaters);
			place.setRating(newRate);
			entityManager.merge(place);
			b = true;
		} catch (Exception e) {
		}
		return b;
	}

	@Override
	public Place findPlaceByPlaceName(String name) {
		String jpql = "select s from Place s where s.name LIKE :param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", '%' + name + '%');
		System.out.println((Place) query.getSingleResult());
		return (Place) query.getSingleResult();
	}

}
