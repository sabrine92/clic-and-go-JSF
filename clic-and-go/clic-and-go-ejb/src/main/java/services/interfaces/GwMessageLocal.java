package services.interfaces;

import javax.ejb.Local;

@Local
public interface GwMessageLocal {

	public void sendEmail(String to, String from, String subject, String content);
}
