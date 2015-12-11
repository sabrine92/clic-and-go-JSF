package services.interfaces;

import javax.ejb.Local;

@Local
public interface StationServicesLocal {

	Boolean deleteStationById(Integer Id);

}
