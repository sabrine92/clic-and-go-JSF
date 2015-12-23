package services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
