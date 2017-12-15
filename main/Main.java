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

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Student> studArr = null;
		String path = "tmp/307.xml";
		
		try {
			studArr = new ArrayList<Student>();
			String[] group307Sbj = { "Math", "Computer networks", "Operation system", "Java", "Python" };
			String[] group304Sbj = { "Geometry", "Math", "Python", "Algebra" };
			
			Integer[] mark1 = { 75, 70, 82, 85, 90 };
			Integer[] mark2 = { 68, 84, 73, 68, 93 };
			Integer[] mark3 = { 92, 68, 56, 81 };
			Integer[] mark4 = { 82, 75, 77, 88 };
			Integer[] mark5 = { 60, 85, 91, 75 };
			
			
			Student student1 = new StudentBuilder("Vlad").setLastName("Zsfdgddf").setBirthDay(LocalDate.of(1997, Month.DECEMBER, 11))
									.setPhoneNumber("0975463187").setEmail("Proh@gmail.com")
									.setFaculty("Math-fac").setShip(true).setEdu(group307Sbj,  mark1).build();
			Student student2 = new StudentBuilder("Rostik").setLastName("Rgfdgb").setBirthDay(LocalDate.of(1997, Month.JULY, 22))
									.setPhoneNumber("0975497111").setLeader(true).setEmail("Khlan@gmail.com")
									.setFaculty("Math-fac").setMissings(11).setShip(true).setEdu(group307Sbj, mark2).build();
			Student student3 = new StudentBuilder("Danka").setLastName("Zsdfa").setBirthDay(LocalDate.of(1999, Month.AUGUST, 31))
									.setPhoneNumber("0971131548").setEmail("Danka@gmail.com")
									.setFaculty("Math-fac").setMissings(7).setShip(true).setEdu(group304Sbj, mark3).build();
		
			Student student4 = new StudentBuilder("Artem").setLastName("Omsk").setBirthDay(LocalDate.of(1998, Month.MARCH, 3))
									.setPhoneNumber("0978721375").setEmail("Katia@gmail.com")
									.setFaculty("Math-fac").setShip(true).setLeader(true).setEdu(group304Sbj, mark4).build();
			
			Student student5 = new StudentBuilder("Jecka").setLastName("Sidoruk").setBirthDay(LocalDate.of(1997, Month.DECEMBER, 21))
									.setPhoneNumber("0971733375").setEmail("jeckSid@gmail.com")
									.setFaculty("Math-fac").setShip(false).setEdu(group304Sbj, mark5).build();
			
			//studArr.add(student3);
			//studArr.add(student4);
			Group tmpGroup = new Group();
			//Group group307 = new GroupBuilder().setGroupName("System-analysis").setStudents(studArr).setCuratorName("Malyk Igor Volodymirovich").setGroupNumber(307).build();
			//DataBase db = new DataBase();
			//Group group304 = new GroupBuilder("Math").setStudents(studArr).setGroupNumber(304).setCuratorName("Nesterenko").build();
			
			//group307.sortByLastName();
			//System.out.println(student3.years() + " years old");
			//group307.sortByEdu();
			
			//System.out.println(group307.studentShip(0.4f).toString());
			
			//group307.sortByN();
			
			//System.out.println(group307.toString());
			
			//System.out.print(group307.maxN());
			
			//tmpGroup = Group.fromJson(path);
			//tmpGroup = Group.fromXml(path);
			//tmpGroup = Group.fromTxt(path);

			//tmpGroup = db.fromSql(302);
			//db.toSql(group304);
			//db.deleteStudent(307, 1);
			//db.newMarks(307, 1, subjects1, baly1);
			//db.newMark(307, 1, "Computer networks", 82);
			//db.addStudent(304, student5);
			//db.deleteBD("test");
			//System.out.println(group307.toJson(path));
			//System.out.println(group307.toXml(path));
			//System.out.println(group307.toTxt(path));
			
			//System.out.println(tmpGroup.toString());
		}
		
		catch(IllegalArgumentException e) {
			System.out.println(e.toString()); 
		}
	}
}
