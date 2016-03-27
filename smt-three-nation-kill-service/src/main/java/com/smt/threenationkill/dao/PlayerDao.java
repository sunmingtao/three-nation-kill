package com.smt.threenationkill.dao;

import java.util.List;

import com.smt.threenationkill.domain.Player;

/**
 * This class contains methods to handle Player
 * @author Mingtao Sun
 */
public interface PlayerDao {
	/**
	 * Returns all players
	 * @return all players
	 */
	public List<Player> getAllPlayers();
}
