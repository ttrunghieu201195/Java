package com.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Main.ConnectionString;
import com.Model.LandlordModel;

public class LoginDao {

	public boolean Login (String userName, String passWord) throws SQLException
	{
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		int countUser = 0;
		try {	
			conn = connString.ConnectString();
			String sqlLogin = "Select count(*) from user where username = '" + userName + "' AND password = '" + passWord+  "'" ;
						
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlLogin);
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				 countUser = rs.getInt(1); 
		      }
		      if (countUser > 0 )
		      {
		    	  return true;
		      }
		      else return false;
		}
		catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
	}
}

