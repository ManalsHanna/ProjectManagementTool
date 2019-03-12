package application.db.model;

import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProjectTableView {

	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty shortDescription;
	private SimpleStringProperty longDescription;
	private SimpleObjectProperty<LocalDate> startdate;
	private SimpleObjectProperty<Employee> employee;
	
	public ProjectTableView(int id, String name, String shortDescription,
			String longDescription, LocalDate startdate, Employee employee) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.shortDescription = new SimpleStringProperty(shortDescription);
		this.longDescription = new SimpleStringProperty(longDescription);
		this.startdate= new SimpleObjectProperty<>(startdate);
		this.employee = new SimpleObjectProperty<>(employee);
	}
	
	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public int getId() {
		return id.get();
	}

	public void setId(int pId) {
		id.set(pId);
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}
	
	public String getName() {
		return name.get();
	}

	public void setName(String pName) {
		name.set(pName);
	}

	public SimpleStringProperty shortDescriptionProperty() {
		return shortDescription;
	}
	
	public String getShortDescription() {
		return shortDescription.get();
	}

	public void setShortDescription(String pShortDescription) {
		name.set(pShortDescription);
	}

	
	public SimpleStringProperty longDescriptionProperty() {
		return longDescription;
	}

	public String getLongDescription() {
		return longDescription.get();
	}

	public void setLongDescription(String pLongDescription) {
		longDescription.set(pLongDescription);
	}
	
	public SimpleObjectProperty<LocalDate> startdateProperty() {
		return startdate;
	}

	public LocalDate getStartdate() {
		return startdate.get();
	}

	public void setStartdate(LocalDate pStartdate) {
		startdate.set(pStartdate);
	}
	
	public SimpleObjectProperty<Employee> employeeProperty() {
		return employee;
	}

	public Employee getEmployee() {
		return employee.get();
	}

	public void setEmployee(Employee employeep) {
		employee.set(employeep);
	}

	public Project toProject() {
		return new Project(getId(), getName(), getShortDescription(), getLongDescription(), getStartdate(), getEmployee());
	}
}

