package application;

import application.db.model.Employee;

public class Session {
	Employee employee;
	
	private static Session instance = new Session();
	
	private Session() {
		
	}
	
	public void setLoggedInUser(Employee employee) {
		this.employee = employee;
	}
	
	public Employee getLoggedInUser() {
		return this.employee;
	}
	
	public static Session getInstance() {
		return  instance;
	}
}
