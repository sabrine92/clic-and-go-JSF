package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import services.interfaces.LineServicesLocal;
import services.interfaces.MeanOfTransportServicesLocal;
import entities.Line;
import entities.MeanOfTransport;

@ManagedBean
@SessionScoped
public class MeanBean {
	@EJB
	private MeanOfTransportServicesLocal meanOfTransportServicesLocal;
	@EJB
	private LineServicesLocal lineServices;
	
	private MeanOfTransport m;
	private MeanOfTransport selectedmean;
	private List<MeanOfTransport> meanOfTransports;
	private Boolean Display;
	private Boolean DisplayDetails;
	private List<MeanOfTransport> filtredmeanOfTransports;
	private Integer LineId;

	public String doAdd() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"ERROR", "Registration Number existe ");

		meanOfTransportServicesLocal.addMeanOfTransport(m);
		//meanOfTransportServicesLocal.assignMeanOfTransportToLine(m.getRegistrationNumber(), 2);
		Display = false;
		String nav="Mot";
       return nav;
	}
	
	@PostConstruct
	public void init() {
	    setM(new MeanOfTransport());
		setDisplay(false);
		setDisplayDetails(false);

	}
	public  String doAssign(){
		//Line lin=lineServices.findLineByName(selectedmean.getRegistrationNumber());
		meanOfTransportServicesLocal.assignMeanOfTransportToLine(selectedmean.getRegistrationNumber(),5);
		setDisplayDetails(false);
		String nav="Mot";
	       return nav;
		
	}
	public void doCancel() {
		 setM(new MeanOfTransport());
			setDisplay(false);
	}
	public void doCancel1() {
		 setM(new MeanOfTransport());
			setDisplayDetails(false);
	}

	
	public void doNew() {
		setDisplay(true);
		setDisplayDetails(false);
		m = new MeanOfTransport();

	}
	public String doDelete() {
		System.out.println(selectedmean);
		
		meanOfTransportServicesLocal.deleteMeanOfTransport(selectedmean);
		
		return "";
	}

	//
	// public MeanOfTransport getMeanOfTransport() {
	// return meanOfTransport;
	// }
	//
	// public void setMeanOfTransport(MeanOfTransport meanOfTransport) {
	// this.meanOfTransport = meanOfTransport;
	// }

	
	
	public MeanOfTransportServicesLocal getMeanOfTransportServicesLocal() {
		return meanOfTransportServicesLocal;
	}

	public LineServicesLocal getLineServices() {
		return lineServices;
	}

	public void setLineServices(LineServicesLocal lineServices) {
		this.lineServices = lineServices;
	}

	public void setMeanOfTransportServicesLocal(
			MeanOfTransportServicesLocal meanOfTransportServicesLocal) {
		this.meanOfTransportServicesLocal = meanOfTransportServicesLocal;
	}

	public MeanOfTransport getM() {
		return m;
	}

	public void setM(MeanOfTransport m) {
		this.m = m;
	}


	public void onRowSelect(SelectEvent event) {
		setDisplayDetails(true);
	}
	public List<MeanOfTransport> getMeanOfTransports() {
		meanOfTransports= meanOfTransportServicesLocal.findAllMeanOfTransports();
		return meanOfTransports;
	}


	public void setMeanOfTransports(List<MeanOfTransport> meanOfTransports) {
		this.meanOfTransports = meanOfTransports;
	}


	public List<MeanOfTransport> getFiltredmeanOfTransports() {
		return filtredmeanOfTransports;
	}


	public void setFiltredmeanOfTransports(List<MeanOfTransport> filtredmeanOfTransports) {
		this.filtredmeanOfTransports = filtredmeanOfTransports;
	}
	public MeanOfTransport getSelectedmean() {
		return selectedmean;
	}
	public void setSelectedmean(MeanOfTransport selectedmean) {
		this.selectedmean = selectedmean;
	}


	public Boolean getDisplay() {
		return Display;
	}


	public void setDisplay(Boolean display) {
		Display = display;
	}

	public Boolean getDisplayDetails() {
		return DisplayDetails;
	}

	public void setDisplayDetails(Boolean displayDetails) {
		DisplayDetails = displayDetails;
	}

	public Integer getLineId() {
		return LineId;
	}

	public void setLineId(Integer lineId) {
		LineId = lineId;
	}

	
}
