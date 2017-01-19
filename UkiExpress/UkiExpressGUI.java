
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * The user interface that serve as the purpose of a order menu.
 * @author QiQi Yuki Ou
 */
public class UkiExpressGUI extends Application {
	UkiExpressPresenter presenter;
	CreditCard card;

	public UkiExpressGUI(UkiExpressPresenter presenter) {
		this.presenter = presenter;
		presenter.attachView(this);
	}

	@Override
	public void start(Stage primaryStage) {
		BorderPane ui = new BorderPane();
		final double[] totalPrice = new double[1]; // total price that the customers pay
		final HashMap<String, Integer> pair = new HashMap<>(); // Store the name and price for each item

		// Top Pane. The shop's name: Uki Express
		HBox titlePane = new HBox();
		titlePane.getChildren().add(new Label("Welcome to Uki Express"));
		titlePane.setAlignment(Pos.CENTER);
		titlePane.setStyle(STYLESHEET_MODENA);

		// Right Pane. This is the order pane that handle the order selection.
		// Including selection of items, cancel order, confirm order, enter
		// credit card number.
		VBox orderPane = new VBox();

		// The textfield where you enter your credit card number.
		GridPane text = new GridPane();
		TextField orderDetails = new TextField("Enter your Credit Card Number");
		Label cc = new Label("You have enter...");
		cc.textProperty().bind(orderDetails.textProperty());
		text.add(orderDetails, 0, 0);
		text.add(cc, 1, 0);
		orderPane.getChildren().add(text); // added to the orderpane.

		// The button to cancel the entire order.
		VBox displayPane = new VBox();
		Button cancel = new Button("Cancel");
		cancel.setOnMouseReleased(e -> {
			totalPrice[0] = 0;
			pair.clear();
			displayPane.getChildren().clear();
		});
		cancel.setCancelButton(true);
		orderPane.getChildren().add(cancel); // added the cancel button to the orderpane.

		// Center Pane.
		// Create Button Array for menuItems
		UkiExpressPresenter ukiExpressPresenter = new UkiExpressPresenter();
		ukiExpressPresenter.loadMenuItems(); // load menu items from presenter.

		FlowPane menuPane = new FlowPane();

		for (int i = 0; i < ukiExpressPresenter.size(); i++) {
			Button button = new Button(ukiExpressPresenter.getName(i)); // create button with given menu item name
			double price = ukiExpressPresenter.getPrice(i); // price for each item
			String name = ukiExpressPresenter.getName(i); // name for each item
			
			VBox finalDisplayPane = displayPane;

			button.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					totalPrice[0] += price;
					pair.put(name, pair.containsKey(name) ? pair.get(name) + 1 : 1);
					// clear the display pane before adding new item
					displayPane.getChildren().clear();
					displayPane.getChildren().add(new Text("MenuItem\tItemCount"));
					// add each item to display Pane
					for (String element : pair.keySet()) {
						displayPane.getChildren().add(new Text(element + " " + pair.get(element)));
					}
					displayPane.getChildren().add(new Text("...................................\n\n"));
					displayPane.getChildren().add(new Text(String.format("Total Price is %.2f", totalPrice[0]) + ""));
				}
			});
			menuPane.getChildren().add(button);
		}

		// The button that confirm the order.
		// Also print the receipts, check if credit card is valid, and identify
		// the type of credit card.
		// If the credit card is valid, then the order is proceed successfully.
		Button confirm = new Button("Confirm");
		confirm.setOnMouseReleased(e -> {
			displayPane.getChildren().clear();

			System.out.println("\n\n---------Order is confirmed---------\n");
			System.out.println("---------Printing out receipt----------\n");
			System.out.println("     Welcome to McPatterns!");
			System.out.println("        777 Story Rd");
			System.out.println("      San Jose, CA95122");
			System.out.println("        (408)298-6533");
			System.out.println("         OPEN HOURS:");
			System.out.println("       MON-SUN 6AM-12AM\n");
			System.out.println("You have order:");

			for (Map.Entry<String, Integer> entry : pair.entrySet()) // print selected order item out
			{
				String key = entry.getKey();
				Integer value = entry.getValue();
				System.out.println(value + " " + key);
			}

			System.out.println("\nTotal Price is: $" + totalPrice[0]);
			String ordernum = orderDetails.getText();
			System.out.println("You have enter: " + ordernum + " as your credit card number");
			int cardType = UkiExpressPresenter.getCardID(ordernum);
			CreditCard card = null;
			try {
				if ((UkiExpressPresenter.validCC(ordernum))) { // validate the cc#
					switch (cardType) {
					case 0:
						card = new VisaCC(ordernum);
						break;
					case 1:
						card = new MasterCC(ordernum);
						break;
					case 2:
						card = new AmeriExpress(ordernum);
						break;
					case 3:
						card = new DiscoverCC(ordernum);
					}
					System.out.println("\n  Processing...");
					System.out.println("Transcation success");
					System.out.println(
							"This is a " + UkiExpressPresenter.getCardName(cardType) + " card. " + card.toString());
				} else {
					System.out.println("Transcation unsucessful");
					System.out.println("This card is invalid or unsupported");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("Error: unable to read");
			}
			displayPane.getChildren().clear();
			System.out.println("\nThank you for choosing Uki Express.");
			System.out.println("Have a nice day!");
		});
		orderPane.getChildren().add(confirm); // added the confirm button and the button action to the orderPane

		ui.setTop(titlePane);
		ui.setBottom(orderPane);
		ui.setCenter(menuPane);
		ui.setRight(displayPane);
		Scene scene = new Scene(ui, 800, 600);

		primaryStage.setTitle("Uki Express");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}