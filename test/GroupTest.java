package test;

import org.testng.annotations.Test;

import models.Group;
import models.Student;
import builders.GroupBuilder;
import builders.StudentBuilder;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupTest {
	Group group1 = null;
	Student student1 = null;
	Student student2 = null;
	Student student3 = null;
	Student student4 = null;
	List<Student> studArr = null;
	
	@BeforeTest
	public void initGroups() throws SQLException {
		studArr = new ArrayList<Student>();
		
		String[] groupSbj1 = { "Math", "Computer networks", "Operation system", "Java", "Python" };
		Integer[] mark1 = { 75, 70, 82, 85, 90 };
		Integer[] mark2 = { 68, 84, 73, 68, 93 };
		Integer[] mark3 = { 92, 68, 56, 81, 60 };
		Integer[] mark4 = { 82, 75, 77, 88, 91 };
		
		student1 = new StudentBuilder("Vlad").setLastName("Zsfdgddf").setBirthDay(LocalDate.of(1997, Month.DECEMBER, 11))
								.setPhoneNumber("0975463187").setEmail("Proh@gmail.com")
								.setFaculty("Math-fac").setShip(true).setEdu(groupSbj1,  mark1).build();
		student2 = new StudentBuilder("Rostik").setLastName("Rgfdgb").setBirthDay(LocalDate.of(1997, Month.JULY, 22))
	   							.setPhoneNumber("0975497111").setLeader(true).setEmail("Khlan@gmail.com")
								.setFaculty("Math-fac").setMissings(11).setShip(true).setEdu(groupSbj1, mark2).build();
		student3 = new StudentBuilder("Danka").setLastName("Zsdfa").setBirthDay(LocalDate.of(1999, Month.AUGUST, 31))
								.setPhoneNumber("0971131548").setEmail("Danka@gmail.com")
								.setFaculty("Math-fac").setMissings(7).setShip(true).setEdu(groupSbj1, mark3).build();
		student4 = new StudentBuilder("Artem").setLastName("Omsk").setBirthDay(LocalDate.of(1998, Month.MARCH, 3))
								.setPhoneNumber("0978721375").setMissings(3).setEmail("Katia@gmail.com")
								.setFaculty("Math-fac").setShip(true).setEdu(groupSbj1, mark4).build();
		
		studArr.add(student1);
		studArr.add(student2);
		studArr.add(student3);
		studArr.add(student4);
		
		group1 = new GroupBuilder("Group one").setGroupNumber(101).setCuratorName("Curator one").setStudents(studArr).build();
	}
	
	public boolean equalsStudentsCollection(List<Student> students, List<Student> expected) {
		if(students.size() != expected.size())
			return false;
	    for(int i = 0; i < students.size(); ++ i)
	      if(!students.get(i).equals(expected.get(i)))
	        return false;
	    return true;
	  }
	
	public <T> boolean equalsArr(T[] obj1, T[] obj2) {
		if(obj1.length != obj2.length)
			return false;
		boolean wasEquals;
		for(T i: obj1) {
			wasEquals = false;
			for(T j: obj2)
				if(i == j) {
					wasEquals = true;
					break;
				}
			if(!wasEquals)
				return false;
		}
		
		return true;
	  }
	
	
	@Test(dataProvider = "sortByNProvider")
	public void sortByNTest(Group group, List<Student> exp) {
		group.sortByMissings();
		assertTrue(equalsStudentsCollection(group.getStudents(), exp));
	}
	
	@DataProvider()
	public Object[][] sortByNProvider() {
		List<Student> newStud = new ArrayList<Student>();
		
		newStud.add(student1);
		newStud.add(student4);
		newStud.add(student3);
		newStud.add(student2);
		
		return new Object[][] { {group1, newStud} };
	}
	
	@Test(dataProvider = "sortByLastNameProvider")
	public void sortByLastNameTest(Group group, List<Student> exp) {
		group.sortByLastName();
		assertTrue(equalsStudentsCollection(group.getStudents(), exp));
	}
	
	@DataProvider()
	public Object[][] sortByLastNameProvider() {
		List<Student> newStud = new ArrayList<Student>();
		
		newStud.add(student4);
		newStud.add(student2);
		newStud.add(student3);
		newStud.add(student1);
		
		return new Object[][] { {group1, newStud} };
	}
	
	@Test(dataProvider = "sortByEduProvider")
	public void sortByEduTest(Group group, List<Student> exp) {
		group.sortByEdu();
		assertTrue(equalsStudentsCollection(group.getStudents(), exp));
	}
	
	@DataProvider()
	public Object[][] sortByEduProvider() {
		List<Student> newStud = new ArrayList<Student>();
		
		newStud.add(student4);
		newStud.add(student1);
		newStud.add(student2);
		newStud.add(student3);
		
		return new Object[][] { {group1, newStud} };
	}
	
	@Test
	public void countShipStudentsTest() {
		assertTrue(group1.getPayQuantity() == 0 && group1.getShipQuantity() == 4);
	}
	
	@Test(dataProvider = "studentShipProvider")
	public void studentShipTest(Group group, List<Student> exp, float persent) {
		assertTrue(equalsStudentsCollection(group.studentShip(persent), exp));
	}
	
	@DataProvider()
	public Object[][] studentShipProvider() {
		List<Student> newStud = new ArrayList<Student>();
		List<Student> newStud1 = new ArrayList<Student>();
		
		newStud1.add(student4);
		newStud1.add(student1);
		
		newStud.add(student4);
		
		return new Object[][] { {group1, newStud, 0.4f}, {group1, newStud1, 0.6f} };
	}
	
	@Test
	public void maxNTest() {
		assertTrue(student2.equals(group1.maxMissings()));
	}
	
	@Test(dataProvider = "averageMarkProvider")
	public void averageMarkTest(Group group, Map<String, Float> exp) {
		
		assertTrue(exp.equals(group.averageMark()));
	}
	
	@DataProvider()
	public Object[][] averageMarkProvider() {
		Map<String, Float> averageListExp = new HashMap<String, Float>();
		averageListExp.put(student4.getLastName(), 82.6f);
		averageListExp.put(student1.getLastName(), 80.4f);
		averageListExp.put(student2.getLastName(), 77.2f);
		averageListExp.put(student3.getLastName(), 71.4f);
		
		return new Object[][] { {group1, averageListExp} };
	}
	
	@Test
	public void getSubjectsTest() {
		assertTrue(equalsArr(Group.getSubjects(student1), new String[] { "Math", "Computer networks", "Operation system", "Java", "Python" } ));
	}
	
	@Test
	public void getMarksTest() {
		assertTrue(equalsArr(Group.getMarks(student1), new Integer[] { 75, 70, 82, 85, 90 } ));
	}
	
	
}
