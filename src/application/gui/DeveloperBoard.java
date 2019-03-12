package application.gui;

import java.net.URI;
import java.nio.file.Paths;

import application.Session;
import application.db.model.Employee;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeveloperBoard extends Application {

	@Override
	public void start(Stage primaryStage) {
		GridPane gridpane = new GridPane();
		Label photo;
		URI uri = Paths.get("C:\\Users\\manal\\Desktop\\PMT\\screenshot\\PMTool.jpg").toUri();
		photo = new Label("", new ImageView(uri.toString()));
		// VBox vbox1= new VBox(30, photo);
		gridpane.add(photo, 0, 0);
		String name = (Session.getInstance().getLoggedInUser()==null) ? "" : Session.getInstance().getLoggedInUser().getName();
		Label l1 = new Label("Guten Tag " + name);
		Button btn1 = new Button("Show Tasks");
		btn1.setPrefWidth(150);
		btn1.setOnAction(e->{
			TaskListDeveloper tl = new TaskListDeveloper();
			tl.showAndWait();
		});
		
		Button btn3 = new Button("Change own password");
		btn3.setPrefWidth(150);
		btn3.setOnAction(e -> {
			Employee emp = Session.getInstance().getLoggedInUser();
			ChangePassword em = new ChangePassword(emp);
			em.showAndWait();
		});
		VBox vbox2 = new VBox(25, l1, btn1, btn3);
		gridpane.add(vbox2, 1, 0);
		gridpane.setPadding(new Insets(5, 5, 5, 5));
		gridpane.setHgap(40);
		gridpane.setVgap(60);
		gridpane.setAlignment(Pos.CENTER);
		// HBox hbox = new HBox(40, vbox1, vbox2);
		primaryStage.setScene(new Scene(gridpane, 500, 300));
		primaryStage.setTitle("Developer Board");
		primaryStage.show();

	}

}
