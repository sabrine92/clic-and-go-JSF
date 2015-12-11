package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Line;
import entities.Station;
import entities.StationLine;

@Remote
public interface StationLineManagementRemote {
	public Boolean assignNewStationToLine(Station newStation, Integer lineId,
			Integer position, Integer duration, Integer distance);

	public Boolean assignStationToLine(Integer stationId, Integer lineId,
			Integer position, Integer duration, Integer distance);

	public Station findStationById(Integer id);

	public Object findStationByName(String name);
	public List<Line> lookUpLinet(String name);
	public Line findLineById(Integer id);

	public Object findLineByName(String name);

	public List<Station> findAllStations();

	public List<Line> findAllLines();

	public List<Station> findAllStationsByLineId(Integer id);

	public Line findLineOfTwoStations(Integer stationId, Integer stationId1);

	public StationLine findStationLineByLineAndStation(Line line,
			Station station);

	public StationLine findStationLineOfOneStationInTheSameLineOfAnotherStation(
			Station station, Station station1);

	public Integer AntecedentInTheSameLine(Station station, Station station1);

	public Boolean addLine(Line line);

	public List<Line> findAllLinesByStationId(Integer stationId);

	public int[][] RemplirMatrice();

	Line findoutLIneBynName(String name);

	List<Station> lookUpStation(String id);
}
