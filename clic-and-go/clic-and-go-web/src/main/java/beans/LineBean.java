package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import services.interfaces.LineServicesLocal;
import services.interfaces.StationLineManagementLocal;
import entities.Line;

@ManagedBean
@SessionScoped
public class LineBean {

	private Line line= new Line();
	private List<Line> lines;
	private Boolean displayform = false;
	private Boolean displayDetails = false;
	private Line lineselected;
	@EJB
	private LineServicesLocal lineServicesLocal ;
	@EJB
	private StationLineManagementLocal stationLineManagementLocal;
	
	
	@PostConstruct
	public void init() {
	    setLine(new Line());
		setDisplayform(false);
		setDisplayDetails(false);

	}
	
	public void doNew() {
		setDisplayform(true);
		line = new Line();

	}
	public String doUpdate(){
	lineServicesLocal.updateLine(lineselected);
	String navigateTo = "ListLinel";
    setDisplayDetails(false);
	return navigateTo;
	
	
	}
	public void doCancel() {
		line = new Line();
		displayDetails = false;
	}
	public void doCancel1() {
		line = new Line();

		displayform = false;
	}
	public String doAddline(){
		stationLineManagementLocal.addLine(line);
		setDisplayform(false);
		String navigateTo = "ListLinel";

		return navigateTo;
		
	}
	public void onRowSelect(SelectEvent event) {
		setDisplayDetails(true);
	}
	public LineServicesLocal getLineServicesLocal() {
		return lineServicesLocal;
	}
	public void setLineServicesLocal(LineServicesLocal lineServicesLocal) {
		this.lineServicesLocal = lineServicesLocal;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public List<Line> getLines() {
	lines=	lineServicesLocal.findAllLines();
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

	public Line getLineselected() {
		return lineselected;
	}

	public void setLineselected(Line lineselected) {
		this.lineselected = lineselected;
	}


	public Boolean getDisplayDetails() {
		return displayDetails;
	}


	public void setDisplayDetails(Boolean displayDetails) {
		this.displayDetails = displayDetails;
	}
}
