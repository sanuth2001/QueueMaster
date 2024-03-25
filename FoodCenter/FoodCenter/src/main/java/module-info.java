module com.example.store {
	requires javafx.controls;
	requires javafx.fxml;

	opens com.example.store to javafx.fxml;

	exports com.example.store;
}