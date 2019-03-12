package application.gui;

import java.sql.SQLException;
import java.time.LocalDate;

import application.Session;
import application.db.Database;
import application.db.model.Employee;
import application.db.model.Project;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddProject extends Dialog {

	public AddProject() {
		this.setTitle("Add a new Project");
		GridPane gp = new GridPane();
		Label label = new Label("Starting a new Project");
		Label l1 = new Label("Name");

		gp.add(l1, 0, 0);
		TextField txt1 = new TextField();
		gp.add(txt1, 1, 0);
		Label l2 = new Label("Description");
		//l2.setPrefWidth(150);
		gp.add(l2, 0, 1);
		TextArea txt2 = new TextArea();
		//txt2.setPrefWidth(200);
		gp.add(txt2, 1, 1);
		Label l3 = new Label("shortDesc");

		gp.add(l3, 0, 2);
		TextField txt3 = new TextField();
		gp.add(txt3, 1, 2);
		gp.setVgap(20);
		gp.setHgap(20);
		gp.setAlignment(Pos.CENTER);
		
		
		Label l4 = new Label("StartDate");
		//l2.setPrefWidth(150);
		
		gp.add(l4, 0, 3);
		
		
		
		
		DatePicker datePicker = new DatePicker();
		datePicker.setValue(LocalDate.now());
		//datePicker.setOnAction(e -> txt4.setPromptText(datePicker.setValue(LocalDate.now()))); 
		gp.add(datePicker, 1, 3);
		Button btn1 = new Button("save");
		btn1.setPrefWidth(150);
		btn1.setOnAction(e->{
			if(txt1.getText().equals("")) {
    			Alert alert = new Alert(AlertType.ERROR, "Name is Required!");
    			alert.showAndWait();
			}
			else {
			Employee employee = Session.getInstance().getLoggedInUser();
			Project project = new Project(txt1.getText(), txt3.getText(), txt2.getText(), datePicker.getValue(), employee);
			try {
				Database.insertProject(project);
				close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}

		});
		Button btn2 = new Button("cancel");
		btn2.setPrefWidth(150);
		HBox vb= new HBox(20, btn1, btn2);
		vb.setAlignment(Pos.CENTER);
		VBox hb2 = new VBox(30,label,gp,vb);
		hb2.setAlignment(Pos.CENTER);
		hb2.setPadding(new Insets(5,5,5,5));
		this.getDialogPane().setContent(hb2);
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);
	}

	

	
}
