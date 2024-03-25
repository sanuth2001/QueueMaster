package com.example.store;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class FoodStoreController {
	@FXML
	private AnchorPane stage;
	private static BurgerQueue cashier1;
	private static BurgerQueue cashier2;
	private static BurgerQueue cashier3;
	private static WaitingQueue waitingQueue;
	private int burgers;

	@FXML
	private Text cus11;
	@FXML
	private Text cus12;
	@FXML
	private Text cus21;
	@FXML
	private Text cus22;
	@FXML
	private Text cus23;
	@FXML
	private Text cus31;
	@FXML
	private Text cus32;
	@FXML
	private Text cus33;
	@FXML
	private Text cus34;
	@FXML
	private Text cus35;
	@FXML
	private TextField couName;
	@FXML
	private ListView<String> listview;
	@FXML
	private TextArea cusDetails;
	private Text[] textArray;
	ArrayList<Customer> customers = new ArrayList<>();
	ArrayList<Customer> waitingListCustomers = new ArrayList<>();

	public void setData(int burgers, BurgerQueue cashier1, BurgerQueue cashier2, BurgerQueue cashier3,
			WaitingQueue waitingQueue) {
		this.burgers = burgers;
		this.cashier1 = cashier1;
		this.cashier2 = cashier2;
		this.cashier3 = cashier3;
		this.waitingQueue = waitingQueue;
		textArray = new Text[] { cus11, cus12, cus21, cus22, cus23, cus31, cus32, cus33, cus34, cus35 };
		customers.addAll(cashier1.getCustomers());
		customers.addAll(cashier2.getCustomers());
		customers.addAll(cashier3.getCustomers());
		customers.addAll(waitingQueue.getCustomers());
		waitingListCustomers.addAll(waitingQueue.getCustomers());
		setWaitingList();
		setdetails();
	}

	@FXML
	private void setdetails() {

		setCashier1();
		setCashier2();
		setCashier3();
	}

	private void setCashier1() {
		try {
			for (int i = 0; i < 2; i++) {
				Text text = textArray[i];
				text.setText(cashier1.getCustomer(i).getFullName());
			}
		} catch (NullPointerException e) {
			// Blank catch block (no stack trace printing)
			System.out.println();
		}
	}

	private void setCashier2() {
		try {
			for (int i = 0; i < 3; i++) {
				Text text = textArray[i + 2];
				text.setText(cashier2.getCustomer(i).getFullName());
			}
		} catch (NullPointerException e) {
			// Blank catch block (no stack trace printing)
			System.out.println();
		}
	}

	private void setCashier3() {
		try {
			for (int i = 0; i < 5; i++) {
				Text text = textArray[i + 5];
				text.setText(cashier3.getCustomer(i).getFullName());
			}
		} catch (NullPointerException e) {
			// Blank catch block (no stack trace printing)
			System.out.println();
		}
	}

	private void setWaitingList() {
		ObservableList<String> names = FXCollections.observableArrayList();
		
		for (Customer customer : waitingListCustomers) {
			if (customer != null) {
				names.add(customer.getFullName());
			}
		}
		listview.setItems(names);
	}

	public void searchCoustermer() {
		String name = "";
		name = stringInput(couName.getText().trim().toLowerCase());
		search(name);

	}

	private String stringInput(String text) {
		String pattern = "^[a-zA-Z]+$";
		if (Pattern.matches(pattern, text.trim())) {
			return text.trim();
		} else {
			cusDetails.setText("Please enter a valid name");
			return null; // Return null or handle the invalid input case appropriately
		}
	}

	private void search(String name) {
		try {
			String lowerCaseName = name.toLowerCase();
			boolean found = false;

			for (Customer customer : customers) {
				String fullName = customer.getFullName().toLowerCase();
				String firstName = customer.getFirstName().toLowerCase();
				String secondName = customer.getSecondName().toLowerCase();

				if (fullName.equals(lowerCaseName) || firstName.equals(lowerCaseName)
						|| secondName.equals(lowerCaseName)) {
					String fname = customer.getFirstName();
					String sname = customer.getSecondName();
					int burgers = customer.getBurgersRequired();
					cusDetails.setText(
							"First name: " + fname + "\n" + "Second name: " + sname + "\n" + "Burgers: " + burgers);
					found = true;
					break;
				}
			}

			if (!found) {
				cusDetails.setText("No Customer found");
			}
		} catch (Exception e) {

		}

	}

}
