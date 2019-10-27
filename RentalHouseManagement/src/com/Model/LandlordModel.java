package com.Model;

public class LandlordModel {
	private String Code;
	
	private String FullName;
	
	private String IdCardNo;
	
	private String PhoneNumber;
	
	private String Email;
	
	private String Facebook;
	
	private int Gender; //1 là Nam, 0 là Nữ
	
	private String DateOfBirth;
	
	private String Status;
	
	private String Address;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getIdCardNo() {
		return IdCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		IdCardNo = idCardNo;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFacebook() {
		return Facebook;
	}

	public void setFacebook(String facebook) {
		Facebook = facebook;
	}

	public int getGender() {
		return Gender;
	}

	public void setGender(int gender) {
		Gender = gender;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getStatus() {
		return Status;
	}
	
	public void setStatus(String status) {
		Status = status;
	}

	public void setAddress(String stataddress) {
		Address = stataddress;
	}
	
	public String getAddress() {
		return Address;
	}
	

}
