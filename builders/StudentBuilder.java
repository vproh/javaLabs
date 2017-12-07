package builders;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.Student;

public class StudentBuilder {
	private String name, lastName, faculcy, birthDay, email, phoneNumber;
	private boolean starosta, d;
	private int n; // missings
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
	
	public StudentBuilder setFaculcy(String f) {
		this.faculcy = f;
		return this;
	}
	
	public StudentBuilder setBirthDay(String b) {
		this.birthDay = b;
		return this;
	}
	
	public StudentBuilder setEmail(String e) {
		this.email = e;
		return this;
	}
	
	public StudentBuilder setStarosta(boolean i) {
		this.starosta = i;
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
	
	public StudentBuilder setN(int n) {
		this.n = n;
		
		return this;
	}
	
	public StudentBuilder setEdu(String[] obj, Integer[] b) {
		for(int i = 0; i < obj.length; ++ i)
			this.edu.put(obj[i], b[i]);
		
		return this;
	}
	
	public StudentBuilder setD(boolean d) {
		this.d = d;
		return this;
	}
	
	public Student build() {
		Student student = new Student();
	
		Pattern namePattern = Pattern.compile("^[A-Z][a-z]{1,14}([- ][a-zA-Z]{1,15}){0,3}$");
		Pattern lastNamePattern = Pattern.compile("^[A-Z][a-z]{1,15}$"); // faculty too
		Pattern birthDatePattern = Pattern.compile("^(((0?[1-9])|([12][0-9]))\\.((0(?=[1-9])[^2])|(1[0-2]))|(((0?[1-9])|([12][0-8]))\\.(02))|(30\\.((0(?=[1-9])[^2])|(1[0-2])))|(31\\.((0[13578])|1[02])))\\.((19[6-9]\\d)|(200[0-1]))$");
		Pattern emailPattern = Pattern.compile("^\\w+([\\.-]?\\w+){1,3}@(\\w+\\.\\w{2,3})$");
		Pattern phoneNumberPattern = Pattern.compile("^(\\+?38)?(((099)|(098)|(097)|(096)|(095)|(068)|(067)|(066)|(065))\\d{7})$");
	
		Matcher nameMatch = namePattern.matcher(this.name);
		Matcher lastNameMatch = lastNamePattern.matcher(this.lastName);
		Matcher facultyMatch = namePattern.matcher(this.faculcy);
		Matcher birthDateMatch = birthDatePattern.matcher(this.birthDay);
		Matcher emailMatch = emailPattern.matcher(this.email);
		Matcher phoneNumberMatch = phoneNumberPattern.matcher(this.phoneNumber);
		
		if(!(nameMatch.matches()))
			throw new IllegalArgumentException("Enter correct name!(Contains latin letters up to 15!");
		if(!(lastNameMatch.matches()))
			throw new IllegalArgumentException("Enter correct last name! (Contains latin letters up to 15!)");
		if(!(birthDateMatch.matches()))
			throw new IllegalArgumentException("Enter correct birth date(In format dd(day).mm(month).YYYY(year)!");
		if(!(phoneNumberMatch.matches()))
			throw new IllegalArgumentException("Enter correct phone number!(begin on \"+380\" or \"0xx\")");
		if(!(emailMatch.matches()))
			throw new IllegalArgumentException("Enter correct e-mail!(must have \'@\' and \'.\' ");
		if(!(facultyMatch.matches()))
			throw new IllegalArgumentException("Enter correct faculty name! (Contains latin letters up to 15!)");			
		if(n < 0 || n > 50)
			throw new IllegalArgumentException("Enter correct n!(not negative and not more than 50)\n");
		if(averageB < 0 || averageB > 100)
			throw new IllegalArgumentException("Enter correct average Bal!(not negative and not more than 100)\n");
		
		for(String key: edu.keySet()) {
			Matcher subjectMatch = namePattern.matcher(key);
			if(!(subjectMatch.matches()))
				throw new IllegalArgumentException("One or more name of subjects is incorrect!");
		}
		for(Integer v: edu.values()) {
			if(v < 0 || v > 100)
				throw new IllegalArgumentException("One or more baliv is incorrect(from 0 to 100)!");
		}
				
		student.setName(name);
		student.setLastName(lastName);
		student.setBirthDay(birthDay);
		student.setPhoneNumber(phoneNumber);
		student.setEmail(email);
		student.setFaculty(faculcy);
		student.setN(n);
		student.setStarosta(starosta);
		student.setEdu(edu);
		student.setAverageBl(averageB);
		student.setD(d);
			
		return student;
	}
}