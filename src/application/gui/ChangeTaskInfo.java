package application.gui;

import application.Session;
import application.db.Database;
import application.db.model.Employee;
import application.db.model.Task;
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
import javafx.stage.Stage;

public class ChangeTaskInfo extends Dialog<Task> {

	public ChangeTaskInfo(Task task) {
		Label label1 = new Label("Change Taskname Details");
		//label1.setAlignment(Pos.CENTER_LEFT);
		label1.setPadding(new Insets(5,5,5,5));
		GridPane gp = new GridPane();
		Label l1 = new Label("Task Name");
		gp.add(l1, 0, 0);
		TextField name = new TextField();
		name.setPrefWidth(200);
		name.setText(task.getName());
		gp.add(name, 1, 0);
		Label l0 = new Label("Short Description");
		gp.add(l0, 0, 1);
		TextArea shdes= new TextArea();
		shdes.setPrefSize(100, 200);
		gp.add(shdes, 1, 1);
		shdes.setText(task.getShortDescription());
		Label l2 = new Label("Long Description");
		gp.add(l2, 0, 2);
		TextArea lodes = new TextArea();
		lodes.setPrefSize(100, 200);
		gp.add(lodes, 1, 1);
		lodes.setText(task.getLongDescription());
		Label l3= new Label("Expected Start Date");
		gp.add(l3, 0, 3);
		DatePicker datePicker1 = new DatePicker();
		datePicker1.setValue(task.getExpectedStartDate());
		gp.add(datePicker1, 1, 3);
		Label l4= new Label("Expected finishing Date");
		gp.add(l4, 0, 4);
		DatePicker datepicker2 = new DatePicker();
		datepicker2.setValue(task.getExpectedEndDate());
		gp.add(datepicker2, 1, 4);
		Label l5 = new Label("Assign Developer");
		gp.add(l5, 0, 5);
		ComboBox<Employee> cob5 = new ComboBox<>();
		cob5.setItems(FXCollections.observableArrayList(Database.allDevelopers()));
		cob5.setPrefWidth(200);
		cob5.setValue(task.getDeveloper());
		gp.add(cob5, 1, 5);
		gp.setVgap(20);
		gp.setHgap(20);
		gp.setPadding(new Insets(5,5,5,5));
		gp.setAlignment(Pos.CENTER);
		Button btn1 = new Button("save");
		btn1.setPrefWidth(150);
		
		btn1.setOnAction(e->{
			if(name.getText().equals("")) {
    			Alert alert = new Alert(AlertType.ERROR, "Name is Required!");
    			alert.showAndWait();
			}
			else if(cob5.getSelectionModel().getSelectedItem() == null) {
    			Alert alert = new Alert(AlertType.ERROR, "Developer is Required!");
    			alert.showAndWait();				
			}
			else {
			task.setName(name.getText());
			task.setLongDescription(shdes.getText());
			task.setShortDescription(lodes.getText());
			task.setExpectedStartDate(datePicker1.getValue());
			task.setExpectedEndDate(datepicker2.getValue());
			task.setManager(Session.getInstance().getLoggedInUser());
			task.setDeveloper(cob5.getSelectionModel().getSelectedItem());
			Database.updateTask(task);
			close();
			}
		});
		
		Button btn2 = new Button("cancel");
		btn2.setPrefWidth(150);
		HBox hb= new HBox(20, btn1, btn2);
		hb.setAlignment(Pos.CENTER);
		VBox vb =new VBox(20,label1,gp,hb);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(5,5,5,5));
		//ButtonType cancel = ButtonType.CANCEL;
		this.getDialogPane().setContent(vb);
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);
	
	}

	
}
