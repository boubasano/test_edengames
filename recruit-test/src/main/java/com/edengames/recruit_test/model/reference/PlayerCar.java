package com.edengames.recruit_test.model.reference;

import com.edengames.recruit_test.model.Player;

public class PlayerCar {
	private Player player;
	private Car car;
	
	public PlayerCar(Player player, Car car) {
		super();
		this.player = player;
		this.car = car;
	}

	public PlayerCar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	
	
}
