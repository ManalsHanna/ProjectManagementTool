package application.gui;

import java.awt.color.CMMException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import application.Session;
import application.db.Database;
import application.db.model.Employee;
import application.db.model.Project;
import application.db.model.Task;
import application.db.model.type.EmployeeType;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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


public class AddTask extends Dialog<Project> {

	
	
	public AddTask(Project project){
		this.setTitle("Add New Task");
		Label label1 = new Label("Create a new Task");
		//label1.setAlignment(Pos.CENTER_LEFT);
		label1.setPadding(new Insets(5,5,5,5));
		GridPane gp = new GridPane();
		Label l1 = new Label("Task Name");
		gp.add(l1, 0, 0);
		TextField txt1 = new TextField();
		txt1.setPrefWidth(200);
		gp.add(txt1, 1, 0);
		Label sho = new Label("Short Desc");
		gp.add(sho, 0, 1);
		TextField shortd = new TextField();
		shortd.setPrefWidth(200);
		gp.add(shortd, 1, 1);
		Label l2 = new Label("detail Description");
		gp.add(l2, 0, 2);
		TextArea det = new TextArea();
		det.setPrefSize(100, 200);
		gp.add(det, 1, 2);
		Label l3= new Label("expStart Date");
		gp.add(l3, 0, 3);
		DatePicker datePicker1 = new DatePicker();
		datePicker1.setValue(LocalDate.now());
		gp.add(datePicker1, 1, 3);
		Label l4= new Label("Expected endDate");
		gp.add(l4, 0, 4);
		DatePicker datePicker2 = new DatePicker();
		datePicker2.setValue(LocalDate.now());
		//datePicker.setOnAction(e -> txt4.setPromptText(datePicker.setValue(LocalDate.now()))); 
		gp.add(datePicker2, 1, 4);
		Label l5 = new Label("Assign Developer");
		gp.add(l5, 0, 5);
		ComboBox<Employee> cob5 = new ComboBox<>();
		cob5.setItems(FXCollections.observableArrayList(Database.allDevelopers()));
		cob5.setPrefWidth(200);
		gp.add(cob5, 1, 5);
		gp.setVgap(20);
		gp.setHgap(20);
		gp.setPadding(new Insets(5,5,5,5));
		gp.setAlignment(Pos.CENTER);
		Button btn1 = new Button("save");
		btn1.setPrefWidth(150);
		btn1.setOnAction(e->{
			if(txt1.getText().equals("")) {
    			Alert alert = new Alert(AlertType.ERROR, "Name is Required!");
    			alert.showAndWait();
			}
			else if(cob5.getSelectionModel().getSelectedItem() == null) {
    			Alert alert = new Alert(AlertType.ERROR, "Developer is Required!");
    			alert.showAndWait();				
			}
			else {
			Employee manager = Session.getInstance().getLoggedInUser();
			Task task = new Task(txt1.getText(), shortd.getText(), det.getText(), datePicker1.getValue(),
					datePicker2.getValue(), manager, cob5.getSelectionModel().getSelectedItem(), project);
			try {
				Database.insertTask(task);
				close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		HBox hb= new HBox(20, btn1);
		hb.setAlignment(Pos.CENTER);
		VBox vb =new VBox(20,label1,gp,hb);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(5,5,5,5));
		this.getDialogPane().setContent(vb);
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);
	}

	
}
