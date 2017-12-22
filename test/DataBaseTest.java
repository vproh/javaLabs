package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import builders.GroupBuilder;
import builders.StudentBuilder;
import dataBase.DataBase;
import models.Group;
import models.Student;

public class DataBaseTest {
	Group group1 = null;
	Group group2 = null;
	Student student1 = null;
	Student student2 = null;
	Student student3 = null;
	Student student4 = null;
	List<Student> studArr1 = null;
	List<Student> studArr2 = null;
	DataBase db = null;
	String[] groupSbj1 = { "Math", "Computer networks", "Operation system", "Java", "Python" };
	String[] groupSbj2 = { "Geometry", "Math", "Python", "Algebra" };
	
	public void initDB() throws ClassNotFoundException, SQLException {
		db = new DataBase("jdbc:mysql://localhost:3306/test", "root", "toor");
	}
	public void initGroups() throws SQLException {
		studArr1 = new ArrayList<Student>();
		studArr2 = new ArrayList<Student>();
		
		Integer[] mark1 = { 75, 70, 82, 85, 90 };
		Integer[] mark2 = { 68, 84, 73, 68, 93 };
		Integer[] mark3 = { 92, 68, 56, 81 };
		Integer[] mark4 = { 82, 75, 77, 88 };
		
		student1 = new StudentBuilder("Vlad").setLastName("Zsfdgddf").setBirthDay(LocalDate.of(1997, Month.DECEMBER, 11))
								.setPhoneNumber("0975463187").setEmail("Proh@gmail.com")
								.setFaculty("Math-fac").setShip(true).setEdu(groupSbj1,  mark1).build();
		student2 = new StudentBuilder("Rostik").setLastName("Rgfdgb").setBirthDay(LocalDate.of(1997, Month.JULY, 22))
								.setPhoneNumber("0975497111").setLeader(true).setEmail("Khlan@gmail.com")
								.setFaculty("Math-fac").setMissings(11).setShip(true).setEdu(groupSbj1, mark2).build();
		student3 = new StudentBuilder("Danka").setLastName("Zsdfa").setBirthDay(LocalDate.of(1999, Month.AUGUST, 31))
								.setPhoneNumber("0971131548").setEmail("Danka@gmail.com")
								.setFaculty("Math-fac").setMissings(7).setShip(true).setEdu(groupSbj2, mark3).build();
		student4 = new StudentBuilder("Artem").setLastName("Omsk").setBirthDay(LocalDate.of(1998, Month.MARCH, 3))
								.setPhoneNumber("0978721375").setEmail("Katia@gmail.com")
								.setFaculty("Math-fac").setShip(true).setLeader(true).setEdu(groupSbj2, mark4).build();
		
		studArr1.add(student1);
		studArr1.add(student2);
		
		studArr2.add(student3);
		studArr2.add(student4);
		
		group1 = new GroupBuilder("Group one").setGroupNumber(101).setCuratorName("Curator one").setStudents(studArr1).build();
		group2 = new GroupBuilder("Group two").setGroupNumber(102).setCuratorName("Curator two").setStudents(studArr2).build();
	}

	@Test(dataProvider = "addGroupProvider", priority=1)
	public void addGroupTest(Group group, boolean result) {
		try {
			assertEquals(result, db.addGroup(group));
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	@DataProvider()
	public Object[][] addGroupProvider() {
		try {
			initDB();
			initGroups();
			return new Object[][] { { group1, true }, { group2, true } };
		}
		catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	@Test(priority=2)
	public void getGroupTest() {
		Group emptyGroup = null;
		try {
			emptyGroup = db.getGroup(101);
			assertTrue(emptyGroup.equals(group1));
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	@Test(priority=3)
	public void getGroupTest1() {
		Group emptyGroup = null;
		try {
			emptyGroup = db.getGroup(102);
			assertTrue(emptyGroup.equals(group2));
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	@Test(dataProvider = "deleteStudentProvider", priority=4)
	public void deleteStudentTest(int studId, boolean result) throws SQLException {
		try {
			assertTrue(result == db.deleteStudent(studId));
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	@DataProvider()
	public Object[][] deleteStudentProvider() {
		return new Object[][] { {1, true}, {3, true} };
	}
	
	@Test(dataProvider = "addStudentProvider", priority=5)
	public void addStudentTest(Student student, int groupNum, boolean result) {
		try {
			assertTrue(result == db.addStudent(groupNum, student));
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	@DataProvider()
	public Object[][] addStudentProvider() {
		try {
			initGroups();
			return new Object[][] { {student1, 101, true}, {student4, 102, true} };
		} 
		catch (SQLException e) {
			e.toString();
		}
		return null;
	}
	
	@Test(dataProvider = "updateMarkProvider", priority=6)
	public void updateMarkTest(int studentId, String sbj, Integer mark, boolean result) {
		try {
			assertTrue(result == db.updateMark(studentId, sbj, mark));
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	@DataProvider()
	public Object[][] updateMarkProvider() {
			return new Object[][] { {5, "Java", 82, true}, {4, "Algebra", 75, true} };
	}
	
	@Test(dataProvider = "updateMarksProvider", priority=7)
	public void updateMarksTest(int studentId, List<String> sbj, List<Integer> marks, boolean result) {
		try {
			assertTrue(result == db.updateMarks(studentId, sbj, marks));
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	@DataProvider()
	public Object[][] updateMarksProvider() {
		List<String> newSbjs = new ArrayList<String>();
		List<Integer> newMarks = new ArrayList<Integer>();
		
		try {
			initGroups();
			newSbjs.add("Math");
			newSbjs.add("Geometry");
			newSbjs.add("Python");
			newMarks.add(64);
			newMarks.add(75);
			newMarks.add(82);
			
			return new Object[][] { {2, newSbjs, newMarks, true} };
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	@Test(dataProvider = "deleteDbProvider", priority=8)
	public void deleteDbTest(String nameDB, boolean result) {
		try {
			assertEquals(result, db.deleteDB(nameDB));
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	@DataProvider()
	public Object[][] deleteDbProvider() {
		return new Object[][] { {"test", true} };
	}
	
}
