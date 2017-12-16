package models;

import java.io.IOException;
import java.time.LocalDate;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import builders.*;


@XmlRootElement(name = "Group")
@XmlType(propOrder = {"groupName", "groupNumber", "curatorName", "shipQuantity", "payQuantity", "students"})
public class Group {
	private String curatorName, groupName;
	private int groupNumber, shipQuantity, payQuantity;
	List<Student> students = new ArrayList<Student>();
	
	public Group() {
		curatorName = null;
		groupName = null;
		groupNumber = 100;
		students = null;
		shipQuantity = 0;
		payQuantity = 0;
	}
	
	public Group(ArrayList<Student> s, String cName, String gName, int gNumber) {
		curatorName = cName;
		groupName = gName;
		groupNumber = gNumber;
		students = s;
		shipQuantity = 0;
		payQuantity = 0;
	}
	
	@XmlElement(name = "groupName")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@XmlElement(name = "curator")
	public String getCuratorName() {
		return curatorName;
	}

	public void setCuratorName(String curatorName) {
		this.curatorName = curatorName;
	}
	
	@XmlElement(name = "groupNumber")
	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}
	
	@XmlElement(name = "shipStudents")
	public int getshipQuantity() {
		return this.shipQuantity;
	}

	public void setshipQuantity(int shipQuantity) {
		this.shipQuantity = shipQuantity;
	}
	
	@XmlElement(name = "payStudents")
	public int getpayQuantity() {
		return this.payQuantity;
	}

	public void setpayQuantity(int payQuantity) {
		this.payQuantity = payQuantity;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	@XmlElement(name = "Students")
	public List<Student> getStudents() {
		return this.students;
	}
	
	public void countShipStudents() {
		int d = 0, k = 0;
		for(int i = 0; i < students.size(); ++ i) {
			if(this.students.get(i).isShip())
				d ++;
			else
				k ++;
		}
		setshipQuantity(d);
		setpayQuantity(k);
				
		return;
	}
	
	public List<Student> studentShip (float persent) {
		List<Student> res = new ArrayList<Student>();
		int m = 0;
		
		m = (int) (getshipQuantity() * persent);
		sortByEdu();
		for(int i = 0; i < m; ++ i)
			res.add(students.get(i));
		
		return res;
	}

	public void sortByN() {
		if(students == null || students.size() == 0)
			return;
		this.quicksortN(0, students.size() - 1);
		
		return;
	}
	
	public void sortByEdu() {
		if(students == null || students.size() == 0)
			return;
		quicksortEdu(0, students.size() - 1);

		return;
	}
	
	private void swap(int i, int j) {
		Student tmp = students.get(i);
		students.set(i, students.get(j));
		students.set(j,  tmp);
	}
	
	private void quicksortN (int low, int high) {
		int i = low, j = high;
		int half = students.get(low + (high - low) / 2).getMissings();
		
		while(i <= j) {
			while(students.get(i).getMissings() < half)
				i ++;
			while(students.get(j).getMissings() > half)
				j --;
			if(i <= j) {
				swap(i, j);
				i ++;
				j --;
			}
		}
		
		if(low < j)
			quicksortN(low, j);
		if(i < high)
			quicksortN(i, high);
	}
	
	private void quicksortEdu(int low, int high) {
		int i = low, j = high;
		float half = students.get(low + (high - low) / 2).getAverageBl();
		
		while(i <= j) {
			while(students.get(i).getAverageBl() > half)
				i ++;
			while(students.get(j).getAverageBl() < half)
				j --;
			if(i <= j) {
				swap(i, j);
				i ++;
				j --;
			}
		}
		
		if(low < j) {
			quicksortEdu(low, j);
		}
		if(i < high) 
			quicksortEdu(i, high);
	}
	
	public Student maxN() {
		int max = 0;
		Student person = new Student();
		
		for(Student i:students)
			if(max < i.getMissings()) {
				person = i;
				max = i.getMissings();
			}
		
		return person;
	}
	
	public void sortByLastName() {
		if(students == null || students.size() == 0)
			return;
		int k = 0;
		char[] name, name1;
		for(int i = 0; i < students.size() - 1; ++ i) {
			name = students.get(i).getLastName().toLowerCase().toCharArray();
			for(int j = i + 1; j < students.size(); ++ j) {
				k = 0;
				name1 = students.get(j).getLastName().toLowerCase().toCharArray();
	
				while(k < name.length && k < name1.length) {
					if( (int)name[k] < (int)name1[k]) {
						break;
					}
					if( (int)name[k] > (int)name1[k] ) {
						swap(i, j);
						name = students.get(i).getLastName().toLowerCase().toCharArray();
						break;
					}
					k ++;
					if(k == name1.length) {
						swap(i, j);
						name = students.get(i).getLastName().toLowerCase().toCharArray();
					}
				}
			}
		}
	}
	
	public Map<String, Float> averageMark () {
		Map<String, Float> averageList = new HashMap<String, Float>();
		Iterator<String> it;
		Set<String> sbjs;
		float avBl = 0f;
		String sbj;
		
		for(int i = 0; i < students.size(); ++ i) {
			avBl = 0f;
			sbjs = students.get(i).getEdu().keySet();
			it = sbjs.iterator();
			for(int j = 0; j < sbjs.size(); ++ j) {
				sbj = it.next();
				avBl += students.get(i).getEdu().get(sbj);
			}
			avBl /= sbjs.size();
			students.get(i).setAverageBl(avBl);
			averageList.put(students.get(i).getLastName(), avBl);
		}
		
		return averageList;
	}
	
	public boolean toJson(String path) throws IOException   {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter outFile = null;
		
		try {
			outFile = new FileWriter(path);
			gson.toJson(this, outFile);
		}
		finally {
			if(outFile != null)
				outFile.close();
		}
		
		return true;
	}
	
	public boolean toXml(String path) throws JAXBException, IOException {
		 JAXBContext c = null;
		 FileWriter outFile = null;
		 Marshaller m = null;
		 
	        try {
	        	outFile = new FileWriter(path);
	            c = JAXBContext.newInstance(Group.class);
	            m = c.createMarshaller();
	            
	            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	            m.marshal(this, System.out);
	            m.marshal(this, outFile);
	        }
	        finally {
	        	if(outFile != null)
	        		outFile.close();
	        }
	        
	        return true;
	}
	 
	public boolean toTxt(String path) throws IOException {
		FileWriter outFile = null;
		String groupInfo = null;
		
		try {
			groupInfo = this.toString();
			outFile = new FileWriter(path);
			outFile.write(groupInfo);
		}
		finally {
			if(outFile != null)
				outFile.close();
		}
		
		return true;
	}
	
	public static Group fromJson(String path) throws IOException{
		GroupBuilder groupBuild = null;
		FileReader inFile = null;
		
		try {
			inFile = new FileReader(path);
			groupBuild = new Gson().fromJson(inFile, GroupBuilder.class);
		}
		finally {
			if(inFile != null)
				inFile.close();
		}
		
		return groupBuild.build();
	}
	
	public static Group fromXml(String path) throws JAXBException, IOException {
		JAXBContext c = null;
		Unmarshaller unmar = null;
		GroupBuilder groupBuild = null;
		FileReader inFile = null;
		
        try {
        	inFile = new FileReader(path);
            c = JAXBContext.newInstance(GroupBuilder.class);
            unmar = c.createUnmarshaller();
            groupBuild = (GroupBuilder) unmar.unmarshal(inFile);
        }
        finally {
        	if(inFile != null)
        		inFile.close();
        }
            
        return groupBuild.build();
}
	
	public static Group fromTxt(String path) throws IOException, IllegalArgumentException {
		Group group = null;
		GroupBuilder gOb = null;
		FileReader inFile = null;
		StudentBuilder ob = null;
		Scanner input = null;
		List<Student> students = null;
		List<String> subjects = null;
		List<Integer> marks = null;
		String tmp = null;
		String[] sbj = null;
		Integer[] mrk = null;
		int i = 0;
		
		try {
			inFile = new FileReader(path);
			input = new Scanner(inFile);
			gOb = new GroupBuilder(null);
			ob = new StudentBuilder(null);
			subjects = new ArrayList<String>();
			marks = new ArrayList<Integer>();
			students = new ArrayList<Student>();
			input.useDelimiter("\n|(; ?)|:|( ?= ?)|, ");
	
			while(input.hasNext()) {
				tmp = input.next();
				switch(tmp) {
				case "Name":
					//System.out.println("Name =" + input.next().trim());
					ob.setName(input.next().trim());
					break;
				case "Last name":
					//System.out.println("Last name =" + input.next().trim());
					ob.setLastName(input.next().trim());
					break;
				case "Date of birthday":
					ob.setBirthDay(LocalDate.parse(input.next().trim()));
					//System.out.println("Date of birthday =" + input.next().trim());
					break;
				case "Faculty":
					//System.out.println("Faculty =" + input.next().trim());
					ob.setFaculty(input.next().trim());
					break;
				case "Missings":
					//System.out.println("Missing =" + input.next());
					ob.setMissings(Integer.parseInt(input.next().trim()));
					break;
				case "Leader":
					//System.out.println("leader =" + input.next());
					ob.setLeader(Boolean.parseBoolean(input.next().trim()));
					break;
				case "Education success":
					tmp = input.next().trim();
					while(tmp.toCharArray().length != 0) {
						//System.out.println("Subject" + i + " =" + tmp);
						//System.out.println("Mark = " + input.next());
						subjects.add(tmp);
						marks.add(Integer.parseInt(input.next().trim()));
						tmp = input.next().trim();
					}
					sbj = new String[subjects.size()];
					mrk = new Integer[marks.size()];
					ob.setEdu(subjects.toArray(sbj), marks.toArray(mrk));
					subjects.clear();
					marks.clear();
					
					break;
				case "Phone number":
					//System.out.println("Phone number =" + input.next().trim());
					ob.setPhoneNumber(input.next().trim());
					break;
				case "E-mail":
					//System.out.println("E-mail =" + input.next().trim());
					ob.setEmail(input.next().trim());
					students.add(ob.build());
					i ++;
					ob = new StudentBuilder(null);
					break;
				case "Group name":
					gOb.setGroupName(input.next().trim());
					//System.out.println("Group name =" + input.next());
					break;
				case "Group number":
					//System.out.println("Group number =" + input.next());
					gOb.setGroupNumber(Integer.parseInt(input.next().trim()));
					break;
				case "curator":
					//System.out.println("curator =" + input.next().trim());
					gOb.setCuratorName(input.next().trim());
					break;					
				}
			}
			gOb.setStudents(students);
			group = gOb.build();
			System.out.println("INFO: Has created group " + group.getGroupName() + " with " + i + " students.");
		}
		finally {
			if(inFile != null)
				inFile.close();
			if(input != null)
				input.close();
		}
		
		return group;
	}
	
	public static Integer[] getMarks(Student stud) {
		return stud.getEdu().values().stream().toArray(size -> new Integer[size]);
	}
	
	public static String[] getSubjects(Student student) {
		
		return student.getEdu().keySet().stream().toArray(size -> new String[size]);
	}
	
	public int amountStudents() {
		return (int) getStudents().stream().count();
	}
	
	public void streamSortByLastName() {
		List<Student> stud = getStudents().stream().sorted((s1, s2) -> s1.getLastName()
										.compareTo(s2.getLastName())).collect(Collectors.toList());
		setStudents(stud);
		
		return;
	}
	
	public void streamSortByEdu() {
		Stream<Student> studStream = getStudents().stream();
		List<Student> stud = studStream.sorted((s1, s2) -> Double.compare(s1.getEdu().values().stream().mapToInt(s -> s.intValue()).average().orElse(0),
													 						s2.getEdu().values().stream().mapToInt(s -> s.intValue()).average().orElse(0)))
				 															.collect(Collectors.toList());
		 Collections.reverse(stud);
		 setStudents(stud);
		 
		 return;
	}
	
	public void streamSortByN() {
		List<Student> stud = getStudents().stream().sorted( (s1, s2) -> Integer.compare(s1.getMissings(), s2.getMissings()) ).collect(Collectors.toList());
	
		setStudents(stud);
		return;
	}
	
	public Student streamMaxN() {
		return getStudents().stream().max((s1, s2) -> Integer.compare(s1.getMissings(), s2.getMissings())).get();
	}
	
	@Override
	public String toString() {
		String res = "";
		
		for(Student i:students)
			res += i.toString();
		return res + "\r\nGroup name: " + groupName + "; Group number: " + groupNumber
				+ "; Contract students = " + this.getpayQuantity() + "; Der. students = " + this.getshipQuantity() + "; curator: " + curatorName + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curatorName == null) ? 0 : curatorName.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + groupNumber;
		result = prime * result + payQuantity;
		result = prime * result + shipQuantity;
		result = prime * result + ((students == null) ? 0 : students.hashCode());
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
		Group other = (Group) obj;
		if (curatorName == null) {
			if (other.curatorName != null)
				return false;
		} else if (!curatorName.equals(other.curatorName))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (groupNumber != other.groupNumber)
			return false;
		if (payQuantity != other.payQuantity)
			return false;
		if (shipQuantity != other.shipQuantity)
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		return true;
	}
}


