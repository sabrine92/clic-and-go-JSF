package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.interfaces.UserServicesLocal;
import entities.Traveler;
import entities.User;

@ManagedBean
@SessionScoped
public class LoginBean {
	private User user = new User();
	@EJB
	private UserServicesLocal userServicesLocal;

	public String doLogin() {
		String navigateTo = "";
		User userLoggedIn = userServicesLocal.authenticate(user.getSurname(),
				user.getPassword());
		if (userLoggedIn != null) {
			user = userLoggedIn;
			if (userLoggedIn instanceof Traveler) {
				navigateTo = "pages/cmHome";
			}
		} else {
			navigateTo = "error";
		}
		return navigateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
