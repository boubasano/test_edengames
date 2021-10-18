package com.edengames.recruit_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edengames.recruit_test.dao.reference.RaceDAO;
import com.edengames.recruit_test.model.reference.Race;

@RestController
@RequestMapping("")
public class RaceController {
	
	@Autowired
	private RaceDAO raceDAO;
	
	@GetMapping(value="/races")
	public List<Race> getAllRaces(){
		return this.raceDAO.loadAll();
	}
	
	@GetMapping(value="/races/{id}")
	public Race getRace(@PathVariable int id) {
		if (this.raceDAO == null) {
			return null;
		}
		return this.raceDAO.loadById(id);
	}
}
