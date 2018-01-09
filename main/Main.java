package main;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import builders.GroupBuilder;
import builders.StudentBuilder;
import dataBase.DataBase;
import models.Group;
import models.Student;
import serialization.*;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Student student1 = null;
		Student student2 = null;
		Student student3 = null;
		Student student4 = null;
		Student student5 = null;
		Student student6 = null;
		List<Student> studArr = null;
		List<Student> studArr2 = null;
		Group group1 = null;
		String jsonPath = null;
		String xmlPath = null;
		String txtPath = null;
		
		try {
			jsonPath = "tmp/307.json";
			xmlPath = "tmp/307.xml";
			txtPath = "tmp/student1.txt";
			
			String[] groupSbj1 = { "Math", "Computer networks", "Operation system", "Java", "Python" };
			String[] groupSbj2 = { "Math", "English", "Java", "Python" };
			studArr = new ArrayList<Student>();
			studArr2 = new ArrayList<Student>();
			
			Integer[] mark1 = { 75, 70, 82, 85, 90 };
			Integer[] mark2 = { 68, 84, 73, 68, 93 };
			Integer[] mark3 = { 92, 68, 56, 81, 60 };
			Integer[] mark4 = { 82, 75, 77, 88, 91 };
			
			Integer[] mark5 = { 75, 70, 42, 75};
			Integer[] mark6 = { 68, 48, 73, 86};
			
			
			student1 = new StudentBuilder("Vlad").setLastName("Zsfdgddf").setBirthDay(LocalDate.of(1997, Month.DECEMBER, 11))
									.setPhoneNumber("0975463187").setEmail("Proh@gmail.com")
									.setFaculty("Math-fac").setShip(true).setEdu(groupSbj1,  mark1).build();
			student2 = new StudentBuilder("Rostik").setLastName("Rgfdgb").setBirthDay(LocalDate.of(1997, Month.JULY, 22))
									.setPhoneNumber("0975497111").setLeader(true).setEmail("Khlan@gmail.com")
									.setFaculty("Math-fac").setMissings(11).setShip(false).setEdu(groupSbj1, mark2).build();
			student3 = new StudentBuilder("Katia").setLastName("Virstiuk").setBirthDay(LocalDate.of(1998, Month.DECEMBER, 07))
									.setPhoneNumber("0982484984").setEmail("Katiavir@gmail.com")
									.setFaculty("Math-fac").setMissings(7).setShip(true).setEdu(groupSbj1, mark3).build();
			student4 = new StudentBuilder("Artem").setLastName("Omsk").setBirthDay(LocalDate.of(1998, Month.MARCH, 3))
									.setPhoneNumber("0978721375").setMissings(3).setEmail("Katia@gmail.com")
									.setFaculty("Math-fac").setShip(true).setEdu(groupSbj1, mark4).build();
			student5 = new StudentBuilder("Igor").setLastName("Ivanitska").setBirthDay(LocalDate.of(1998, Month.AUGUST, 12))
									.setPhoneNumber("0961853146").setEmail("Daa@gmail.com")
									.setFaculty("Math-fac").setMissings(7).setShip(true).setEdu(groupSbj2, mark5).build();
			/*
			student6 = new StudentBuilder("Olga").setLastName("Zabur").setBirthDay(LocalDate.of(1996, Month.MARCH, 30))
									.setPhoneNumber("0978721375").setMissings(3).setEmail("Olia@gmail.com")
									.setFaculty("Math-fac").setEdu(groupSbj2, mark6).build();
			*/
			studArr.add(student1);
			studArr.add(student2);
			studArr.add(student3);
			studArr.add(student4);
			
			studArr2.add(student5);
			//studArr2.add(student6);
			
			group1 = new GroupBuilder("Group one").setGroupNumber(101).setCuratorName("Curator one").setStudents(studArr).build();
			Group group2 = new GroupBuilder("Group two").setGroupNumber(102).setCuratorName("Curator two").setStudents(studArr2).build();
			DataBase db = new DataBase();
			db.addGroup(group1);
			db.addGroup(group2);
			//System.out.println(db.deleteGroup(102));
			//Serialization<Group> jsonSerialize = new JsonSerializator<Group>(Group.class);
			//Serialization<Group> xmlSerialize = new XmlSerializator<Group>(Group.class);
			//Serialization<Student> txtSerialize = new TxtSerializator<Student>(Student.class);
			
			//System.out.println(txtSerialize.toFile(student1, txtPath));
			//System.out.println(txtSerialize.fromFile(txtPath).toString());
			
			//System.out.println(tmpGroup.toString());
		}
		
		catch(Exception e) {
			System.out.println(e.toString()); 
		}
	}
}
