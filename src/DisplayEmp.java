

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/DisplayEmp")
public class DisplayEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
   public void init() {
	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Priya@123");
		
		/*Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from employee1");*/
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
   }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from employee1");
			PrintWriter out = response.getWriter();
			
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>");
			out.println("id");
			out.print("</th>");
			out.print("<th>");
			out.println("name");
			out.print("</th>");
			out.print("<th>");
			out.println("age");
			out.print("</th>");
			out.print("<th>");
			out.println("salary");
			out.print("</th>");
			out.print("<th>");
			out.println("designation");
			out.print("</th>");
			out.print("</tr>");
			out.print("</table>");
			
			while (resultSet.next()) {
				out.println("<tr>");
				out.println("<td>");
				out.print(resultSet.getInt(1));
				out.println("</td>");
				out.println("<td>");
				out.print(resultSet.getString(2));
				out.println("</td>");
				out.println("<td>");
				out.print(resultSet.getInt(3));
				out.println("</td>");
				out.println("<td>");
				out.print(resultSet.getInt(4));
				out.println("</td>");
				out.println("<td>");
				out.print(resultSet.getString(5));
				out.println("</td>");
				out.println("</tr>");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
