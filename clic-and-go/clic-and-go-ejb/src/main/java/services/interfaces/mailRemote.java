package services.interfaces;

import java.io.IOException;

public interface mailRemote {
	void mail(String subject, String text, String destinataire) throws IOException;
}
