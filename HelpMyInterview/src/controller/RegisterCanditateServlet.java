package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import BO.canditate;
import DAO.canditateDAO;



/**
 * Servlet implementation class RegisterCanditateServlet
 */
public class RegisterCanditateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="oracledb")
public DataSource dataSource;       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	canditateDAO.dataSource=this.dataSource;
	
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
canditateDAO.dataSource = this.dataSource;
		
		InputStreamReader reader = new InputStreamReader(req.getInputStream());
		BufferedReader br = new BufferedReader(reader);
		String jsonString = br.readLine();
		System.out.println(jsonString);
		Gson gson = new Gson();
		canditate can =  gson.fromJson(jsonString, canditate.class);
		
		int i = canditateDAO.addCanditate(can);
		if(i>0) {
			System.out.println("added");
			res.getWriter().write(can.getUname());
		}else {
			res.getWriter().write("-1");
		}
	}

}
