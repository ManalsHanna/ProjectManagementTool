package application.gui;

import java.sql.SQLException;

import application.db.Database;
import application.db.model.Employee;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddEmployee extends Dialog {

	public AddEmployee() {
		this.setTitle("ADD Employee");
		GridPane gp = new GridPane();
		Label label = new Label("Insert a new Developer");
		Label l1 = new Label("Name");

		gp.add(l1, 0, 0);
		TextField txt1 = new TextField();
		gp.add(txt1, 1, 0);
		Label l2 = new Label("Passwort");
		// l2.setPrefWidth(150);
		//gp.add(l2, 0, 1);
		TextField txt2 = new TextField();
		//gp.add(txt2, 1, 1);
		Label l3 = new Label("First n");
		// l2.setPrefWidth(150);
		gp.add(l3, 0, 2);
		TextField txt3 = new TextField();
		gp.add(txt3, 1, 2);
		Label l4 = new Label("Last n");
		// l2.setPrefWidth(150);
		gp.add(l4, 0, 3);
		TextField txt4 = new TextField();
		gp.add(txt4, 1, 3);
		Label l5 = new Label("Email");
		gp.add(l5, 0, 4);
		TextField txt5 = new TextField();
		gp.add(txt5, 1, 4);
		Label l6 = new Label("Phone");
		gp.add(l6, 0, 5);
		TextField txt6 = new TextField();
		gp.add(txt6, 1, 5);
		Label l7 = new Label("Possition");
		gp.add(l7, 0, 6);
		ComboBox<String> cob7 = new ComboBox<>();
		cob7.setItems(FXCollections.observableArrayList("Manager", "Developer"));
		cob7.setPrefWidth(200);
		cob7.setValue("Manager");
		gp.add(cob7, 1, 6);
		gp.setVgap(20);
		gp.setHgap(20);
		gp.setAlignment(Pos.CENTER);
		Button btn1 = new Button("save");
		btn1.setPrefWidth(150);

		btn1.setOnAction(e -> {
			if (txt1.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR, "Name is Required!");
				alert.showAndWait();
			}
			else if  (txt1.getText().equals("admin")) {
				Alert alert = new Alert(AlertType.ERROR, "Name admin is already taken!");
				alert.showAndWait();
			}
			else {
				int type = 0;
				if (cob7.getValue().equals("Manager")) {
					type = 1;
				} else {
					type = 2;
				}
				Employee employee = new Employee(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(),
						txt5.getText(), txt6.getText(), type);
				try {
					Database.insertUser(employee);
					close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Button btn2 = new Button("cancel");
		btn2.setPrefWidth(150);
		HBox vb = new HBox(20, btn1, btn2);
		vb.setAlignment(Pos.CENTER);
		VBox hb2 = new VBox(30, label, gp, vb);
		hb2.setAlignment(Pos.CENTER);
		hb2.setPadding(new Insets(5, 5, 5, 5));
		this.getDialogPane().setContent(hb2);
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);

	}

}
