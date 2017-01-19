import java.io.*;
import java.util.*;

/*
 * A class that read the class, break down the item and save the item into arraylist.
 */
class FileLoader {
	public static ArrayList<MenuModel> productList;
	private static File inputFile;

	/**
	 * Load the menu items from the file.
	 */
	static void loadMenuItems() {
		// read text file & create an arraylist of menu items;
		productList = new ArrayList<MenuModel>();
		try {
			Scanner in = new Scanner(inputFile);
			if (inputFile.length() != 0) {
				while (in.hasNextLine()) {
					String line = in.nextLine();
					System.out.println(line);
					if (line.endsWith("|")) {
						System.out.println(
								"Please complete the menu selection. One of the item in the menu is incomplete.");
						System.out.println("Error: missing price");
						System.exit(0);
					}
					String[] products = line.split("\\|"); // Item | Cost
					String name = products[0];
					String price = products[1];
					if (name.length() == 0) {
						System.out.println(
								"Please complete the menu selection. One of the item in the menu is incomplete.");
						System.out.println("Error: missing name");
						System.exit(0);
					} else if (!price.matches("(\\d+\\.?)+")) {
						System.out.println(
								"Please complete the menu selection. One of the item in the menu is incomplete");
						System.out.println("Error: the price contain invalid character");
						System.exit(0);
					} else {
						double cost = Double.parseDouble(price);
						MenuModel theItem = new MenuModel(name, cost);
						productList.add(theItem);
					}
				}
			} else {
				System.out.println("Unable to read file. Please make sure the file has any content");
				System.exit(0);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Unable to load order from " + inputFile + ".");
		}
	}

	static void getFile(String filename) {
		inputFile = new File(filename);

	}
}