package com.Biz;

import java.sql.SQLException;

import com.Dao.LandlordDao;
import com.Model.LandlordModel;

public class HostValidate {

	public static String validation(LandlordModel model, boolean isEdit) throws SQLException {
		LandlordDao dao = new LandlordDao();
		String validMess = "";

		if (model.getCode().equals("")) {
			validMess += "Mã chủ nhà trọ là bắt buộc";
		} else {
			if (!isEdit & dao.IsExistCode(model.getCode())) {
				validMess += "Mã chủ nhà trọ đã tồn tại";
			}
		}

		if (model.getFullName().equals("")) {
			validMess += "\nTên chủ nhà trọ là bắt buộc";
		}

		if (model.getPhoneNumber().equals("")) {
			validMess += "\nSố điện thoại là bắt buộc";
		} else {
			if (!isNumberic(model.getPhoneNumber())) {
				validMess += "\nSố điện thoại phải là chuỗi số";
			}
		}
		
		if(model.getIdCardNo().equals(""))
		{
			validMess += "\nCMND là bắt buộc";
		}
		else {
			if (!isEdit && dao.IsExistIdCard(model.getIdCardNo())) {
				validMess += "\nCMND đã tồn tại";
			}
		}
		return validMess;
	}

	public static boolean isNumberic(String strNum) {
		try {
			Integer.parseInt(strNum);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
}
