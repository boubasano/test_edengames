package com.edengames.recruit_test.model.reference;

//cette classe va représenter le dto (ou pojo) de la classe course
public class Race {

	private int id;
	private int winMoneyReward;
	private int allowedCarId;

	public Race(int id, int allowedCarId, int winMoneyReward) {
		this.id = id;
		this.allowedCarId = allowedCarId;
		this.winMoneyReward = winMoneyReward;

	}

	public int getId() {
		return id;
	}

	public int getAllowedCarId() {
		return allowedCarId;
	}

	public void setAllowedCarId(int allowedCarId) {
		this.allowedCarId = allowedCarId;
	}

	public int getWinMoneyReward() {
		return winMoneyReward;
	}

	public void setWinMoneyReward(int winMoneyReward) {
		this.winMoneyReward = winMoneyReward;
	}

	@Override
	public String toString() {
		return "Race: " + id + ",\r\n WinMoneyReward=" + winMoneyReward + ",\r\n\r\n AllowedCarId: ";
	}
	
	
}