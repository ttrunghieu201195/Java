package com.Dao;

import com.Model.LandlordModel;
import com.Model.RoomModel;
import com.Main.ConnectionString;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LandlordDao {

	public List<LandlordModel> GetAll() throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		List<LandlordModel> Host = new ArrayList<LandlordModel>();
		try {
			conn = connString.ConnectString();
			String sqlMaPhong = "Select  " + "  `Code`," + "  `FullName`," + "  `IdCardNo`," + "  `Address`,"
					+ "  `PhoneNumber`," + "  `Email`," + "  `Facebook`," + "  `Gender`," + "  `DateOfBirth`,"
					+ "  `Status`" + "from host ";

			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlMaPhong);
			while (rs.next()) {
				LandlordModel model = new LandlordModel();
				String code = rs.getString(1);
				model.setCode(code);
				String ten = rs.getString(2);
				model.setFullName(ten);
				String idCard = rs.getString(3);
				model.setIdCardNo(idCard);
				;
				String address = rs.getString(4);
				model.setAddress(address);
				String phone = rs.getString(5);
				model.setPhoneNumber(phone);
				String email = rs.getString(6);
				model.setEmail(email);
				String facebook = rs.getString(7);
				model.setFacebook(facebook);
				int gender = rs.getInt(8);
				model.setGender(gender);
				String birthday = rs.getString(9);
				model.setDateOfBirth(birthday);
				String status = rs.getString(10);
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

	public LandlordModel GetByCode(String landCode) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		LandlordModel model = new LandlordModel();
		try {
			conn = connString.ConnectString();
			String sqlMaPhong = "Select  " + "  `Code`," + "  `FullName`," + "  `IdCardNo`," + "  `Address`,"
					+ "  `PhoneNumber`," + "  `Email`," + "  `Facebook`," + "  `Gender`," + "  `DateOfBirth`,"
					+ "  `Status`" + "from host where `Code` = " + "'" + landCode + "'";

			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlMaPhong);
			while (rs.next()) {

				String code = rs.getString(1);
				model.setCode(code);
				String ten = rs.getString(2);
				model.setFullName(ten);
				String idCard = rs.getString(3);
				model.setIdCardNo(idCard);
				;
				String address = rs.getString(4);
				model.setAddress(address);
				String phone = rs.getString(5);
				model.setPhoneNumber(phone);
				String email = rs.getString(6);
				model.setEmail(email);
				String facebook = rs.getString(7);
				model.setFacebook(facebook);
				int gender = rs.getInt(8);
				model.setGender(gender);
				String birthday = rs.getString(9);
				model.setDateOfBirth(birthday);
				String status = rs.getString(10);
				model.setStatus(status);
			}

		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return model;
	}

	public boolean Add(LandlordModel model) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {
			conn = connString.ConnectString();
			String sql = "INSERT INTO `host` (" + "  `Code`," + "  `FullName`," + "  `IdCardNo`," + "  `Address`,"
					+ "  `PhoneNumber`," + "  `Email`," + "  `Facebook`," + "  `Gender`," + "  `DateOfBirth`,"
					+ "  `Status`" + ")" + "VALUES(" + "'" + model.getCode() + "'," + "'" + model.getFullName() + "',"
					+ "'" + model.getIdCardNo() + "'," + "'" + model.getAddress() + "'," + "'" + model.getPhoneNumber()
					+ "'," + "'" + model.getEmail() + "'," + "'" + model.getFacebook() + "'," + "'" + model.getGender()
					+ "'," + "'" + model.getDateOfBirth() + "'," + "'" + model.getStatus() + "'" + "  )";

			Statement statement = conn.createStatement();
			int rersult = statement.executeUpdate(sql);

		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}

	public boolean Edit(LandlordModel model) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {
			conn = connString.ConnectString();
			String sql = "UPDATE" + "`host`" + "SET" + "  `Code` = " + "'" + model.getCode() + "'," + "  `FullName` = "
					+ "'" + model.getFullName() + "'," + "  `IdCardNo` = " + "'" + model.getIdCardNo() + "',"
					+ "  `Address` = " + "'" + model.getAddress() + "'," + "  `PhoneNumber` = " + "'"
					+ model.getPhoneNumber() + "'," + "  `Email` = " + "'" + model.getEmail() + "'," + "  `Facebook` = "
					+ "'" + model.getFacebook() + "'," + "  `Gender` = " + "'" + model.getGender() + "',"
					+ "  `DateOfBirth` = " + "'" + model.getDateOfBirth() + "'," + "  `Status` = " + "'"
					+ model.getStatus() + "'" + "WHERE `Code` = " + "'" + model.getCode() + "'";
			Statement statement = conn.createStatement();
			int rersult = statement.executeUpdate(sql);
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}

	public boolean Delete(String code) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {
			conn = connString.ConnectString();
			String sql = "DELETE " + "FROM " + "`host` " + "WHERE `Code` = " + "'" + code + "'";
			Statement statement = conn.createStatement();
			int rersult = statement.executeUpdate(sql);
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}
		return true;
	}

	public boolean IsExistCode(String code) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {
			conn = connString.ConnectString();
			String sql = "select `Code` from host where `Code` = " + "'" + code + "'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.first()) {
				return true;
			} else
				return false;
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}

	}

	public boolean IsExistIdCard(String idCard) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		try {
			conn = connString.ConnectString();
			String sql = "select `IdCardNo` from host where `IdCardNo` = " + "'" + idCard + "'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.first()) {
				return true;
			} else
				return false;
		} catch (Exception ex) {
			return false;
		} finally {
			conn.close();
		}

	}

	public boolean IsUsedHost(String code) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		boolean result = false;
		try {			
			conn = connString.ConnectString();
			String sql = "SELECT `Host` FROM bill_detail WHERE `Host` = '"+code+"'";
			String sql1 = "SELECT `HostCode` FROM contract WHERE `HostCode` = '"+code+"'";
			String sql2 = "SELECT `Host` FROM motel_room WHERE `Host` = '"+code+"'";
			String sql3 = "SELECT `host` FROM user WHERE `host` = '"+code+"'";
			
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
			
			ResultSet rs3 = statement.executeQuery(sql3);
			if(rs3.first()) {
				return true;
			}
			
		} catch (Exception ex) {
			return true;
		} finally {
			conn.close();
		}
		return result;
	}
}
