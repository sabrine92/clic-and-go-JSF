package services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Line;
import entities.Station;

@Remote
public interface LineServicesRemote {

	Boolean addLine(Line line);
	Line findLineByName(String name);
	public List<Line> findAllLines();

}
