package com.edengames.recruit_test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.edengames.recruit_test.dao.reference.CarDAO;
import com.edengames.recruit_test.dao.reference.RaceDAO;
import com.edengames.recruit_test.model.reference.Car;
import com.edengames.recruit_test.model.reference.Race;

@Service
public class RewardManagement {

	private CarDAO carDAO;

	private RaceDAO raceDAO;

	@Autowired
	public RewardManagement(CarDAO carDAO, RaceDAO raceDAO) {
		super();
		this.carDAO = carDAO;
		this.raceDAO = raceDAO;
	}

	public void rewardFirstRace(int allowedCardToRace) {
		// ====================CHARGEMENT DE LA LISTE DES COURSES
		final List<Race> raceList = raceDAO.loadAll();
		System.out.println("==============LISTE DE TOUTES LES COURSES======================");
		System.out.println(raceList);
		// PARCOURS DE CETTE LISTE
		for (Race races : raceList) {
			races.setAllowedCarId(allowedCardToRace);

			Car carForRace = carDAO.loadById(allowedCardToRace);

			// PERMET DE VERIFIER SI C'EST UNE PREMIERE VICTOIRE
			boolean firstVictory = true;

			// SI C'EST UNE PREMIERE VICTOIRE
			if (firstVictory == true) {
				// METTRE L'ARGENT DE LA RECOMPENSE DE LA COURSE DANS LES RECOMPENSES
				// OBTENUES DE LA VOITURE
				carForRace.setRewards(races.getWinMoneyReward());
				carDAO.save(carForRace);
				System.out.println(
						"=================VOITURE VICTORIEUSE AVEC RECOMPENSE POUR PREMIERE COURSE===============");
				System.out.println(carForRace);
			}

		}

	}
	
	public void rewardRace(int allowedCarToRace, int bonus) {
		// ====================CHARGEMENT DE LA LISTE DES COURSES
		final List<Race> raceList = raceDAO.loadAll();
		// PARCOURS DE CETTE LISTE
		for (Race races : raceList) {
			races.setAllowedCarId(allowedCarToRace);

			Car carForRace = carDAO.loadById(allowedCarToRace);

			// PERMET DE VERIFIER SI C'EST UNE PREMIERE VICTOIRE
			boolean victory = true;
			boolean defeat = true;

			// SI C'EST UNE PREMIERE VICTOIRE
			if ((victory == true) || (defeat == true)) {
				// METTRE L'ARGENT DE LA RECOMPENSE DE LA COURSE DANS LES RECOMPENSES
				// OBTENUES DE LA VOITURE
				
				carForRace.setRewards(bonusCalcul(bonus, races.getWinMoneyReward()));
				carDAO.save(carForRace);
				System.out.println(
						"=================VOITURE AVEC RECOMPENSE APRES CHAQUE VICTOIRE OU DEFAITE===============");
				System.out.println(carForRace);
			}

		}

	}
	
	public int bonusCalcul(int bonus, int reward) {
		int bonusAmount = (reward/100)*bonus;				
		return bonusAmount;		
	}


}
