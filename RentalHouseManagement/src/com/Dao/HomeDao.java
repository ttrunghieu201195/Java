package com.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.Main.ConnectionString;
import com.Model.LandlordModel;

public class HomeDao {
	public ArrayList<LandlordModel> GetAll() throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		ArrayList<LandlordModel> Host = new ArrayList<LandlordModel>();
		try {			
			conn = connString.ConnectString();
			String sqlMaPhong = "Select  "
						+ "	Code, "
						+ "FullName, "
						+ "IdCardNo,"
						+ "Email, "
						+ "Status "
						+ "from host ";
						
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlMaPhong);
			LandlordModel model = new LandlordModel();
			while (rs.next()) {
				String ma = rs.getString(1);
				model.setCode(ma);
				String ten = rs.getString(2);
				model.setFullName(ten);
				String cmnd = rs.getString(3);
				model.setIdCardNo(cmnd);
				String email = rs.getString(4);
				model.setEmail(email);
				String status = rs.getString(5);
				model.setStatus(status);
				Host.add(model);
				
			}
			
			
		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return Host;
	}

}
