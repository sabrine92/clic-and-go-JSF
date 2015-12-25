package services.impl;

import javax.ejb.Stateful;

import services.interfaces.SessionLocal;
import services.interfaces.SessionRemote;

/**
 * Session Bean implementation class SessionServices
 */
@Stateful
public class Session implements SessionRemote ,SessionLocal {

	public static String login;
    public static String pwd;
    public static String departure;
    public static String arrival;
    public static Integer duration;

    
    
    
    public  Integer getDuration() {
		return duration;
	}

	public  void setDuration(Integer duration) {
		Session.duration = duration;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		Session.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		Session.arrival = arrival;
	}

	public  String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        Session.login = login;
    }

    public  String getPwd() {
        return pwd;
    }

    public  void setPwd(String pwd) {
        Session.pwd = pwd;
    }
    
    
    public Session() {
        arrival="Ariana";
        departure="Ariana";
    }

	@Override
	public void exit() {
		login=null;
		pwd=null;
		arrival=null;
		departure=null;
		duration=null;
		
	}

	@Override
	public void stopSession() {
		login=null;
		pwd=null;
		arrival=null;
		departure=null;
		duration=null;
		
	}
  

}
