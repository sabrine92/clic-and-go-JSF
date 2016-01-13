package beans;


	

	import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entities.MeanOfTransport;
import services.interfaces.MeanOfTransportServicesLocal;




public class MeanofTransportConverter  implements Converter {

	@EJB
	private MeanOfTransportServicesLocal MeanOfTransportServicesLocal;	
		@Override
		public Object getAsObject(FacesContext context, UIComponent component,String value) {
				MeanOfTransport mean = null;
				System.out.println(MeanOfTransportServicesLocal);
				mean = MeanOfTransportServicesLocal.findMeanOfTransportByName(value);
				return mean;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component,
				Object value) {
			String typecar = null;
			if (value == null || value.equals("")) {
				typecar="";
			}else {
			typecar = ((MeanOfTransport)value).getRegistrationNumber();
			}
			return typecar;
		}
		


	}


