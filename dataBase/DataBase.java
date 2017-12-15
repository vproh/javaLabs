package dataBase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import builders.GroupBuilder;
import builders.StudentBuilder;
import models.Group;
import models.Student;

public class DataBase {
	private static Connection con;
    private static String user;
    private static String password;
    private static String url;
	
	public DataBase() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		user = "root";
		password = "toor";
		url = "jdbc:mysql://localhost:3306/test";
		con = DriverManager.getConnection(url, user, password);
	}
	
	public DataBase(String url, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		DataBase.url = url;
		DataBase.user = user;
		DataBase.password = password;
		con = DriverManager.getConnection(url, user, password);
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		DataBase.con = con;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		DataBase.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		DataBase.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		DataBase.password = password;
	}
	
	public static List<String> getColumns (String nameTable) throws SQLException {
		List<String> columns = null;
		ResultSet res = null;
		PreparedStatement stmt = null;
		try {
			columns = new ArrayList<String>();
			stmt = con.prepareStatement(String.format("show columns from %s;", nameTable));
			res = stmt.executeQuery();
			res.next(); // miss markId
			while(res.next())
				columns.add(res.getString("Field"));
			return columns;
		}
		finally {
			if(stmt != null)
				stmt.close();
			if(res != null)
				res.close();
		}
	}
	
	private boolean IsSbjInBD(String sbj) throws SQLException {
		List<String> columns = null;
		try {
			columns = getColumns("marks");
			for(int i = 0; i < columns.size(); ++ i) {
				//System.out.println("Sbj = " + sbj +"\nresSbj = " + columns.get(i));					
				if(sbj.equals(columns.get(i)))
					return true;
			}
			return false;
		}
		finally {
		}
	}
	
	protected boolean isStudentGroupCorrect(int groupNum, int studentId) throws SQLException {
		String request = null;
		ResultSet res = null;
		PreparedStatement stmt = null;
		
		try {
			request = "select groupId from students where studentId=?;";
			stmt = con.prepareStatement(request);
			stmt.setInt(1, studentId);
			res = stmt.executeQuery();
			res.first();
			if(Integer.parseInt(res.getString(1)) == groupNum)
				return true;
			return false;
		}
		finally {
			if(stmt != null)
				stmt.close();
			if(res != null)
				res.close();
		}
	}
	
	protected boolean isStudentIdCorrect(int studId) throws SQLException {
		String request = null;
		ResultSet res = null;
		PreparedStatement prStmt = null;
		
		try {
			request = "select * from students where studentId=?;";
			prStmt = con.prepareStatement(request);
			prStmt.setInt(1, studId);
			res = prStmt.executeQuery();
			res.first();
			if(res.isFirst())
				return true;
			return false;
		}
		finally {
			if(res != null)
				res.close();
			if(prStmt != null)
				prStmt.close();
		}
	}
	
	private void addToTableSubject(String nameTable, String sbj) throws SQLException {
		String request = null;
		PreparedStatement stmt = null;
		
		try {
			request = String.format("alter table %s add %s varchar(20)", nameTable, '`' + sbj + '`');
			stmt = con.prepareStatement(request);
			stmt.executeUpdate();
		}
		finally {
			if(stmt != null)
				stmt.close();
		}
	}
	
	private void fieldsMoveUp(int start, int end) throws SQLException { // works ?
		String request = null;
		PreparedStatement stmt = null;
		
		try {
			while(start < end) {
				request = "update students set studentId=? where studentId=?;";
				stmt = con.prepareStatement(request);
				stmt.setInt(1, start);
				stmt.setInt(2, start + 1);
				stmt.executeUpdate();
				request = "update marks set markId=? where markId=?;";
				stmt.close();
				stmt = con.prepareStatement(request);
				stmt.setInt(1, start);
				stmt.setInt(2, start + 1);
				stmt.executeUpdate();
				stmt.close();
				request = "update students set markId=? where markId=?;";
				stmt = con.prepareStatement(request);
				stmt.setInt(1, start);
				stmt.setInt(2, start + 1);
				stmt.executeUpdate();
				
				start ++;
			}
		}
		finally {
			if(stmt != null)
				stmt.close();
		}
	}
	
	private boolean createStudentsTable() throws SQLException {
		String request = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			request = "create table students (studentId int primary key, name varchar(20), lastName varchar(20), "
					+ "birthDay varchar(15), faculty varchar(20), isLeader tinyint(1), missings int, "
					+ "ship tinyint (1), email varchar(20), phoneNumber varchar(13), groupId int, markId int)";
			stmt.executeUpdate(request);
			System.out.println("Students table succssefully created");
			return true;
		}
		finally {
			if(stmt != null)
				stmt.close();
		}
	}
	
	private boolean createStudentGroupTable() throws SQLException {
		String request = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			request = "create table studentGroup(groupNumber int primary key, groupName varchar(20), curatorName varchar(30))";
			stmt.executeUpdate(request);
			System.out.println("Student`s group table succssefully created");
			return true;
		}
		finally {
			if(stmt != null)
				stmt.close();
		}
	}
	
	private boolean createMarksTable(Student student) throws SQLException {
		String request = null;
		String[] sbj = null;
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			sbj = Group.getSubjects(student);
			
			request = "create table marks(markId int primary key, ";
			for(int i = 0; i < sbj.length - 1; ++ i) 
				request += "`" + sbj[i] + "`" + " int, ";
			request += "`" + sbj[sbj.length - 1] + "`" + " int);";
			
			stmt.executeUpdate(request);
			//System.out.println("Request = " + request);
			System.out.println("Marks table succssefully created");
			
			return true;
		}
		finally {
			if(stmt != null)
				stmt.close();
		}
	}
	
	public boolean toSql(Group group) throws SQLException {
	Student student = null;
	String request = null;
	List<Student> students = null;
	ResultSet res = null;
	Statement stmt = null;
	PreparedStatement prStmt = null;
	
	try {
		students = group.getStudents();
		if(students == null)
			throw new IllegalArgumentException("This group have no students !");
		request = "show tables;";
		stmt = con.createStatement();
		res = stmt.executeQuery(request);
		res.first();
		if(!res.isFirst()) {
			createStudentsTable();
			createStudentGroupTable();
			createMarksTable(students.get(0));
		}
		/*adding to studentGroup*/
		request = "insert into studentGroup values(?, ?, ?);";
		prStmt = con.prepareStatement(request);
		prStmt.setInt(1, group.getGroupNumber());
		prStmt.setString(2, group.getGroupName());
		prStmt.setString(3, group.getCuratorName());
		prStmt.executeUpdate();
		/*adding students and their marks*/
		for(int i = 0; i < students.size(); ++ i) {
			student = students.get(i);
			addStudent(group.getGroupNumber(), student);
		}
		
	}
	finally {
		if(res != null)
			res.close();
		if(prStmt != null)
			prStmt.close();
		if(stmt != null)
			stmt.close();
	}
	
	return true;
}
	
	public Group fromSql(int groupNum) throws SQLException {
		String request = null;
		ResultSet res = null;
		ResultSet res1 = null;
		List<Student> students = null;
		List<Integer> newMarks = null;
		List<String> newSbj = null;
		int tmpMark = 0;
		StudentBuilder studBuild = null;
		GroupBuilder groupBuild = null;
		String[] sbj = null;
		Integer[] marks = null;
		PreparedStatement stmt = null;
		
		try {
			students = new ArrayList<Student>();
			newMarks = new ArrayList<Integer>();
			groupBuild = new GroupBuilder();
			newSbj = getColumns("marks");
			
			request = "select groupNumber, groupName, curatorName from studentgroup"
					+ " where groupNumber=?;";
			stmt = con.prepareStatement(request);
			stmt.setInt(1, groupNum);
			res = stmt.executeQuery();
			res.first();
			if(!res.isFirst())
				throw new IllegalArgumentException("Incorrect group number !!!");
			/* paste group information */
			
			groupBuild.setGroupName(res.getString("groupName"));
			groupBuild.setGroupNumber(groupNum);
			groupBuild.setCuratorName(res.getString("curatorName"));		
			stmt.close();
			res.close();
			request = "select name, lastName, birthDay, faculty, isLeader, missings, ship, email, "
					+ "phoneNumber, markId from students where groupId=?;";
			stmt = con.prepareStatement(request);
			stmt.setInt(1, groupNum);
			res = stmt.executeQuery();
			
			while(res.next()) {
				studBuild = new StudentBuilder(null);
				studBuild.setName(res.getString("name"));
				studBuild.setLastName(res.getString("lastName"));
				studBuild.setBirthDay(res.getDate("birthDay").toLocalDate());
				studBuild.setFaculty(res.getString("faculty"));
				studBuild.setLeader(res.getBoolean("isLeader"));
				studBuild.setMissings(res.getInt("missings"));
				studBuild.setShip(res.getBoolean("ship"));
				studBuild.setEmail(res.getString("email"));
				studBuild.setPhoneNumber(res.getString("phoneNumber"));
				
				request = "select * from marks where markId=?;";
				stmt = con.prepareStatement(request);
				stmt.setInt(1, res.getInt("markId"));
				res1 = stmt.executeQuery();
				res1.next();
				for(int i = 0; i < newSbj.size(); ++ i) {
					tmpMark = res1.getInt(newSbj.get(i));
					if(res1.wasNull()) {
						newSbj.remove(i);
						-- i;
					}
					else 
						newMarks.add(tmpMark);
				}
				marks = new Integer[newMarks.size()];
				sbj = new String[newSbj.size()];
				marks = newMarks.toArray(marks);
				sbj = newSbj.toArray(sbj);
				newMarks.clear();
				
				studBuild.setEdu(sbj, marks);
				students.add(studBuild.build());
			}

			return groupBuild.setStudents(students).build();
		}
		finally {
			if(stmt != null)
				stmt.close();
			if(res != null)
				res.close();
			if(res1 != null)
				res1.close();
		}
	}
	
	public boolean addStudent(int groupNum, Student student) throws SQLException {
		ResultSet res = null;
		int n = 0;
		Integer[] marks = null;
		String[] sbj = null;
		String request = null;
		Statement stmt = null;
		PreparedStatement prStmt = null;
		
		try {
			request = "select * from studentGroup where groupNumber=?;";
			prStmt = con.prepareStatement(request);
			prStmt.setInt(1, groupNum);
			res = prStmt.executeQuery();
			res.first();
			if(!res.isFirst())
				throw new IllegalArgumentException("This group doesn`t exists in data base. Maybe you have to call 'toSql' method firstly.");
			
			res.close();
			request = "select * from students;";
			stmt = con.createStatement();
			res = stmt.executeQuery(request);
			if(res.last())
				n = res.getInt(1) + 1;
			else
				n = 1;
			
			prStmt.close();
			/* adding to students table */		
			request = "insert into students values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			prStmt = con.prepareStatement(request);
			prStmt.setInt(1, n);
			prStmt.setString(2, student.getName());
			prStmt.setString(3, student.getLastName());
			prStmt.setDate(4, Date.valueOf(LocalDate.of(student.getBirthDay().getYear(),
														student.getBirthDay().getMonth(),
														student.getBirthDay().getDayOfMonth())));
			prStmt.setString(5, student.getFaculty());
			prStmt.setBoolean(6, student.isLeader());
			prStmt.setInt(7, student.getMissings());
			prStmt.setBoolean(8, student.isShip());
			prStmt.setString(9, student.getEmail());
			prStmt.setString(10, student.getPhoneNumber());
			prStmt.setInt(11, groupNum);
			prStmt.setInt(12, n);
			prStmt.executeUpdate();
			prStmt.close();
			/* adding to marks table */
			sbj = Group.getSubjects(student);
			marks = Group.getMarks(student);
			
			for(int i = 0; i < sbj.length; ++ i) {
				if(!IsSbjInBD(sbj[i]))
					addToTableSubject("marks", sbj[i]);
			}
			
			request = "insert into marks(markId, `";
			for(int i = 0; i < sbj.length - 1; ++ i)
				request += sbj[i] + "`, `";
			request += sbj[sbj.length - 1] + "`) values(?, ";
			
			for(int i = 0; i < marks.length - 1; ++ i)
				request += "?, ";
			request += "?);";
			
			prStmt = con.prepareStatement(request);
			prStmt.setInt(1, n);
			for(int i = 0; i < marks.length; ++ i)
				prStmt.setInt(i + 2, marks[i]);
			prStmt.executeUpdate();
			
			return true;
		}
		finally {
			if(res != null)
				res.close();
			if(stmt != null)
				stmt.close();
			if(prStmt != null)
				prStmt.close();
		}
	}
	
	public boolean deleteStudent(int groupNum, int studentId) throws SQLException {
		String request = null;
		ResultSet res = null;
		PreparedStatement prStmt = null;
		Statement stmt = null; 
		int n = 0;
		
		try {
			stmt = con.createStatement();
			request = "select * from students;";
			res = stmt.executeQuery(request);
			if(res.last()) {
				n = res.getInt(1);
			}
			else {
				throw new IllegalArgumentException("Table is empty!!!");
			}
			if(n < studentId || studentId <= 0) {
				throw new IllegalArgumentException("Enter correct student ID!!!");
			}
			if(!isStudentGroupCorrect(groupNum, studentId)) {
				throw new IllegalArgumentException("Can`t delete student from another group!!!");
			}
			request = "delete from students where studentId=?;";
			prStmt = con.prepareStatement(request);
			prStmt.setInt(1, studentId);
			prStmt.executeUpdate();
			prStmt.close();
			request = "delete from marks where markId=?;";
			prStmt = con.prepareStatement(request);
			prStmt.setInt(1, studentId);
			prStmt.executeUpdate();
			if(n != studentId)
				fieldsMoveUp(studentId, n);
			res.close();
			prStmt.close();
			request = "select * from students where groupId=?;";
			prStmt = con.prepareStatement(request);
			prStmt.setInt(1, groupNum);
			res = prStmt.executeQuery();
			res.first();
			if(!res.isFirst()) {
				prStmt.close();
				request = "delete from studentGroup where groupNumber=?;";
				prStmt = con.prepareStatement(request);
				prStmt.setInt(1, groupNum);
				prStmt.executeUpdate();
			}
			return true;
		}
		finally {
			if(res != null)
				res.close();
			if(stmt != null)
				stmt.close();
			if(prStmt != null)
				prStmt.close();
		}
	}
	
	public boolean deleteBD(String nameBD) throws SQLException {
		ResultSet tables = null;
		Statement stmt = null;
		PreparedStatement prStmt = null;

		try {
			stmt = con.createStatement();
			tables = stmt.executeQuery("show tables;");
			/* deleting all tables */
			while(tables.next()) {
				prStmt = con.prepareStatement(String.format("drop table %s", tables.getString(1)));
				prStmt.executeUpdate();
				prStmt.close();
			}
			/*delete BD */
			prStmt = con.prepareStatement(String.format("drop database %s", nameBD));
			prStmt.executeUpdate();
			
			return true;
		}
		finally {
			if(tables != null)
				tables.close();
			if(stmt != null)
				stmt.close();
			if(prStmt != null)
				prStmt.close();
		}
	}
	
	public void newMark(int groupNum, int studentId, String sbj, int mark) throws SQLException {
		String request = null;
		PreparedStatement prStmt = null;
		
		try {
			if(!isStudentIdCorrect(studentId))
				throw new IllegalArgumentException("This stedentId does not exists !");
			if(!IsSbjInBD(sbj))
				throw new IllegalArgumentException("This student has not current course !!!");
			if(!isStudentGroupCorrect(groupNum, studentId))
				throw new IllegalArgumentException("This student does not belong this group !!!");
			
			request = String.format("update marks set %s=? where markId=?;", '`' + sbj + '`');
			prStmt = con.prepareStatement(request);
			prStmt.setInt(1, mark);
			prStmt.setInt(2, studentId);
			prStmt.executeUpdate();
		}
		finally {
			if(prStmt != null)
				prStmt.close();
		}
	}
	
	public void newMarks(int groupNum, int studentId, String[] sbj, Integer[] marks) throws SQLException {
		String request = null;
		PreparedStatement prStmt = null;
		
		try {
			if(!isStudentIdCorrect(studentId))
				throw new IllegalArgumentException("This stedentId does not exists !");
			if(!isStudentGroupCorrect(groupNum, studentId))
				throw new IllegalArgumentException("This student does not belong this group !!!");
			request = "update marks set `";
			for(int i = 0; i < sbj.length - 1; ++ i) {
				if(!IsSbjInBD(sbj[i]))
					throw new IllegalArgumentException("One or more subjects does not exists in database !!!");
				request += sbj[i] + "`=?, `";
			}
			request += sbj[sbj.length - 1] + "`=? where markId=?;";
			prStmt = con.prepareStatement(request);
			for(int i = 0; i < marks.length; ++ i)
					prStmt.setInt(i + 1, marks[i]);
			
			prStmt.setInt(sbj.length + 1, studentId);
			prStmt.executeUpdate();
			//System.out.println("Request = " + prStmt.toString());
		}
		finally {
			if(prStmt != null)
				prStmt.close();
		}
	}
}
