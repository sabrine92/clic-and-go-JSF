package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Station;

@Remote
public interface StationServicesRemote {

	Boolean addStation(Station station);

	Boolean deleteStationById(Integer Id);
	public Boolean updateStation(Station station);
	public Station findStationById(Integer
			Id);
	public Boolean deleteStation(Station station);
	public List<Station> findAllStations();
	public Station findStationByStationByReference(int ref);
	public Station findStationByStationName(String name);

}
