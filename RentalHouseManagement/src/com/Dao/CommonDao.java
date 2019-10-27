package com.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.Main.ConnectionString;
import com.Model.CommonModel;

public class CommonDao {
	public ArrayList<CommonModel> GetSys(String type) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		ArrayList<CommonModel> result = new ArrayList<CommonModel>();
		try {			
			conn = connString.ConnectString();
			String sqlRoomFree = "SELECT `Code`, `Name` FROM sys_common WHERE `TYPE`= '"+ type + "'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlRoomFree);
			while (rs.next()) {
				CommonModel model = new CommonModel();
				String code = rs.getString(1);
				model.setCode(code);
				String name = rs.getString(2);
				model.setName(name);
				result.add(model);
			}
			
			
		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return result;
	}

}
