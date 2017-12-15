package test;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import builders.StudentBuilder;

public class StudentTest {
  @Test(dataProvider = "oldProvider")
  public void oldTest(String n, String l, LocalDate b, String p, String e, String f, boolean st, String[] sbj, Integer[] bl, int y) {
	  assertEquals(new StudentBuilder(n).setLastName(l).setBirthDay(b)
			  							     .setPhoneNumber(p).setEmail(e)
			  							     .setFaculty(f).setMissings(10).setLeader(st).setEdu(sbj,  bl).build().years(), y);
  }
  @DataProvider
  public Object[][] oldProvider() {
	  LocalDate birthDay = LocalDate.of(2000, Month.JANUARY, 1);
	  String[] subjects = { "Matan", "Algebra", "Computer networks", "Java", "Python" };
	  Integer[] baly = { 65, 50, 75, 85, 94 };
	  
	  return new Object[][] { {"Vlad", "Proeh", birthDay, "0971256842", "Prog@fsdkfds.com", "Math-fac", true, subjects, baly, 17} };
  }
}
