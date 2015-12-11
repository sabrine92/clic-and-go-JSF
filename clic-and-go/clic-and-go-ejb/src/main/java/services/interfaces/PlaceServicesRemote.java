package services.interfaces;

import javax.ejb.Remote;

import entities.Place;

@Remote
public interface PlaceServicesRemote {
	Boolean addPlace(Place place);

	Boolean deletePlaceByPlaceId(Integer placeId);

	Boolean updatePlace(Place place);

	Place findPlaceByPlaceId(Integer placeId);

	Boolean deletePlace(Place place);
	
	Boolean assignPlaceToStation(Place place, Integer stationId);

}
