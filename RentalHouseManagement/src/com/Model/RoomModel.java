package com.Model;

public class RoomModel {
	
	private String RoomCode;
	
	private String RoomName;
	
	private String Status;
	
	private float Area;
	
	private int Floor;
	
	private String RoomType;
	
	private int LimitPerson;

	public String getRoomCode() {
		return this.RoomCode;
	}

	public void setCode(String roomCode) {
		this.RoomCode = roomCode;
	}

	public String getRoomName() {
		return this.RoomName;
	}

	public void setRoomName(String roomName) {
		this.RoomName = roomName;
	}

	public String getStatus() {
		return this.Status;
	}

	public void setStatus(String status) {
		this.Status = status;
	}

	public float getArea() {
		return this.Area;
	}

	public void setArea(float area) {
		this.Area = area;
	}

	public int getFloor() {
		return this.Floor;
	}

	public void setFloor(int floor) {
		this.Floor = floor;
	}
	
	public String getRoomType() {
		return this.RoomType;
	}

	public void setRoomType(String roomType) {
		this.RoomType = roomType;
	}
	
	public int getLimitPerson() {
		return this.LimitPerson;
	}

	public void setLimitPerson(int limitPerson) {
		this.LimitPerson = limitPerson;
	}
}
