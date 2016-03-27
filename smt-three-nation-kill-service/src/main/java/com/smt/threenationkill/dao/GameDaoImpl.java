package com.smt.threenationkill.dao;

import java.util.List;

import com.smt.threenationkill.domain.Game;

/**
 * This class is the implementation of GameDao
 * @author Mingtao Sun
 */
public class GameDaoImpl implements GameDao {
	/** The mock database */
	private ThreeNationKillDB db;
	
	public List<Game> getAllGames() {
		return db.getAllGames();
	}

	public void saveGame(Game game) {
		db.saveGame(game);
	}
	
	/* =================== *
	 * Setters and getters *
	 * ====================*/	
	public void setDb(ThreeNationKillDB db) {
		this.db = db;
	}
}
