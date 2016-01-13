package beans;


 
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import entities.User;
 
@ManagedBean
public class RadioView {
     
    private String speed; 
    
    private List<String> speeds; 
    private User user;
    private Boolean displayforNumberOfWords = false;
    @PostConstruct
    public void init() {
        speeds= new ArrayList<String>();
        speeds.add("Slow reader : elementary school level");
        speeds.add("Average reader : middle school level");
        speeds.add("Good reader : high school level");
        speeds.add("Fast reader : trained to fast reading");
    }
 
    

  
 
    public String getSpeed() {
        return speed;
    }
 
    public void setSpeed(String speed) {
        this.speed = speed;
    }
 
    public List<String> getSpeeds() {
        return speeds;
    }





	public User getUser() {
		return user;
	}





	public void setUser(User user) {
		this.user = user;
		
	}





	public Boolean getDisplayforNumberOfWords() {
		return displayforNumberOfWords;
	}





	public void setDisplayforNumberOfWords(Boolean displayforNumberOfWords) {
		this.displayforNumberOfWords = displayforNumberOfWords;
	}
	public void doDisplayforNumberOfWords() {
		if (user.getNbOfWordsPerMinute()==null);
		{
		displayforNumberOfWords = true;
	}
		}

	public void doUndisplayforNumberOfWords() {
		displayforNumberOfWords = false;
	}
}