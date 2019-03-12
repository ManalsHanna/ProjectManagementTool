package application.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeveloperComments extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label l1 = new Label("Develper name and Task Name");
		TextArea txt =new TextArea();
		txt.setPrefWidth(100);
		Button btn = new Button("close");
		btn.setPrefWidth(150);
		VBox vb = new VBox(30, l1, txt, btn);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(10,10,10,10));
		primaryStage.setScene(new Scene(vb,400, 350));
		primaryStage.setTitle("Developer Comments");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
