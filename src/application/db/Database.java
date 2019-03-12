package application.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.derby.iapi.sql.dictionary.FileInfoDescriptor;

import application.db.model.Project;
import application.db.model.Task;
import application.db.model.type.EmployeeType;
import application.db.model.Employee;

public class Database {

	public static final String connString = "jdbc:derby:D:\\Development\\Projects\\PMT\\database;";

	public static void DropTables() throws SQLException {
		DropTaskTable();
		DropProjectTable();
		DropUserTable();
	}

	public static void DropUserTable() throws SQLException {
		String ct = "DROP TABLE EMPLOYEE";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			stmt.executeUpdate(ct);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void CreateUserTable() throws SQLException {
		String ct = "CREATE TABLE EMPLOYEE (" + "ID integer generated always as identity, " + "NAME VARCHAR(200),"
				+ "PASSWORD VARCHAR(200), " + "FIRST_NAME VARCHAR(200)," + "LAST_NAME VARCHAR(200),"
				+ "EMAIL VARCHAR(200)," + "PHONE VARCHAR(200)," + "TYPE INTEGER," + "PRIMARY KEY(ID))";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			stmt.executeUpdate(ct);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertUser(Employee employee) throws SQLException {

		String i = "INSERT INTO EMPLOYEE (NAME,PASSWORD,FIRST_NAME,LAST_NAME,EMAIL,PHONE,TYPE) VALUES(?,?,?,?,?,?,?)"; // Insert
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.prepareStatement(i); // Preparestatement macht Präkompilieren
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getPassword());
			stmt.setString(3, employee.getFirstName());
			stmt.setString(4, employee.getLastName());
			stmt.setString(5, employee.getEmail());
			stmt.setString(6, employee.getPhone());
			stmt.setInt(7, employee.getType());
			stmt.executeUpdate(); // führe den SQL Kommander aus
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Employee> allUsers() {
		String s = "SELECT * FROM EMPLOYEE";
		Connection conn = null;
		Statement stmt = null;
		List<Employee> userList = new ArrayList<Employee>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("ID"), rs.getString("NAME"), rs.getString("PASSWORD"),
						rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"),
						rs.getString("PHONE"), rs.getInt("TYPE"));
				userList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}

	public static List<Employee> allManagers() {
		return findUserByType(EmployeeType.MANAGER.getType());
	}	
	public static List<Employee> allDevelopers() {
		return findUserByType(EmployeeType.DEVELOPER.getType());
	}	
	
	public static Employee findUserbyID(int id) {
		Employee retEmployee = null;
		String s = "SELECT * FROM EMPLOYEE WHERE ID = " + id;
		Connection conn = null;
		Statement stmt = null;
		List<Employee> userList = new ArrayList<Employee>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("ID"), rs.getString("NAME"), rs.getString("PASSWORD"),
						rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"),
						rs.getString("PHONE"), rs.getInt("TYPE"));
				userList.add(employee);
				if(userList.size() == 1) {
					retEmployee = userList.get(0);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retEmployee;
	}
	
	public static List<Employee> findUserByType(int type) {
		String s = "SELECT * FROM EMPLOYEE WHERE TYPE = " + type;
		Connection conn = null;
		Statement stmt = null;
		List<Employee> userList = new ArrayList<Employee>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("ID"), rs.getString("NAME"), rs.getString("PASSWORD"),
						rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"),
						rs.getString("PHONE"), rs.getInt("TYPE"));
				userList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userList;
	}
	
	public static void updateEmployee(Employee employee) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String update = "UPDATE EMPLOYEE SET NAME = ?, FIRST_NAME = ?, LAST_NAME =?, EMAIL=?,PHONE = ?, TYPE= ? WHERE ID = ?";
			stmt = conn.prepareStatement(update);
			stmt.setString(1, employee.getName());
			stmt.setString(2, employee.getFirstName());
			stmt.setString(3, employee.getLastName());
			stmt.setString(4, employee.getEmail());
			stmt.setString(5, employee.getPhone());
			stmt.setInt(6, employee.getType());
			stmt.setInt(7, employee.getId());
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Employee getEmployeeByName(String name) {
		String s = "SELECT * FROM EMPLOYEE WHERE NAME='" + name + "'";
		Connection conn = null;
		Statement stmt = null;
		Employee retEmployee = null;
		List<Employee> userList = new ArrayList<Employee>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("ID"), rs.getString("NAME"), rs.getString("PASSWORD"),
						rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"),
						rs.getString("PHONE"), rs.getInt("TYPE"));
				userList.add(employee);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (userList.size() > 0) {
			retEmployee = userList.get(0);
		}

		return retEmployee;
	}

	public static void updateEmployeePassword(int id, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String update = "UPDATE EMPLOYEE SET PASSWORD = ? WHERE ID = ?";
			stmt = conn.prepareStatement(update);
			stmt.setString(1, password);
			stmt.setInt(2, id);
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	public static void deleteEmployee(Employee employee) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String delete = "DELETE FROM EMPLOYEE WHERE ID = ?";
			stmt = conn.prepareStatement(delete);
			stmt.setInt(1, employee.getId());
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


	public static void DropProjectTable() throws SQLException {
		String ct = "DROP TABLE Project";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			stmt.executeUpdate(ct);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void CreateProject() throws SQLException {
		String ct = "CREATE TABLE Project (" + "ID integer generated always as identity, " + "NAME VARCHAR(200),"
				+ "SHORT_Description VARCHAR(200), " + "LONG_Description VARCHAR(200)," + "START_DATE DATE,"
				+ "USER_ID BIGINT," + "PRIMARY KEY(ID))";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			stmt.executeUpdate(ct);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertProject(Project project) throws SQLException {

		String i = "INSERT INTO PROJECT (NAME, SHORT_Description, LONG_Description, START_DATE, USER_ID) VALUES(?,?,?,?,?)"; // Insert i wird in Zeile 73 verwendet
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.prepareStatement(i); // Preparestatement macht Präkompilieren
			stmt.setString(1, project.getName());
			stmt.setString(2, project.getShortDescription());
			stmt.setString(3, project.getLongDescription());
			// LocalDateTime dt = project.getStartDate().atTime(LocalTime.NOON);
			// //LocalDateTime.of(customer.getDatum().getYear(),customer.getDatum().getMonth(),1,1,1,1,1);
			// java.sql.Date startDate = new
			// java.sql.Date(dt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
			stmt.setObject(4, Util.getConvertedDate(project.getStartDate()));
			stmt.setInt(5, project.getUser().getId());
			stmt.executeUpdate(); // führe den SQL Kommander aus
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


	public static void updateProject(Project project) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String update = "UPDATE PROJECT SET NAME = ?, SHORT_Description = ?, LONG_Description = ?, START_DATE = ?, USER_ID = ? WHERE ID = ?";
			stmt = conn.prepareStatement(update);
			stmt.setString(1, project.getName());
			stmt.setString(2, project.getShortDescription());
			stmt.setString(3, project.getLongDescription());
			// LocalDateTime dt = project.getStartDate().atTime(LocalTime.NOON);
			// //LocalDateTime.of(customer.getDatum().getYear(),customer.getDatum().getMonth(),1,1,1,1,1);
			// java.sql.Date startDate = new
			// java.sql.Date(dt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
			stmt.setObject(4, Util.getConvertedDate(project.getStartDate()));
			stmt.setInt(5, project.getUser().getId());
			stmt.setInt(6, project.getId());
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void deleteProject(Project project) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String delete = "DELETE FROM PROJECT WHERE ID = ?";
			stmt = conn.prepareStatement(delete);
			stmt.setInt(1, project.getId());
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Project> allProjects() {
		String s = "SELECT * FROM PROJECT";
		Connection conn = null;
		Statement stmt = null;
		List<Project> projectList = new ArrayList<Project>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				Employee employee = findUserbyID(rs.getInt("USER_ID"));
				Project project = new Project(rs.getInt("ID"), rs.getString("NAME"), rs.getString("SHORT_Description"),
						rs.getString("LONG_Description"),rs.getDate("START_DATE").toLocalDate(), employee);
				projectList.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return projectList;
	}

	public static Project findProjectById(int id) {
		Project retProject = null;
		String s = "SELECT * FROM PROJECT WHERE ID =" + id;
		Connection conn = null;
		Statement stmt = null;
		List<Project> projectList = new ArrayList<Project>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				Employee employee = findUserbyID(rs.getInt("USER_ID"));
				Project project = new Project(rs.getInt("ID"), rs.getString("NAME"), rs.getString("SHORT_Description"),
						rs.getString("LONG_Description"),rs.getDate("START_DATE").toLocalDate(), employee);
				projectList.add(project);
			}

			if(projectList.size() == 1) {
				retProject = projectList.get(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retProject;
	}

	public static List<Project> projectsByUserId(int userId) {
		String s = "SELECT * FROM PROJECT WHERE USER_ID = " + userId;
		Connection conn = null;
		Statement stmt = null;
		List<Project> projectList = new ArrayList<Project>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				Employee employee = findUserbyID(rs.getInt("USER_ID"));
				Project project = new Project(rs.getInt("ID"), rs.getString("NAME"), rs.getString("SHORT_Description"),
						rs.getString("LONG_Description"),rs.getDate("START_DATE").toLocalDate(), employee);
				projectList.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return projectList;
	}


	public static void DropTaskTable() throws SQLException {
		String ct = "DROP TABLE Task";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			stmt.executeUpdate(ct);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void CreateTask() throws SQLException {
		String ct = "CREATE TABLE TASK (" + "ID integer generated always as identity, " + "NAME VARCHAR(200),"
				+ "SHORT_Description VARCHAR(200), " + "LONG_Description VARCHAR(200)," +  "COMMENTS VARCHAR(200),"
				+"STATUS INTEGER,"+ "EXPECTED_START_DATE DATE," + "EXPECTED_END_DATE DATE,"
				+ "ACTUELL_START_DATE DATE," + "ACTUELL_END_DATE DATE," + "MANAGER_ID INT," + "DEVELOPER_ID INT," + "PROJECT_ID INT,"
				+ "PRIMARY KEY(ID))";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			stmt.executeUpdate(ct);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertTask(Task task) throws SQLException {

		String i = "INSERT INTO TASK(NAME, SHORT_Description, LONG_Description, COMMENTS, STATUS, EXPECTED_START_DATE, EXPECTED_END_DATE, ACTUELL_START_DATE, ACTUELL_END_DATE, MANAGER_ID, DEVELOPER_ID, PROJECT_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)"; // Insert i wird in Zeile 73 verwendet
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.prepareStatement(i); // Preparestatement macht Präkompilieren
			stmt.setString(1, task.getName());
			stmt.setString(2, task.getShortDescription());
			stmt.setString(3, task.getLongDescription());
			stmt.setString(4, task.getComments());
			stmt.setInt(5, task.getStatus());
			stmt.setObject(6, Util.getConvertedDate(task.getExpectedStartDate()));
			stmt.setObject(7, Util.getConvertedDate(task.getExpectedEndDate()));
			stmt.setObject(8, Util.getConvertedDate(task.getActuellStartDate()));
			stmt.setObject(9, Util.getConvertedDate(task.getActuellEndDate()));
			stmt.setInt(10, task.getManager().getId());
			stmt.setInt(11, task.getDeveloper().getId());
			stmt.setInt(12, task.getProject().getId());
			stmt.executeUpdate(); // führe den SQL Kommander aus
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static List<Task> allTasks() {
		String s = "SELECT * FROM TASK";
		Connection conn = null;
		Statement stmt = null;
		List<Task> taskList = new ArrayList<Task>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {

				Employee manager = findUserbyID(rs.getInt("MANAGER_ID"));
				Employee developer = findUserbyID(rs.getInt("DEVELOPER_ID"));
				Project project = findProjectById(rs.getInt("PROJECT_ID"));

				Task task = new Task(rs.getInt("ID"),rs.getString("NAME"),rs.getString("SHORT_Description"),
						rs.getString("LONG_Description"),rs.getString("COMMENTS"),rs.getInt("STATUS"),Util.getConvertedDate(rs.getDate("EXPECTED_START_DATE")),
						Util.getConvertedDate(rs.getDate("EXPECTED_END_DATE")),Util.getConvertedDate(rs.getDate("ACTUELL_START_DATE")),Util.getConvertedDate(rs.getDate("ACTUELL_END_DATE")),manager,developer,project);
				taskList.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return taskList;
	}

	public static List<Task> tasksByDeveloperId(int developerId) {
		String s = "SELECT * FROM TASK WHERE DEVELOPER_ID=" + developerId ;
		Connection conn = null;
		Statement stmt = null;
		List<Task> taskList = new ArrayList<Task>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {

				Employee manager = findUserbyID(rs.getInt("MANAGER_ID"));
				Employee developer = findUserbyID(rs.getInt("DEVELOPER_ID"));
				Project project = findProjectById(rs.getInt("PROJECT_ID"));

				Task task = new Task(rs.getInt("ID"),rs.getString("NAME"),rs.getString("SHORT_Description"),
						rs.getString("LONG_Description"),rs.getString("COMMENTS"),rs.getInt("STATUS"),Util.getConvertedDate(rs.getDate("EXPECTED_START_DATE")),
						Util.getConvertedDate(rs.getDate("EXPECTED_END_DATE")),Util.getConvertedDate(rs.getDate("ACTUELL_START_DATE")),Util.getConvertedDate(rs.getDate("ACTUELL_END_DATE")),manager,developer,project);
				taskList.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return taskList;
	}
	
	public static void deleteTask(Task task) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String delete = "DELETE FROM TASK WHERE ID = ?";
			stmt = conn.prepareStatement(delete);
			stmt.setInt(1, task.getId());
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void updateTask(Task task) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String update = "UPDATE TASK SET NAME = ?, SHORT_Description = ?, LONG_Description = ?, COMMENTS = ?, STATUS = ?,"
					+ " EXPECTED_START_DATE = ?,EXPECTED_END_DATE = ?, ACTUELL_START_DATE = ?, ACTUELL_END_DATE = ?, MANAGER_ID = ?, DEVELOPER_ID = ?, PROJECT_ID= ? WHERE ID = ?";
			stmt = conn.prepareStatement(update);
			stmt.setString(1, task.getName());
			stmt.setString(2, task.getShortDescription());
			stmt.setString(3, task.getLongDescription());
			stmt.setString(4, task.getComments());
			stmt.setInt(5, task.getStatus());
			stmt.setObject(6, Util.getConvertedDate(task.getExpectedStartDate()));
			stmt.setObject(7, Util.getConvertedDate(task.getExpectedEndDate()));
			stmt.setObject(8, Util.getConvertedDate(task.getActuellStartDate()));
			stmt.setObject(9, Util.getConvertedDate(task.getActuellEndDate()));
			stmt.setObject(10, task.getManager().getId());
			stmt.setObject(11, task.getDeveloper().getId());
			stmt.setInt(12, task.getProject().getId());
			stmt.setInt(13, task.getId());
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

	public static ArrayList<Task> tasksByProjectId(int projectId) {
		String s = "SELECT * FROM TASK WHERE PROJECT_ID = " + projectId;
		Connection conn = null;
		Statement stmt = null;
		List<Task> taskList = new ArrayList<Task>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(s);
			while (rs.next()) {
				
				Employee manager = findUserbyID(rs.getInt("MANAGER_ID"));
				Employee developer = findUserbyID(rs.getInt("DEVELOPER_ID"));
				Project project = findProjectById(rs.getInt("PROJECT_ID"));
				
				Task task = new Task(rs.getInt("ID"), rs.getString("NAME"), rs.getString("SHORT_Description"),
						rs.getString("LONG_Description"),rs.getString("COMMENTS"), rs.getInt("STATUS"), Util.getConvertedDate(rs.getDate("EXPECTED_START_DATE")),
						Util.getConvertedDate(rs.getDate("EXPECTED_END_DATE")),Util.getConvertedDate(rs.getDate("ACTUELL_START_DATE")),Util.getConvertedDate(rs.getDate("ACTUELL_END_DATE")),
						manager, developer , project);
				taskList.add(task);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (ArrayList<Task>) taskList;
	}

	public static void deleteTasksByProject(int projectId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String delete = "DELETE FROM TASK WHERE PROJECT_ID = ?";
			stmt = conn.prepareStatement(delete);
			stmt.setInt(1, projectId);
			stmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	

	
}
