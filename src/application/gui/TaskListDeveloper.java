package application.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import application.Session;
import application.db.Database;
import application.db.model.Employee;
import application.db.model.ProjectTableView;
import application.db.model.Task;
import application.db.model.TaskTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TaskListDeveloper extends Dialog {
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private TableView<TaskTableView> table = new TableView<>();
	
	private Button add = new Button("Change Status Comments Dates"); 
	private Button des = new Button("Description");
	
	public TaskListDeveloper() {
		BorderPane br = new BorderPane();
		HBox hb = new HBox(10,add, des);
		br.setBottom(hb);
		TableColumn<TaskTableView,String> pnameCol = new TableColumn<>("Project Name");
		pnameCol.setCellValueFactory(new PropertyValueFactory<>("projectName"));
		TableColumn<TaskTableView,String> nameCol = new TableColumn<>("Tasks Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<TaskTableView,String> shortDesc = new TableColumn<>("Short Description");
		shortDesc.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));
		TableColumn<TaskTableView,String> longDesc = new TableColumn<>("Long Description");
		longDesc.setCellValueFactory(new PropertyValueFactory<>("longDescription"));
		TableColumn<TaskTableView,String> comment = new TableColumn<>("Comment");
		comment.setCellValueFactory(new PropertyValueFactory<>("comments"));
		TableColumn<TaskTableView,String> status = new TableColumn<>("Status");
		status.setCellValueFactory(new PropertyValueFactory<>("statusName"));
		
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
		table.getColumns().addAll(pnameCol, nameCol,shortDesc,comment, status,exstCol,exendCol,acstCol,acenCol);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			//popup.getContent().addAll(new Label(newSelection.getLongDescription()));
			enbaleDisableButtons();
			//popup.show(, 0, 0);
		});
		br.setCenter(table);
		this.getDialogPane().setContent(br);
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);
		
		add.setOnAction(e->{
			TaskTableView ptv = table.getSelectionModel().getSelectedItem();
			StatusandComments sc = new StatusandComments(ptv.toTask());
			sc.showAndWait();
			refreshTableView();
			
		});
		des.setOnAction(e->{
			TaskTableView ptv = table.getSelectionModel().getSelectedItem();
			ReadDetails sc = new ReadDetails(ptv.toTask());
			sc.showAndWait();
			
			
		});
		refreshTableView();
		
	}
	public void refreshTableView() {
		Employee developer = Session.getInstance().getLoggedInUser();
		ArrayList<Task> taskList = (ArrayList<Task>) Database.tasksByDeveloperId(developer.getId());
		
		ObservableList<TaskTableView>data=FXCollections.observableArrayList();  
		for (Task task : taskList) {
			TaskTableView taskTableView = new TaskTableView(task.getId(), task.getName(),task.getShortDescription(), task.getLongDescription(),task.getComments(),task.getStatus(),
					task.getExpectedStartDate(),task.getExpectedEndDate(),task.getActuellStartDate(),task.getActuellEndDate(),task.getManager(),task.getDeveloper(),task.getProject());
			data.add(taskTableView); 
		}
		table.setItems(data);
		enbaleDisableButtons();
		
	}
	
	
	public void enbaleDisableButtons() {
		if(table.getSelectionModel().getSelectedItems().size() == 0) {
			add.setDisable(true);
			des.setDisable(true);
		}
		else {
			add.setDisable(false);
			des.setDisable(false);
		}
	}


}
