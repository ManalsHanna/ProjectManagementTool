package application;
	
import java.net.URI;
import java.nio.file.Paths;
import java.sql.SQLException;

import application.db.Database;
import application.db.Seeder;
import application.db.model.Employee;
import application.gui.ChangePassword;
import application.gui.DeveloperBoard;
import application.gui.ManagerBoard;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
			Label photo;
			URI uri = Paths.get("C:\\Users\\manal\\Desktop\\PMT\\screenshot\\PMTool.jpg").toUri();
			photo = new Label("", new ImageView(uri.toString()));
			Label l3 = new Label();
			Label l1= new Label("User Name");
			TextField t1= new TextField();
			Label l2= new Label("Passwort");
			PasswordField t2 = new PasswordField();
			Button btn = new Button("Login");
			
			btn.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	Employee employee = Database.getEmployeeByName(t1.getText());
			    	if(employee == null) {
			    		l3.setText("User name or Password is wrong");
			    	}
			    	else {
			    	if(employee.getPassword().equals(t2.getText())) {
			    	    Session session = Session.getInstance();
			    	    session.setLoggedInUser(employee);
			    	    if((employee.getPassword() == null|| employee.getPassword().equals(""))) {
			    	    	ChangePassword md = new ChangePassword(employee); 
			    	    	md.showAndWait(); 
			    	    }
			    	    else {
			    	    	if(employee.getType()== 1) {
				    			ManagerBoard m = new ManagerBoard();
						    	m.start(primaryStage);	
				    		}
				    		else if(employee.getType()== 2) {
				    			DeveloperBoard m = new DeveloperBoard();
						    	m.start(primaryStage);	
				    		}	
			    	    }
			    	       
			    	}
			    	else {
			    		l3.setText("User name or Password is wrong");
			    	}
			    	}
			    	
			    }
			});
			
			btn.setPrefWidth(100);
			Button btn1 = new Button("Cancel");
			btn1.setPrefWidth(100);
			btn1.setOnAction(e->{
				System.exit(0);
			});
			//gridpane.add(photo, 0, 0);
			//gridpane.add(l1, 0, 0);
			//gridpane.add(t1, 1, 0);
			//gridpane.add(l2, 0, 1);
			//gridpane.add(t2, 1, 1);
			//gridpane.add(btn, 0, 3);
			//gridpane.add(btn1, 1, 3);
			//primaryStage.setScene(new Scene(gridpane,350,200));
			VBox vbox1= new VBox(10, photo);
			vbox1.setAlignment(Pos.CENTER);
			VBox vb2 = new VBox(10, l3);
			vb2.setAlignment(Pos.CENTER);
			HBox hbox1 = new HBox(10, l1, t1);
			hbox1.setAlignment(Pos.CENTER);
			HBox hbox2 = new HBox(22,l2, t2);
			hbox2.setAlignment(Pos.CENTER);
			HBox hbox3 = new HBox(20, btn, btn1);
			hbox3.setAlignment(Pos.CENTER);
			VBox vbox2 = new VBox(20,vbox1,vb2,hbox1,hbox2,hbox3);
			vbox2.setAlignment(Pos.CENTER);
			
			primaryStage.setScene(new Scene(vbox2,500,400));
			primaryStage.setTitle("Registration");
			primaryStage.show();
	}
	
	public static void main(String[] args) {
			try {
				initDataBase();
				launch(args);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

	private static void initDataBase() throws SQLException {
		//Seeder.DropUserTable();
		//Seeder.creatUserTable();
		//Seeder.createProjectTable();
		//Seeder.insertUserRecords();
		
		//Seeder.DropTaskTable();
		//Seeder.createTaskTable();
		
		Seeder.readUserRecords();
		Seeder.readProjectRecords();
		Seeder.readTaskRecords();
	}
}
