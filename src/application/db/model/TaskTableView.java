package application.db.model;

import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


public class TaskTableView {

	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty shortDescription;
	private SimpleStringProperty longDescription;
	private SimpleStringProperty comments;
	private SimpleIntegerProperty status;
	private SimpleObjectProperty<LocalDate> expectedStartDate;
	private SimpleObjectProperty<LocalDate> expectedEndtDate;
	private SimpleObjectProperty<LocalDate> actuellStartDate;
	private SimpleObjectProperty<LocalDate> actuellEndDate;
	private SimpleObjectProperty<Employee> managerName;
	private SimpleObjectProperty<Employee> developerName;
	private SimpleObjectProperty<Project> projectName;
	private SimpleStringProperty statusName;
	
	public TaskTableView(int id, String name, String shortDescription,
			String longDescription, String comments, int status,
			LocalDate expectedStartDate, LocalDate expectedEndtDate,
			LocalDate actuellStartDate, LocalDate actuellEndDate,
			Employee managerName,Employee developerName, Project projectName) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.shortDescription =  new SimpleStringProperty(shortDescription);
		this.longDescription = new SimpleStringProperty(longDescription);
		this.comments = new SimpleStringProperty(comments);
		this.status = new SimpleIntegerProperty(status);
		this.expectedStartDate = new SimpleObjectProperty<>(expectedStartDate);
		this.expectedEndtDate = new SimpleObjectProperty<>(expectedEndtDate);
		this.actuellStartDate = new SimpleObjectProperty<>(actuellStartDate);
		this.actuellEndDate = new SimpleObjectProperty<>(actuellEndDate);
		this.managerName = new SimpleObjectProperty<>(managerName);
		this.developerName = new SimpleObjectProperty<>(developerName);
		this.projectName = new SimpleObjectProperty<>(projectName);
		String statusDisplay = "";
		if(status == 0) {
			statusDisplay = "Assigned";
		}
		else if(status == 1) {
			statusDisplay = "On Work";
		}
		else if(status == 2) {
			statusDisplay = "Done";
		}
		this.statusName = new SimpleStringProperty (statusDisplay);
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

	public void setName(String tName) {
		name.set(tName);
	}

	public SimpleStringProperty shortDescriptionProperty() {
		return shortDescription;
	}
	
	public String getShortDescription() {
		return shortDescription.get();
	}

	public void setShortDescription(String tShortDescription) {
		name.set(tShortDescription);
	}

	
	public SimpleStringProperty longDescriptionProperty() {
		return longDescription;
	}

	public String getLongDescription() {
		return longDescription.get();
	}

	public void setLongDescription(String tLongDescription) {
		longDescription.set(tLongDescription);
	}
	
	public SimpleStringProperty commentsProperty() {
		return comments;
	}

	public String getComments() {
		return comments.get();
	}

	public void setComments(String tComments) {
	comments.set(tComments);
	}
	
	public SimpleIntegerProperty statusProperty() {
		return status;
	}

	public Integer getStatus() {
		return status.get();
	}

	public void setStatus(int tStatus) {
		status.set(tStatus);
	}

	public SimpleObjectProperty<LocalDate> expectedstartdateProperty() {
		return expectedStartDate;
	}

	public LocalDate getexpectedStartDate() {
		return expectedStartDate.get();
	}

	public void setexpectedStartDate(LocalDate texpectedStartDate) {
		expectedStartDate.set(texpectedStartDate);
	}
	
	public SimpleObjectProperty<LocalDate> expectedEndtDateProperty() {
		return expectedEndtDate;
	}

	public LocalDate getexpectedEndtDate() {
		return expectedEndtDate.get();
	}

	public void setexpectedEndtDate(LocalDate texpectedEndtDate) {
		expectedEndtDate.set(texpectedEndtDate);
	}
	
	public SimpleObjectProperty<LocalDate> actuellStartDateProperty() {
		return actuellStartDate;
	}

	public LocalDate getActuellStartDate() {
		return actuellStartDate.get();
	}

	public void setActuellStartDate(LocalDate tActuellStartDate) {
		actuellStartDate.set(tActuellStartDate);
	}

	public SimpleObjectProperty<LocalDate> actuellEndDateProperty() {
		return actuellEndDate;
	}

	public LocalDate getActuellEndDate() {
		return actuellEndDate.get();
	}

	public void setActuellEndDate(LocalDate tActuellEndDate) {
		actuellEndDate.set(tActuellEndDate);
	}

	public SimpleObjectProperty<Employee> managerNameProperty() {
		return managerName;
	}

	public Employee getManagerName() {
		return managerName.get();
	}

	public void setManagerName(Employee tmanagerName) {
		managerName.set(tmanagerName);
	}
	
	public SimpleObjectProperty<Employee> developerNameProperty() {
		return developerName;
	}

	public Employee getDeveloperName() {
		return developerName.get();
	}

	public void setDeveloperName(Employee tdeveloperName) {
		developerName.set(tdeveloperName);
	}
	

	public SimpleObjectProperty<Project> projectNameProperty() {
		return projectName;
	}

	public Project getProjectName() {
		return projectName.get();
	}

	public void setProjectName(Project tProjectName) {
		projectName.set(tProjectName);
	}
	
	public SimpleStringProperty statusNameProperty() {
		return statusName;
	}
	
	public String getStatusName() {
		return statusName.get();
	}

	public void setStatusName(String tName) {
		statusName.set(tName);
	}

	public Task toTask() {
		
		return new Task(getId(),getName(),getShortDescription(), getLongDescription(), getComments(), getStatus(), getexpectedStartDate(),
				getexpectedEndtDate(), getActuellStartDate(), getActuellEndDate(), getManagerName(), getDeveloperName(), getProjectName());
	}



	
}

