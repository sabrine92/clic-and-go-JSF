package services.impl;

import javax.ejb.Stateless;
import services.interfaces.DijkestraLocal;
import services.interfaces.DijkestraRemote;

/**
 * Session Bean implementation class Dijkestra
 */
@Stateless
public class Dijkestra implements DijkestraRemote, DijkestraLocal {

    /**
     * Default constructor. 
     */
    public Dijkestra() {
        // TODO Auto-generated constructor stub
    }

}
