package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import builders.GroupBuilder;
import builders.StudentBuilder;
import models.Group;
import models.Student;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "toor";
		String path = "tmp/307.xml";
		
		try {
			String[] subjects1 = { "Matan", "Algebra", "Computer networks", "Java", "Python" };
			Integer[] baly1 = { 65, 66, 75, 85, 94 };
			
			String[] subjects2 = {"Algebra", "Computer networks", "Java", "Python" };
			Integer[] baly2 = {66, 82, 85, 94 };
			
			String[] subjects3 = { "Matan", "Algebra", "Computer networks", "Java" };
			Integer[] baly3 = { 15, 66, 89, 53 };
			
			String[] subjects4 = { "Matan", "Algebra", "Computer networks", "Java", "Python" };
			Integer[] baly4 = { 100, 75, 66, 50, 91 };
			/*
			String[] subjects5 = { "Matan", "Algebra", "Java", "Python" };
			Integer[] baly5 = { 92, 68, 56, 81};
			*/
			
			Student student1 = new StudentBuilder("Vlad").setLastName("Zsfdgddf").setBirthDay("03.01.1999")
									.setPhoneNumber("0975463187").setEmail("Proh@gmail.com")
									.setFaculcy("Math-fac").setD(true).setEdu(subjects1,  baly1).build();
			Student student2 = new StudentBuilder("Rostik").setLastName("Rgfdgb").setBirthDay("21.02.1998")
									.setPhoneNumber("0975497111").setEmail("Khlan@gmail.com")
									.setFaculcy("Math-fac").setN(11).setD(true).setEdu(subjects2, baly2).build();
			Student student3 = new StudentBuilder("Danka").setLastName("Zsdfa").setBirthDay("09.11.1997")
									.setPhoneNumber("0971131548").setEmail("Danka@gmail.com")
									.setFaculcy("Math-fac").setN(7).setD(true).setEdu(subjects3, baly3).build();
		
			Student student4 = new StudentBuilder("Yarik").setLastName("Asfsfdvb").setBirthDay("16.12.1998")
									.setPhoneNumber("0978721375").setEmail("Yarik@gmail.com")
									.setFaculcy("Math-fac").setD(true).setStarosta(true).setEdu(subjects4, baly4).build();
			/*
			Student student5 = new StudentBuilder("Jecka").setLastName("Asdgfdhvb").setBirthDay("30.08.1999")
									.setPhoneNumber("0971733375").setEmail("jeckSid@gmail.com")
									.setFaculcy("Math-fac").setD(true).setEdu(subjects5, baly5).build();
			*/
			student1.addN(4);
	  		student4.addN(15);
	  		//student5.addN(4);
			
			ArrayList<Student> studArr = new ArrayList<Student> ();
			
			studArr.add(student1);
			studArr.add(student2);
			studArr.add(student3);
			//studArr.add(student4);
			//studArr.add(student5);
			
			Group group307 = new GroupBuilder().setGroupName("System-analysis").setStudents(studArr).setCuratorName("Malyk Igor Vladimirovich").setGroupNumber(307).build();
			Group tmpGroup = new Group();
			
			//group307.sortByLastName();
			
			//group307.sortByEdu();
			
			//System.out.println(group307.studentShip(0.4f).toString());
			
			//group307.sortByN();
			
			//System.out.println(group307.toString());
			
			//System.out.print(group307.maxN());
			//tmpGroup = Group.fromJson(path);
			//tmpGroup = Group.fromXml(path);
			//tmpGroup = Group.fromTxt(path);
			
			//tmpGroup = Group.fromSql(url, user, password, 307);
			//tmpGroup.toSql(url, user, password);
			//group307.addStudentSql(url, user, password, student2);
			//group307.deleteStudentSql(url, user, password, 2);
			//System.out.println(group307.toJson(path));
			System.out.println(group307.toXml(path));
			//System.out.println(group307.toTxt(path));
			
			//System.out.println(tmpGroup.toString());
		}
		
		catch(IllegalArgumentException | IOException | JAXBException e) {
			System.out.println(e.toString()); 
		}
	}
}
