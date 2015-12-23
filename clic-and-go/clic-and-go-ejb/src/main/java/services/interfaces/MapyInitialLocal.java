package services.interfaces;

import java.awt.Graphics;

import javax.ejb.Local;

@Local
public interface MapyInitialLocal {
	public void paintComponent(Graphics g);
}
