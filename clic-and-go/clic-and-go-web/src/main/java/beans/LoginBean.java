package beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import services.interfaces.SessionLocal;
import services.interfaces.UserServicesLocal;
import entities.Traveler;
import entities.User;

@ManagedBean
@SessionScoped
public class LoginBean {
	private User user = new User();
	private Boolean cm = true;
	private Boolean LoggedIn=false;
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
		if (userLoggedIn != null) {
			setLoggedIn(true);
			user = userLoggedIn;
			sessionLocal.setLogin(userLoggedIn.getName());
			sessionLocal.setPwd(userLoggedIn.getPassword());
			if (userLoggedIn instanceof Traveler) {
				cm = false;
				System.out.println(userLoggedIn instanceof Traveler);
				navigateTo = "pages/traveler/trHome?faces-redirect=true";
			}else{
				cm=true;
				navigateTo="pages/cm/cmHome?faces-redirect=true";
			}
		} else {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Wrong credentials"));
			
		}
		return navigateTo;
	}
	
	public Boolean isLoggedIn(){
		return LoggedIn;
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