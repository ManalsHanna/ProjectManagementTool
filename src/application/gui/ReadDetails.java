package application.gui;

import application.db.model.Task;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;

public class ReadDetails extends Dialog<Task> {
	public ReadDetails(Task task) {
		Label l1 = new Label("Task desc.");
		TextArea txt1 = new TextArea();
		txt1.setText(task.getLongDescription());
		Label l2 = new Label("Project desc.");
		TextArea txt2 = new TextArea();
		txt2.setText(task.getProject().getLongDescription());
		VBox hb = new VBox(10, l1, txt1,l2,txt2);
		this.getDialogPane().setContent(hb);
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);
		
	}
}
