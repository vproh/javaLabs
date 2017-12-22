package test;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import builders.StudentBuilder;

public class StudentTest {
  @Test(dataProvider = "oldProvider")
  public void oldTest(String firstName, String lastName, LocalDate birthDay, String phone, String email,
		  				String faculty, boolean leader, String[] sbj, Integer[] marks, int expAge) {
	  assertEquals(new StudentBuilder(firstName).setLastName(lastName).setBirthDay(birthDay)
			  							     .setPhoneNumber(phone).setEmail(email)
			  							     .setFaculty(faculty).setLeader(leader).setEdu(sbj,  marks).build().getAge(), expAge);
  }
  @DataProvider
  public Object[][] oldProvider() {
	  LocalDate birthDay = LocalDate.of(2000, Month.JANUARY, 1);
	  String[] subjects = { "Matan", "Algebra", "Computer networks", "Java", "Python" };
	  Integer[] baly = { 65, 50, 75, 85, 94 };
	  
	  return new Object[][] { {"Vlad", "Proeh", birthDay, "0971256842", "Prog@fsdkfds.com", "Math-fac", true, subjects, baly, 17} };
  }
}
