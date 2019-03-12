package application.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import application.Session;
import application.db.Database;
import application.db.model.Employee;
import application.db.model.Project;
import application.db.model.ProjectTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class ProjectList extends Dialog {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private TableView<ProjectTableView> table= new TableView<>();
	private Button edit = new Button("edit");
	
	private Button delete = new Button("Delete");
	private Button task = new Button("Tasks");
	
	public ProjectList() {
		this.setTitle("Projects");
		GridPane gp = new GridPane();
		TableColumn<ProjectTableView,String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<ProjectTableView,String> shortDesc = new TableColumn<>("Short Description");
		shortDesc.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));
		TableColumn<ProjectTableView,String> longDesc = new TableColumn<>("Long Description");
		longDesc.setCellValueFactory(new PropertyValueFactory<>("longDescription"));
		TableColumn<ProjectTableView, LocalDate> dateCol = new TableColumn<ProjectTableView, LocalDate>("Start Date");
		dateCol.setCellValueFactory(cellData -> cellData.getValue().startdateProperty());
		dateCol.setCellFactory(col -> new TableCell<ProjectTableView, LocalDate>() {
			 @Override
			    protected void updateItem(LocalDate item, boolean empty) {

			        super.updateItem(item, empty);
			        if (empty)
			            setText(null);
			        else
			            setText(String.format(item.format(formatter)));
			    }
			});
	
		final Popup popup = new Popup(); popup.setX(300); popup.setY(200);
	    table.getColumns().addAll(nameCol,shortDesc, longDesc, dateCol);
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			//popup.getContent().addAll(new Label(newSelection.getLongDescription()));
			enbaleDisableButtons();
			//popup.show(, 0, 0);
		});
		gp.add(table, 0, 0);
		Button add = new Button("add");
		add.setOnAction(e ->{
			AddProject ap = new AddProject();
			ap.showAndWait();
			refreshTableView();
		});
		task.setOnAction(e->{
			ProjectTableView ptv = table.getSelectionModel().getSelectedItem();
			TaskListManager tl = new TaskListManager(ptv.toProject());
			tl.showAndWait();
			
		});
		
		edit.setOnAction(e->{
			ProjectTableView ptv = table.getSelectionModel().getSelectedItem();
			ChangeProjectInfo editproj = new ChangeProjectInfo(ptv.toProject());
			editproj.showAndWait();
			refreshTableView();
			
		});
		delete.setOnAction(e->{
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete project or cancel?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() &&  result.get() == ButtonType.OK) {
			ProjectTableView ptv = table.getSelectionModel().getSelectedItem();
			Database.deleteTasksByProject(ptv.toProject().getId());
			Database.deleteProject(ptv.toProject());
			refreshTableView();
			}
		});
		HBox hb =new HBox(10,add,edit,delete,task);
		VBox vb = new VBox(gp,hb);
		this.getDialogPane().setContent(vb);
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);
		
		refreshTableView();
	}
	
	public void refreshTableView() {
		Employee emp = Session.getInstance().getLoggedInUser();
		ArrayList<Project> projectList = (ArrayList<Project>) Database.projectsByUserId(emp.getId());
		ObservableList<ProjectTableView>data=FXCollections.observableArrayList();  
		for (Project project : projectList) {
			ProjectTableView projectTableView = new ProjectTableView(project.getId(), project.getName(), project.getShortDescription(), project.getLongDescription(), project.getStartDate(), null);
			data.add(projectTableView); 
		}
		table.setItems(data);
		enbaleDisableButtons();
	}
	
	public void enbaleDisableButtons() {
		if(table.getSelectionModel().getSelectedItems().size() == 0) {
			edit.setDisable(true);
			delete.setDisable(true);
			task.setDisable(true);
		}
		else {
			edit.setDisable(false);
			delete.setDisable(false);
			task.setDisable(false);			
		}
	}
	
}
