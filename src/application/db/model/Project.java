package application.db.model;

import java.time.LocalDate;

public class Project {

	private int id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private LocalDate startDate;
	private Employee employee;
	
	public Project(int id, String name, String shortDescription, String longDescription, LocalDate startDate, Employee employee) {
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.startDate = startDate;
		this.employee = employee;
	}
	

	public Project(String name, String shortDescription, String longDescription, LocalDate startDate,
			Employee employee) {
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.startDate = startDate;
		this.employee = employee;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Employee getUser() {
		return employee;
	}

	public void setUser(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return name;
				}
	
	
	
}
