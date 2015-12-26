package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.interfaces.SessionLocal;
import services.interfaces.UserServicesLocal;
import entities.Traveler;
import entities.User;

@ManagedBean
@SessionScoped
public class LoginBean {
	private User user = new User();
	@EJB
	private UserServicesLocal userServicesLocal;
	@EJB
	private SessionLocal sessionLocal;

	public String doLogin() {
		String navigateTo = "";
		User userLoggedIn = userServicesLocal.authenticate(user.getName(),
				user.getPassword());
		System.out.println("userLoggedIn :"+userLoggedIn);
		if (userLoggedIn != null) {
			user = userLoggedIn;
			sessionLocal.setLogin(userLoggedIn.getName());
			sessionLocal.setLogin(userLoggedIn.getPassword());
			if (userLoggedIn instanceof Traveler) {
				System.out.println(userLoggedIn instanceof Traveler);
				navigateTo = "pages/cm/cmHome";
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