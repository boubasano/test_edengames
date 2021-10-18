package com.edengames.recruit_test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edengames.recruit_test.dao.PlayerDAO;
import com.edengames.recruit_test.dao.reference.CarDAO;
import com.edengames.recruit_test.model.Player;
import com.edengames.recruit_test.model.reference.Car;

@Service
public class BuyCarManagement {

	private CarDAO carDAO;
	private PlayerDAO playerDAO;
	private Player player;

	@Autowired
	public BuyCarManagement(CarDAO carDAO, PlayerDAO playerDAO) {
		super();
		this.carDAO = carDAO;
		this.playerDAO = playerDAO;
	}

	public void buyCar(boolean choiceCar, Player player) {
		// LORSQUE CE PROCESSUS EST ENCLENCHÉ (C'EST À DIRE À "TRUE")
		if (choiceCar == true) {
			// CHARGEMENT DE LA LISTE DES VOITURES
			List<Car> carList = this.carDAO.loadAll();
			System.out.println("===========LISTE DES VOITURES===========================");
			System.out.println(carList);
			// PARCOURS DE LA LISTE DE VOITURES
			for (Car car : carList) {

				final int carId = car.getId();
				// J'OBTIENS LA LISTE DES VOITURES ACHETABLES
				final Car carToBuy = this.carDAO.loadById(carId);

				// J'ENCLENCHE L'ACHAT DE VOITURE À CE NIVEAU
				if (player.getMoney() >= carToBuy.getPrice()) {	
					int remainingAmount = player.getMoney() - carToBuy.getPrice();
					player.setMoney(remainingAmount);
					player.setCar(carToBuy);
					playerDAO.save(player);
					System.out.println("=========VOITURE ACHETEE============================== ");
					System.out.println(player);

				}
			}
		}

	}
	


}
