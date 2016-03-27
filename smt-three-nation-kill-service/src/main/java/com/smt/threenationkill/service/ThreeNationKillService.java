package com.smt.threenationkill.service;

import java.io.File;
import java.util.List;

import com.smt.threenationkill.domain.Game;
import com.smt.threenationkill.domain.Player;
import com.smt.threenationkill.domain.WinPercent;

/**
 * This class provides methods to process, load, and save games.
 * @author Mingtao Sun
 */
public interface ThreeNationKillService {
	/**
	 * Returns all players
	 * @return all players
	 */
	public List<Player> getAllPlayers();
	/**
	 * Returns the player by Id
	 * @param id the id
	 * @return the player by Id
	 */
	public Player getPlayerById(Long id);
	/**
	 * Returns all games
	 * @return all games
	 */
	public List<Game> getAllGames();
	/**
	 * Returns the player by name
	 * @param name the player's name
	 * @return the player by name
	 */
	public Player getPlayerByName(String name);
	/**
	 * Processes the game data on a file, saves the games
	 * and return a list of games
	 * @param file the file containing game data
	 * @return a list of games
	 */
	public List<Game> loadAndProcessGamesFromFile(File file);
	/**
	 * Returns the league's average win percentage
	 * @return the league's average win percentage
	 */
	public WinPercent getWinPercent();
}
