package application.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.db.model.Employee;
import application.db.model.Project;
import application.db.model.Task;

public class Seeder {

	public static void creatUserTable() throws SQLException {
		Database.CreateUserTable();
	}
	
	public static boolean insertUserRecords() throws SQLException {
		ArrayList<Employee> userList = new ArrayList<>();
		userList.add(new Employee("admin", "", "", "", "", "", 1));
		//userList.add(new Employee("manal", "manal", "manal", "Hanna", "manal@gmail.com", "06888", 1));
		//userList.add(new Employee("nibras", "nibras", "nibras", "Hanna", "manal@gmail.com", "06888", 2));
				
		for (Employee employee : userList) {
			Database.insertUser(employee);
			System.out.println(employee + " eingetragen");
		}
		return true;
	}

	public static void testUpdatePassword(int id, String password)
	{
		Database.updateEmployeePassword(id, password);
	}
	
	public static void readUserRecords() {
		List<Employee> userList = Database.allUsers();
		for(Employee employee: userList){
			System.out.println(employee);
		}
	}

	public static void DropUserTable() throws SQLException {
		Database.DropUserTable();
		
	}

	public static void DropProjectTable() throws SQLException {
		Database.DropProjectTable();
		
	}
	
	public static void createProjectTable() throws SQLException {
		Database.CreateProject();
		
	}

	public static void readProjectRecords() {
		List<Project> projectList = Database.allProjects();
		for(Project project: projectList){
			System.out.println(project);
		}
		
	}

	public static void DropTaskTable() throws SQLException {
		Database.DropTaskTable();
		
	}
	
	public static void createTaskTable() throws SQLException {
		Database.CreateTask();
	}
	
	public static void readTaskRecords() {
		List<Task> taskList = Database.allTasks();
		for(Task task: taskList){
			System.out.println(task);
		}
	}

	
}
