package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.interfaces.LineServicesLocal;
import entities.Line;

@ManagedBean
@SessionScoped
public class LineBean {

	private Line line= new Line();
	private List<Line> lines;
	private Boolean displayform = false;
	
	@EJB
	private LineServicesLocal lineServicesLocal ;
	
	public String doAddline(){
		lineServicesLocal.addLine(line);
		String navigateTo = "ListLinel";

		return navigateTo;
		
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
}
