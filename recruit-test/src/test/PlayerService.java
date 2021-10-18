package com.edengames.recruit_test.service;

import com.edengames.recruit_test.model.Player;
@Test
public class PlayerService {

	public Player fromPlayerModel(Player player) {
		Player newPlayer = new Player("");
		newPlayer.setMoney(player.getMoney());
		newPlayer.setCar(player.getCar());
//		player.getCar().forEach((car -> newPlayer.getCar().add(fromPlayerModel(car))));
		return newPlayer;
	}
}
