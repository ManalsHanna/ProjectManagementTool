package application.gui;

import application.db.model.Task;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.ButtonBar.ButtonData;

public class Comment extends Dialog<Task> {

	public Comment(Task task) {
		TextArea txt = new TextArea();
		txt.setText(task.getComments());
		this.getDialogPane().setContent(txt);
		ButtonType cancel =  new ButtonType("Close",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);
		
	}
}
