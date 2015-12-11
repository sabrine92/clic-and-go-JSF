package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Ebook;
import entities.StationLine;
import entities.Traveler;

@Local
public interface ReadingManagementLocal {

	Boolean addEbook(Ebook ebook);
	
	List<Ebook> viewLibrary();
	
	List<Ebook> viewLibraryByCategory(String category);
	
	List<Ebook> lookUpEbook(String search);
	
	List<Ebook> suggestEbooks(Integer duration, Traveler traveler );

}
