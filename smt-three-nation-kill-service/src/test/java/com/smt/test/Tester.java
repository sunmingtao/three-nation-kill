package com.smt.test;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

import com.smt.threenationkill.dao.GameDaoImpl;
import com.smt.threenationkill.dao.PlayerDaoImpl;
import com.smt.threenationkill.dao.ThreeNationKillDB;
import com.smt.threenationkill.domain.Game;
import com.smt.threenationkill.domain.GamePO;
import com.smt.threenationkill.domain.Player;
import com.smt.threenationkill.io.GameLoader;
import com.smt.threenationkill.service.ThreeNationKillServiceImpl;

public class Tester extends TestCase {
	
	String fileName = "src/main/resources/test.txt";
	
	public void testLoadGames() {
		List<GamePO> games = GameLoader.loadFromFile(new File(fileName));
		for (GamePO game: games){
			System.out.println(game);
		}
		assertEquals(games.size(), 11);
	}
	
	public void testService(){
		ThreeNationKillServiceImpl service = createService();
		List<Game> games = service.loadAndProcessGamesFromFile(new File(fileName));
		assertEquals(games.size(), 11);
		Game game = games.get(0);
		Player player = game.getZhugong();
		System.out.println(player.print());
	}
	
	public void testGetGameStat(){
		ThreeNationKillServiceImpl service = createService();
		List<Game> games = service.loadAndProcessGamesFromFile(new File(fileName));
		assertEquals(games.size(), 11);
		List<Player> players = service.getAllPlayers();
		assertEquals(players.size(), 7);
	}
	
	public void testSaveGame(){
		ThreeNationKillServiceImpl service = createService();
		List<Game> games = service.loadAndProcessGamesFromFile(new File(fileName));
		assertEquals(games.size(), 11);
		games = service.getAllGames();
		assertEquals(games.size(), 11);
		List<Player> players = service.getAllPlayers();
		assertEquals(players.size(), 7);
		for (Player player: players){
			System.out.println(player.print());
		}
	}
	
	private ThreeNationKillServiceImpl createService(){
		ThreeNationKillServiceImpl service = new ThreeNationKillServiceImpl();
		ThreeNationKillDB db = new ThreeNationKillDB();
		db.clearAll();
		PlayerDaoImpl playerDao = new PlayerDaoImpl();
		playerDao.setDb(db);
		GameDaoImpl gameDao = new GameDaoImpl();
		gameDao.setDb(db);
		service.setGameDao(gameDao);
		service.setPlayerDao(playerDao);
		return service;
	}
	
}