package com.edengames.recruit_test.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edengames.recruit_test.dao.reference.CarDAO;
import com.edengames.recruit_test.model.Player;
import com.edengames.recruit_test.model.reference.Car;

// cette classe va nous renvoyer nos données et les mettre à jour 
@Repository
public class PlayerDAO {

	private static final String TEST_PLAYER_ID = "myPlayerId";

	private static final Map<String, Player> PLAYERS;
	
	private CarDAO carDAO;
	
	@Autowired
	public PlayerDAO(CarDAO carDAO) {
		super();
		this.carDAO = carDAO;
	}

	static {
		PLAYERS = new HashMap<>();
		PLAYERS.put(TEST_PLAYER_ID, new Player(TEST_PLAYER_ID));

	}

	public Player loadById(String id) {
		return PLAYERS.get(id);
	}
	
	public List<Player> loadAll() {
		return new ArrayList<>(PLAYERS.values());
	}

	public void save(Player player) {
		PLAYERS.put(player.getId(), player);
	}
	
	public Player loadAllWithCar(Player player){
		final Car carList = this.carDAO.loadById(player.getCar().getId());
		player.setCar(carList);
		return player;
		
	}
	
	public void loadLazy(Player player) {
		final Car carList = this.carDAO.loadById(player.getCar().getId());
		player.setCar(carList);
	}
}
