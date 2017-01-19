import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that will handle the model <-> UI communication. Reference:
 * Validate credit card from Stack Overflow and with the help of CS Club Tutors.
 * @author QiQi Yuki Ou
 */

public class UkiExpressPresenter {
	MenuModel model;
	UkiExpressGUI view;
	ArrayList<String> item = new ArrayList<String>();
	double checkout;
	public static final int INVALID = -1;
	public static final int VISA = 0;
	public static final int MASTERCARD = 1;
	public static final int AMERICAN_EXPRESS = 2;
	public static final int DISCOVER = 3;
	private static final String[] cardNames = { "Visa", "MasterCard", "American Express", "Discover" };

	/**
	 * Read a file and load the menu items from FileLoader.
	 * Initialize menu model that read each item, which contains name and price.
	 */
	void loadMenuItems() {
		FileLoader.loadMenuItems();
		model = new MenuModel();
	}

	/**
	 * Attach view
	 * @param view the GUI view
	 */
	void attachView(UkiExpressGUI view) {
		this.view = view;
	}

	/**
	 * Get the name of the item
	 * @param i the index of item's name
	 * @return the correspond index of item's name
	 */
	String getName(int i) {
		return model.item.get(i);
	}

	/**
	 * Get the price of the item
	 * @param i the index of item's price
	 * @return return the correspond index of item's price
	 */
	Double getPrice(int i) {
		return model.price.get(i);
	}

	/**
	 * Get the size of the item 
	 * @return the size of the item
	 */
	int size() {
		return model.item.size();
	}

	/**
	 * Check the validity of credit card
	 * @param number the number that passed into the argument, in this case it is the credit card
	 * @return if the credit card is valid or not
	 * @throws Exception
	 */
	public static boolean validCC(String number) throws Exception {
		int CardID;
		if ((CardID = getCardID(number)) != -1) {
			return validCCNumber(number);
		}
		return false;
	}

	/**
	 * Check the type of card.
	 * @param number the number that passed into the argument, in this case it is the credit card
	 * @return the type of credit card
	 */
	public static int getCardID(String number) {
		int valid = INVALID;

		String digit1 = number.substring(0, 1);
		String digit2 = number.substring(0, 2);
		String digit3 = number.substring(0, 3);
		String digit4 = number.substring(0, 4);

		if (isNumber(number)) {
			if (digit1.equals("4")) {
				if (number.length() == 13 || number.length() == 16)
					valid = VISA;
			} else if (digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0) {
				if (number.length() == 16)
					valid = MASTERCARD;
			} else if (digit4.equals("6011")) {
				if (number.length() == 16) {
					valid = DISCOVER;
				}
			} else if (digit2.equals("34") || digit2.equals("37")) {
				if (number.length() == 15)
					valid = AMERICAN_EXPRESS;
			}
		}

		return valid;
	}

	/**
	 * Check if the string that's passed into the argument is all number. Credit
	 * card cannot have letters
	 * @param n the number that passed into the argument, in this case it is the credit card
	 * @return true if the card is all number
	 */
	public static boolean isNumber(String n) {
		try {
			double d = Double.valueOf(n).doubleValue();
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Get the card name.
	 * @param id the number that passed into the argument, in this case it is the credit card
	 * @return the string
	 */
	public static String getCardName(int id) {
		return (id > -1 && id < cardNames.length ? cardNames[id] : "");
	}

	/**
	 * Check if the credit card is valid. For reference see above comments
	 * @param n the number that passed into the argument, in this case it is the credit card
	 * @return true if the credit card is valid
	 */
	public static boolean validCCNumber(String n) {
		try {
			int j = n.length();

			String[] s1 = new String[j];

			for (int i = 0; i < n.length(); i++)
				s1[i] = "" + n.charAt(i);

			int checksum = 0;

			for (int i = s1.length - 1; i >= 0; i -= 2) {
				int k = 0;

				if (i > 0) {
					k = Integer.valueOf(s1[i - 1]).intValue() * 2;
					if (k > 9) {
						String s = "" + k;
						k = Integer.valueOf(s.substring(0, 1)).intValue() + Integer.valueOf(s.substring(1)).intValue();
					}
					checksum += Integer.valueOf(s1[i]).intValue() + k;
				} else {
					checksum += Integer.valueOf(s1[0]).intValue();
				}
			}
			return ((checksum % 10) == 0);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}