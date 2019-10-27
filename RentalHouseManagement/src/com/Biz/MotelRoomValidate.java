package com.Biz;

import java.sql.SQLException;

import com.Dao.MotelRoomDao;
import com.Model.MotelRoomModel;

public class MotelRoomValidate {

	public static String validation(MotelRoomModel model, boolean isEdit) throws SQLException {
		MotelRoomDao dao = new MotelRoomDao();
		String validMess = "";

		if (model.getMotelCode().equals("")) {
			validMess += "Mã nhà trọ là bắt buộc";
		} else {
			if (!isEdit && dao.isExistCode(model.getMotelCode())) {
				validMess += "Mã nhà trọ đã tồn tại";
			}
		}

		if (model.getMotelName().equals("")) {
			validMess += "\nTên nhà trọ là bắt buộc";
		}
		
		if(model.getHostId().equals(""))
		{
			validMess += "\nMã chủ nhà là bắt buộc";
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
