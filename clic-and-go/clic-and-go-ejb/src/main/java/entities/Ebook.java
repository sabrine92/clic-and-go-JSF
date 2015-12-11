package entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ebooks
 *
 */
@Entity

public class Ebook implements Serializable {

	
	private Integer id;
	private String title;
	private String author;
	private String description;
	private Category category;
	private Type type;
	private String content;
	private int nbOfWords;
	private static final long serialVersionUID = 1L;

	public Ebook() {
		super();
	} 
	


	@Override
	public String toString() {
		return "Ebook [id=" + id + ", title=" + title + ", author=" + author
				+ ", description=" + description + ", category=" + category
				+ ", type=" + type + ", nbOfWords=" + nbOfWords + "]";
	}


	

	public Ebook(String title, String author, String description,
			Category category, Type type, String content) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.category = category;
		this.type = type;
		this.content = content;
		this.nbOfWords = nbOfWords(content);
	}



	@Id    
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}   
	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}   
	public int getNbOfWords() {
		return this.nbOfWords;
	}

	public void setNbOfWords(int nbOfWords) {
		this.nbOfWords = nbOfWords;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Integer nbOfWords( String content)
	{
		content=getContent().trim();
		Integer nb = 0;
		for(int i=0; i<content.length();i++)
		{ if(Character.isWhitespace(content.charAt(i)))
					nb++;
		}
		return nb;
		
	}
	
   
}
