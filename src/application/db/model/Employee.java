package application.db.model;

public class Employee {
	private int id;
	private String name;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private int type;
	
	public Employee(String name, String password, String firstName, String lastName, String email, String phone,
			int type) {
		
		this.name = name;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.type = type;
	}
	
	
	public Employee(int id, String name, String password, String firstName, String lastName, String email, String phone,
			int type) {
		
		this.id = id;
		this.name = name;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.type = type;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		//return "User [id=" + id + ", name=" + name + ", password=" + password + ", firstName=" + firstName
		//		+ ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", type=" + type + "]";
		return name + " " + lastName;
	}
	
	
	
}
