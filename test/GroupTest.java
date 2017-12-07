package test;

import org.testng.annotations.Test;

import models.Group;
import models.Student;
import builders.GroupBuilder;
import builders.StudentBuilder;

import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

class Global {
	public String[] subjects1 = { "Matan", "Algebra", "Computer networks", "Java", "Python" };
	public Integer[] baly1 = { 65, 66, 75, 85, 94 };
		
	public String[] subjects2 = {"Algebra", "Computer networks", "Java", "Python" };
	public  Integer[] baly2 = {66, 82, 85, 94 };
		
	public String[] subjects3 = { "Matan", "Algebra", "Computer networks", "Java" };
	public Integer[] baly3 = { 15, 66, 89, 53 };
	
	public Student student1 = new StudentBuilder("Vlad").setLastName("Zsfdgddf").setBirthDay("03.01.1999")
			.setPhoneNumber("0975463187").setEmail("Proh@gmail.com")
			.setFaculcy("Math-fac").setD(true).setEdu(subjects1,  baly1).build();
	public Student student2 = new StudentBuilder("Rostik").setLastName("Rgfdgb").setBirthDay("21.02.1998")
			.setPhoneNumber("0975497111").setEmail("Khlan@gmail.com")
			.setFaculcy("Math-fac").setN(11).setD(true).setEdu(subjects2, baly2).build();
	public Student student3 = new StudentBuilder("Danka").setLastName("Zsdfa").setBirthDay("09.11.1997")
			.setPhoneNumber("0971131548").setEmail("Danka@gmail.com")
			.setFaculcy("Math-fac").setN(7).setD(true).setEdu(subjects3, baly3).build();
	  
	public ArrayList<Student> studArr = new ArrayList<Student>();
	  
	public void setAll() {
		  student1.addN(5);
	
		  studArr.add(student1);
		  studArr.add(student2);
		  studArr.add(student3);
	  }
}


public class GroupTest {
	@Test(dataProvider = "sortByNProvider")
	public void sortByNTest(ArrayList<Student> studArr, String cName, String gName, int gNumber, String res) {
		Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
		ob.sortByN();
		assertEquals(ob.toString(), res);
	}

  	@DataProvider
  	public Object[][] sortByNProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "\n" + 
	  		"Name: Vlad;\n" + 
	  		"Last name: Zsfdgddf;\n" + 
	  		"Date of birthday: 03.01.1999;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 5; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=85, Algebra=66, Matan=65, Computer networks=75, Python=94}\n" + 
	  		"Average Bal = 77.0\n" + 
	  		"\n" + 
	  		"Name: Danka;\n" + 
	  		"Last name: Zsdfa;\n" + 
	  		"Date of birthday: 09.11.1997;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 7; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=53, Algebra=66, Matan=15, Computer networks=89}\n" + 
	  		"Average Bal = 55.75\n" + 
	  		"\n" + 
	  		"Name: Rostik;\n" + 
	  		"Last name: Rgfdgb;\n" + 
	  		"Date of birthday: 21.02.1998;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 11; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=85, Algebra=66, Computer networks=82, Python=94}\n" + 
	  		"Average Bal = 81.75\n" + 
	  		"\n" + 
	  		"Group name: System-analysis; Group number: 307; Contract students = 0; Der. students = 3; Kurator: Malyk Igor Vladimirivich.\n";
	  
    return new Object[][] {
      new Object[] { ob.studArr, "Malyk Igor Vladimirivich", "System-analysis", 307, res }
    };
  }
 
  @Test(dataProvider = "sortByLastNameProvider")
  	public void sortByLastNameTest(ArrayList<Student> studArr, String cName, String gName, int gNumber, String res) {
	 Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
	 ob.sortByLastName();
	 assertEquals(ob.toString(), res);
  }
  
  @DataProvider 
  	public Object[][] sortByLastNameProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "\n" + 
	  		"Name: Rostik;\n" + 
	  		"Last name: Rgfdgb;\n" + 
	  		"Date of birthday: 21.02.1998;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 11; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=85, Algebra=66, Computer networks=82, Python=94}\n" + 
	  		"Average Bal = 81.75\n" + 
	  		"\n" + 
	  		"Name: Danka;\n" + 
	  		"Last name: Zsdfa;\n" + 
	  		"Date of birthday: 09.11.1997;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 7; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=53, Algebra=66, Matan=15, Computer networks=89}\n" + 
	  		"Average Bal = 55.75\n" + 
	  		"\n" + 
	  		"Name: Vlad;\n" + 
	  		"Last name: Zsfdgddf;\n" + 
	  		"Date of birthday: 03.01.1999;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 5; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=85, Algebra=66, Matan=65, Computer networks=75, Python=94}\n" + 
	  		"Average Bal = 77.0\n" + 
	  		"\n" + 
	  		"Group name: System-analysis; Group number: 307; Contract students = 0; Der. students = 3; Kurator: Malyk Igor Vladimirivich.\n";
	  		
	  return new Object[][] {
	      new Object[] { ob.studArr, "Malyk Igor Vladimirivich", "System-analysis", 307, res }
	    };
  }

  @Test(dataProvider = "sortByEduProvider")
	public void sortByEduTest(ArrayList<Student> studArr, String cName, String gName, int gNumber, String res) {
		Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
		ob.sortByEdu();
		assertEquals(ob.toString(), res);
	}
  
  @DataProvider 
  	public Object[][] sortByEduProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "\n" + 
	  		"Name: Rostik;\n" + 
	  		"Last name: Rgfdgb;\n" + 
	  		"Date of birthday: 21.02.1998;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 11; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=85, Algebra=66, Computer networks=82, Python=94}\n" + 
	  		"Average Bal = 81.75\n" + 
	  		"\n" + 
	  		"Name: Vlad;\n" + 
	  		"Last name: Zsfdgddf;\n" + 
	  		"Date of birthday: 03.01.1999;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 5; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=85, Algebra=66, Matan=65, Computer networks=75, Python=94}\n" + 
	  		"Average Bal = 77.0\n" + 
	  		"\n" + 
	  		"Name: Danka;\n" + 
	  		"Last name: Zsdfa;\n" + 
	  		"Date of birthday: 09.11.1997;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 7; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=53, Algebra=66, Matan=15, Computer networks=89}\n" + 
	  		"Average Bal = 55.75\n" + 
	  		"\n" + 
	  		"Group name: System-analysis; Group number: 307; Contract students = 0; Der. students = 3; Kurator: Malyk Igor Vladimirivich.\n";
	  		
	  return new Object[][] {
	      new Object[] { ob.studArr, "Malyk Igor Vladimirivich", "System-analysis", 307, res }
	    };
  }
  
  
   	@Test(dataProvider = "hasMaxNProvider")
  	public void maxNTest(ArrayList<Student> studArr, String cName, String gName, int gNumber, String res) {
		Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
		assertEquals(ob.maxN(), res);
	}

  	@DataProvider 
	public Object[][] hasMaxNProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "Has the most N:\n" + 
	  		"\n" + 
	  		"Name: Rostik;\n" + 
	  		"Last name: Rgfdgb;\n" + 
	  		"Date of birthday: 21.02.1998;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 11; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=85, Algebra=66, Computer networks=82, Python=94}\n" + 
	  		"Average Bal = 81.75\n" + 
	  		"\n";
	  		
	  return new Object[][] {
	      new Object[] { ob.studArr, "Malyk Igor Vladimirivich", "System-analysis", 307, res }
	    };
}

  	@Test(dataProvider = "studentShipProvider")
  	public void studentShipTest(ArrayList<Student> studArr, String cName, String gName, int gNumber, float persent, String res) {
		Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
		assertEquals(ob.studentShip(persent).toString(), res);
	}
  	
  	@DataProvider 
	public Object[][] studentShipProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "[\n" + 
	  		"Name: Rostik;\n" + 
	  		"Last name: Rgfdgb;\n" + 
	  		"Date of birthday: 21.02.1998;\n" + 
	  		"Faculcy: Math-fac; \n" + 
	  		"Propusky: 11; \n" + 
	  		"Starosta: false;\n" + 
	  		"Education succes:\n" + 
	  		"{Java=85, Algebra=66, Computer networks=82, Python=94}\n" + 
	  		"Average Bal = 81.75\n" + 
	  		"]";
	  		
	  return new Object[][] {
	      new Object[] { ob.studArr, "Malyk Igor Vladimirivich", "System-analysis", 307, 0.4f, res }
	    };
  	}
  	
  	@Test(dataProvider = "averageBalProvider")
  	public void averageBalTest(ArrayList<Student> studArr, String cName, String gName, int gNumber, String res) {
		Group ob = new GroupBuilder(gName).setCuratorName(cName).setStudents(studArr).setGroupNumber(gNumber).build();
		assertEquals(ob.averageBal().toString(), res);
	}
  	
  	@DataProvider 
	public Object[][] averageBalProvider() {
	  Global ob = new Global();
	  ob.setAll();
	  String res = "{Zsfdgddf=77.0, Rgfdgb=81.75, Zsdfa=55.75}";
	  		
	  return new Object[][] {
	      new Object[] { ob.studArr, "Malyk Igor Vladimirivich", "System-analysis", 307, res }
	    };
  	}
  	
  	
  	
  	
}
