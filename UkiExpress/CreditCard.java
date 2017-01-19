
/**
 * Classes that will print out the credit card type messages
 * @author QiQi Yuki Ou
 */
public class CreditCard {

	String number;

	public CreditCard(String number) {
		super();
		this.number = number;
	}

	public CreditCard() {
		super();
	}

}

class MasterCC extends CreditCard {
	public MasterCC(String cardNumber) {
		super.number = cardNumber;
	}

	public String toString() {
		return "MasterCC [" + super.number + "]";
	}
}

class VisaCC extends CreditCard {
	public VisaCC(String cardNumber) {
		super.number = cardNumber;
	}

	public String toString() {
		return "VisaCC [" + super.number + "]";
	}
}

class AmeriExpress extends CreditCard {
	public AmeriExpress(String cardNumber) {
		super.number = cardNumber;
	}

	public String toString() {
		return "AmericanExpress [" + super.number + "]";
	}
}

class DiscoverCC extends CreditCard {
	public DiscoverCC(String cardNumber) {
		super.number = cardNumber;
	}

	public String toString() {
		return "DiscoverCC [" + super.number + "]";
	}
}