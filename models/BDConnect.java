package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDConnect {
	private Connection con;
	private Statement stmt;
	private ResultSet result;
	private String url, user, password;
	
	public BDConnect() {
		setCon(null);
	    setStmt(null);
	    setResult(null);
	    url = null;
	    user = null;
	    password = null;
	}
	
	public BDConnect(String url, String user, String password) {
		setCon(null);
	    setStmt(null);
	    setResult(null);
	    this.url = url;
	    this.user = user;
	    this.password = password;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public ResultSet getResult() {
		return result;
	}

	public void setResult(ResultSet result) {
		this.result = result;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ResultSet startExecute(String request) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
	    	con = DriverManager.getConnection(url, user,  password);
	    	stmt = con.createStatement();
	    	result = stmt.executeQuery(request);
	    }
		finally {
			
		}
		
		return result;
	}
	
	public void startUpdate(String request) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
	    	con = DriverManager.getConnection(url, user,  password);
	    	stmt = con.createStatement();
	    	stmt.executeUpdate(request);
	    }
		finally {
			
		}
		
		return;
	}
	
	public void close() {
		try {
			con.close();
			stmt.close();
			result.close();
			System.out.println("BD connection has closed succefully!");
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
}
