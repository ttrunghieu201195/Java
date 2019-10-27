package com.Model;

public class RoomRentModel {
		
		private String RoomCode;
		
		private String RoomName;
		
		private String FullName;
		
		private String Status;
		
		private float Area;
		
		private float BasicPrice;
		
		private float ElectricPrice;
		
		private float WaterPrice;
		
		private float InternetPrice;
		
		private int Floor;
		
		private String RoomType;
		
		private int LimitPerson;
		
		private String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}
		
		public String getFullName() {
			return this.FullName;
		}
		
		public String setFullName(String fullName) {
			return this.FullName = fullName;
		}
		
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

		public float getBasicPrice() {
			return this.BasicPrice;
		}

		public void setBasicPrice(float basicprice) {
			this.BasicPrice = basicprice;
		}
		
		public float getElectricPrice() {
			return this.ElectricPrice;
		}

		public void setElectricPrice(float eprice) {
			this.ElectricPrice = eprice;
		}
		
		public float getWaterPrice() {
			return this.WaterPrice;
		}

		public void setWaterPrice(float wprice) {
			this.WaterPrice = wprice;
		}
		public float getInternetPrice() {
			return this.InternetPrice;
		}

		public void setInternetPrice(float iprice) {
			this.InternetPrice = iprice;
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
