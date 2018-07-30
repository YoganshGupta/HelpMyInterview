package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.google.gson.Gson;

import BO.canditatelogin;
import DAO.canditateDAO;

/**
 * Servlet implementation class LoginCanditateServlet
 */
public class LoginCanditateServlet extends HttpServlet {
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
		
		
		Gson gson = new Gson();
		canditatelogin can =  gson.fromJson(jsonString, canditatelogin.class);
		
		int i = canditateDAO.fetchCanditate(can);
			
		if(i>0) {
			System.out.println("login succesful");
			String resp = "Welcome "+can.getUname();
			res.getWriter().write(gson.toJson(resp));
			
		}else {
			res.getWriter().write("-1");
		}
	}
	}


