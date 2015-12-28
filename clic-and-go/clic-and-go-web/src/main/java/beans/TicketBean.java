package beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import services.interfaces.StationLineManagementLocal;
import services.interfaces.TicketsServicesLocal;
import entities.Line;
import entities.Ticket;

@ManagedBean
@ViewScoped
public class TicketBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Models

	private Ticket ticket = new Ticket();
	private List<Line> lines;
	private Line line = new Line();
	private Boolean displayform = false;
	private Line lineSelected;

	// Injection

	@EJB
	private TicketsServicesLocal ticketsServicesLocal;
	@EJB
	private StationLineManagementLocal stationLineManagementLocal;

	public StationLineManagementLocal getStationLineManagementLocal() {
		return stationLineManagementLocal;
	}

	public void setStationLineManagementLocal(
			StationLineManagementLocal stationLineManagementLocal) {
		this.stationLineManagementLocal = stationLineManagementLocal;
	}

	public TicketsServicesLocal getTicketsServicesLocal() {
		return ticketsServicesLocal;
	}

	public void setTicketsServicesLocal(
			TicketsServicesLocal ticketsServicesLocal) {
		this.ticketsServicesLocal = ticketsServicesLocal;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Line> getLines() {
		lines = stationLineManagementLocal.findAllLines();
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public Boolean getDisplayform() {
		return displayform;
	}

	public void setDisplayform(Boolean displayform) {
		this.displayform = displayform;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Line getLineSelected() {
		return lineSelected;
	}

	public void setLineSelected(Line lineSelected) {
		this.lineSelected = lineSelected;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Line Selected",
				((Line) event.getObject()).getName());
		ticketsServicesLocal.chooseLineForTicket(ticket, lineSelected);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
