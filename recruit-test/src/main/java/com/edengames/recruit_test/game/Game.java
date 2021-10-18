package com.edengames.recruit_test.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edengames.recruit_test.dao.PlayerDAO;
import com.edengames.recruit_test.dao.reference.CarDAO;
import com.edengames.recruit_test.dao.reference.RaceDAO;
import com.edengames.recruit_test.model.Player;
import com.edengames.recruit_test.model.reference.Car;
import com.edengames.recruit_test.model.reference.Race;
import com.edengames.recruit_test.services.BuyCarManagement;
import com.edengames.recruit_test.services.RewardManagement;

@Controller
@RequestMapping("")
public class Game extends Thread {

	private final int DELAY_ROTATION = 10000; // (60 * 1000) * 60;

	private PlayerDAO playerDAO;

	private CarDAO carDAO;

	private RaceDAO raceDAO;
	
	@Autowired
	private BuyCarManagement buyingCar;
	
	@Autowired
	private RewardManagement rewardRace;

	@Autowired
	public Game(PlayerDAO playerDAO, CarDAO carDAO, RaceDAO raceDAO) {
		super();
		this.playerDAO = playerDAO;
		this.carDAO = carDAO;
		this.raceDAO = raceDAO;
	}

	@GetMapping("/game")
	public void gamePlay() {
		boolean start = true;

		// ======================CHARGEMENT DE LA LISTE DES JOUEURS DISPONIBLES SANS LES
		// VOITURES ===================//

		final List<Player> playersWithoutCar = playerDAO.loadAll();
		System.out
				.println("=======================LISTE DES JOUEURS SANS VOITURE=====================================");
		System.out.println(playersWithoutCar);

		while (start) {

			// JE BOUCLE SUR CETTE LISTE POUR AVOIR UN JOUEUR AVEC MISE DE DEPART SANS
			// VOITURES
			for (Player player : playersWithoutCar) {
				// A PARTIR DE L'ID JE VAIS POUVOIR OBTENIR UN JOUEUR
				final String playerId = player.getId();
				final Player gamePlayer = playerDAO.loadById(playerId);
				// JE VERIFIE SI L'ID DU JOUEUR EXISTE
				if (gamePlayer.getId().isEmpty()) {
					System.out.println("Inscrivez pour participer au jeu");
					continue;
					
				}
				// CE BOOLEAN À FALSE INDIQUE QUE LE PROCESSUS DE CHOIX DE VOITURE N'EST PAS
				// ENCORE ENCLENCHÉ
				boolean choiceCar = true;
				
				// GESTION D'ACHAT DE VOITURE
				buyingCar.buyCar(choiceCar, player);
				
				// CE BOOLÉEN VA ARRETER LE PROCESSUS D'ACHAT DE LA MEME VOITURE
				choiceCar = false;

			}

			// =====================LISTE DES JOUEURS AVEC DES
			// VOITURES=========================================//
			for (Player player : playersWithoutCar) {
				final String playerId = player.getId();
				final Player playerWithCar = playerDAO.loadById(playerId);
				if (playerWithCar.getCar() == null) {
					System.out.println("Vous devez acheter une voiture pour participer à une course");
					continue;
				}

				// GRACE À LOADLAZY JE VAIS FAIRE UN CHARGMENT DE TOUS LES JOUEURS AVEC LEUR
				// VOITURE
				playerDAO.loadLazy(playerWithCar); 
				System.out.println(
						"=======================LISTE DES JOUEURS AVEC VOITURE=====================================");
				System.out.println(playerWithCar);
				// CHARGEMENT D'UNE VOITURE SPECIFIQUE QUI PARTICIPE A LA COURSE
				Car participatingCar = playerWithCar.getCar();

				System.out.println("========VOITURES PARTICIPANTES A LA COURSE=================");
				System.out.println(participatingCar);
				// JE RECUPERE SON ID QUI VA CORRESPONDRE A L'ID POUR PARTICIPER A LA COURSE
				final int allowedCardToRace = participatingCar.getId();
				
				
				// LISTE DES COURSES ET RECOMPENSE POUR CHAQUE COURSE, AINSI QUE LA PREMIERE COURSE
				this.rewardRace.rewardFirstRace(allowedCardToRace);
				
				int bonus = 10;
				this.rewardRace.rewardRace(allowedCardToRace, bonus);
				
				
			}

			try {
				Thread.sleep(DELAY_ROTATION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
