package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import entities.Station;
import services.interfaces.StationServicesLocal;


@ManagedBean
@FacesConverter("stationConverter")
public class StationConverter implements Converter{
	
@EJB
StationServicesLocal servicesLocal;

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		Station station=null;
		System.out.println(servicesLocal.findStationByStationName(value));
		station=servicesLocal.findStationByStationName(value);
		return station;
		
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String equivalentString = null;
		if (value ==null || value.equals("")){
			equivalentString="";
		
			
		}else{ equivalentString=((Station)value).getName();
		}
		return equivalentString;
}
	

}
