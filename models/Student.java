package models;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Student {
	private String name, lastName, faculty, birthDay, email, phoneNumber;
	boolean starosta, d; // d - Can student become a ship ?
	int n; // missings
	float averageBl;
	Map<String, Integer> edu = new HashMap<String, Integer>();
	
	public Student() {
		name = null;
		lastName = null;
		faculty = null;
		birthDay = null;
		email = null;
		phoneNumber = null;
		starosta = false;
		d = false;
		n = 0;
		edu = null;
		averageBl = 0f;
	}
	
	public Student(String name, String l, String b, String p, String e, String f, boolean i, boolean d, int n, float avB, String[] obj, Integer[] bl) {
		this.name = name;
		this.lastName = l;
		this.birthDay = b;
		this.phoneNumber = p;
		this.email = e;
		this.faculty = f;
		this.d = d;
		this.starosta = i;
		this.n = n;
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
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
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
	
	public void setN(int n) {
		this.n = n;
	}
	
	public int getN() {
		return this.n;
	}
	
	public boolean isStarosta () {
		return this.starosta;
	}
	
	public void setStarosta (boolean st) {
		this.starosta = st;
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
	
	public void setD(boolean d) {
		this.d = d;
		
		return;
	}
	
	public boolean isD() {
		return this.d;
	}
	
	public String years() {
		int old = 0;
		Calendar c = Calendar.getInstance();
		int yearNow = c.get(Calendar.YEAR);
		int monthNow = c.get(Calendar.MONTH) + 1;
		int dayNow = c.get(Calendar.DAY_OF_MONTH);
		
		char[] dateBirth = this.birthDay.toCharArray();
		
		int y = (dateBirth[6] - 48) * 1000 + ((int)dateBirth[7] - 48) * 100 + ((int)dateBirth[8] - 48) * 10 + (int)dateBirth[9] - 48;
		int m = (dateBirth[3] - 48) * 10 + dateBirth[4] - 48;
		int d = (dateBirth[0] - 48) * 10 + dateBirth[1] - 48;
		old = yearNow - y;
	
		if((yearNow - old < y) ||(m > monthNow))
			old --;
		else if(m == monthNow && d <= dayNow)
			old ++;
		
		return old + " years old";
	}
	
	protected boolean checkN(int n1) {
		if(this.n >= n) {
			return true;
		}
		
		return false;
	}
	
	protected boolean checkN() {
		if(this.n >= 30) {
			return true;
		}
		
		return false;
	}
	
	public void addN() {
		this.n ++;
	}
	
	public void addN(int n) {
		if(n < 0 || n > 50)
			throw new IllegalArgumentException("Enter correct n!(not negative and not more than 50)\n");
		if(this.checkN())
			throw new IllegalArgumentException("Can`t add N - this student reach max of N! Current value is: " + this.n + '\n');
		this.n += n;
		
		return;
	}
	
	@Override
	public String toString() {
		String res = "\r\nName: " + name + ";\r\nLast name: " + lastName + ";\r\nDate of birthday: " + birthDay
				+ ";\r\nFaculty: " + faculty  + ";\r\nPropusky: " + n  + ";\r\nStarosta: " + starosta + ";\r\nEducation succes: "
				+ edu.toString() + ";\r\nAverage Bal = " + this.averageBl + ";\r\nPhone number: " + phoneNumber + ";\r\nE-mail: " + email + "\r\n";
		if(this.checkN())
			res += "This student reached maximum of N(propusky) !!!\n";
		
		return res;
	}
}
	
