package com.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Main.ConnectionString;
import com.Model.LandlordModel;
import com.Model.RoomModel;

public class RoomDao {

	public boolean Add(RoomModel model) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {			
			conn = connString.ConnectString();
			String sql = "INSERT INTO `room` (" +
			"  `RoomCode`," + 
			"  `RoomName`," + 
			"  `Area`," + 
			"  `Floor`," + 
			"  `Status`," +
			"  `RoomType`," +
			"  `LitmitPerson`) " +
			"VALUES("+ 
				"'" + model.getRoomCode() +"'," + 
				"'" + model.getRoomName() +"'," +  
				"'" + model.getArea() +"'," + 
				"'" + model.getFloor() +"'," + 
				"'" + model.getStatus() +"'," + 
				"'" + model.getRoomType() +"'," + 
				"'" + model.getLimitPerson() +"') ";

			Statement statement = conn.createStatement();
			int rersult = statement.executeUpdate(sql);
			
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}
	
	public boolean Edit(RoomModel model) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {			
			conn = connString.ConnectString();
			String sql = "UPDATE " + 
					" `room` " + 
					"SET" + 
					"  `RoomName` = '" + model.getRoomName() + "',"+
					"  `Area` = '" + model.getArea() + "'," +
					"  `Floor` =  '" + model.getFloor() + "',"+
					"  `Status` = '" + model.getStatus() + "',"+
					"  `RoomType` = '" + model.getRoomType() + "',"+
					"  `LitmitPerson` = '" + model.getLimitPerson() + "'"+
					"WHERE `RoomCode` = '"+model.getRoomCode()+"' ";

			Statement statement = conn.createStatement();
			int rersult = statement.executeUpdate(sql);
			
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}
	
	public List<RoomModel> GetAll(RoomModel modelSearch) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		List<RoomModel> Host = new ArrayList<RoomModel>();
		try {			
			conn = connString.ConnectString();
			String sqlMaPhong = "Select  "
						+ "	RoomCode, "
						+ "RoomName, "
						+ "Area,"
						+ "Floor, "
						+ "Status, "
						+ "RoomType,"
						+ "LitmitPerson "
						+ "from room where 1 = 1 ";
			
			if(modelSearch.getRoomCode() != null && modelSearch.getRoomCode() != "" ) {
				sqlMaPhong += " and RoomCode = '"+modelSearch.getRoomCode()+"' ";
			}
			
			if(modelSearch.getRoomName() != null && modelSearch.getRoomName() != "" ) {
				sqlMaPhong += " and RoomName LIKE '%"+modelSearch.getRoomName()+"%' ";
			}
			
			if(modelSearch.getStatus() != null && modelSearch.getStatus() != "All" ) {
				sqlMaPhong += " and Status = '"+modelSearch.getStatus()+"' ";
			}
			
			
			if(modelSearch.getRoomType() != null && modelSearch.getRoomType() != "All" ) {
				sqlMaPhong += " and RoomType = '"+modelSearch.getRoomType()+"' ";
			}
			
			if(modelSearch.getLimitPerson() != 0 ) {
				sqlMaPhong += " and LitmitPerson = "+modelSearch.getLimitPerson()+" ";
			}
			
						
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlMaPhong);
			while (rs.next()) {
				RoomModel model = new RoomModel();
				String ma = rs.getString(1);
				model.setCode(ma);
				String ten = rs.getString(2);
				model.setRoomName(ten);
				float area = rs.getFloat(3);
				model.setArea(area);
				int floor = rs.getInt(4);
				model.setFloor(floor);
				String status = rs.getString(5);
				model.setStatus(status);
				String roomType = rs.getString(6);
				model.setRoomType(roomType);
				int limitPerson = rs.getInt(7);
				model.setLimitPerson(limitPerson);
				Host.add(model);
				
			}
			
			
		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return Host;
	}
	
	public RoomModel GetRoomByCode(String roomCode) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		RoomModel result = new RoomModel();
		try {			
			conn = connString.ConnectString();
			String sqlMaPhong = "Select  "
						+ "	RoomCode, "
						+ "RoomName, "
						+ "Area,"
						+ "Floor, "
						+ "Status, "
						+ "RoomType,"
						+ "LitmitPerson "
						+ "from room where RoomCode ='"+roomCode+"'";
						
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlMaPhong);
			if(rs.first()) {
				String ma = rs.getString(1);
				result.setCode(ma);
				String ten = rs.getString(2);
				result.setRoomName(ten);
				float area = rs.getFloat(3);
				result.setArea(area);
				int floor = rs.getInt(4);
				result.setFloor(floor);
				String status = rs.getString(5);
				result.setStatus(status);
				String roomType = rs.getString(6);
				result.setRoomType(roomType);
				int limitPerson = rs.getInt(7);
				result.setLimitPerson(limitPerson);
			}
		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return result;
	}
	
	public boolean CheckExistByRoomCode(String roomCode) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		boolean result = false;
		try {			
			conn = connString.ConnectString();
			String sql = "SELECT * FROM RoomCode WHERE `RoomCode` = '"+roomCode+"'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.first()) {
				result = true;
			}
		} catch (Exception ex) {
			return true;
		} finally {
			conn.close();
		}
		return result;
	}
	
	public boolean CheckUsedByRoomCode(String roomCode) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		boolean result = false;
		try {			
			conn = connString.ConnectString();
			String sql = "SELECT * FROM room_price WHERE `RoomCode` = '"+roomCode+"'";
			String sql1 = "SELECT * FROM contract WHERE `RoomCode` = '"+roomCode+"'";
			String sql2 = "SELECT * FROM bill_monthly WHERE `RoomCode` = '"+roomCode+"'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.first()) {
				return true;
			}
			ResultSet rs1 = statement.executeQuery(sql1);
			if(rs1.first()) {
				return true;
			}
			ResultSet rs2 = statement.executeQuery(sql2);
			if(rs2.first()) {
				return true;
			}
			
		} catch (Exception ex) {
			return true;
		} finally {
			conn.close();
		}
		return result;
	}
	
	public boolean DeleteByRoomCode(String roomCode) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {			
			conn = connString.ConnectString();
			
			String sql = "DELETE FROM `room` WHERE `RoomCode` = '"+roomCode+"'";

			Statement statement = conn.createStatement();
			
			statement.executeUpdate(sql);
			
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}
}
