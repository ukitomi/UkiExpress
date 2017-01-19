import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that identify what property menu model has.
 * MenuModel contains name and price.
 * @author QiQi Yuki Ou
 */
class MenuModel {
	// Add your implementation for Menu Items here.
	// Determine what data you want to store for each item.

	// Data: name|price

	public ArrayList<String> item = new ArrayList<String>();
	public ArrayList<Double> price = new ArrayList<Double>();
	String name;
	double cost;

	/**
	 * Constructor of MenuModel
	 * @param name the name
	 * @param cost the price
	 */
	MenuModel(String name, double checkout) {
		this.name = name;
		this.cost = checkout;
	}

	/**
	 * Add the name and price individually to the arraylist
	 */
	public MenuModel() {
		for (int i = 0; i < FileLoader.productList.size(); i++) {
			item.add(FileLoader.productList.get(i).name);
		}
		for (int i = 0; i < FileLoader.productList.size(); i++) {
			price.add(FileLoader.productList.get(i).cost);
		}
	}
}