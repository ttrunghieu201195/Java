package com.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.Main.ConnectionString;
import com.Model.ContractModel;
import com.Model.RoomModel;

public class ContractDao {
	public ArrayList<ContractModel> getHost () throws SQLException
	{
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		ArrayList<ContractModel> result = new ArrayList<ContractModel>();
		try 
		{
			conn = connString.ConnectString();
	      Statement statement = conn.createStatement();
	      String sqlHost = "SELECT `Code`, FullName\r\n" + 
	      							"FROM `host`\r\n" + 
	      								"WHERE `Status` = \"ACTIVE\"";
	      
	      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
	      ResultSet rs = statement.executeQuery(sqlHost);
	      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
	    	  	ContractModel model = new ContractModel();
				String codeHost = rs.getString(1);
				model.setHostCode(codeHost);
				String codeName = rs.getString(2);
				model.setHostName(codeName);
				result.add(model);
				
	      }
		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return result;   
	}
	public ArrayList<ContractModel> getCustomerFree () throws SQLException
	{
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		ArrayList<ContractModel> result = new ArrayList<ContractModel>();
		try 
		{
			conn = connString.ConnectString();
	      Statement statement = conn.createStatement();
	      String sqlCustomer = "SELECT CustomerId, FullName\r\n" + 
	      					"FROM customer \r\n" + 
	      					"WHERE CustomerId NOT IN (SELECT CustomerId FROM detail_rent)";
	      
	      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
	      ResultSet rs = statement.executeQuery(sqlCustomer);
	      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
	    	  	ContractModel model = new ContractModel();
				String customerId = rs.getString(1);
				model.setCustomerCode(customerId);
				String customerName = rs.getString(2);
				model.setCustomerName(customerName);
				result.add(model);
				
	      }
		} catch (Exception ex) {
			return null;
		} finally {
			conn.close();
		}
		return result;   
	}
	
	public int Add(ContractModel model) throws SQLException {
		ConnectionString connString = new ConnectionString();
		Connection conn = null;
		int returnCode = 0;
        int	countContract = 0;
		try {			
			conn = connString.ConnectString();
			Statement statement = conn.createStatement();
			//Kiểm tra mã trùng
			String checkId = "SELECT Count(*) FROM contract WHERE ContractId = '" + model.getContractID() + "'";
			ResultSet rs = statement.executeQuery(checkId);
		      while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
		    	  countContract = rs.getInt(1); 
		      }
		      
			if (countContract > 0)
			{
				return returnCode =1;
			}
			else {
			//Insert table contract 
			String sqlContract = "INSERT INTO `contract` (" +
			"  `ContractId`, " +
			"  `RoomCode`," + 
			"  `CustomerId`," + 
			"  `HostCode`," + 
			"  `Payment`," + 
			"  `Deposit`," +
			"  `FromDate`," +
			"  `ToDate`) " +
			"VALUES("+ 
				"'" + model.getContractID() +"'," + 
				"'" + model.getRoomCode() +"'," +  
				"'" + model.getCustomerCode() +"'," + 
				"'" + model.getHostCode() +"'," + 
				"'" + model.getPayment() +"'," + 
				"'" + model.getDisposit() +"'," + 
				"'" + model.getFromDate() +"'," + 
				"'" + model.getToDate() +"') ";
			 statement.executeUpdate(sqlContract);
			//Insert table detail_rent
			String sqlRent = "INSERT INTO `detail_rent` (" +
					"  `ContractId`, " +
					"  `RoomCode`," + 
					"  `CustomerId`," + 
					"  `Status`) " +
					"VALUES("+ 
						"'" + model.getContractID() +"'," + 
						"'" + model.getRoomCode() +"'," +  
						"'" + model.getCustomerCode() +"'," + 
						" 'STILLLIVE') ";
			 statement.executeUpdate(sqlRent);
			
			return returnCode = 0;
		}	
		} catch (Exception ex) {
			return returnCode = 9;
		} finally {
			conn.close();
		}
	}
	

}
