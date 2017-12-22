package test;

import static org.testng.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import builders.GroupBuilder;
import builders.StudentBuilder;
import models.Group;
import models.Student;
import serialization.JsonSerializator;
import serialization.Serialization;
import serialization.TxtSerializator;
import serialization.XmlSerializator;

public class SerializationTest {
	private static final String JSON_PATH = "tmp/group.json";
	private static final String XML_PATH = "tmp/group.xml";
	private static final String TXT_PATH = "tmp/group.txt";
	private static final String STUDENT_JSON_PATH = "tmp/student.json";
	private static final String STUDENT_XML_PATH = "tmp/student.xml";
	private static final String STUDENT_TXT_PATH = "tmp/student.txt";
	
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
	
	@Test
	public void toJsonTest() {
		try {
			Serialization<Group> jsonSeralization = new JsonSerializator<Group>(Group.class);
			assertTrue(jsonSeralization.toFile(group1, JSON_PATH));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void toXmlTest() {
		try {
			Serialization<Group> xmlSeralization = new XmlSerializator<Group>(Group.class);
			assertTrue(xmlSeralization.toFile(group1, XML_PATH));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void toTxtTest() {
		try {
			Serialization<Group> txtSeralization = new TxtSerializator<Group>(Group.class);
			assertTrue(txtSeralization.toFile(group1, TXT_PATH));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void toJsonStudentTest() {
		try {
			Serialization<Student> jsonSeralization = new JsonSerializator<Student>(Student.class);
			assertTrue(jsonSeralization.toFile(student1, STUDENT_JSON_PATH));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void toXmlStudentTest() {
		try {
			Serialization<Student> xmlSeralization = new XmlSerializator<Student>(Student.class);
			assertTrue(xmlSeralization.toFile(student1, STUDENT_XML_PATH));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void toTxtStudentTest() {
		try {
			Serialization<Student> txtSeralization = new TxtSerializator<Student>(Student.class);
			assertTrue(txtSeralization.toFile(student1, STUDENT_TXT_PATH));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fromJsonTest() {
		try {
			Serialization<Group> jsonSeralization = new JsonSerializator<Group>(Group.class);
			assertTrue(group1.equals(jsonSeralization.fromFile(JSON_PATH)));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fromXmlTest() {
		try {
			Serialization<Group> xmlSeralization = new XmlSerializator<Group>(Group.class);
			assertTrue(group1.equals(xmlSeralization.fromFile(XML_PATH)));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fromTxtTest() {
		try {
			Serialization<Group> txtSeralization = new TxtSerializator<Group>(Group.class);
			assertTrue(group1.equals(txtSeralization.fromFile(TXT_PATH)));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fromJsonStudentTest() {
		try {
			Serialization<Student> jsonSeralization = new JsonSerializator<Student>(Student.class);
			assertTrue(student1.equals(jsonSeralization.fromFile(STUDENT_JSON_PATH)));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fromXmlStudentTest() {
		try {
			Serialization<Student> xmlSeralization = new XmlSerializator<Student>(Student.class);
			assertTrue(student1.equals(xmlSeralization.fromFile(STUDENT_XML_PATH)));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fromTxtStudentTest() {
		try {
			Serialization<Student> txtSeralization = new TxtSerializator<Student>(Student.class);
			assertTrue(student1.equals(txtSeralization.fromFile(STUDENT_TXT_PATH)));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

	
	
	
	
	
	
	
	
	
