package test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import builders.StudentBuilder;

public class StudentTest {
  @Test(dataProvider = "oldProvider")
  public void oldTest(String n, String l, String b, String p, String e, String f, boolean st, String[] sbj, Integer[] bl, String y) {
	  assertEquals(new StudentBuilder(n).setLastName(l).setBirthDay(b)
			  							     .setPhoneNumber(p).setEmail(e)
			  							     .setFaculcy(f).setN(10).setStarosta(st).setEdu(sbj,  bl).build().years(), y);
  }
  @DataProvider
  public Object[][] oldProvider() {
	  String[] subjects = { "Matan", "Algebra", "Computer networks", "Java", "Python" };
	  Integer[] baly = { 65, 50, 75, 85, 94 };
	  
	  return new Object[][] { {"Vlad", "Proeh", "01.01.2000", "0971256842", "Prog@fsdkfds.com", "Math-fac", true, subjects, baly, "17 years old"} };
  }
}
