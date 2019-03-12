package application.gui;

import java.time.LocalDate;

import application.Session;
import application.db.Database;
import application.db.model.Task;
import application.db.model.type.TaskStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StatusandComments extends Dialog<Task> {

	private int status =0;

	public StatusandComments(Task task) {
		Label l1 = new Label("Task1...  "); 
		Label l2 = new Label ("Assigned dev");
		HBox hb = new HBox(l1, l2);

		RadioButton r1 = new RadioButton("assigned");
		RadioButton r2 = new RadioButton("on work");
		RadioButton r3 = new RadioButton("done");
		VBox vbox = new VBox(20,hb, r1, r2, r3);

		ToggleGroup tg = new ToggleGroup();
		r1.setToggleGroup(tg);
		r2.setToggleGroup(tg);
		r3.setToggleGroup(tg);

		r1.setSelected(task.getStatus() == TaskStatus.ASSIGNED.getType());
		r2.setSelected(task.getStatus() == TaskStatus.ONWORK.getType());
		r3.setSelected(task.getStatus() == TaskStatus.DONE.getType());

		tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				if(r1.isSelected()) {
					l2.setText("Assigned");
					status = TaskStatus.ASSIGNED.getType(); 
				}
				if(r2.isSelected()) {
					l2.setText("It's on Work");
					status = TaskStatus.ONWORK.getType();
				}
				if(r3.isSelected()) {
					l2.setText("it's finished");
					status = TaskStatus.DONE.getType();
				}
			}

		});
		TextArea comment = new TextArea("Please add your comment here...");
		comment.setPrefWidth(350);
		Label l3 = new Label("Actuell Start Date");
		DatePicker datePicker = new DatePicker();
		datePicker.setValue(LocalDate.now());
		HBox vb = new HBox(10, l3,datePicker);
		Label l4 = new Label("Actuell End Date");
		DatePicker datePicker1 = new DatePicker();
		datePicker1.setValue(LocalDate.now());
		HBox hb3 = new HBox(10, l4,datePicker1);
		VBox picker = new VBox(10,vb,hb3);

		Button btn =new Button("save");
		btn.setPrefWidth(150);

		btn.setOnAction(e->{
			if(comment.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR, "Comment is Required!");
				alert.showAndWait();
			}
			else if(datePicker.getValue() == null) {
				Alert alert = new Alert(AlertType.ERROR, "Start Date is Required!");
				alert.showAndWait();				
			}
			else {

				task.setComments(comment.getText());
				task.setStatus(status);
				task.setActuellStartDate(datePicker.getValue());
				task.setActuellEndDate(datePicker1.getValue());

				Database.updateTask(task);
				close();
			}
		});

		Button btn1 = new Button("cancel");
		btn1.setPrefWidth(150);
		HBox hb2 = new HBox(20,btn,btn1);

		VBox vb2 = new VBox(40, comment, hb2);
		HBox hbox = new HBox(10,vbox, vb2);
		HBox hbox1 = new HBox(10,hbox,picker);

		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setAlignment(Pos.CENTER);

		this.getDialogPane().setContent(hbox1);
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);

	}


}
