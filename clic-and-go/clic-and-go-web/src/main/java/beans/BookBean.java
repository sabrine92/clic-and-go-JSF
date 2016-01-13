package beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RateEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.event.UnselectEvent;

import services.interfaces.ReadingManagementLocal;
import entities.Card;
import entities.Ebook;
import entities.Place;
import entities.User;


@ManagedBean
@SessionScoped
public class BookBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String navigateTo = "";

	// Models
	private Ebook ebook = new Ebook();
	private List<Ebook> ebooks;
	private List<Ebook> filteredBooks;
	//private List<Station> stations;
	private Ebook selectedBook;
	private Ebook modifiedBook;
	private Boolean displayformadd = false;
	
	//private Boolean displayformrate = false;
	

	private final static String[] categories;
	static {
		categories = new String[9];
		categories[0] = "Romans";
		categories[1] = "Fiction,Aventure";
		categories[2] = "Action";
		categories[3] = "Classique";
		categories[4] = "Erotique";
		categories[5] = "Espionnage";
		categories[6] = "Fantastique";
		categories[7] = "Informatique";
		categories[8] = "Business";
		
		
	}
	private final static String[] types;
	static {
		types = new String[2];
		types[0] = "Livre";
		types[1] = "Article";
		
		
		
	}
	
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	// Injection
		@EJB
		private ReadingManagementLocal readingManagementLocal;
		

		public BookBean() {
			// TODO Auto-generated constructor stub
		}
		// CRUD&Display stuff
		public String doAddBook() {
			System.out.println(ebook);
			ebook.setNbOfWords(nbOfWords(ebook));
			readingManagementLocal.addEbook(ebook);
			navigateTo = "listBooks";
			setDisplayformadd(false);
			ebook = new Ebook();

			return navigateTo;
		}
		public String doUpdateEbook() {
			readingManagementLocal.updateEbook(modifiedBook);
			navigateTo = "listBooks";
			return "";
		}

		public String doDeleteEbook() {
			System.out.println(selectedBook);
			readingManagementLocal.deleteEbook(selectedBook);
			return "";
		}
		public void doDisplayAdd() {
			displayformadd = true;
		}

		public void doUndisplayAdd() {
		displayformadd = false;
			
		}

		
		
		// GETTERS&SETTERS
		
		public List<Ebook> getEbooks() {
			return ebooks=readingManagementLocal.viewLibrary();
		}
		public void setEbooks(List<Ebook> ebooks) {
			this.ebooks = ebooks;
		}
		public Boolean getDisplayformadd() {
			return displayformadd;
		}
		public void setDisplayformadd(Boolean displayformadd) {
			this.displayformadd = displayformadd;
		}
	
		public List<String> getCategories() {
			return Arrays.asList(categories);
		}
		public Ebook getEbook() {
			return ebook;
		}
		public void setEbook(Ebook ebook) {
			this.ebook = ebook;
		}
		public Ebook getSelectedBook() {
			return selectedBook;
		}
		public void setSelectedBook(Ebook selectedBook) {
			this.selectedBook = selectedBook;
		}
		public LoginBean getLoginBean() {
			return loginBean;
		}
		public void setLoginBean(LoginBean loginBean) {
			this.loginBean = loginBean;
		}
		public List<String> getTypes() {
			return Arrays.asList(types);
		}
		public List<Ebook> getFilteredBooks() {
			return filteredBooks;
		}
		public void setFilteredBooks(List<Ebook> filteredBooks) {
			this.filteredBooks = filteredBooks;
		}

		public Ebook getModifiedBook() {
			return modifiedBook;
		}
		public void setModifiedBook(Ebook modifiedBook) {
			this.modifiedBook = modifiedBook;
		}
		
		
		
		
		// Event Handling	
		public void onRowSelect(SelectEvent event) {
			FacesMessage msg = new FacesMessage("Book selected",
					((Ebook) event.getObject()).getTitle());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		public void onRowUnselect(UnselectEvent event) {
			FacesMessage msg = new FacesMessage("Book Unselected",
					((Ebook) event.getObject()).getTitle());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		public void onTabChange(TabChangeEvent event) {
			FacesMessage msg = new FacesMessage("Tab opened", "Active Tab: "
					+ event.getTab().getTitle());
			selectedBook = readingManagementLocal.findEbookByEbookTitle(event.getTab()
					.getTitle().toString().trim());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(selectedBook);
		}

		public void onTabClose(TabCloseEvent event) {
			FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: "
					+ event.getTab().getTitle());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			selectedBook = new Ebook();
			System.out.println(selectedBook);
		}

		//public void onRowEdit(RowEditEvent event) {
			//doUpdatePlace();
			//System.out.println(place + " selected " + selectedPlace + "modified "
			//		+ modifiedPlace);
			//FacesMessage msg = new FacesMessage("Place edited",
				//	((Place) event.getObject()).getName());
			//FacesContext.getCurrentInstance().addMessage(null, msg);
		//}

		//public void onRowCancel(RowEditEvent event) {
			//FacesMessage msg = new FacesMessage("Edit Cancelled",
			//	((Place) event.getObject()).getName());
	//	FacesContext.getCurrentInstance().addMessage(null, msg);
		//}

		public void onCellEdit(CellEditEvent event) {
			Object oldValue = event.getOldValue();
			Object newValue = event.getNewValue();
			if (newValue != null && !newValue.equals(oldValue)) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Cell Changed", "Old: " + oldValue + ", New:" + newValue);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}
			
			

	
		
		public Integer nbOfWords( Ebook book)
		{
			String content=book.getContent().trim();
			Integer nb = 1;
			for(int i=0; i<content.length();i++)
			{ if(Character.isWhitespace(content.charAt(i)))
						nb++;
			}
			return nb;
			
		}
		public List<Ebook> doRefresh(){
//			 Boolean trip= false;
//			 if (trip=false)
//			 {
//				getEbooks()
//			 }
//			 else
//			 {
				ebooks= readingManagementLocal.lookUpEbook("sa");
//			 }
	
			return ebooks;
}
		
		
}

