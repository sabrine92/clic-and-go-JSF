package services.interfaces;

import java.io.IOException;

public interface mailLocal {
	void mail(String subject, String text, String destinataire)
			throws IOException;

}
