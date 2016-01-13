package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import services.interfaces.LineServicesLocal;
import services.interfaces.StationServicesLocal;
import entities.Line;
import entities.Station;

@ManagedBean
public class ConverterBean implements Converter {


	@EJB
	private StationServicesLocal stationServicesLocal;
		@Override
		public Object getAsObject(FacesContext context, UIComponent component,String value) {
		Station line = null;
				System.out.println(stationServicesLocal);
				line = stationServicesLocal.findStationByStationName(value);
				return line;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component,
				Object value) {
			String typecar = null;
			if (value == null || value.equals("")) {
				typecar="";
			}else {
			typecar = ((Station)value).getName();
			}
			return typecar;
		}
		

}