package beans;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import entities.Line;
import entities.Station;
import services.impl.Dijkestra;
import services.impl.Graphe;
import services.interfaces.LineServicesLocal;
import services.interfaces.MapyInitialLocal;
import services.interfaces.StationLineManagementLocal;

@ManagedBean
public class MapyBean {

	@EJB
	private MapyInitialLocal mapyInitialLocal;
	@EJB
	 private StationLineManagementLocal stationLineManagement;
	@EJB
	private LineServicesLocal lineServices;
	
	private Graphics2D gr;

	
		public Graphics2D paintComponent(Graphics g) {
			Graphics2D gr = (Graphics2D) g;
			String depart ="Ariana";
			String arrival = "Passage";
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
			return gr;
		}
	
	
	public StationLineManagementLocal getStationLineManagement() {
			return stationLineManagement;
		}


		public void setStationLineManagement(
				StationLineManagementLocal stationLineManagement) {
			this.stationLineManagement = stationLineManagement;
		}


		public LineServicesLocal getLineServices() {
			return lineServices;
		}


		public void setLineServices(LineServicesLocal lineServices) {
			this.lineServices = lineServices;
		}


	public Graphics2D getG2() {
		return gr;
	}

	public void setG2(Graphics2D gr) {
		this.gr = gr;
	}

	public MapyInitialLocal getMapyInitialLocal() {
		return mapyInitialLocal;
	}

	public void setMapyInitialLocal(MapyInitialLocal mapyInitialLocal) {
		this.mapyInitialLocal = mapyInitialLocal;
	}
	
	
	
	
	
	
}
