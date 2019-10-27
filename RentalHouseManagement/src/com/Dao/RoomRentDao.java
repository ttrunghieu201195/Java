package com.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.Main.ConnectionString;
import com.Model.LandlordModel;
import com.Model.RoomRentModel;


public class RoomRentDao {
	public ArrayList<RoomRentModel> GetRoomRent() throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		ArrayList<RoomRentModel> roomRent = new ArrayList<RoomRentModel>();
		try {			
			conn = connString.ConnectString();
			String sqlRoomRent = "SELECT  dr.`RoomCode`, r.`RoomName`, r.`Floor`, cm.`Name` AS `RoomType`,cs.`FullName`, common.Name AS `Status`\r\n" + 
									"FROM detail_rent dr LEFT JOIN room r ON dr.`RoomCode`= r.`RoomCode`\r\n" + 
									"LEFT JOIN customer cs ON dr.`CustomerId` = cs.`CustomerId`\r\n" + 
									"LEFT JOIN sys_common common ON dr.`Status`= common.`Code`\r\n" + 
									"LEFT JOIN sys_common cm ON r.`RoomType`= cm.`Code` ";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlRoomRent);
			while (rs.next()) {
				RoomRentModel model = new RoomRentModel();
				String maPhong = rs.getString(1);
				model.setCode(maPhong);
				String tenPhong = rs.getString(2);
				model.setRoomName(tenPhong);
				int tang = rs.getInt(3);
				model.setFloor(tang);
				String loaiPhong = rs.getString(4);
				model.setRoomType(loaiPhong);
				String nguoio = rs.getString(5);
				model.setFullName(nguoio);
				String trangthai = rs.getString(6);
				model.setStatus(trangthai);
				roomRent.add(model);
			}
			
			
		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return roomRent;
		
	}
	public ArrayList<RoomRentModel> GetRoomFree() throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		ArrayList<RoomRentModel> roomFree = new ArrayList<RoomRentModel>();
		try {			
			conn = connString.ConnectString();
			String sqlRoomFree = "SELECT r.RoomCode, r.RoomName, r.Floor, common.Name AS STATUS, r.LitmitPerson, pr.BasicPrice, pr.ElectricPrice, pr.WaterPrice, pr.InternetPrice\r\n" + 
								"FROM room r LEFT JOIN room_price pr ON r.RoomCode = pr.RoomCode\r\n" + 
								"LEFT JOIN sys_common common  ON r.Status = common.Code\r\n" + 
								"WHERE r.Status = 'READY'; ";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlRoomFree);
			while (rs.next()) {
				RoomRentModel model = new RoomRentModel();
				String maPhong = rs.getString(1);
				model.setCode(maPhong);
				String tenPhong = rs.getString(2);
				model.setRoomName(tenPhong);
				int tang = rs.getInt(3);
				model.setFloor(tang);
				String trangthai = rs.getString(4);
				model.setStatus(trangthai);
				int toiDa = rs.getInt(5);
				model.setLimitPerson(toiDa);
				float giaPhong = rs.getFloat(6);
				model.setBasicPrice(giaPhong);
				float giaDien = rs.getFloat(7);
				model.setElectricPrice(giaDien);
				float giaNuoc = rs.getFloat(8);
				model.setWaterPrice(giaNuoc);
				float giaInternet = rs.getFloat(9);
				model.setInternetPrice(giaInternet);
				roomFree.add(model);
			}
			
			
		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return roomFree;
	}
	
	public RoomRentModel getPhong (String maPhong) throws ClassNotFoundException, SQLException
	{
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		 RoomRentModel model = new RoomRentModel();
	      Statement statement = conn.createStatement();
	      String sqlRoomRent = "SELECT  dr.`RoomCode`, r.`RoomName`, r.`Floor`, cm.`Name` AS `RoomType`,cs.`FullName`, common.Name AS `Status`\r\n" + 
					"FROM detail_rent dr LEFT JOIN room r ON dr.`RoomCode`= r.`RoomCode`\r\n" + 
					"LEFT JOIN customer cs ON dr.`CustomerId` = cs.`CustomerId`\r\n" + 
					"LEFT JOIN sys_common common ON dr.`Status`= common.`Code`\r\n" + 
					"LEFT JOIN sys_common cm ON r.`RoomType`= cm.`Code` "
					+ "WHERE dr.RoomCode = '" + maPhong + "'";
	      
	      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
	      ResultSet rs = statement.executeQuery(sqlRoomRent);
	      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String maphong = rs.getString(1);
				model.setCode(maPhong);
				String tenPhong = rs.getString(2);
				model.setRoomName(tenPhong);
				int tang = rs.getInt(3);
				model.setFloor(tang);
				String loaiPhong = rs.getString(4);
				model.setRoomType(loaiPhong);
				String nguoio = rs.getString(5);
				model.setFullName(nguoio);
				String trangthai = rs.getString(6);
				model.setStatus(trangthai);
	          
	      }
	      conn.close();
		return model;
	      
	}
	
}
