package com.smt.threenationkill.dao;

import java.util.List;

import com.smt.threenationkill.domain.Game;

/**
 * This class contains methods to retrieve and save games
 * @author Mingtao Sun
 */
public interface GameDao {
	/**
	 * Returns all games
	 * @return all games
	 */
	public List<Game> getAllGames();
	/**
	 * Saves a game
	 * @param game the Game to be saved
	 */
	public void saveGame(Game game);
}
