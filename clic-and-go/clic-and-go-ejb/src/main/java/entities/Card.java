package entities;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Card
 *
 */
@Entity

public class Card implements Serializable {

	
	private Integer cardId;
	private Integer pwd;
	private Double amount;
	private static final long serialVersionUID = 1L;

	public Card() {
		super();
	}   
	@Id    
	public Integer getCardId() {
		return this.cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}   
	public Integer getPwd() {
		return this.pwd;
	}

	public void setPwd(Integer pwd) {
		this.pwd = pwd;
	}   
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
   
}
