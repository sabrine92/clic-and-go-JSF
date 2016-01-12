package beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import services.interfaces.SessionLocal;
import services.interfaces.StationLineManagementLocal;
import services.interfaces.TicketsServicesLocal;
import services.interfaces.UserServicesLocal;
import entities.Card;
import entities.Line;
import entities.Ticket;
import entities.User;

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
	private Card card = new Card();
	private Boolean displayform = false;
	private Line lineSelected;
	private Integer qt;
	private User user;
	private List<Ticket> tickets;

	// Injection

	@EJB
	private TicketsServicesLocal ticketsServicesLocal;
	@EJB
	private StationLineManagementLocal stationLineManagementLocal;
	@EJB
	private UserServicesLocal userServicesLocal;
	@EJB
	private SessionLocal sessionLocal;

	// Getters & Setters

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

	public UserServicesLocal getUserServicesLocal() {
		return userServicesLocal;
	}

	public void setUserServicesLocal(UserServicesLocal userServicesLocal) {
		this.userServicesLocal = userServicesLocal;
	}

	public SessionLocal getSessionLocal() {
		return sessionLocal;
	}

	public void setSessionLocal(SessionLocal sessionLocal) {
		this.sessionLocal = sessionLocal;
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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getQt() {
		return qt;
	}

	public void setQt(Integer qt) {
		this.qt = qt;
	}

	public List<Ticket> getTickets() {
		tickets = ticketsServicesLocal.findAllTicketsByUserId(doAuthentUser()
				.getUserId());
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	// Functionality

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Line Selected : "
				+ ((Line) event.getObject()).getName(), "  Price : "
				+ ((Line) event.getObject()).getPrice() + " DT");

		ticketsServicesLocal.chooseLineForTicket(ticket, lineSelected);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Card doAuthentificateCard(String cardId, Integer pwd) {
		card = ticketsServicesLocal.authentificateCard(cardId, pwd);
		return card;

	}

	public Double doCheckCardAmount(Card card) {
		Double b = ticketsServicesLocal.CheckCardAmount(card);

		return b;

	}

	public Double doCalculateTotalPrice(Line line, Integer qt) {
		Double b = ticketsServicesLocal.CalculateTotalPrice(line, qt);
		ticket.setPrice(ticketsServicesLocal.CalculateTotalPrice(line, qt));
		return b;
	}

	public Boolean doPayTicket(Ticket ticket, Card card) {
		Boolean b = ticketsServicesLocal.payTicket(ticket, card);
		ticketsServicesLocal.assignTicketToUser(ticket.getTicketId(),
				doAuthentUser().getUserId());
		return b;
	}

	public User doAuthentUser() {
		return userServicesLocal.authenticate(sessionLocal.getLogin(),
				sessionLocal.getPwd());
	}

}
