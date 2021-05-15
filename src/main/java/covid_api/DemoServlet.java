package covid_api;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import covid_api.District;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/demo")

public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int dist_id = Integer.parseInt(request.getParameter("district_id"));
	
		String dist_name = fetchDistrictById(dist_id);
		Gson gson = new Gson();
		District district = new District(dist_name);
        String JsonString = gson.toJson(district);
		
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(JsonString);
        out.flush();   
	}
	
	private static String fetchDistrictById(int id) {
		Connection c;
        java.sql.PreparedStatement smt;String dist_name=null;
        
    try{
        // Database Connection :
        Class.forName("org.postgresql.Driver");
        System.out.println("Opened DB");
      
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/shubham","shubham","root");

        Statement stmt = con.createStatement();           
        ResultSet rs;
        rs = stmt.executeQuery("SELECT dist FROM district WHERE dist_id = "+ Integer.toString(id));
        rs.next();
            dist_name = rs.getString("dist");
            //System.out.println(dist_name);
           } catch (Exception ex) {
        System.out.println(ex);
    }
	return dist_name;
	
	}

}
