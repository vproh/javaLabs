package test;

import org.testng.annotations.Test;

import models.Group;
import models.Student;
import builders.GroupBuilder;
import builders.StudentBuilder;

import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

class Global {
	
	String[] subjects1 = { "Matan", "Algebra", "Computer networks", "Java", "Python" };
	Integer[] baly1 = { 65, 66, 75, 85, 94 };
	
	String[] subjects2 = {"Algebra", "Computer networks", "Java", "Python" };
	Integer[] baly2 = {66, 82, 85, 94 };
	
	String[] subjects3 = { "Matan", "Algebra", "Computer networks", "Java" };
	Integer[] baly3 = { 15, 66, 89, 53 };
	
	String[] subjects4 = { "Matan", "Algebra", "Computer networks", "Java", "Python" };
	Integer[] baly4 = { 100, 75, 66, 50, 91 };
	
	Student student1 = new StudentBuilder("Vlad").setLastName("Zsfdgddf").setBirthDay(LocalDate.of(1997, Month.DECEMBER, 11))
			.setPhoneNumber("0975463187").setEmail("Proh@gmail.com")
			.setFaculty("Math-fac").setShip(true).setEdu(subjects1,  baly1).build();
	Student student2 = new StudentBuilder("Rostik").setLastName("Rgfdgb").setBirthDay(LocalDate.of(1997, Month.JULY, 22))
			.setPhoneNumber("0975497111").setEmail("Khlan@gmail.com")
			.setFaculty("Math-fac").setMissings(11).setShip(true).setEdu(subjects2, baly2).build();
	Student student3 = new StudentBuilder("Danka").setLastName("Zsdfa").setBirthDay(LocalDate.of(1999, Month.AUGUST, 31))
			.setPhoneNumber("0971131548").setEmail("Danka@gmail.com")
			.setFaculty("Math-fac").setMissings(7).setShip(true).setEdu(subjects3, baly3).build();
	Student student4 = new StudentBuilder("Katrin").setLastName("Virstiuk").setBirthDay(LocalDate.of(1998, Month.MARCH, 3))
			.setPhoneNumber("0978721375").setEmail("Katia@gmail.com")
			.setFaculty("Math-fac").setShip(true).setLeader(true).setEdu(subjects4, baly4).build();
	  
	public List<Student> studArr = new ArrayList<Student>();
	  
	public void setAll() {
	
		  studArr.add(student1);
		  studArr.add(student2);
		  studArr.add(student3);
	  }
}


public class GroupTest {
	@Test(dataProvider = "sortByNProvider")
	public void sortByNTest(List<Student> studArr, String cName, String gName, int gNumber, String res) {
		Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
		ob.sortByN();
		assertEquals(ob.toString(), res);
	}

  	@DataProvider
  	public Object[][] sortByNProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "\r\n" + 
	  		"Name: Vlad;\r\n" + 
	  		"Last name: Zsfdgddf;\r\n" + 
	  		"Date of birthday: 1997-12-11;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 0;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=85, Algebra=66, Matan=65, Computer networks=75, Python=94};\r\n" + 
	  		"Average Bal = 77.0;\r\n" + 
	  		"Phone number: 0975463187;\r\n" + 
	  		"E-mail: Proh@gmail.com\r\n" + 
	  		"\r\n" + 
	  		"Name: Danka;\r\n" + 
	  		"Last name: Zsdfa;\r\n" + 
	  		"Date of birthday: 1999-08-31;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 7;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=53, Algebra=66, Matan=15, Computer networks=89};\r\n" + 
	  		"Average Bal = 55.75;\r\n" + 
	  		"Phone number: 0971131548;\r\n" + 
	  		"E-mail: Danka@gmail.com\r\n" + 
	  		"\r\n" + 
	  		"Name: Rostik;\r\n" + 
	  		"Last name: Rgfdgb;\r\n" + 
	  		"Date of birthday: 1997-07-22;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 11;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=85, Algebra=66, Computer networks=82, Python=94};\r\n" + 
	  		"Average Bal = 81.75;\r\n" + 
	  		"Phone number: 0975497111;\r\n" + 
	  		"E-mail: Khlan@gmail.com\r\n" + 
	  		"\r\n" + 
	  		"Group name: System-analysis; Group number: 307; Contract students = 0; Der. students = 3; curator: Malyk Igor Vladimirovich\n";
	  
    return new Object[][] {
      new Object[] { ob.studArr, "Malyk Igor Vladimirovich", "System-analysis", 307, res }
    };
  }
 
  @Test(dataProvider = "sortByLastNameProvider")
  	public void sortByLastNameTest(List<Student> studArr, String cName, String gName, int gNumber, String res) {
	 Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
	 ob.sortByLastName();
	 assertEquals(ob.toString(), res);
  }
  
  @DataProvider 
  	public Object[][] sortByLastNameProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "\r\n" + 
	  		"Name: Rostik;\r\n" + 
	  		"Last name: Rgfdgb;\r\n" + 
	  		"Date of birthday: 1997-07-22;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 11;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=85, Algebra=66, Computer networks=82, Python=94};\r\n" + 
	  		"Average Bal = 81.75;\r\n" + 
	  		"Phone number: 0975497111;\r\n" + 
	  		"E-mail: Khlan@gmail.com\r\n" + 
	  		"\r\n" + 
	  		"Name: Danka;\r\n" + 
	  		"Last name: Zsdfa;\r\n" + 
	  		"Date of birthday: 1999-08-31;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 7;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=53, Algebra=66, Matan=15, Computer networks=89};\r\n" + 
	  		"Average Bal = 55.75;\r\n" + 
	  		"Phone number: 0971131548;\r\n" + 
	  		"E-mail: Danka@gmail.com\r\n" + 
	  		"\r\n" + 
	  		"Name: Vlad;\r\n" + 
	  		"Last name: Zsfdgddf;\r\n" + 
	  		"Date of birthday: 1997-12-11;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 0;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=85, Algebra=66, Matan=65, Computer networks=75, Python=94};\r\n" + 
	  		"Average Bal = 77.0;\r\n" + 
	  		"Phone number: 0975463187;\r\n" + 
	  		"E-mail: Proh@gmail.com\r\n" + 
	  		"\r\n" + 
	  		"Group name: System-analysis; Group number: 307; Contract students = 0; Der. students = 3; curator: Malyk Igor Vladimirovich\n";
	  		
	  return new Object[][] {
	      new Object[] { ob.studArr, "Malyk Igor Vladimirovich", "System-analysis", 307, res }
	    };
  }

  @Test(dataProvider = "sortByEduProvider")
	public void sortByEduTest(List<Student> studArr, String cName, String gName, int gNumber, String res) {
		Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
		ob.sortByEdu();
		assertEquals(ob.toString(), res);
	}
  
  @DataProvider 
  	public Object[][] sortByEduProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "\r\n" + 
	  		"Name: Rostik;\r\n" + 
	  		"Last name: Rgfdgb;\r\n" + 
	  		"Date of birthday: 1997-07-22;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 11;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=85, Algebra=66, Computer networks=82, Python=94};\r\n" + 
	  		"Average Bal = 81.75;\r\n" + 
	  		"Phone number: 0975497111;\r\n" + 
	  		"E-mail: Khlan@gmail.com\r\n" + 
	  		"\r\n" + 
	  		"Name: Vlad;\r\n" + 
	  		"Last name: Zsfdgddf;\r\n" + 
	  		"Date of birthday: 1997-12-11;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 0;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=85, Algebra=66, Matan=65, Computer networks=75, Python=94};\r\n" + 
	  		"Average Bal = 77.0;\r\n" + 
	  		"Phone number: 0975463187;\r\n" + 
	  		"E-mail: Proh@gmail.com\r\n" + 
	  		"\r\n" + 
	  		"Name: Danka;\r\n" + 
	  		"Last name: Zsdfa;\r\n" + 
	  		"Date of birthday: 1999-08-31;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 7;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=53, Algebra=66, Matan=15, Computer networks=89};\r\n" + 
	  		"Average Bal = 55.75;\r\n" + 
	  		"Phone number: 0971131548;\r\n" + 
	  		"E-mail: Danka@gmail.com\r\n" + 
	  		"\r\n" + 
	  		"Group name: System-analysis; Group number: 307; Contract students = 0; Der. students = 3; curator: Malyk Igor Vladimirovich\n";
	  		
	  return new Object[][] {
	      new Object[] { ob.studArr, "Malyk Igor Vladimirovich", "System-analysis", 307, res }
	    };
  }
  
   	@Test(dataProvider = "hasMaxNProvider")
  	public void maxNTest(List<Student> studArr, String cName, String gName, int gNumber, String res) {
		Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
		assertEquals(ob.maxN(), res);
	}

  	@DataProvider 
	public Object[][] hasMaxNProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "Has the most N:\r\n" + 
	  		"\r\n" + 
	  		"Name: Rostik;\r\n" + 
	  		"Last name: Rgfdgb;\r\n" + 
	  		"Date of birthday: 1997-07-22;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 11;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=85, Algebra=66, Computer networks=82, Python=94};\r\n" + 
	  		"Average Bal = 81.75;\r\n" + 
	  		"Phone number: 0975497111;\r\n" + 
	  		"E-mail: Khlan@gmail.com\r\n";
	  		
	  return new Object[][] {
	      new Object[] { ob.studArr, "Malyk Igor Vladimirovich", "System-analysis", 307, res }
	    };
}
	
  	@Test(dataProvider = "studentShipProvider")
  	public void studentShipTest(List<Student> studArr, String cName, String gName, int gNumber, float persent, String res) {
		Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
		assertEquals(ob.studentShip(persent).toString(), res);
	}
  	
  	@DataProvider 
	public Object[][] studentShipProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "[\r\n" + 
	  		"Name: Rostik;\r\n" + 
	  		"Last name: Rgfdgb;\r\n" + 
	  		"Date of birthday: 1997-07-22;\r\n" + 
	  		"Faculty: Math-fac;\r\n" + 
	  		"Missings: 11;\r\n" + 
	  		"Leader: false;\r\n" + 
	  		"Education success: " + 
	  		"{Java=85, Algebra=66, Computer networks=82, Python=94};\r\n" + 
	  		"Average Bal = 81.75;\r\n" + 
	  		"Phone number: 0975497111;\r\n" + 
	  		"E-mail: Khlan@gmail.com\r\n" + 
	  		"]";
;
	  		
	  return new Object[][] {
	      new Object[] { ob.studArr, "Malyk Igor Vladimirovich", "System-analysis", 307, 0.4f, res }
	    };
  	}
  	
  	@Test(dataProvider = "averageBalProvider")
  	public void averageBalTest(List<Student> studArr, String cName, String gName, int gNumber, String res) {
		Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
		assertEquals(ob.averageBal().toString(), res);
	}
  	
  	@DataProvider 
	public Object[][] averageBalProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "{Zsfdgddf=77.0, Rgfdgb=81.75, Zsdfa=55.75}";
	  		
	  return new Object[][] {
	      new Object[] { ob.studArr, "Malyk Igor Vladimirovich", "System-analysis", 307, res }
	    };
  	}
}
