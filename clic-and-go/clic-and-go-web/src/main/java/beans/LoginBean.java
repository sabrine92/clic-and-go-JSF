package beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.NewCookie;

import services.interfaces.SessionLocal;
import services.interfaces.UserServicesLocal;
import entities.ContentManager;
import entities.Traveler;
import entities.User;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
	private User user = new User();
	private ContentManager contentManager= new ContentManager();
	private Traveler traveler = new Traveler();
	
	private Boolean cm = true;
	private Boolean LoggedIn = false;
	
	@EJB
	private UserServicesLocal userServicesLocal;
	@EJB
	private SessionLocal sessionLocal;

	public String doLogin() {
		setLoggedIn(false);
		String navigateTo = "";
		User userLoggedIn = userServicesLocal.authenticate(user.getName(),
				user.getPassword());
		System.out.println("userLoggedIn :" + userLoggedIn);
		if (userLoggedIn.getName() != null) {
			System.out.println("s7i7!!!");
			setLoggedIn(true);
			user = userLoggedIn;
			sessionLocal.setLogin(userLoggedIn.getName());
			sessionLocal.setPwd(userLoggedIn.getPassword());
			if (userLoggedIn instanceof Traveler) {
				//setTraveler((Traveler)userLoggedIn);
				cm = false;
				navigateTo = "pages/traveler/trHome?faces-redirect=true";
			} else {
				//setContentManager((ContentManager)userLoggedIn);
				cm = true;
				navigateTo = "pages/cm/cmHome?faces-redirect=true";
			}
		} else {
			navigateTo="";
			System.out.println("on va aller à "+navigateTo);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
							"Wrong credentials"));

			navigateTo="";
		}
		return navigateTo;
	}

	public Boolean isLoggedIn() {
		return LoggedIn;
	}
	

	public ContentManager getContentManager() {
		return contentManager;
	}

	public void setContentManager(ContentManager contentManager) {
		this.contentManager = contentManager;
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getCm() {
		return cm;
	}

	public void setCm(Boolean cm) {
		this.cm = cm;
	}

	public Boolean getLoggedIn() {
		return LoggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		LoggedIn = loggedIn;
	}

}