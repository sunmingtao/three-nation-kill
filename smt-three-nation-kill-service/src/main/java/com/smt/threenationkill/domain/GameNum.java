package com.smt.threenationkill.domain;

import com.smt.threenationkill.enums.Role;

/**
 * This class represents a set of game stats played by the same role
 * @author Mingtao Sun
 */
public class GameNum {
	/** Role played in the game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)*/
	private Role role;
	/** The number of games played */
	private int numPlayed;
	/** The number of games won */
	private int numWon;

	/**
	 * Constructor
	 * @param role the role played in the game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 */
	public GameNum(Role role) {
		super();
		if (role == null){
			throw new NullPointerException("Role is invalid:"+role);
		}
		this.role = role;
	}

	/**
	 * Updates the game stats
	 * @param win whether the game has been won
	 */
	public void update(boolean win) {
		numPlayed++;
		if (win){
			numWon++;
		}
	}
	
	/* =================== *
	 * Setters and getters *
	 * ====================*/
	public Role getRole() {
		return role;
	}
	public int getNumPlayed() {
		return numPlayed;
	}
	public int getNumWon() {
		return numWon;
	}

}
