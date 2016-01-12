package beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import services.interfaces.GwMessageLocal;
import services.interfaces.NewsServicesLocal;
import entities.News;

@ManagedBean
@SessionScoped
public class NewsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Models
	private News news = new News();
	private List<News> newss;

	private MapModel simpleModel;

	private Marker marker;

	private String title;

	private double lat;

	private double lng;
	private String type;
	private Map<String, String> types = new HashMap<String, String>();

	// Injection
	@EJB
	private NewsServicesLocal newsServicesLocal;
	@EJB
	private GwMessageLocal gwMessageLocal;

	// Getters & Setters
	public NewsServicesLocal getNewsServicesLocal() {
		return newsServicesLocal;
	}

	public void setNewsServicesLocal(NewsServicesLocal newsServicesLocal) {
		this.newsServicesLocal = newsServicesLocal;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<News> getNewss() {
		newss = newsServicesLocal.findAllNewss();
		return newss;
	}

	public void setNewss(List<News> newss) {
		this.newss = newss;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getTypes() {
		return types;
	}

	public void setTypes(Map<String, String> types) {
		this.types = types;
	}

	// Functionality

	public GwMessageLocal getGwMessageLocal() {
		return gwMessageLocal;
	}

	public void setGwMessageLocal(GwMessageLocal gwMessageLocal) {
		this.gwMessageLocal = gwMessageLocal;
	}

	public Boolean doAddNews(News news) {
		return newsServicesLocal.addNews(news);

	}

	public Boolean doDeleteNews(News news) {

		return newsServicesLocal.deleteNews(news);

	}

	public Boolean doUpdateNews(News news) {

		return newsServicesLocal.updateNews(news);
	}

	public Boolean doDeleteNewsByNewsId(Integer newsId) {

		return newsServicesLocal.deleteNewsByNewsId(newsId);
	}

	public News doFindNewsByNewsId(Integer newsId) {
		return newsServicesLocal.findNewsByNewsId(newsId);

	}

	public List<News> doFindAllNewss() {

		return newsServicesLocal.findAllNewss();
	}

	public News doFindNewsByNewsTitle(String title) {
		return newsServicesLocal.findNewsByNewsTitle(title);

	}

	// MAP

	@PostConstruct
	public void init() {

		simpleModel = new DefaultMapModel();

		newss = newsServicesLocal.findAllNewss();

		for (News n : newss) {
			simpleModel.addOverlay(new Marker(
					new LatLng(n.getLat(), n.getLng()), n.getTitle(), n));

			types = new HashMap<String, String>();
			types.put("Accident", "Accident");
			types.put("Breakdown", "Breakdown");
		}

		// simpleModel = new DefaultMapModel();
		//
		// // Shared coordinates
		// LatLng coord1 = new LatLng(36.809590, 10.162847);
		// LatLng coord2 = new LatLng(36.799663, 10.180025);
		// LatLng coord3 = new LatLng(36.784016, 10.177511);
		//
		// // Basic marker
		// simpleModel.addOverlay(new Marker(coord1, "Beb El Khadhra"));
		// simpleModel.addOverlay(new Marker(coord2, "Avenue Habib Bourguiba"));
		// simpleModel.addOverlay(new Marker(coord3, "Bab Alouia"));

	}

	public void onMarkerSelect(OverlaySelectEvent event) {

		marker = (Marker) event.getOverlay();

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected",
						marker.getTitle()));

	}

	public String addMarker() {
		String nav = "";
		Marker marker = new Marker(new LatLng(lat, lng), title, news);
		// Marker marker = new Marker(new LatLng(lat, lng), title);
		simpleModel.addOverlay(marker);
		news.setLat(lat);
		news.setLng(lng);
		newsServicesLocal.addNews(news);
		newss.add(news);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert Added",
						news.getTitle()));
		nav = "News";
		return nav;

	}

	public void send() {

		gwMessageLocal.sendEmail("mohamedheni.gafsi@esprit.tn",
				"nadi.badr@esprit.tn", "Accident", "ijewni ejriiiiiiiwliiii");
	}

}
