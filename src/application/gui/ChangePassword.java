package application.gui;

import application.db.Database;
import application.db.model.Employee;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ChangePassword extends Dialog<Employee> {
	
	public ChangePassword(Employee employee) {
		this.setTitle("Change User Password");
		GridPane gp = new GridPane();
		Label l1 = new Label ("Old password");
		gp.add(l1, 0, 0);
		PasswordField t1 = new PasswordField();
		gp.add(t1, 1, 0);
		Label l2 = new Label ("new password");
		gp.add(l2, 0, 1);
		PasswordField t2 = new PasswordField();
		gp.add(t2, 1, 1);
		Label l3 = new Label ("repeat new password");
		gp.add(l3, 0, 2);
		PasswordField t3 = new PasswordField();
		gp.add(t3, 1, 2);
		 
		Button save =new Button("Save");
		save.setOnAction(e->{
			Employee loggedInEmployee = employee;
	    	//prove the old Password == to the password in DB
	    	//prove that the new Password and the repeat new Password is equal
	    	Employee databaseEmployee = Database.getEmployeeByName(loggedInEmployee.getName());
	    	if(t1.getText().equals(databaseEmployee.getPassword())) {
	    		if(t2.getText().equals(t3.getText())){
	    			Database.updateEmployeePassword(databaseEmployee.getId(), t2.getText());
	    			close();
	    		}else {
	    			Alert alert = new Alert(AlertType.ERROR, "new Password is Wrong!");
	    			alert.showAndWait();
	    		}
	    	}
	    	else {
	    		Alert alert = new Alert(AlertType.ERROR, "Old Password is Wrong!");
    			alert.showAndWait();
	    	}
		});
		VBox vb = new VBox(gp,save);
		this.getDialogPane().setContent(vb);
		//ButtonType save =  new ButtonType("Save",ButtonData.OK_DONE);
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel); 
	}
	
	
	/*@Override
	public void start(Stage primaryStage) {
		GridPane gp = new GridPane();
		Label l1 = new Label ("Old password");
		gp.add(l1, 0, 0);
		TextField t1 = new TextField();
		gp.add(t1, 1, 0);
		Label l2 = new Label ("new password");
		gp.add(l2, 0, 1);
		TextField t2 = new TextField();
		gp.add(t2, 1, 1);
		Label l3 = new Label ("repeat new password");
		gp.add(l3, 0, 2);
		TextField t3 = new TextField();
		gp.add(t3, 1, 2);
		Button save =  new Button("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Session session = Session.getInstance();
		    	Employee loggedInEmployee = session.getLoggedInUser();
		    	//prove the old Password == to the password in DB
		    	//prove that the new Password and the repeat new Password is equal
		    	Employee databaseEmployee = Database.getEmployeeByName(loggedInEmployee.getName());
		    	if(t1.getText().equals(databaseEmployee.getPassword())) {
		    		if(t2.getText().equals(t3.getText())){
		    			Database.updateEmployeePassword(databaseEmployee.getId(), t2.getText());
		    			
		    		}
		    	}
		    }	
		});

		Button cancel =  new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	
		    }
		});

		HBox hb = new HBox(save,cancel);
		VBox vb = new VBox(gp,hb);
		primaryStage.setScene(new Scene(vb));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}*/
}
