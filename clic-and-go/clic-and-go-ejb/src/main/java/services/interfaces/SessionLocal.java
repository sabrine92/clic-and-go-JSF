package services.interfaces;

import javax.ejb.Local;

import entities.Line;

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

	Line getLineSelected();

	void setLineSelected(Line lineSelected);

	void exit();

	void stopSession();
}
