package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBC {
	public ArrayList<Employee> connection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres","postgres","1234");
		Statement smt = con.createStatement();
		ArrayList<Employee> ae = new ArrayList<Employee>();
		ResultSet rs = smt.executeQuery("select * from employee");
		while(rs.next()) {
			ae.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
		}
		return ae;
	}
	public void addcon(String name,String age,String sal,String job,String dept) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres","postgres","1234");
		PreparedStatement ps = con.prepareStatement("insert into employee values(?,?,?,?,?)");
		ps.setString(1, name);
		ps.setString(2, age);
		ps.setString(3, sal);
		ps.setString(4, job);
		ps.setString(5, dept);
		ps.executeUpdate();
		connection();
	}
	public void editcon(String name,String age,String sal,String job,String dept,int index) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres","postgres","1234");
		PreparedStatement ps = con.prepareStatement("update employee set name=?,age=?,salary=?,job=?,department=? where eid=?");
		ps.setString(1, name);
		ps.setString(2, age);
		ps.setString(3, sal);
		ps.setString(4, job);
		ps.setString(5, dept);
		ps.setInt(6, index);
		System.out.println(index+"hello");
		ps.executeUpdate();
		connection();
	}
	public void deletecon(int index) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres","postgres","1234");
		PreparedStatement ps = con.prepareStatement("delete from employee where eid=?");
		ps.setInt(1, index);
		ps.executeUpdate();
		connection();
	}
}
