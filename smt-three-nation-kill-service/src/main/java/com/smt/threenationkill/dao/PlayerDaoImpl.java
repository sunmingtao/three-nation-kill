package com.smt.threenationkill.dao;

import java.util.List;

import com.smt.threenationkill.domain.Player;

/**
 * This is the implementation of PlayerDao
 * @author Mingtao Sun
 */
public class PlayerDaoImpl implements PlayerDao {

	/** The mock database */
	private ThreeNationKillDB db;
	
	public List<Player> getAllPlayers() {
		return db.getAllPlayers();
	}
	
	/** The mock database */
	public void setDb(ThreeNationKillDB db) {
		this.db = db;
	}

}
