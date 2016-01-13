package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Line;

@Local
public interface LineServicesLocal {
	Boolean addLine(Line line);

	Line findLineByName(String name);

	public List<Line> findAllLines();
	Boolean updateLine(Line line);

}
