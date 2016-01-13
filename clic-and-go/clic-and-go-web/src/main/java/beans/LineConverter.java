package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import entities.Line;
import services.interfaces.LineServicesLocal;
@ManagedBean
public class LineConverter implements Converter {


	@EJB
	private LineServicesLocal lineServicesLocal;
		@Override
		public Object getAsObject(FacesContext context, UIComponent component,String value) {
			Line line = null;
				System.out.println(lineServicesLocal);
				line = lineServicesLocal.findLineByName(value);
				return line;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component,
				Object value) {
			String typecar = null;
			if (value == null || value.equals("")) {
				typecar="";
			}else {
			typecar = ((Line)value).getName();
			}
			return typecar;
		}
		

}
