package com.Dao;

import com.Model.MotelRoomModel;
import com.Main.ConnectionString;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MotelRoomDao {

	public List<MotelRoomModel> getAll() throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		List<MotelRoomModel> motelList = new ArrayList<>();
		try {
			conn = connString.ConnectString();
			String sqlMaPhong = "Select " + "`Code`," + "  `Name`," + "  `NumOfFloor`," + "  `Address`,"
					+ "  `Host` " + "from motel_room ";
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlMaPhong);
			while (rs.next()) {
				MotelRoomModel model = new MotelRoomModel();
				String code = rs.getString(1);
				model.setMotelCode(code);
				String name = rs.getString(2);
				model.setMotelName(name);
				int numOfFloor = rs.getInt(3);
				model.setNumOfFloor(numOfFloor);
				
				String address = rs.getString(4);
				model.setAddress(address);
				String hostId = rs.getString(5);
				model.setHostId(hostId);
				motelList.add(model);

			}

		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return motelList;
	}

	public MotelRoomModel getMotelRoomByCode(String motelCode) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		MotelRoomModel motel = new MotelRoomModel();
		try {
			conn = connString.ConnectString();
			String sqlMaPhong = "Select  " + "  `Code`," + "  `Name`," + "  `NumofFloor`," + "  `Address`,"
					+ "  `Host`" + "from motel_room where `Code` = " + "'" + motelCode + "'";

			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlMaPhong);
			while (rs.next()) {

				String code = rs.getString(1);
				motel.setMotelCode(code);
				String ten = rs.getString(2);
				motel.setMotelName(ten);
				int idCard = rs.getInt(3);
				motel.setNumOfFloor(idCard);
				
				String address = rs.getString(4);
				motel.setAddress(address);
				String hostId = rs.getString(5);
				motel.setHostId(hostId);
			}

		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return motel;
	}

	public boolean addNewMotelRoom(MotelRoomModel model) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {
			conn = connString.ConnectString();
			String sql = "INSERT INTO `motel_room` (" + "  `Code`," + "  `Name`," + "  `NumOfFloor`," + "  `Address`,"
					+ "  `Host`" + ")" + "VALUES(" + "'" + model.getMotelCode() + "'," + "'" + model.getMotelName() + "',"
					+ "'" + model.getNumOfFloor() + "'," + "'" + model.getAddress() + "'," + "'" + model.getHostId() + "'" + "  )";

			Statement statement = conn.createStatement();
			int rersult = statement.executeUpdate(sql);

		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}

	public boolean editMotelRoom(MotelRoomModel model) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {
			conn = connString.ConnectString();
			String sql = "UPDATE" + "`motel_room`" + "SET" + "  `Code` = " + "'" + model.getMotelCode() + "'," + "  `Name` = "
					+ "'" + model.getMotelName() + "'," + "  `NumOfFloor` = " + "'" + model.getNumOfFloor() + "',"
					+ "  `Address` = " + "'" + model.getAddress() + "'," + "  `Host` = " + "'"
					+ model.getHostId() + "'" + "WHERE `Code` = " + "'" + model.getMotelCode() + "'";
			Statement statement = conn.createStatement();
			int rersult = statement.executeUpdate(sql);
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}

	public boolean deleteMotelRoom(String code) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {
			conn = connString.ConnectString();
			String sql = "DELETE " + "FROM " + "`motel_room` " + "WHERE `Code` = " + "'" + code + "'";
			Statement statement = conn.createStatement();
			int rersult = statement.executeUpdate(sql);
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}

	public boolean isExistCode(String code) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {
			conn = connString.ConnectString();
			String sql = "select `Code` from motel_room where `Code` = " + "'" + code + "'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.first()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
	}
	
	public List<String> getAllHostId() throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		List<String> hostIdList = new ArrayList<>();
		try {
			conn = connString.ConnectString();
			String sql = "select `Code` from host";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				hostIdList.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return hostIdList;
	}
}