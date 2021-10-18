package com.edengames.recruit_test.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.edengames.recruit_test.model.reference.Car;


// cette classe va représenter le dto (ou pojo) de la classe joueur
public class Player {

	private static final int STARTING_MONEY = 10000;

	private String id;
	private int money; 
	private Car car;
	private int rewards;
	
	
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Player(String id) {
		this.id = id;
		this.money = STARTING_MONEY;
	}

	public String getId() {
		return id;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

		public int getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}
	@Override
	public String toString() {
		return "Player: " + id + ",\r\n Money: " + money + ",\r\n Car: " + car;
				
	}


	

}
