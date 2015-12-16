package services.impl;

import javax.ejb.Stateless;
import services.interfaces.PlacesServicesLocal;
import services.interfaces.PlacesServicesRemote;

/**
 * Session Bean implementation class PlacesServices
 */
@Stateless
public class PlacesServices implements PlacesServicesRemote, PlacesServicesLocal {

    /**
     * Default constructor. 
     */
    public PlacesServices() {
        // TODO Auto-generated constructor stub
    }

}
