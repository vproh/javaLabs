package builders;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import models.Group;
import models.Student;

@XmlRootElement (name = "Group")
public class GroupBuilder {
	String curatorName, groupName;
	ArrayList<Student> students = new ArrayList<Student>();
	int groupNumber;
	
	public GroupBuilder() {
		curatorName = null;
		groupName = null;
		students = null;
		groupNumber = 0;
	}
	
	public GroupBuilder(String grName) {
		this.groupName = grName;
	}
	
	@XmlElement(name = "groupName")
	public String getGroupName() {
		return groupName;
	}
	
	public GroupBuilder setGroupName(String name) {
		this.groupName = name;
		
		return this;
	}
	
	@XmlElement(name = "Students")
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public GroupBuilder setStudents(ArrayList<Student> s) {
		this.students = s;
		
		return this;
	}
	
	@XmlElement(name = "curator")
	public String getCuratorName() {
		return curatorName;
	}
	
	public GroupBuilder setCuratorName(String curName) {
		this.curatorName = curName;
		
		return this;			
	}
	
	@XmlElement(name = "groupNumber")
	public int getGroupNumber() {
		return groupNumber;
	}
	
	public GroupBuilder setGroupNumber(int grNum) {
		this.groupNumber = grNum;
		
		return this;
	}
	
	public Group build() {
		Group ob = new Group();
		Pattern namePattern = Pattern.compile("^[A-Z][a-z]{1,14}([- ][a-zA-Z]{1,15}){0,3}$");
		
		Matcher curNameMatcher = namePattern.matcher(this.curatorName);
		Matcher grNameMatcher = namePattern.matcher(this.groupName);
	
		if(!(curNameMatcher.matches()))
			throw new IllegalArgumentException("Enter correct curator`s last name! (Contains latin letters up to 15!)");
		if(!(grNameMatcher.matches()))
			throw new IllegalArgumentException("Enter correct group`s name! (Contains latin letters up to 15!)");
		if(this.groupNumber < 100 || this.groupNumber > 999)
			throw new IllegalArgumentException("Enter correct group`s number! (Numbers from 100 up to 999!)");
		
		ob.setGroupName(this.groupName);
		ob.setCuratorName(this.curatorName);
		ob.setGroupNumber(this.groupNumber);
		ob.setStudents(this.students);
		ob.countDandK();
		ob.averageBal();
		
		return ob;
	}
}