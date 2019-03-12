package application.gui;

import application.Session;
import application.db.Database;
import application.db.model.Employee;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeEmployeeInfo extends Dialog<Employee> {

	
	public ChangeEmployeeInfo(Employee employee) {
		this.setTitle("Edit Employee");
		GridPane gp = new GridPane();
		Label label = new Label("Change Employee Information");
		Label l1 = new Label("Name");
		
		gp.add(l1, 0, 0);
		TextField txt1 = new TextField();
		gp.add(txt1, 1, 0);
		txt1.setText(employee.getName());
		Label l2 = new Label("Passwort");
		// l2.setPrefWidth(150);
		//gp.add(l2, 0, 1);
		TextField txt2 = new TextField();
		//gp.add(txt2, 1, 1);
		Label l3 = new Label("First name");
		// l2.setPrefWidth(150);
		gp.add(l3, 0, 2);
		TextField txt3 = new TextField();
		txt3.setText(employee.getFirstName());
		gp.add(txt3, 1, 2);
		Label l4 = new Label("Last name");
		// l2.setPrefWidth(150);
		gp.add(l4, 0, 3);
		TextField txt4 = new TextField();
		txt4.setText(employee.getLastName());
		gp.add(txt4, 1, 3);
		Label l5 = new Label("Email");
		gp.add(l5, 0, 4);
		TextField txt5 = new TextField();
		txt5.setText(employee.getEmail());
		gp.add(txt5, 1, 4);
		Label l6 = new Label("Phone");
		gp.add(l6, 0, 5);
		TextField txt6 = new TextField();
		txt6.setText(employee.getPhone());
		gp.add(txt6, 1, 5);
		Label l7 = new Label("Possition");
		gp.add(l7, 0, 6);
		ComboBox<String> cob7 = new ComboBox<>();
		
		cob7.setItems(FXCollections.observableArrayList("Manager", "Developer"));
		cob7.setPrefWidth(200);
		cob7.setValue(employee.getType() == 1? "Manager" : "Developer");
		gp.add(cob7, 1, 6);
		gp.setVgap(20);
		gp.setHgap(20);
		gp.setAlignment(Pos.CENTER);
		Button btn1 = new Button("save");
		btn1.setPrefWidth(150);
		
		btn1.setOnAction(e->{
			if(txt1.getText().equals("")) {
    			Alert alert = new Alert(AlertType.ERROR, "Name is Required!");
    			alert.showAndWait();
			}
			else {
			//Employee employee1 = Session.getInstance().getLoggedInUser();
			employee.setName(txt1.getText());
			employee.setFirstName(txt2.getText());
			employee.setLastName(txt3.getText());
			employee.setEmail(txt4.getText());
			employee.setPhone(txt5.getText());
			employee.setType(cob7.getValue().equals("Manager") ? 1 : 2);
			//employee.setUser(Session.getInstance().getLoggedInUser());
			Database.updateEmployee(employee);
			close();
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
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);
}

}
