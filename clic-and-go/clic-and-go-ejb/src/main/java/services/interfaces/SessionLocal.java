package services.interfaces;

import javax.ejb.Local;

@Local
public interface SessionLocal {
	String getLogin();

	void setLogin(String login);

	String getPwd();

	void setPwd(String pwd);

	String getDeparture();

	void setDeparture(String departure);

	String getArrival();

	void setArrival(String arrival);

	Integer getDuration();

	void setDuration(Integer duration);

	void exit();

	void stopSession();
}
