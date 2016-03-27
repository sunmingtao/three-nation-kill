package com.smt.threenationkill.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.smt.threenationkill.enums.Result;
import com.smt.threenationkill.enums.Role;
import com.smt.threenationkill.util.CalculateUtils;
import com.smt.threenationkill.util.PointUtils;

/**
 * This class represents a game statistics by the number of players in a game
 * @author Mingtao Sun
 */
public class GameStat implements ByRole{
	/** ID as the primary key */
	private Long id;
	/** The number of players in a game */
	private int playerNum;
	/** A list of GameNums representing game stats played by the same role */
	private List<GameNum> gameNums = new ArrayList<GameNum>();
	
	/**
	 * Constructor
	 */
	public GameStat(){
		this(0);
	}
	
	/**
	 * Constructor
	 * @param playerNum the number of players in a game
	 */
	public GameStat(int playerNum){
		this.playerNum = playerNum;
		//Ensure getGameNumByRole() never returns null
		for (Role role: Role.values()){
			gameNums.add(new GameNum(role));
		}
	}
	
	/**
	 * Returns the GameNum by the role played in the game
	 * @param role the role played in the game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @return the GameNum by the role played in the game
	 */
	public GameNum getGameNumByRole(Role role){
		if (role == null){
			throw new NullPointerException("Role is invalid:"+role);
		}
		for (GameNum gameNum: gameNums){
			if (role == gameNum.getRole()){
				return gameNum;
			}
		}
		//Should never reach here
		return null;
	}
	
	/**
	 * Updates the statistics by role and whether win the game or not
	 * @param role the role played in the game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @param win whether win the game or not
	 */
	public void updateByRole(Role role, boolean win){
		getGameNumByRole(role).update(win);
	}
	
	/**
	 * Returns the number of games played by the role 
	 * @param role the role played in the game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @return the number of games played by the role
	 */
	public int getGameNumPlayedByRole(Role role){
		return getGameNumByRole(role).getNumPlayed();
	}
	
	/**
	 * Returns the number of games won by the role 
	 * @param role the role played in the game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @return the number of games won by the role
	 */
	public int getGameNumWonByRole(Role role){
		return getGameNumByRole(role).getNumWon();
	}
	
	/**
	 * Returns the number of games played
	 * @return the number of games played
	 */
	public int getGameNumPlayed() {
		int total = 0;
		for (GameNum gameNum: gameNums){
			total += gameNum.getNumPlayed();
		}
		return total;
	}
	
	/**
	 * Returns the number of games won
	 * @return the number of games won
	 */
	public int getGameNumWon() {
		int total = 0;
		for (GameNum gameNum: gameNums){
			total += gameNum.getNumWon();
		}
		return total;
	}
	
	/**
	 * Returns the number of games lost
	 * @return the number of games lost
	 */
	public int getGameNumLost() {
		return getGameNumPlayed() - getGameNumWon();
	}
	
	/**
	 * Returns the total points earned
	 * @return the total points earned
	 */
	public BigDecimal getTotalPoints(){
		BigDecimal total = BigDecimal.ZERO;
		for (Role role: Role.values()){
			total = total.add(getPointsByRole(role));
		}
		return total;
	}
	
	/**
	 * Returns the points earned by role
	 * @param role the role played in a game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @return the points earned by role
	 */
	public BigDecimal getPointsByRole(Role role){
		BigDecimal winGamePoints = CalculateUtils.multiply(PointUtils.earnPoints(role, playerNum, Result.WON), 
				getGameNumWonByRole(role));
		BigDecimal loseGamePoints = CalculateUtils.multiply(PointUtils.earnPoints(role, playerNum, Result.LOST), 
				getGameNumPlayedByRole(role) - getGameNumWonByRole(role));
		return winGamePoints.add(loseGamePoints);
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
	public int getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	
}
