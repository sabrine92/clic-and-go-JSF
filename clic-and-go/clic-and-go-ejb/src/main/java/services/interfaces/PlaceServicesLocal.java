package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Place;

@Local
public interface PlaceServicesLocal {

	Boolean addPlace(Place place);

	Boolean updatePlace(Place place);

	Boolean deletePlace(Place place);

	Boolean deletePlaceByPlaceId(Integer placeId);

	Place findPlaceByPlaceId(Integer placeId);

	Boolean assignPlaceToStation(Integer placeId, Integer stationId);

	List<Place> findAllPlaces();

	List<Place> findPlacesByDestination(Integer stationId);

	Boolean ratePlace(Integer placeId, Integer rating);

	Place findPlaceByPlaceName(String name);

}
