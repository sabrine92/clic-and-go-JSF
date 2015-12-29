package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Card
 *
 */
@Entity
public class Card implements Serializable {

	private String cardId;
	private Integer pwd;
	private Double amount;
	private static final long serialVersionUID = 1L;

	public Card() {
		super();
	}

	public Card(String cardId, Integer pwd, Double amount) {
		super();
		this.cardId = cardId;
		this.pwd = pwd;
		this.amount = amount;
	}

	@Id
	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", pwd=" + pwd + ", amount=" + amount
				+ "]";
	}

}
