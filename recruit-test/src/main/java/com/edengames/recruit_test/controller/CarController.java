package com.edengames.recruit_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edengames.recruit_test.dao.reference.CarDAO;
import com.edengames.recruit_test.model.reference.Car;

@RestController
@RequestMapping("")
public class CarController {
	
	@Autowired
	private CarDAO cardDAO;
	
	@GetMapping(value="/cars")
	public List<Car> getAllCars(){
		return this.cardDAO.loadAll();
	}
	
	@GetMapping(value="/cars/{id}")
	public Car getCar(@PathVariable int id) {
		if (this.cardDAO == null) {
			return null;
		}
		return this.cardDAO.loadById(id);
	}

}
