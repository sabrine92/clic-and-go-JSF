package services.interfaces;

import javax.ejb.Remote;

import entities.Place;

@Remote
public interface PlaceServicesRemote {
	Boolean addPlace(Place place);

	Boolean updatePlace(Place place);

	Boolean deletePlace(Place place);

	Boolean deletePlaceByPlaceId(Integer placeId);

	Place findPlaceByPlaceId(Integer placeId);

	Boolean assignPlaceToStation(Integer placeId, Integer stationId);

}
