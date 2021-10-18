package com.edengames.recruit_test.dao.reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edengames.recruit_test.dao.PlayerDAO;
import com.edengames.recruit_test.model.Player;
import com.edengames.recruit_test.model.reference.Car;

//cette classe va nous renvoyer nos données et les mettre à jour 
@Repository
public class CarDAO {

	private static final int FIRST_CAR_PRICE = 10000;

	private static final Map<Integer, Car> CARS;

	static {
		CARS = new HashMap<>();
		for (int id = 1; id < 5; id++) {
			CARS.put(id, new Car(id, id * FIRST_CAR_PRICE));
		}
	}

	public List<Car> loadAll() {
		return new ArrayList<>(CARS.values());
	}

	public Car loadById(int id) {
		return CARS.get(id);
	}
	
	public void save(Car car) {
		CARS.put(car.getId(), car);
	}

}
