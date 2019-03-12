package application.db.model.type;

public enum EmployeeType {
	MANAGER(1),
	DEVELOPER(2);
	private int type;   

	EmployeeType(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
