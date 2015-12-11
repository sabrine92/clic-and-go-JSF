package services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import services.interfaces.StationLineManagementLocal;
import services.interfaces.StationLineManagementRemote;
import entities.Line;
import entities.Station;
import entities.StationLine;

/**
 * Session Bean implementation class StationLineManagement
 */
@Stateless
public class StationLineManagement implements StationLineManagementRemote,
		StationLineManagementLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public StationLineManagement() {
	}

	@Override
	public Boolean assignNewStationToLine(Station newStation, Integer lineId,
			Integer position, Integer duration, Integer distance) {

		Boolean b = false;
		try {
			Line line = (Line) entityManager.find(Line.class, lineId);
			entityManager.merge(newStation);
			StationLine stationLine = new StationLine(newStation, line,
					position, duration, distance);
			entityManager.merge(stationLine);
			b = true;

		} catch (Exception e) {
			System.out.println("errooor new station to line");
		}
		return b;

	}

	@Override
	public Boolean assignStationToLine(Integer stationId, Integer lineId,
			Integer position, Integer duration, Integer distance) {
		Boolean b = false;
		try {
			Line line = (Line) entityManager.find(Line.class, lineId);
			Station station = (Station) entityManager.find(Station.class,
					stationId);
			StationLine stationLine = new StationLine(station, line, position,
					duration, distance);
			entityManager.merge(stationLine);
			b = true;

		} catch (Exception e) {
			System.out.println("errooor station to line");
		}
		return b;

	}

	@Override
	public Station findStationById(Integer id) {
		try {
			return entityManager.find(Station.class, id);
		} catch (Exception e) {
			System.err
					.println("A problem occured while trying to find Station by  "
							+ id);

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object findStationByName(String name) {
		try {
			String jpql = "select s from Station s where s.name=:param";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param", name);
			return query.getSingleResult();

		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Line findLineById(Integer id) {
		try {
			return entityManager.find(Line.class, id);
		} catch (Exception e) {
			System.err
					.println("A problem occured while trying to find Line by  "
							+ id);

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object findLineByName(String name) {
		try {
			String jpql = "select l from Line l where l.name=:param";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param", name);
			return query.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Station> findAllStations() {
		String jpql = "select s from Station s";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findAllLines() {
		String jpql = "select i from Line i";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public List<Station> findAllStationsByLineId(Integer id) {

		String jpql = "select s from Station s join s.stationLines l where l.line.lineId=:param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", id);
		System.out.println("succes");

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public StationLine findStationLineByLineAndStation(Line line,
			Station station) {
		try {
			String jpql = "select sl from StationLine sl where sl.line.lineId=:param and sl.station.stationId=:paramm";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param", line.getLineId());
			query.setParameter("paramm", station.getStationId());
			return (StationLine) query.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public StationLine findStationLineOfOneStationInTheSameLineOfAnotherStation(
			Station station, Station station1) {

		StationLine stationLine = new StationLine();
		Line line = new Line();
		line = findLineOfTwoStations(station.getStationId(),
				station.getStationId());
		stationLine = findStationLineByLineAndStation(line, station);
		return stationLine;

	}

	@Override
	public Boolean addLine(Line line) {
		Boolean b = false;
		try {
			entityManager.merge(line);
			b = true;
		} catch (Exception e) {
			System.err.println("A problem occured while adding " + line);
		}
		return b;
	}

	@Override
	public List<Line> findAllLinesByStationId(Integer stationId) {
		String jpql = "select l from Line l join l.stationLines sl where sl.station.stationId=:param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", stationId);
		System.out.println("succes");

		return query.getResultList();

	}

	@Override
	public Line findLineOfTwoStations(Integer stationId, Integer stationId1) {

		Station station = (Station) entityManager
				.find(Station.class, stationId);
		Station station1 = (Station) entityManager.find(Station.class,
				stationId1);
		List<Line> lines = findAllLinesByStationId(station.getStationId());
		List<Line> lines1 = findAllLinesByStationId(station1.getStationId());
		Line line = null;

		try {
			if (line == null) {
				for (Line l : lines) {
					if (line == null) {
						for (Line l1 : lines1) {
							if (l.getLineId() == l1.getLineId()) {
								line = l1;
							}
						}
					}
				}
			}

		} catch (Exception e) {
			return null;
		}

		return line;

	}

	@Override
	public Integer AntecedentInTheSameLine(Station station, Station station1) {
		Integer b = 0;
		Line line = findLineOfTwoStations(station.getStationId(),
				station1.getStationId());
		if (line == null) {
			b = 0;
		} else {
			StationLine stationLine = findStationLineByLineAndStation(line,
					station);
			StationLine stationLine1 = findStationLineByLineAndStation(line,
					station1);
			if (stationLine.getPosition() == stationLine1.getPosition() + 1) {
				b = 1;
			} else {

				if (stationLine.getPosition() == stationLine1.getPosition() - 1) {
					b = 1;
				} else {
					if (station.equals(station1)) {
						b = 0;
					}

				}
			}
		}

		return b;
	}

	@Override
	public int[][] RemplirMatrice() {
int N=9999;
		List<Station> stations = findAllStations();
		List<Station> stations1 = findAllStations();

		StationLine stationLine = new StationLine();

		Integer Nmax = stations.size();
		int[][] matrice = new int[Nmax][Nmax];
		for (int i = 0; i < Nmax; i++) {
			for (int j = 0; j < Nmax; j++) {
				matrice[i][j] = N;
			}

		}

		for (Station s : stations) {
			for (Station s1 : stations1) {
				Integer etat = AntecedentInTheSameLine(s, s1);
				if (etat == 0) {
					System.out.print("insctruction1");
					matrice[s.getReference()][s1.getReference()] = N;
					matrice[s1.getReference()][s.getReference()] = N;
				} else {
					if (etat == 1) {
						System.out.print("insctruction2");
						stationLine = findStationLineOfOneStationInTheSameLineOfAnotherStation(
								s, s1);
						matrice[s.getReference()][s1.getReference()] = stationLine
								.getDistance();
						matrice[s1.getReference()][s.getReference()] = stationLine
								.getDistance();
					} else {
						if (s.equals(s1)) {
							matrice[s.getReference()][s1.getReference()] = 0;
							matrice[s1.getReference()][s.getReference()] = 0;

						}
					}
				}
			}

		}

		// for (Station s : stations) {
		// for (Station s1 : stations1) {
		//
		// stationLine =
		// findStationLineOfOneStationInTheSameLineOfAnotherStation(
		// s, s1);
		// matrice[s.getReference()][s1.getReference()] = stationLine
		// .getDistance();
		// matrice[s1.getReference()][s.getReference()] = stationLine
		// .getDistance();
		// }
		// }

		return matrice;
	}
	@Override
	public Line findoutLIneBynName(String name) {
		String jpql = "select L from Line L where L.name LIKE :param";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param",'%'+name+'%');
		return (Line) query.getSingleResult();
		}

	@Override
	public List<Line> lookUpLinet(String id) {
		try {
			String jpql = "select m from Line m where m.name LIKE :param";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param", '%' + id + '%');
			return query.getResultList();

		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Station> lookUpStation(String id) {
		try {
			String jpql = "select m from Station m where m.name LIKE :param";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param", '%' + id + '%');
			return query.getResultList();

		} catch (Exception e) {
			return null;
		}
	}
	


}

