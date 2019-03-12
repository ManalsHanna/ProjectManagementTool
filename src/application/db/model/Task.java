package application.db.model;

import java.time.LocalDate;


public class Task {
	
	private int id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private String comments;
	private int status;
	private LocalDate expectedStartDate;
	private LocalDate expectedEndDate;
	private LocalDate actuellStartDate;
	private LocalDate actuellEndDate;
	private Employee manager;
	private Employee developer;
	private Project project;
	
	
	public Task(String name, String shortDescription, String longDescription,
			LocalDate expectedStartDate, LocalDate expectedEndDate, 
			 Employee manager,Employee developer, Project project) {
		
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.expectedStartDate = expectedStartDate;
		this.expectedEndDate = expectedEndDate;
		this.manager = manager;
		this.developer = developer;
		this.project = project;
	}


	public Task(int id, String name, String shortDescription, String longDescription, String comments, int status,
			LocalDate expectedStartDate, LocalDate expectedEndDate, LocalDate actuellStartDate,
			LocalDate actuellEndDate, Employee manager, Employee developer, Project project) {
		super();
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.comments = comments;
		this.status = status;
		this.expectedStartDate = expectedStartDate;
		this.expectedEndDate = expectedEndDate;
		this.actuellStartDate = actuellStartDate;
		this.actuellEndDate = actuellEndDate;
		this.manager = manager;
		this.developer = developer;
		this.project = project;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getExpectedStartDate() {
		return expectedStartDate;
	}

	public void setExpectedStartDate(LocalDate expectedStartDate) {
		this.expectedStartDate = expectedStartDate;
	}

	public LocalDate getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(LocalDate expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public LocalDate getActuellStartDate() {
		return actuellStartDate;
	}

	public void setActuellStartDate(LocalDate actuellStartDate) {
		this.actuellStartDate = actuellStartDate;
	}

	public LocalDate getActuellEndDate() {
		return actuellEndDate;
	}

	public void setActuellEndDate(LocalDate actuellEndDate) {
		this.actuellEndDate = actuellEndDate;
	}

	
	public Employee getManager() {
		return manager;
	}


	public void setManager(Employee manager) {
		this.manager = manager;
	}


	public Employee getDeveloper() {
		return developer;
	}


	public void setDeveloper(Employee developer) {
		this.developer = developer;
	}


	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}


	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", shortDescription=" + shortDescription + ", longDescription="
				+ longDescription + ", comments=" + comments + ", status=" + status + ", expectedStartDate="
				+ expectedStartDate + ", expectedEndDate=" + expectedEndDate + ", actuellStartDate=" + actuellStartDate
				+ ", actuellEndDate=" + actuellEndDate + ", manager=" + manager + ", developer=" + developer
				+ ", project=" + project + "]";
	}

	
	
}
