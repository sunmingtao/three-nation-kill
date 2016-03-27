package com.smt.threenationkill.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.smt.threenationkill.enums.Role;
import com.smt.threenationkill.util.CalculateUtils;
import com.smt.threenationkill.util.PointUtils;

/**
 * This class represents a Player
 * @author Mingtao Sun
 */
public class Player implements ByRole{
	/** ID of the player, serves as Primary Key */
	private Long id;
	/** The name of the player */
	private String name;
	/** A list of game statistics */
	private List<GameStat> gameStats;
	
	/**
	 * Constructor
	 */
	public Player(){
		gameStats = new ArrayList<GameStat>();
	}
	
	/**
	 * Constructor
	 * @param name the name of the player
	 */
	public Player(String name){
		this.name = name;
		gameStats = new ArrayList<GameStat>();
	}
	
	/**
	 * Returns the number of games played by the role 
	 * @param role the role played in a game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @return the number of games played by the role
	 */
	public int getGameNumPlayedByRole(Role role){
		int total = 0;
		for (GameStat gameStat: this.gameStats){
			total += gameStat.getGameNumPlayedByRole(role);
		}
		return total;
	}
	
	/**
	 * Returns the number of games won by the role 
	 * @param role the role played in a game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @return the number of games won by the role
	 */
	public int getGameNumWonByRole(Role role){
		int total = 0;
		for (GameStat gameStat: this.gameStats){
			total += gameStat.getGameNumWonByRole(role);
		}
		return total;
	}
	
	/**
	 * Returns the total number of games played
	 * @return the total number of games played
	 */
	public int getTotalGameNumPlayed() {
		int total = 0;
		for (GameStat gameStat: this.gameStats){
			total += gameStat.getGameNumPlayed();
		}
		return total;
	}
	
	/**
	 * Returns the total number of games won
	 * @return the total number of games won
	 */
	public int getTotalNumWon() {
		int total = 0;
		for (GameStat gameStat: this.gameStats){
			total += gameStat.getGameNumWon();
		}
		return total;
	}
	
	/**
	 * Returns the total number of games lost
	 * @return the total number of games lost
	 */
	public int getTotalNumLost() {
		return getTotalGameNumPlayed() - getTotalNumWon();
	}
	
	/**
	 * Returns total points earned
	 * @return total points earned
	 */
	public BigDecimal getTotalPoints(){
		BigDecimal total = BigDecimal.ZERO;
		for (GameStat gameStat: gameStats){
			total = total.add(gameStat.getTotalPoints());
		}
		return total;
	}
	
	/**
	 * Returns points earned by the role 
	 * @param role the role played in the game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @return points earned by the role
	 */
	public BigDecimal getPointsByRole(Role role){
		BigDecimal total = BigDecimal.ZERO;
		for (GameStat gameStat: gameStats){
			if (PointUtils.isPlayerNumValid(gameStat.getPlayerNum())){
				total = total.add(gameStat.getPointsByRole(role));	
			}
		}
		return total;
	}
	
	/**
	 * Returns the win percentage
	 * @return the win percentage
	 */
	public BigDecimal getWinRate(){
		return CalculateUtils.percent(getTotalNumWon(), getTotalGameNumPlayed());
	}
	
	/**
	 * Returns the win percentage by role  
	 * @param role the role played in the game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @return the win percentage by role
	 */
	public BigDecimal getWinRateByRole(Role role){
		int gameNumPlayed = getGameNumPlayedByRole(role);
		if (gameNumPlayed == 0){
			return null;
		}
		return CalculateUtils.percent(getGameNumWonByRole(role), getGameNumPlayedByRole(role));
	}
	
	/**
	 * Returns the GameStat based on the number of players in a game
	 * @param playerNum the number of players in a game
	 * @return the GameStat based on the number of players in a game
	 */
	public GameStat getGameStat(int playerNum){
		for (GameStat gameStat: this.gameStats){
			if (gameStat.getPlayerNum() == playerNum){
				return gameStat;
			}
		}
		return null;
	}
	
	/**
	 * Adds a GameStat to gameStats list
	 * @param gameStat the GameStat to be added
	 */
	public void addGameStat(GameStat gameStat){
		if (gameStat != null){
			gameStats.add(gameStat);	
		}
	}
	
	/* =================== *
	 * Setters and getters *
	 * ====================*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<GameStat> getGameStats() {
		return gameStats;
	}
	
	public String toString(){
		return getName();
	}
	
	public String print(){
		String result = getId() + " " + getName() + " Total:"+getTotalGameNumPlayed()+ " "+
		                getTotalNumWon()+"-"+getTotalNumLost()+"\n";
		return result;
	}
}
