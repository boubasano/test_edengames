package com.edengames.recruit_test.dao.reference;

import com.edengames.recruit_test.model.reference.Race;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RaceDAO {

	private static final int DEFAULT_REWARD = 2000;

	private static final Map<Integer, Race> RACES;

	static {
		RACES = new HashMap<>();
		for (int id = 1; id < 41; id++) {
			int allowedCarId = allowedCar(id);
			int reward = allowedCarId * DEFAULT_REWARD;
			RACES.put(id, new Race(id, allowedCarId, reward));
		}
	}

	private static int allowedCar(int raceId) {
		if (raceId <= 10) {
			return 1;
		}
		if (raceId <= 20) {
			return 2;
		}
		if (raceId <= 30) {
			return 3;
		}
		if (raceId <= 40) {
			return 4;
		}
		throw new RuntimeException("Configuration failure : race id " + raceId + " has no allowed car");
	}

	public List<Race> loadAll() {
		return new ArrayList<>(RACES.values());
	}

	public Race loadById(int id) {
		return RACES.get(id);
	}

	public List<Race> allowedForCar(int carId) {
		return RACES.values() //
				.stream() //
				.filter(race -> race.getAllowedCarId() == carId) //
				.collect(Collectors.toList());
	}
}
