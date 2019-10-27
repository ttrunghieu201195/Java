package com.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Main.ConnectionString;
import com.Model.RoomPriceModel;

public class RoomPriceDao {
	
	public boolean Add(RoomPriceModel model) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {			
			conn = connString.ConnectString();
			String sql = "INSERT INTO `room_price` (" +
			"  `RoomCode`," + 
			"  `BasicPrice`," + 
			"  `ElectricPrice`," + 
			"  `WaterPrice`," + 
			"  `InternetPrice`," +
			"  `Month`," +
			"  `Year`) " +
			"VALUES("+ 
				"'" + model.getRoomCode() +"'," + 
				"'" + model.getBasicPrice() +"'," +  
				"'" + model.getElectricPrice() +"'," + 
				"'" + model.getWaterPrice() +"'," + 
				"'" + model.getInternetPrice() +"'," + 
				"'" + model.getMonth() +"'," + 
				"'" + model.getYear() +"') ";

			Statement statement = conn.createStatement();
			int rersult = statement.executeUpdate(sql);
			
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}
	
	public boolean Edit(RoomPriceModel model) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {			
			conn = connString.ConnectString();
			String sql = "UPDATE " + 
					" `room_price` " + 
					"SET" + 
					"  `BasicPrice` = '" + model.getBasicPrice() + "',"+
					"  `ElectricPrice` = '" + model.getElectricPrice() + "'," +
					"  `WaterPrice` =  '" + model.getWaterPrice() + "',"+
					"  `InternetPrice` = '" + model.getInternetPrice() + "',"+
					"WHERE `Id` = '"+model.getId()+"' ";

			Statement statement = conn.createStatement();
			int rersult = statement.executeUpdate(sql);
			
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}
	
	public List<RoomPriceModel> GetAll() throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		List<RoomPriceModel> Host = new ArrayList<RoomPriceModel>();
		try {			
			conn = connString.ConnectString();
			String sqlMaPhong = "Select  "
						+ "	Id, "
						+ "RoomCode, "
						+ "BasicPrice,"
						+ "ElectricPrice, "
						+ "WaterPrice, "
						+ "InternetPrice,"
						+ "`Month`,"
						+ "`Year` "
						+ "from room_price ";
						
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlMaPhong);
			while (rs.next()) {
				RoomPriceModel model = new RoomPriceModel();
				int id = rs.getInt(0);
				model.setId(id);
				String ma = rs.getString(1);
				model.setRoomCode(ma);
				float basicPrice = rs.getFloat(2);
				model.setBasicPrice(basicPrice);
				float electricPrice = rs.getFloat(3);
				model.setElectricPrice(electricPrice);
				float waterPrice = rs.getFloat(4);
				model.setWaterPrice(waterPrice);
				float internetPrice = rs.getFloat(5);
				model.setInternetPrice(internetPrice);
				int month = rs.getInt(6);
				model.setMonth(month);
				int year = rs.getInt(7);
				model.setYear(year);
				Host.add(model);
				
			}
			
			
		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return Host;
	}
	
	public RoomPriceModel GetRoomPriceByCode(String roomCode) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		RoomPriceModel result = new RoomPriceModel();
		try {			
			conn = connString.ConnectString();
			String sqlMaPhong = "Select  "
					+ "	Id, "
					+ "RoomCode, "
					+ "BasicPrice,"
					+ "ElectricPrice, "
					+ "WaterPrice, "
					+ "InternetPrice,"
					+ "`Month`,"
					+ "`Year` "
					+ "from room_price where RoomCode = '"+roomCode+"' ";
						
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlMaPhong);
			if(rs.first()) {
				int id = rs.getInt(0);
				result.setId(id);
				String ma = rs.getString(1);
				result.setRoomCode(ma);
				float basicPrice = rs.getFloat(2);
				result.setBasicPrice(basicPrice);
				float electricPrice = rs.getFloat(3);
				result.setElectricPrice(electricPrice);
				float waterPrice = rs.getFloat(4);
				result.setWaterPrice(waterPrice);
				float internetPrice = rs.getFloat(5);
				result.setInternetPrice(internetPrice);
				int month = rs.getInt(6);
				result.setMonth(month);
				int year = rs.getInt(7);
				result.setYear(year);
			}
		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return result;
	}

}
