package models;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
@XmlType(propOrder = {"groupName", "groupNumber", "curatorName", "DQuantity", "KQuantity", "students"})
public class Group {
	private String curatorName, groupName;
	private int groupNumber, dQuantity, kQuantity;
	ArrayList<Student> students = new ArrayList<Student>();
	
	public Group() {
		curatorName = null;
		groupName = null;
		groupNumber = 100;
		students = null;
		dQuantity = 0;
		kQuantity = 0;
	}
	
	public Group(ArrayList<Student> s, String cName, String gName, int gNumber) {
		curatorName = cName;
		groupName = gName;
		groupNumber = gNumber;
		students = s;
		dQuantity = 0;
		kQuantity = 0;
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
	
	@XmlElement(name = "dQuantity")
	public int getDQuantity() {
		return this.dQuantity;
	}

	public void setDQuantity(int dQuantity) {
		this.dQuantity = dQuantity;
	}
	
	@XmlElement(name = "kQuantity")
	public int getKQuantity() {
		return this.kQuantity;
	}

	public void setKQuantity(int kQuantity) {
		this.kQuantity = kQuantity;
	}
	
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	@XmlElement(name = "Students")
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	
	public void countDandK() {
		int d = 0, k = 0;
		for(int i = 0; i < students.size(); ++ i) {
			if(this.students.get(i).isD())
				d ++;
			else
				k ++;
		}
		setDQuantity(d);
		setKQuantity(k);
				
		return;
	}
	
	public ArrayList<Student> studentShip (float persent) {
		ArrayList<Student> res = new ArrayList<Student>();
		int m = 0;
		
		m = (int) (getDQuantity() * persent);
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
		int half = students.get(low + (high - low) / 2).getN();
		
		while(i <= j) {
			while(students.get(i).getN() < half)
				i ++;
			while(students.get(j).getN() > half)
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
	
	public String maxN() {
		int max = 0;
		Student person = new Student();
		
		for(Student i:students)
			if(max < i.getN()) {
				person = i;
				max = i.getN();
			}
		
		return "Has the most N:\n" + person.toString() + '\n';
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
	
	public Map<String, Float> averageBal () {
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
		ArrayList<Student> students = null;
		ArrayList<String> subjects = null;
		ArrayList<Integer> baly = null;
		String tmp = null;
		int i = 0;
		
		try {
			inFile = new FileReader(path);
			input = new Scanner(inFile);
			gOb = new GroupBuilder(null);
			ob = new StudentBuilder(null);
			subjects = new ArrayList<String>();
			baly = new ArrayList<Integer>();
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
					ob.setBirthDay(input.next().trim());
					//System.out.println("Date of birthday =" + input.next().trim());
					break;
				case "Faculty":
					//System.out.println("Faculty =" + input.next().trim());
					ob.setFaculcy(input.next().trim());
					break;
				case "Propusky":
					//System.out.println("Propusky =" + input.next());
					ob.setN(Integer.parseInt(input.next().trim()));
					break;
				case "Starosta":
					//System.out.println("Starosta =" + input.next());
					ob.setStarosta(Boolean.parseBoolean(input.next()));
					break;
				case "Education succes":
					tmp = input.next();
					while(tmp.toCharArray().length != 1 && tmp.toCharArray()[0] != (char)13) {
						//System.out.println("Subject" + i + " =" + tmp);
						//System.out.println("Bal = " + input.next());
						subjects.add(tmp.trim());
						baly.add(Integer.parseInt(input.next().trim()));
						tmp = input.next();
					}
					String[] sbj = new String[subjects.size()];
					Integer[] b = new Integer[baly.size()];
					ob.setEdu(subjects.toArray(sbj), baly.toArray(b));
					break;
				case "Phone number":
					//System.out.println("Phone number =" + input.next().trim());
					ob.setPhoneNumber(input.next().trim());
					break;
				case "Email":
					//System.out.println("E-mail =" + input.next().trim());
					ob.setEmail(input.next().trim());
					students.add(ob.build());
					i ++;
					break;
				case "Average Bal":
					ob.setAveregeB(Float.parseFloat(input.next().trim()));
					//System.out.println("Average Bal =" + input.next().trim());
					break;
				case "Group name":
					gOb.setGroupName(input.next().trim());
					//System.out.println("Group name =" + input.next());
					break;
				case "Group number":
					//System.out.println("Group number =" + input.next());
					gOb.setGroupNumber(Integer.parseInt(input.next().trim()));
					break;
				case "Kurator":
					//System.out.println("Kurator =" + input.next());
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
	
	private static String[] deleteSbj(String[] sbj, int i) {
 		for(int j = i; j < sbj.length - 1; ++ j)
			sbj[j] = sbj[j + 1];
		
		return sbj;
	}
	
	public static Group fromSql(String url, String user, String password, int groupNum) throws SQLException, ClassNotFoundException {
		BDConnect obj = null;
		String request = null;
		ResultSet res = null;
		ResultSet res1 = null;
		StudentBuilder studBuild = new StudentBuilder(null);
		ArrayList<Student> students = new ArrayList<Student>();
		int counter = 0;
		int tmp = 0;
		GroupBuilder groupBuild = new GroupBuilder(null);
		Group group = new Group();
		String[] sbj = null;
		Integer[] baly = null;
		
		try {
			obj = new BDConnect(url, user, password);
			request = "select groupNumber, groupName, curatorName from studentgroup"
					+ " where groupNumber=" + groupNum + ';';
			res = obj.startExecute(request);
			res.next();
			groupBuild.setGroupName(res.getString("groupName"));
			groupBuild.setGroupNumber(groupNum);
			groupBuild.setCuratorName(res.getString("curatorName"));
			obj.close();
			request = "show columns from marks;";
			res = obj.startExecute(request);
			while(res.next())
				counter ++;
			res.first();
			sbj = new String[counter - 1];
			baly = new Integer[counter - 1];
			for(int i = 0; i < counter - 1; ++ i) {
				res.next();
				sbj[i] = res.getString("Field");
			}
			obj.close();
			request = "select name, lastName, birthDay, faculty, isStarosta, missings, ship, email, "
					+ "phoneNumber, markId from students where groupId=" + groupNum + ';';
			res = obj.startExecute(request);
			while(res.next()) {
				studBuild.setName(res.getString("name"));
				studBuild.setLastName(res.getString("lastName"));
				studBuild.setBirthDay(res.getString("birthDay"));
				studBuild.setFaculcy(res.getString("faculty"));
				studBuild.setStarosta(res.getBoolean("isStarosta"));
				studBuild.setN(res.getInt("missings"));
				studBuild.setD(res.getBoolean("ship"));
				studBuild.setEmail(res.getString("email"));
				studBuild.setPhoneNumber(res.getString("phoneNumber"));
				request = "select * from marks where markId=" + res.getInt("markId");
				res1 = obj.startExecute(request);
				res1.next();
				for(int i = 0; i < counter - 1; ++ i) {
					tmp = res1.getInt(sbj[i]);
					if(res1.wasNull()) {
						deleteSbj(sbj, i);
						System.out.println("Subject deleted");
					}
					else
						baly[i] = tmp;
				}
				System.out.println("Subjects");
				
				studBuild.setEdu(sbj, baly);
				students.add(studBuild.build());
			}

			groupBuild.setStudents(students);
			group = groupBuild.build();

			return group;
		}
		finally {
			if(obj != null)
				obj.close();
		}
	}
	
	private boolean createStudentsTable(String url, String user, String password) throws SQLException, ClassNotFoundException {
		BDConnect ob = null;
		String request = null;
		try {
			ob = new BDConnect(url, user, password);
			request = "create table students (studentId int primary key, name varchar(20), lastName varchar(20), "
					+ "birthDay varchar(15), faculty varchar(20), isStarosta tinyint(1), missings int, "
					+ "ship tinyint (1), email varchar(20), phoneNumber varchar(13), groupId int, markId int)";
			ob.startUpdate(request);
			System.out.println("Student`s group table succssefully created");
			return true;
		}
		finally {
		}
	}
	
	private boolean createStudentGroupTable(String url, String user, String password) throws SQLException, ClassNotFoundException {
		BDConnect ob = null;
		String request = null;
		try {
			ob = new BDConnect(url, user, password);
			request = "create table studentGroup(groupNumber int primary key, groupName varchar(20), curatorName varchar(30))";
			ob.startUpdate(request);
			System.out.println("Students table succssefully created");
			return true;
		}
		finally {
		}
	}
	
	private boolean createMarksTable(String url, String user, String password) throws SQLException, ClassNotFoundException {
		BDConnect ob = null;
		String request = null;
		String[] sbj = null;
		Integer[] marks = null;
		Set<String> tmp = null;
		
		try {
			ob = new BDConnect(url, user, password);
			if(students == null)
				return false;
			tmp = students.get(0).getEdu().keySet();
			sbj = new String[tmp.size()];
			marks = new Integer[tmp.size()];
			sbj = tmp.toArray(sbj);
			marks = students.get(0).getEdu().values().toArray(marks);
			
			request = "create table marks(markId int primary key, ";
			for(int i = 0; i < tmp.size() - 1; ++ i) {
				request += "`" + sbj[i] + "`" + " int, ";
				
			}
			request += "`" + sbj[tmp.size() - 1] + "`" + " int);";
			
			ob.startUpdate(request);
			System.out.println("Marks table succssefully created");
			return true;
		}
		finally {
		}
	}
	
	public boolean toSql(String url, String user, String password)
				throws SQLException, ClassNotFoundException {
		BDConnect ob = null;
		Student student = null;
		String request = null;
		
		try {
			ob = new BDConnect(url, user, password);
			
			createStudentsTable(url, user, password);
			createStudentGroupTable(url, user, password);
			createMarksTable(url, user, password);
			
			for(int i = 0; i < students.size(); ++ i) {
				student = students.get(i);
				addStudentSql(url, user, password, student);
			}
			/* adding to studentGroup */
			request = "insert into studentGroup values(" + getGroupNumber()
					+ ", '" + getGroupName() + "', '" + getCuratorName() + "');";
			ob.startUpdate(request);
		}
		finally {
		}
		
		return true;
	}
	
	public boolean addStudentSql(String url, String user, String password, Student student)
			throws SQLException, ClassNotFoundException {
		BDConnect ob = null;
		ResultSet res = null;
		int sbjCount = 0;
		int n = 0;
		Integer[] marks = null;
		String request = null;
		
		try {
			ob = new BDConnect(url, user, password);
			res = ob.startExecute("show columns from marks;");
			while(res.next())
				sbjCount ++;
			request = "select * from students;";
			res = ob.startExecute(request);
			if(res.last())
				n = res.getInt(1) + 1;
			else
				n = 1;
			/* adding to students table */
			request = "insert into students values(" + n + ", "
					+ "'" + student.getName() + "', "
					+ "'" + student.getLastName() + "', "
					+ "'" + student.getBirthDay() + "', "
					+ "'" + student.getFaculty() + "', "
					+ student.isStarosta() + ", "
					+ student.getN() + ", "
					+ student.isD() + ", "
					+ "'" + student.getEmail() + "', "
					+ "'" + student.getPhoneNumber() + "', "
					+ getGroupNumber() + ", " + n + ");";
			ob.startUpdate(request);
			/* adding to marks table */
			marks = new Integer[sbjCount - 1];
			marks = student.getEdu().values().toArray(marks);
			request = "insert into marks values(" + n + ", ";
			for(int j = 0; j < marks.length - 1; ++ j)
				request += marks[j] + ", ";
			request += marks[marks.length - 1] + ");";
			ob.startUpdate(request);
			
			return true;
		}
		finally {
			if(ob != null)
				ob.close();
		}
	}
	
	private void fieldsMoveUp(int start, int end, BDConnect ob) throws SQLException, ClassNotFoundException {
		String request = null;
		
		try {
			while(start < end) {
				request = "update students set studentId=" + start + " where studentId=" + (start + 1) + ";";
				ob.startUpdate(request);
				request = "update marks set markId=" + start + " where markId=" + (start + 1) + ";";
				ob.startUpdate(request);
				start ++;
			}
		}
		finally {
		}
	}
	
	public boolean deleteStudentSql(String url, String user, String password, int studentId)
			throws SQLException, ClassNotFoundException {
		BDConnect ob = null;
		String request = null;
		ResultSet res = null;
		int n = 0;
		
		try {
			ob = new BDConnect(url, user, password);
			request = "select * from students;";
			res = ob.startExecute(request);
			if(res.last()) {
				n = res.getInt(1);
			}
			else {
				throw new IllegalArgumentException("Table is empty!!!");
			}
			if(n < studentId || studentId <= 0) {
				throw new IllegalArgumentException("Enter correct student ID!!!");
			}
			request = "delete from students where studentId=" + studentId + ';';
			ob.startUpdate(request);
			request = "delete from marks where markId=" + studentId + ';';
			ob.startUpdate(request);
			if(n != studentId)
				fieldsMoveUp(studentId, n, ob);
		}
		finally {
			if(ob != null)
				ob.close();
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		String res = "";
		
		for(Student i:students)
			res += i.toString();
		return res + "\r\nGroup name: " + groupName + "; Group number: " + groupNumber
				+ "; Contract students = " + this.getKQuantity() + "; Der. students = " + this.getDQuantity() + "; Kurator: " + curatorName + '.';
	}
}


