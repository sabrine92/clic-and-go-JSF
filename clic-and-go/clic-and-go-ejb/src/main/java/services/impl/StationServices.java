
    package services.impl;

    import java.util.List;

    import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

    import services.interfaces.StationServicesLocal;
import services.interfaces.StationServicesRemote;
import entities.Station;

    /**
     * Session Bean implementation class StationServices
     */
    @Stateless
    public class StationServices implements StationServicesRemote,
    		StationServicesLocal {
    	@PersistenceContext
    	private EntityManager entityManager;

    	/**
    	 * Default constructor.
    	 */
    	public StationServices() {

    	}

    	@Override
    	public Boolean addStation(Station station) {
    		Boolean b = false;
    		try {
    			entityManager.persist(station);
    			b = true;
    		} catch (Exception e) {
    			System.err.println("A problem occured while adding "
    					+ station);
    		}
    		return b;
    	}

    	@Override
    	public Boolean deleteStationById(Integer Id) {
    		Boolean b = false;
    		try {
    			entityManager.remove(findStationById(Id));
    			b = true;
    		} catch (Exception e) {
    			System.err.println("A problem occured while deleting MoT number"
    					+ Id);
    		}
    		return b;
    	}

    	@Override
    	public Boolean updateStation(Station station) {
    		Boolean b = false;
    		try {
    			entityManager.merge(station);
    			b = true;
    		} catch (Exception e) {
    			System.err.println("A problem occured while updating "
    					+ station);
    		}
    		return b;
    	}

    	@Override
    	public Station findStationById(Integer Id) {
    		try {
    			return entityManager.find(Station.class, Id);
    		} catch (Exception e) {
    			System.err
    					.println("A problem occured while trying to find station by  "
    							+ Id);

    		}
    		return null;

    	}
    	@Override
    	public Station findStationByStationByReference(int ref) {
    		String jpql = "select s from Station s where s.reference=:param";
    		Query query = entityManager.createQuery(jpql);
    		query.setParameter("param",ref);
    		return (Station) query.getSingleResult();

    	}

    	@Override
    	public Boolean deleteStation(Station station) {
    		Boolean b = false;
    		try {
    			entityManager.remove(entityManager.merge(station));
    			b = true;
    		} catch (Exception e) {
    			System.err.println("A problem occured while deleting "
    					+ station);
    		}
    		return b;
    	}

    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Station> findAllStations() {
    		String jpql = "select s from Station s";
    		Query query = entityManager.createQuery(jpql);
    		return query.getResultList();
    	}

		@Override
		public Station findStationByStationName(String name) {
			String jpql = "select s from Station s where s.name LIKE :param";
    		Query query = entityManager.createQuery(jpql);
    		query.setParameter("param",'%'+name+'%');
    		return (Station) query.getSingleResult();
    		}

    	
    	
    	
    }


