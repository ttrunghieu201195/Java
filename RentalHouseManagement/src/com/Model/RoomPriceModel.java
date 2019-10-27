package com.Model;

public class RoomPriceModel {
	
	int Id;
	
	String RoomCode;
	
	float BasicPrice;
	
	float ElectricPrice;
	
	float WaterPrice;
	
	float InternetPrice;
	
	int Month;
	
	int Year;
	
	public int getId() {
		return this.Id;
	}
	
	public void setId(int id) {
		this.Id = id;
	}

	public void setRoomCode(String roomCode) {
		this.RoomCode = roomCode;
	}
	
	public String getRoomCode() {
		return this.RoomCode;
	}
	
	public void setBasicPrice(float basicPrice) {
		this.BasicPrice = basicPrice;
	}
	
	public float getBasicPrice() {
		return this.BasicPrice;
	}
	
	public void setElectricPrice(float electricPrice) {
		this.ElectricPrice = electricPrice;
	}
	
	public float getElectricPrice() {
		return this.ElectricPrice;
	}
	
	public void setWaterPrice(float waterPrice) {
		this.WaterPrice = waterPrice;
	}
	
	public float getWaterPrice() {
		return this.WaterPrice;
	}
	
	public void setInternetPrice(float internetPrice) {
		this.InternetPrice = internetPrice;
	}
	
	public float getInternetPrice() {
		return this.InternetPrice;
	}
	
	public void setMonth(int month) {
		this.Month = month;
	}
	
	public float getMonth() {
		return this.Month;
	}
	
	public void setYear(int year) {
		this.Year = year;
	}
	
	public float getYear() {
		return this.Year;
	}
}
