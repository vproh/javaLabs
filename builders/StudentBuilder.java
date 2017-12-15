package builders;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Student;

public class StudentBuilder {
	private String name, lastName, faculcy, email, phoneNumber;
	private LocalDate birthDay;
	private boolean leader, ship;
	private int missings; // missings
	private float averageB = 0f;
	Map<String, Integer> edu = new HashMap<String, Integer>();
	
	public StudentBuilder (String name) {
		
		this.name = name;
	}
	
	public StudentBuilder setName(String name) {
		this.name = name;
		
		return this;
	}
	
	public StudentBuilder setLastName(String l) {
		this.lastName = l;
		return this;
	}
	
	public StudentBuilder setFaculty(String f) {
		this.faculcy = f;
		return this;
	}
	
	public StudentBuilder setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
		return this;
	}
	
	public StudentBuilder setEmail(String e) {
		this.email = e;
		return this;
	}
	
	public StudentBuilder setLeader(boolean i) {
		this.leader = i;
		return this;
	}
	
	public StudentBuilder setPhoneNumber(String p) {
		this.phoneNumber = p;
		return this;
	}
	
	public StudentBuilder setAveregeB(float avB) {
		this.averageB = avB;
		return this;
	}
	
	public StudentBuilder setMissings(int n) {
		this.missings = n;
		
		return this;
	}
	
	public StudentBuilder setEdu(String[] sbj, Integer[] mrk) {
		this.edu.clear();
		for(int i = 0; i < sbj.length; ++ i)
			this.edu.put(sbj[i], mrk[i]);
		
		return this;
	}
	
	public StudentBuilder setShip(boolean ship) {
		this.ship = ship;
		return this;
	}
	
	public Student build() {
		Student student = new Student();
	
		Pattern namePattern = Pattern.compile("^[A-Z][a-z]{1,14}([- ][a-zA-Z]{1,15}){0,3}$");
		Pattern lastNamePattern = Pattern.compile("^[A-Z][a-z]{1,15}$"); // faculty too
		Pattern emailPattern = Pattern.compile("^\\w+([\\.-]?\\w+){1,3}@(\\w+\\.\\w{2,3})$");
		Pattern phoneNumberPattern = Pattern.compile("^(\\+?38)?(((099)|(098)|(097)|(096)|(095)|(068)|(067)|(066)|(065))\\d{7})$");
	
		Matcher nameMatch = namePattern.matcher(this.name);
		Matcher lastNameMatch = lastNamePattern.matcher(this.lastName);
		Matcher facultyMatch = namePattern.matcher(this.faculcy);
		Matcher emailMatch = emailPattern.matcher(this.email);
		Matcher phoneNumberMatch = phoneNumberPattern.matcher(this.phoneNumber);
		
		if(!(nameMatch.matches()))
			throw new IllegalArgumentException("Enter correct name!(Contains latin letters up to 15!");
		if(!(lastNameMatch.matches()))
			throw new IllegalArgumentException("Enter correct last name! (Contains latin letters up to 15!)");
		if(!(phoneNumberMatch.matches()))
			throw new IllegalArgumentException("Enter correct phone number!(begin on \"+380\" or \"0xx\")");
		if(!(emailMatch.matches()))
			throw new IllegalArgumentException("Enter correct e-mail!(must have \'@\' and \'.\' ");
		if(!(facultyMatch.matches()))
			throw new IllegalArgumentException("Enter correct faculty name! (Contains latin letters up to 15!)");			
		if(missings < 0 || missings > 50)
			throw new IllegalArgumentException("Enter correct n!(not negative and not more than 50)\n");
		if(averageB < 0 || averageB > 100)
			throw new IllegalArgumentException("Enter correct average Bal!(not negative and not more than 100)\n");
		if(birthDay.getYear() > 2001 || birthDay.getYear() < 1994)
			throw new IllegalArgumentException("Enter correct born year ! (from 1994 to 2001)");
		
		for(String key: edu.keySet()) {
			Matcher subjectMatch = namePattern.matcher(key);
			if(!(subjectMatch.matches()))
				throw new IllegalArgumentException("One or more name of subjects is incorrect!");
		}
		for(Integer v: edu.values()) {
			if(v < 0 || v > 100)
				throw new IllegalArgumentException("One or more marks is incorrect(from 0 to 100)!");
		}
				
		student.setName(name);
		student.setLastName(lastName);
		student.setBirthDay(birthDay);
		student.setPhoneNumber(phoneNumber);
		student.setEmail(email);
		student.setFaculty(faculcy);
		student.setMissings(missings);
		student.setLeader(leader);
		student.setEdu(edu);
		student.setAverageBl(averageB);
		student.setShip(ship);
			
		return student;
	}
}