package com.Model;

public class MotelRoomModel {

	private String motelCode;
	private String motelName;
	private int numOfFloor;
	private String address;
	private String hostId;

	public String getMotelCode() {
		return motelCode;
	}

	public void setMotelCode(String modelCode) {
		this.motelCode = modelCode;
	}

	public String getMotelName() {
		return motelName;
	}

	public void setMotelName(String modelName) {
		this.motelName = modelName;
	}

	public int getNumOfFloor() {
		return numOfFloor;
	}

	public void setNumOfFloor(int numOfFloor) {
		this.numOfFloor = numOfFloor;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

}
