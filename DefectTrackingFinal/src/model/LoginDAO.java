package model;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;
import model.conn.DBConnection;

public class LoginDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	/*********************************Validation for Login*********************************/
	public String checkLogin(Login l) throws SQLException
		{
			
			String role = "false";
			
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement("select ROLE_TAGGED from LOGIN_TBL where USER_ID=? AND PASSWORD=?");
			pstmt.setString(1, l.getName());
			pstmt.setString(2, l.getPwd());
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				role=rs.getString(1);
			}
				
							
				return role;
		}
	
}

