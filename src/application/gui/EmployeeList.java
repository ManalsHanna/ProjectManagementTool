package application.gui;

import java.util.ArrayList;
import java.util.Optional;

import application.db.Database;
import application.db.model.Employee;
import application.db.model.EmployeeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class EmployeeList extends Dialog {

	private TableView<EmployeeTableView> table = new TableView<>();
	private Button add = new Button("add"); 
	private Button edit = new Button("edit");
	private Button delete = new Button("Delete");

	public EmployeeList() {
		this.setTitle("Employee List");
		BorderPane br = new BorderPane();
		add.setOnAction(e->{
			AddEmployee nd = new AddEmployee();
			nd.showAndWait();
			refreshTableView();
		});
		edit.setOnAction(e->{
			EmployeeTableView ptv = table.getSelectionModel().getSelectedItem();	
			ChangeEmployeeInfo cha = new ChangeEmployeeInfo((Employee) ptv.toEmployee());
			cha.showAndWait();
			refreshTableView();
			
		});
		delete.setOnAction(e->{
			EmployeeTableView employeeTableView = table.getSelectionModel().getSelectedItem();
			if(employeeTableView.getName().equals("admin")) {
				//alert user admin cannot be deleted
				Alert alert = new Alert(AlertType.ERROR, "user admin cannot be deleted");
				alert.showAndWait();
			}
			else {
				Alert alert1 = new Alert(AlertType.CONFIRMATION, "Delete project or cancel?");
				Optional<ButtonType> result = alert1.showAndWait();
				if (result.isPresent() &&  result.get() == ButtonType.OK) {
				Database.deleteEmployee((Employee) employeeTableView.toEmployee());
				refreshTableView();
			}
			}
		});

		HBox hb = new HBox(10,add,edit,delete);
		br.setBottom(hb);

		TableColumn<EmployeeTableView,String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<EmployeeTableView,String> firstn = new TableColumn<>("firstName");
		firstn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		TableColumn<EmployeeTableView,String> lastn = new TableColumn<>("lastname");
		lastn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		TableColumn<EmployeeTableView,String> email = new TableColumn<>("email");
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		TableColumn<EmployeeTableView,String> phone = new TableColumn<>("phone");
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		TableColumn<EmployeeTableView,String> poss = new TableColumn<>("Position");
		poss.setCellValueFactory(new PropertyValueFactory<>("typename"));
		table.getColumns().addAll(nameCol, firstn, lastn,email, phone, poss);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		br.setCenter(table);
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			//popup.getContent().addAll(new Label(newSelection.getLongDescription()));
			enbaleDisableButtons();
			//popup.show(, 0, 0);
		});
		this.getDialogPane().setContent(br);
		ButtonType cancel =  new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		this.getDialogPane().getButtonTypes().addAll(cancel);
		refreshTableView();
	}

	public void refreshTableView() {
		ArrayList<Employee> employeeList = (ArrayList<Employee>) Database.allUsers();
		ObservableList<EmployeeTableView>data=FXCollections.observableArrayList();  
		for (Employee employee : employeeList) {
			EmployeeTableView employeeTableView = new EmployeeTableView(employee.getId(), employee.getName(),employee.getPassword(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhone(), employee.getType());
			data.add(employeeTableView); 
		}
		table.setItems(data);
		enbaleDisableButtons();
	}

	public void enbaleDisableButtons() {
		if(table.getSelectionModel().getSelectedItems().size() == 0) {
			edit.setDisable(true);
			delete.setDisable(true);
		}
		else {
			edit.setDisable(false);
			delete.setDisable(false);
		}
	}
}
