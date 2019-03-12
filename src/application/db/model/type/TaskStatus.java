package application.db.model.type;

public enum TaskStatus {
	ASSIGNED(0),
	ONWORK(1),
	DONE(2);
	
	private int type;   // in kilograms

	TaskStatus(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
