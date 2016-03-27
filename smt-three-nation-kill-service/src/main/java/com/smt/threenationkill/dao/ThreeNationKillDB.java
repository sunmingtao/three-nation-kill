package com.smt.threenationkill.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.smt.threenationkill.domain.Game;
import com.smt.threenationkill.domain.GameStat;
import com.smt.threenationkill.domain.Player;

/**
 * The mock database, to be replaced by JPA or Hibernate
 * @author Mingtao Sun
 */
public class ThreeNationKillDB {
	private static Map<Long, Player> playerTable = new TreeMap<Long, Player>();
	private static Map<Long, Game> gameTable = new TreeMap<Long, Game>();
	private static Map<Long, GameStat> gameStatTable = new TreeMap<Long, GameStat>();
	private static int playerId = 1;
	private static int gameId = 1;
	private static int gameStatId = 1;
	
	static{
//		Player player1 = new Player(new Long(1), "老孙");
//		Player player2 = new Player(new Long(2), "Lina");
//		Player player3 = new Player(new Long(3), "徐俊");
//		Player player4 = new Player(new Long(4), "钱夫人");
//		Player player5 = new Player(new Long(5), "老徐");
//		Player player6 = new Player(new Long(6), "龙人");
//		Player player7 = new Player(new Long(7), "小马");
//		Player player8 = new Player(new Long(8), "莫");
//		playerTable.put(player1.getId(), player1);
//		playerTable.put(player2.getId(), player2);
//		playerTable.put(player3.getId(), player3);
//		playerTable.put(player4.getId(), player4);
//		playerTable.put(player5.getId(), player5);
//		playerTable.put(player6.getId(), player6);
//		playerTable.put(player7.getId(), player7);
//		playerTable.put(player8.getId(), player8);
//		Game game1 = new Game();
//		game1.setId(new Long(1));
//		//2010/09/04
//		Calendar c = new GregorianCalendar(2010, 8, 4);
//		game1.setDate(c.getTime());
//		game1.setPlayerNum(5);
//		game1.setZhugong(player3);
//		List<Player> zhongchenList = new ArrayList<Player>();
//		zhongchenList.add(player8);
//		game1.setZhongchen(zhongchenList);
//		List<Player> neijianList = new ArrayList<Player>();
//		neijianList.add(player1);
//		game1.setNeijian(neijianList);
//		List<Player> fanzeiList = new ArrayList<Player>();
//		fanzeiList.add(player2);
//		fanzeiList.add(player4);
//		game1.setFanzei(fanzeiList);
//		game1.setResult("主公胜");
//		gameTable.put(game1.getId(), game1);
	}
	
	public List<Player> getAllPlayers(){
		List<Player> playerList = new ArrayList<Player>();
		Iterator<Player> playerIt = playerTable.values().iterator();
		while(playerIt.hasNext()){
			Player player =playerIt.next();
			playerList.add(player);
		}
		return playerList;
	}
	
	public Player getPlayerByName(String name){
		if (name == null) return null;
		Iterator<Player> playerIt = playerTable.values().iterator();
		while(playerIt.hasNext()){
			Player player =playerIt.next();
			if (name.equals(player.getName())){
				return player;
			}
		}
		return null;
	}
	
	public List<Game> getAllGames(){
		List<Game> gameList = new ArrayList<Game>();
		Iterator<Game> gameIt = gameTable.values().iterator();
		while(gameIt.hasNext()){
			Game game =gameIt.next();
			gameList.add(game);
		}
		return gameList;
	}
	
	public void saveGame(Game game){
		if (game.getId() == null){
			game.setId(new Long(gameId++));
			gameTable.put(game.getId(), game);
		}
		savePlayer(game.getZhugong());
		for (Player player: game.getZhongchen()){
			savePlayer(player);
		}
		for (Player player: game.getNeijian()){
			savePlayer(player);
		}
		for (Player player: game.getFanzei()){
			savePlayer(player);
		}
	}
	
	public void savePlayer(Player player){
		if (player.getId() == null){
			player.setId(new Long(playerId++));
			playerTable.put(player.getId(), player);
		}
		for (GameStat gameStat: player.getGameStats()){
			saveGameStat(gameStat);
		}
	}
	
	public void saveGameStat(GameStat gameStat){
		if (gameStat.getId() == null){
			gameStat.setId(new Long(gameStatId++));
			gameStatTable.put(gameStat.getId(), gameStat);
		}
	}
	
	public void clearAll(){
		gameTable.clear();
		gameStatTable.clear();
		playerTable.clear();
	}
}
