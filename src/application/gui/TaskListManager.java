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
import application.db.model.Task;
import application.db.model.TaskTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TaskListManager extends Dialog<Project> {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private TableView<TaskTableView> table = new TableView<>();
	private Button add = new Button("Add Task"); 
	
	private Button edit = new Button("Edit Task");
	private Button delete = new Button("Delete");
	private Button comment = new Button("Comments");
	private Button cancel = new Button("Cancel"); 
	private Project project = null;
	
	public TaskListManager(Project project){
		this.setTitle("Tasks");
		
		this.project = project;
		
		BorderPane br = new BorderPane();
		Label proj = new Label("Project Name ...");
		proj.setAlignment(Pos.CENTER_LEFT);
		br.setTop(proj);
		
		HBox hbox2 = new HBox(10,add,edit,delete,comment,cancel);
		br.setBottom(hbox2);
		
//		TableColumn<TaskTableView,String> pnameCol = new TableColumn<>("Project Name");
//		pnameCol.setCellValueFactory(new PropertyValueFactory<>("projectName"));
		TableColumn<TaskTableView,String> nameCol = new TableColumn<>("Tasks Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<TaskTableView,String> shortDesc = new TableColumn<>("Short Description");
		shortDesc.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));
		TableColumn<TaskTableView,String> longDesc = new TableColumn<>("Long Description");
		longDesc.setCellValueFactory(new PropertyValueFactory<>("longDescription"));
		TableColumn<TaskTableView,String> commentview = new TableColumn<>("Comment");
		commentview.setCellValueFactory(new PropertyValueFactory<>("comments"));
		TableColumn<TaskTableView,String> status = new TableColumn<>("Status");
		status.setCellValueFactory(new PropertyValueFactory<>("statusName"));
		TableColumn<TaskTableView,String> dev = new TableColumn<>("Developer");
		dev.setCellValueFactory(new PropertyValueFactory<>("developerName"));
		TableColumn<TaskTableView, LocalDate> exstCol = new TableColumn<TaskTableView, LocalDate>("Expected Start Date");
		exstCol.setCellValueFactory(cellData -> cellData.getValue().expectedstartdateProperty());
		exstCol.setCellFactory(col -> new TableCell<TaskTableView, LocalDate>() {
			 @Override
			    protected void updateItem(LocalDate item, boolean empty) {

			        super.updateItem(item, empty);
			        if (empty || item == null)
			            setText(null);
			        else
			            setText(String.format(item.format(formatter)));
			    }
			});
		TableColumn<TaskTableView, LocalDate> exendCol = new TableColumn<TaskTableView, LocalDate>("Expected End Date");
		exendCol.setCellValueFactory(cellData -> cellData.getValue().expectedEndtDateProperty());
		exendCol.setCellFactory(col -> new TableCell<TaskTableView, LocalDate>() {
			 @Override
			    protected void updateItem(LocalDate item, boolean empty) {

			        super.updateItem(item, empty);
			        if (empty || item == null)
			            setText(null);
			        else
			            setText(String.format(item.format(formatter)));
			    }
			});
		TableColumn<TaskTableView, LocalDate> acstCol = new TableColumn<TaskTableView, LocalDate>("Actuell Start Date");
		acstCol.setCellValueFactory(cellData -> cellData.getValue().actuellStartDateProperty());
		acstCol.setCellFactory(col -> new TableCell<TaskTableView, LocalDate>() {
			 @Override
			    protected void updateItem(LocalDate item, boolean empty) {

			        super.updateItem(item, empty);
			        if (empty || item == null)
			            setText(null);
			        else
			            setText(String.format(item.format(formatter)));
			    }
			});
		TableColumn<TaskTableView, LocalDate> acenCol = new TableColumn<TaskTableView, LocalDate>("Actuell End Date");
		acenCol.setCellValueFactory(cellData -> cellData.getValue().actuellEndDateProperty());
		acenCol.setCellFactory(col -> new TableCell<TaskTableView, LocalDate>() {
			 @Override
			    protected void updateItem(LocalDate item, boolean empty) {

			        super.updateItem(item, empty);
			        if (empty || item == null)
			            setText(null);
			        else
			            setText(String.format(item.format(formatter)));
			    }
			});		
		table.getColumns().addAll(nameCol,status,shortDesc,longDesc, dev,exstCol,exendCol,acstCol,acenCol);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			enbaleDisableButtons();
		});
		br.setCenter(table);
		
		add.setOnAction(e->{
			AddTask at = new AddTask(project);
			at.showAndWait();
			refreshTableView();
		});
		edit.setOnAction(e->{
			TaskTableView ptv = table.getSelectionModel().getSelectedItem();
			ChangeTaskInfo ct = new ChangeTaskInfo(ptv.toTask());
			ct.showAndWait();
			refreshTableView();
		});
		delete.setOnAction(e->{
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete Task or cancel?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() &&  result.get() == ButtonType.OK) {
			TaskTableView ptv = table.getSelectionModel().getSelectedItem();
			Database.deleteTask(ptv.toTask());
			refreshTableView();
			}			
		});
		comment.setOnAction(e->{
			TaskTableView ptv = table.getSelectionModel().getSelectedItem();
			Comment c = new Comment(ptv.toTask());
			c.showAndWait();
		});
		
		this.getDialogPane().setContent(br);
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);
		refreshTableView();
		
	}
	public void refreshTableView() {
		//Employee emp = Session.getInstance().getLoggedInUser();
		ArrayList<Task> taskList = (ArrayList<Task>) Database.tasksByProjectId(project.getId());
		
		ObservableList<TaskTableView>data=FXCollections.observableArrayList();  
		for (Task task : taskList) {
			TaskTableView taskTableView = new TaskTableView(task.getId(), task.getName(),task.getShortDescription(), task.getLongDescription(),task.getComments(),task.getStatus(),
					task.getExpectedStartDate(),task.getExpectedEndDate(),task.getActuellStartDate(),task.getActuellEndDate(),task.getManager(),task.getDeveloper(),task.getProject());
			data.add(taskTableView); 
		}
		table.setItems(data);
		enbaleDisableButtons();
		
	}
	private void enbaleDisableButtons() {
		if(table.getSelectionModel().getSelectedItems().size() == 0) {
			edit.setDisable(true);
			delete.setDisable(true);
			comment.setDisable(true);
		}
		else {
			edit.setDisable(false);
			delete.setDisable(false);
			comment.setDisable(false);			
		}
	}

	
}
