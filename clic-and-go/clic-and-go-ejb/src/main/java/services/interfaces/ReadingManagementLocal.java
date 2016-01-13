package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Ebook;
import entities.Traveler;

@Local
public interface ReadingManagementLocal {

	Boolean addEbook(Ebook ebook);

	List<Ebook> viewLibrary();

	List<Ebook> viewLibraryByCategory(String category);

	List<Ebook> lookUpEbook(String search);

	List<Ebook> suggestEbooks(Integer duration, Traveler traveler);
	
	Ebook findEbookByEbookId(Integer Id);
	
	Ebook findEbookByEbookTitle(String Title);
	
	Boolean deleteEbook(Ebook ebook);
	
	Boolean updateEbook(Ebook ebook);

}
