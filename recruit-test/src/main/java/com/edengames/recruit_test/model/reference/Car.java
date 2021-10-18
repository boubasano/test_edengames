package com.edengames.recruit_test.model.reference;

//cette classe va représenter le dto (ou pojo) de la classe joueur
public class Car {

	private int id;
	private int price;
	private int rewards;

	public Car(int id, int price) {
		this.id = id;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public int getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}

	@Override
	public String toString() {
		return "Car: " + id + ",\r\n Price: " + price + ",\r\n Rewards: " + rewards + "\r\n";
	}
	
	

}
