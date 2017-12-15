package models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(propOrder = {"name", "lastName", "birthDay", "faculty", "missings", "leader", "ship", "edu", "averageBl", "phoneNumber", "email"})
public class Student {
	private String name, lastName, faculty, email, phoneNumber;
	boolean leader, ship; 
	LocalDate birthDay;
	int missings;
	float averageBl;
	Map<String, Integer> edu = new HashMap<String, Integer>();
	
	public Student() {
		name = null;
		lastName = null;
		faculty = null;
		birthDay = null;
		email = null;
		phoneNumber = null;
		leader = false;
		ship = false;
		missings = 0;
		edu = null;
		averageBl = 0f;
	}
	
	public Student(String name, String l, LocalDate bithDay, String p, String e, String f, boolean i, boolean d, int n, float avB, String[] obj, Integer[] bl) {
		this.name = name;
		this.lastName = l;
		this.birthDay = bithDay;
		this.phoneNumber = p;
		this.email = e;
		this.faculty = f;
		this.ship = d;
		this.leader = i;
		this.missings = n;
		this.averageBl = avB;
		for(int j = 0; j < obj.length; ++ j) {
			this.edu.put(obj[j], bl[j]);
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	public LocalDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setMissings(int n) {
		this.missings = n;
	}
	
	public int getMissings() {
		return this.missings;
	}
	
	public boolean isLeader () {
		return this.leader;
	}
	
	public void setLeader (boolean st) {
		this.leader = st;
	}
	
	public void setEdu(Map<String, Integer> e) {
		this.edu = e;
	}
	
	public Map<String, Integer> getEdu() {
		return this.edu;
	}
	
	public void setAverageBl (float avB) {
		this.averageBl = avB;
	}
	
	public float getAverageBl () {
		return this.averageBl;
	}
	
	public void setShip(boolean d) {
		this.ship = d;
		
		return;
	}
	
	public boolean isShip() {
		return this.ship;
	}
	
	public int years() {
		int old = 0;
		LocalDate today = LocalDate.now();
		int monthOfBorn = birthDay.getMonthValue();
		int dayOfBorn = birthDay.getDayOfMonth();
		
		old = today.compareTo(birthDay);
		System.out.println("Old = " + old);
		
		if(monthOfBorn > today.getMonthValue() || (monthOfBorn == today.getMonthValue() && dayOfBorn > today.getDayOfMonth()))
			-- old;
		
		return old;
	}
	
	protected boolean checkN(int n) {
		if(this.missings >= n) {
			return true;
		}
		
		return false;
	}
	
	protected boolean checkMissings() {
		if(this.missings >= 30) {
			return true;
		}
		
		return false;
	}
	
	public void addMissings() {
		this.missings ++;
		
	}
	
	public void addMissings(int n) {
		if(n < 0 || n > 50)
			throw new IllegalArgumentException("Enter correct number of missings!(not negative and not more than 50)\n");
		if(this.checkMissings())
			throw new IllegalArgumentException("Can`t add N - this student reach max of missings! Current value is: " + this.missings + '\n');
		this.missings += missings;
		
		return;
	}
	
	@Override
	public String toString() {
		String res = "\r\nName: " + name + ";\r\nLast name: " + lastName + ";\r\nDate of birthday: " + birthDay
				+ ";\r\nFaculty: " + faculty  + ";\r\nMissings: " + missings  + ";\r\nLeader: " + leader + ";\r\nEducation success: "
				+ edu.toString() + ";\r\nAverage Bal = " + this.averageBl + ";\r\nPhone number: " + phoneNumber + ";\r\nE-mail: " + email + "\r\n";
		if(this.checkMissings())
			res += "This student reached maximum number of missings !!!\r\n";
		
		return res;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(averageBl);
		result = prime * result + ((birthDay == null) ? 0 : birthDay.hashCode());
		result = prime * result + ((edu == null) ? 0 : edu.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (leader ? 1231 : 1237);
		result = prime * result + missings;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + (ship ? 1231 : 1237);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (Float.floatToIntBits(averageBl) != Float.floatToIntBits(other.averageBl))
			return false;
		if (birthDay == null) {
			if (other.birthDay != null)
				return false;
		} else if (!birthDay.equals(other.birthDay))
			return false;
		if (edu == null) {
			if (other.edu != null)
				return false;
		} else if (!edu.equals(other.edu))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (leader != other.leader)
			return false;
		if (missings != other.missings)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (ship != other.ship)
			return false;
		return true;
	}
}
	
