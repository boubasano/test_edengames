package com.edengames.recruit_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edengames.recruit_test.dao.PlayerDAO;
import com.edengames.recruit_test.dao.reference.CarDAO;
import com.edengames.recruit_test.model.Player;
//import com.edengames.recruit_test.services.PlayerService;

@RestController
@RequestMapping("")
public class BuyCarController {
	
	@Autowired
	private PlayerDAO playerDAO;
	
	private CarDAO carDAO;

	@GetMapping(value = "/buy-car")
	public List<Player> getAllPlayersWithCar() {
		return this.playerDAO.loadAll();
	}

	@PostMapping(value = "/buy-car")
	public void createPlayer(@RequestBody Player player) {

		this.playerDAO.save(player);
	}



}
