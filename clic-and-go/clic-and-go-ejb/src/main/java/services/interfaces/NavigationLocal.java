package services.interfaces;

import javax.ejb.Local;
import javax.faces.event.ActionEvent;

@Local
public interface NavigationLocal {

	 public void actionPerformed(ActionEvent e);
	
}
