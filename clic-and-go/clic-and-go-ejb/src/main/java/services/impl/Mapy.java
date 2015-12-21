package services.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import services.interfaces.MapyLocal;
import services.interfaces.MapyRemote;
import entities.Line;
import entities.Station;

/**
 * Session Bean implementation class Mapy
 */
@Stateless
public class Mapy implements MapyRemote, MapyLocal {
	@EJB
	private StationLineManagement stationLineManagement;
	private Session session;
	private LineServices lineServices;

	/**
	 * Default constructor.
	 */
	public Mapy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gr = (Graphics2D) g;
		String depart = session.getDeparture();
		String arrival = session.getArrival();
		Station sta = (Station) stationLineManagement.findLineByName(depart);

		int[][] mat = stationLineManagement.RemplirMatrice();
		Graphe g0 = new Graphe(mat);
		Dijkestra dij = new Dijkestra(sta.getReference(), g0);

		// gr.setColor(Color.red);
		List<Line> lines = lineServices.findAllLines();

		for (Line l : lines) {
			// setBackground(getBackground());

			List<Station> stations = stationLineManagement
					.findAllStationsByLineId(l.getLineId());

			for (Station s : stations) {
				System.out.println("" + l.getName() + "" + s.getName() + " ");
			}

			for (Station station : stations) {

				if (station.getX() != null && station.getY() != null) {
					gr.setColor(Color.red);
					gr.fillOval(station.getX() + 150, station.getY() + 150, 13,
							13);
					gr.setColor(Color.BLUE);

					gr.drawString(station.getName(), station.getX() + 150,
							station.getY() + 140);

				}

			}
			for (int i = 0; i < stations.size() - 1; i++) {
				gr.setColor(Color.GRAY);
				gr.setStroke(new BasicStroke((float) 3));
				gr.drawLine(stations.get(i).getX() + 155, stations.get(i)
						.getY() + 155, stations.get(i + 1).getX() + 155,
						stations.get(i + 1).getY() + 155);
			}

		}

		List<Station> stationtoDraw = dij.afficheListStations(depart, arrival);

		for (int i = 0; i < stationtoDraw.size() - 1; i++) {
			gr.setColor(Color.GREEN);
			gr.setStroke(new BasicStroke((float) 3.5));
			gr.drawLine(stationtoDraw.get(i).getX() + 155, stationtoDraw.get(i)
					.getY() + 158, stationtoDraw.get(i + 1).getX() + 155,
					stationtoDraw.get(i + 1).getY() + 158);

		}

	}

}
