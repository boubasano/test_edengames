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
import com.edengames.recruit_test.model.Player;
//import com.edengames.recruit_test.services.PlayerService;

@RestController
@RequestMapping("")
public class PlayerController {
	
	@Autowired
	private PlayerDAO playerDAO;

	@GetMapping(value = "/players")
	public List<Player> getAllPlayers() {
		return this.playerDAO.loadAll();
	}

	@GetMapping(value = "/players/{id}")
	public Player getPlayer(@PathVariable String id) {
		if (this.playerDAO == null) {
			return null;
		}
		return this.playerDAO.loadById(id);
	}

	@PostMapping(value = "/players")
	public void createPlayer(@RequestBody Player player) {

		this.playerDAO.save(player);
	}



}
