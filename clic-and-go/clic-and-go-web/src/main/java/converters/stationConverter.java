package converters;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import entities.MeanOfTransport;
import entities.Station;
import services.interfaces.StationServicesLocal;

public class stationConverter implements Converter {

	@EJB
	private StationServicesLocal stationServicesLocal;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Station station= null;
		station = stationServicesLocal.findStationByStationName(value);
		return station;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String nameStation = null;
		if (value == null || value.equals("")) {
			nameStation = "";
		} else {
			nameStation = ((Station) value).getStationId().toString();
		}
		return nameStation;
	}
}
