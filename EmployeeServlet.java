package crud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JDBC J = new JDBC();
		try {
			JSONObject obj = new JSONObject();
			ArrayList<Employee> ae= J.connection();
			Employee e=null;
			String ss=request.getParameter("id");
			if(ss.equals("first")) {
				e=ae.get(0);
				obj.put("name", e.getName());
				obj.put("age", e.getAge());
				obj.put("sal", e.getSal());
				obj.put("job", e.getJob());
				obj.put("dept",e.getDept());
				response.getWriter().write(obj.toString());
			}
			if(ss.equals("last")) {
				e=ae.get(ae.size()-1);
				obj.put("name", e.getName());
				obj.put("age", e.getAge());
				obj.put("sal", e.getSal());
				obj.put("job", e.getJob());
				obj.put("dept",e.getDept());
				obj.put("size", ae.size()-1);
				response.getWriter().write(obj.toString());
			}
			if(ss.equals("prev")) {
				
				int pos = Integer.parseInt(request.getParameter("pos"));
				e=ae.get(pos);
				obj.put("name", e.getName());
				obj.put("age", e.getAge());
				obj.put("sal", e.getSal());
				obj.put("job", e.getJob());
				obj.put("dept", e.getDept());
				response.getWriter().write(obj.toString());
			}
			if(ss.equals("next")) {
				int pos = Integer.parseInt(request.getParameter("pos"));
				if(pos>ae.size()-1) {
					pos=ae.size()-1;
				}
				e=ae.get(pos);
				obj.put("name", e.getName());
				obj.put("age", e.getAge());
				obj.put("sal", e.getSal());
				obj.put("job", e.getJob());
				obj.put("dept", e.getDept());
				response.getWriter().write(obj.toString());
			}
			if(ss.equals("add")) {
				String name=request.getParameter("name");
				String age=request.getParameter("age");
				String sal=request.getParameter("sal");
				String job=request.getParameter("job");
				String dept=request.getParameter("dept");
				JDBC jj = new JDBC();
				jj.addcon(name,age,sal,job,dept);
			}
			if(ss.equals("edit")) {
				String name=request.getParameter("name");
				String age=request.getParameter("age");
				String sal=request.getParameter("sal");
				String job=request.getParameter("job");
				String dept=request.getParameter("dept");
				int index = Integer.parseInt(request.getParameter("epos"));
				JDBC jj = new JDBC();
				jj.editcon(name,age,sal,job,dept,index);
			}
			if(ss.equals("delete")) {
				int index = Integer.parseInt(request.getParameter("dpos"));
				JDBC jj = new JDBC();
				jj.deletecon(index);
			}
		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
		}
	}
}
