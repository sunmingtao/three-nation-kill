package com.smt.threenationkill.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.smt.threenationkill.constant.ThreeNationKillConstants;
import com.smt.threenationkill.dao.GameDao;
import com.smt.threenationkill.dao.PlayerDao;
import com.smt.threenationkill.domain.Game;
import com.smt.threenationkill.domain.GamePO;
import com.smt.threenationkill.domain.GameStat;
import com.smt.threenationkill.domain.Player;
import com.smt.threenationkill.domain.PlayerDetail;
import com.smt.threenationkill.domain.WinPercent;
import com.smt.threenationkill.domain.WinPercentDetail;
import com.smt.threenationkill.enums.Role;
import com.smt.threenationkill.io.GameLoader;
import com.smt.threenationkill.util.PointUtils;

/**
 * This is the implementation of ThreeNationKillService
 * @author Mingtao Sun
 */
public class ThreeNationKillServiceImpl implements ThreeNationKillService {

	/** Logger */
	Logger logger = Logger.getLogger(ThreeNationKillServiceImpl.class);
	
	/** A list of players */
	private List<Player> players;
	/** PlayerDao */
	private PlayerDao playerDao;
	/** GameDao */
	private GameDao gameDao;
	
	/**
	 * constructor
	 */
	public ThreeNationKillServiceImpl(){
		logger.info("Service Created");
		players = new ArrayList<Player>();
	}
	
	public List<Player> getAllPlayers() {
		return playerDao.getAllPlayers();
	}

	public Player getPlayerById(Long id) {
		if (id == null) return null;
		for (Player player: this.players){
			if (id.compareTo(player.getId()) == 0){
				return player;
			}
		}
		return null;
	}

	public List<Game> getAllGames() {
		return gameDao.getAllGames();
	}
	
	public Player getPlayerByName(String name) {
		if (name == null) return null;
		for (Player player: this.players){
			if (name.equals(player.getName())){
				return player;
			}
		}
		return null;
	}

	public List<Game> loadAndProcessGamesFromFile(File file){
		List<GamePO> gamePOs = loadGamesFromFile(file);
		if (gamePOs == null) return null;
		List<Game> games = mapFromGamePO(gamePOs);
		if (games == null) return null;
		processGame(games);
		saveGames(games);
		getWinPercent();
		return games;
	}
	
	public synchronized WinPercent getWinPercent(){
		WinPercent winPercent = PointUtils.getWinPercent();
		if (winPercent == null){
			winPercent = new WinPercent();
			for (Game game: getAllGames()){
				WinPercentDetail detail = winPercent.getWinPercentDetail(game.getPlayerNum());
				if (detail == null){
					detail = new WinPercentDetail(game.getPlayerNum());
					winPercent.addWinPercentDetail(detail);
				}
				detail.update(game.getResult());
			}
			PointUtils.setWinPercent(winPercent);
		}
		return winPercent;
	}

	/**
	 * Loads games from a file and returns a list of GamePO
	 * @param file the file that contains the game data
	 * @return a list of GamePO
	 */
	public List<GamePO> loadGamesFromFile(File file) {
		return GameLoader.loadFromFile(file);
	}

	/**
	 * Saves all the games
	 * @param games a list of games to be saved
	 */
	private void saveGames(List<Game> games){
		for (Game game: games){
			gameDao.saveGame(game);
		}
	}
	
	/**
	 * Maps a list of GamePOs to a list of Games
	 * @param gamePOs
	 * @return
	 */
	private List<Game> mapFromGamePO(List<GamePO> gamePOs){
		
		List<Game> games = new ArrayList<Game>();
		for (GamePO gamePO: gamePOs){
			Game game = new Game(gamePO);
			PlayerDetail playerDetail = gamePO.getPlayerDetail();
			game.setZhugong(getOrCreatePlayerByName(playerDetail.getZhugong()));
			List<Player> zhongchenList = mapPlayerFromString(playerDetail.getZhongchen());
			List<Player> neijianList = mapPlayerFromString(playerDetail.getNeijian());
			List<Player> fanzeiList = mapPlayerFromString(playerDetail.getFanzei());
			game.setZhongchen(zhongchenList);
			game.setNeijian(neijianList);
			game.setFanzei(fanzeiList);
			games.add(game);
		}
		return games;
	}
	
	/**
	 * Maps a list of player name strings to a list of Players
	 * @param names a list of player name strings
	 * @return a list of Players
	 */
	private List<Player> mapPlayerFromString(List<String> names){
		List<Player> players = new ArrayList<Player>();
		for(String name: names){
			players.add(getOrCreatePlayerByName(name));
		}
		return players;
	}
	
	/**
	 * Processes the games
	 * @param games a list of Games to be processed
	 */
	private void processGame(List<Game> games){
		for (Game game: games){
			processGame(game);
		}
	}
	
	/** 
	 * Processed a game
	 * @param game the game to be processed
	 */
	private void processGame(Game game){
		if (ThreeNationKillConstants.ZHUGONG_WIN.equals(game.getResult())){
			updatePlayer(game, game.getZhugong(), Role.ZHU_GONG, true);
			updatePlayer(game, game.getZhongchen(), Role.ZHONG_CHEN, true);
			updatePlayer(game, game.getNeijian(), Role.NEI_JIAN, false);
			updatePlayer(game, game.getFanzei(), Role.FAN_ZEI, false);
		} else if (ThreeNationKillConstants.NEIJIAN_WIN.equals(game.getResult())){
			updatePlayer(game, game.getZhugong(), Role.ZHU_GONG, false);
			updatePlayer(game, game.getZhongchen(), Role.ZHONG_CHEN, false);
			updatePlayer(game, game.getNeijian(), Role.NEI_JIAN, true);
			updatePlayer(game, game.getFanzei(), Role.FAN_ZEI, false);
		} else if (ThreeNationKillConstants.FANZEI_WIN.equals(game.getResult())){
			updatePlayer(game, game.getZhugong(), Role.ZHU_GONG, false);
			updatePlayer(game, game.getZhongchen(), Role.ZHONG_CHEN, false);
			updatePlayer(game, game.getNeijian(), Role.NEI_JIAN, false);
			updatePlayer(game, game.getFanzei(), Role.FAN_ZEI, true);
		} else{
			logger.info("Unknown game result");
		}
	}
	
	/**
	 * Updates a group of players' statistics
	 * These players have the same role
	 * @param game the Game which the player played
	 * @param players a list of players having the same role
	 * @param role role the role played in the game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @param win whether the player wins
	 */
	private void updatePlayer(Game game, List<Player> players, Role role, boolean win){
		for (Player player: players){
			updatePlayer(game, player, role, win);
		}
	}
	
	/**
	 * Updates a player's statistics
	 * These players have the same role
	 * @param game the Game which the player played
	 * @param player the player to be updated
	 * @param role role the role played in the game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @param win whether the player wins
	 */
	private void updatePlayer(Game game, Player player, Role role, boolean win){
		//Update game stat
		GameStat gameStat = player.getGameStat(game.getPlayerNum());
		if (gameStat == null){
			gameStat = new GameStat(game.getPlayerNum());
			player.addGameStat(gameStat);
		}
		gameStat.updateByRole(role, win);
	}
	
	/**
	 * Returns a player by the name
	 * if such player with the name doesn't exist, creates one, 
	 * and adds it to players list
	 * @param name the name of the player
	 * @return the Player by the name
	 */
	private Player getOrCreatePlayerByName(String name){
		Player player = getPlayerByName(name);
		if (player == null){
			player = new Player(name);
			players.add(player);
		}
		return player;
	}
	
	/* =================== *
	 * Setters and getters *
	 * ====================*/
	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}

	public void setGameDao(GameDao gameDao) {
		this.gameDao = gameDao;
	}

}
