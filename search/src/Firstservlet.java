

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Firstservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn;
    public Firstservlet() {
        super();
      
    }
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn=DriverManager.getConnection("jdbc:mysql://localhost/student","kausar","admin123");
			} catch (SQLException e) {
				System.out.println(e);
				
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter w = response.getWriter();
			response.setContentType("text/html");
		    w.println("<html>");
		    w.println("<head><title>Details</title> <style> table,td,tr{border-collapse:collapse;}</style></head>");
		    w.println("<body>");
		    w.println("<center><h2>Search Details</h2>");
		    
		    int s=Integer.parseInt(request.getParameter("search"));
			PreparedStatement ps=conn.prepareStatement("select * from regform where sno=?");
			ps.setInt(1,s);
			ResultSet set=ps.executeQuery();
			w.println("<table border=1 width=30% height=50% collapse>");
			while(set.next()) {
				w.println("<tr><td>SNO:-</td><td>"+set.getInt(1)+"</td></tr>");
				w.println("<tr><td>First name:-</td><td>"+set.getString(2)+"</td></tr>");
				w.println("<tr><td>Last name:-</td><td>"+set.getString(3)+"</td></tr>");
				w.println("<tr><td>DOB:-</td><td>"+set.getDate(4)+"</td></tr>");
				w.println("<tr><td>Email:-</td><td>"+set.getString(5)+"</td></tr>");
				w.println("<tr><td>Mobile no:-</td><td>"+set.getBigDecimal(6)+"</td></tr>");
				w.println("<tr><td>Gender:-</td><td>"+set.getString(7)+"</td></tr>");
				w.println("<tr><td>City:-</td><td>"+set.getString(8)+"</td></tr>");
				w.println("<tr><td>Pincode:-</td><td>"+set.getInt(9)+"</td></tr>");
				w.println("<tr><td>State:-</td><td>"+set.getString(10)+"</td></tr>");
				w.println("</table>");
				w.println("<br><br><input type='submit' value='PRINT' onclick=b()>");
				w.println("<script>function b(){window.print();}</script>");
				w.println("</center>");
			    w.println("</body>");
			    w.println("</html>");
			    w.close();
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
	}
}


