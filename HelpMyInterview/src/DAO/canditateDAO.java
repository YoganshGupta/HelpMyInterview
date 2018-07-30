package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import BO.canditate;
import BO.canditatelogin;


public class canditateDAO {
public static DataSource dataSource;
	
				
	
	public static Connection getConnection()
	{
		
		Connection con=null;
		try
		{
			con=dataSource.getConnection();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} 
		return con;
	}
	
	
	
	public static int addCanditate(canditate can)
	{
		//write code to perform insert operation
		Connection con=null;
		
		int result = 0;
			
			try {
				con=getConnection();
				String sql="INSERT INTO reghmi VALUES(?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,can.getFname());
				ps.setString(2,can.getLname());
				ps.setString(3, can.getEmail());
				ps.setLong(4,can.getContact_no());
				ps.setString(5,can.getUname());
				ps.setString(6,can.getPassword());
				ps.setString(7,can.getRpassword());
				result=ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				closeConnection(con);
			}
			
			
			return result;
		
		
	}

	static void closeConnection(Connection con)
	{
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

/*	static List<canditate> getAllCandidtates()
	{
		Connection con=null;
		String sql="SELECT fname,lname,email,contact_no,uname,password,rpassword from reghmi";
		List<canditate> canList=new ArrayList<>();
		
		
			con=getConnection();
			PreparedStatement ps=null;
			try {
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					canList.add(new canditate(rs.getString(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	return canList;
	}	
	

static canditate getCanditate(String uname){
		
		Connection con=null;
		canditate can=null;
		PreparedStatement ps=null;
		
		
		try {
			
			con = getConnection();
			String sql = "SELECT fname,lname,email,contact_no,uname,password,rpassword from reghmi where uname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,uname);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String email = rs.getString("email");
				Long contact = rs.getLong("contact_no");
				String un = rs.getString("uname");
				String password = rs.getString("password");
				String rpassword = rs.getString("rpassword");
				can = new canditate(fname,lname,email,contact,un,password,rpassword);
		}
			rs.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}finally {
			closeConnection(con);
		}
		
		return can;
	}
	
	
public static int deleteCanditate(String uname)
{
	 Connection con=null;
		
		PreparedStatement ps=null;
	
		int result = 0;
		try {
			
			con = getConnection();
			String sql = "DELETE from reghmi where uname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,uname);
		    result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return result;
}

public static int updateCandiate(canditate can)
{
	 Connection con=null;
		
		PreparedStatement ps=null;
	
		int result = 0;
		try {
			
			con = getConnection();
			String sql = "UPDATE reghmi set fname=?,lname=?,email=?,contact_no=?,password=?,rpassword=? where uname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,can.getFname());
			ps.setString(2,can.getLname());
			ps.setString(3,can.getEmail());
			ps.setLong(4, can.getContact_no());
			ps.setString(5,can.getPassword());
			ps.setString(6,can.getRpassword());
			ps.setString(7,can.getUname());
			
		    result = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con);
		}
		return result;
}*/

public static int fetchCanditate(canditatelogin can){
	
	Connection con=null;
	PreparedStatement ps=null;
	int result=0;
	
	try {
		
		con = getConnection();
		String sql = "SELECT uname,password from reghmi where uname=?";
		ps = con.prepareStatement(sql);
		ps.setString(1,can.getUname());
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			String password = rs.getString("password");
			String pass=can.getPassword();
			if(password.equals(pass))
			{
				result=result+1;
			}
	}
		rs.close();
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	}finally {
		closeConnection(con);
	}
	
	return result;
}
}
	
