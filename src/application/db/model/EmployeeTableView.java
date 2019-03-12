package application.db.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EmployeeTableView {
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty password;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastname;
	private SimpleStringProperty email;
	private SimpleStringProperty phone;
	private SimpleIntegerProperty type;
	private SimpleStringProperty typename;
	
	public EmployeeTableView(int id, String name, String password, String firstName,
			String lastname, String email, String phone,
			int type) {		
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.password = new SimpleStringProperty(password);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastname = new SimpleStringProperty(lastname);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
		this.type = new SimpleIntegerProperty (type);
		this.typename = new SimpleStringProperty (type == 1 ? "Manager" : "Developer");
	}

	public SimpleIntegerProperty idProperty() {
		return id;
	}

	public int getId() {
		return id.get();
	}

	public void setId(int eid) {
		id.set(eid);
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}
	
	public String getName() {
		return name.get();
	}

	public void setName(String ename) {
		name.set(ename);
	}

	public SimpleStringProperty passwordProperty() {
		return password;
	}
	
	public String getPassword() {
		return password.get();
	}

	public void setPassword(String ePassword) {
		name.set(ePassword);
	}

	
	public SimpleStringProperty firstNameProperty() {
		return firstName;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String eFirstName) {
		firstName.set(eFirstName);
	}

	public SimpleStringProperty lastnameProperty() {
		return lastname;
	}
	
	public String getLastname() {
		return lastname.get();
	}

	public void setLastname(String eLastname) {
		lastname.set(eLastname);
	}

	public SimpleStringProperty emailProperty() {
		return email;
	}

	public String getEmail() {
		return email.get();
	}
	public void setEmail(String eEmail) {
		email.set(eEmail);
	}

	public SimpleStringProperty phoneProperty() {
		return phone;
	}

	public String getPhone() {
		return phone.get();
	}
	public void setPhone(String ePhone) {
		phone.set(ePhone);
	}

	public SimpleIntegerProperty typeProperty() {
		return type;
	}

	public int getType() {
		return type.get();
	}
	public void setType(int eType) {
		type.set(eType);
	}

	public SimpleStringProperty typenameProperty() {
		return typename;
	}
	
	public String getTypename() {
		return typename.get();
	}

	public void setTypename(String ename) {
		typename.set(ename);
	}

	@Override
	public String toString() {
		return "EmployeeTableView [id=" + id + ", name=" + name + ", password=" + password + ", firstName=" + firstName
				+ ", lastname=" + lastname + ", email=" + email + ", phone=" + phone + ", type=" + type + "]";
	}

	public Object toEmployee() {
		return new Employee(getId(), getName(), getPassword(), getFirstName(), getLastname(), getEmail(),getPhone(),getType());
	
	}
	
	
	
	
	
	
	
}
