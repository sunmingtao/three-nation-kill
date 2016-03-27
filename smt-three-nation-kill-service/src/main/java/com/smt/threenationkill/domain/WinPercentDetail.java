package com.smt.threenationkill.domain;

import java.math.BigDecimal;

import com.smt.threenationkill.constant.ThreeNationKillConstants;
import com.smt.threenationkill.enums.Role;
import com.smt.threenationkill.util.CalculateUtils;

/**
 * This class represents the league's average win percentage by 
 * the number of players in a game.
 * @author Mingtao Sun
 */
public class WinPercentDetail implements Comparable<WinPercentDetail>{
	/** The number of players in a game */
	private int playerNum;
	/** The number of games played */
	private int numPlayed;
	/** The number of games won by Zhu Gong */
	private int zhugongWonNum;
	/** The number of games won by Fan Zei */
	private int fanzeiWonNum;
	/** The number of games won by Nei Jian */
	private int neijianWonNum;
	
	/**
	 * Constructor
	 * @param playerNum the number of players in a game
	 */
	public WinPercentDetail(int playerNum){
		this.playerNum = playerNum;
	}
	
	/**
	 * Constructor
	 * @param numPlayed the number of games played
	 * @param zhugongWonNum the number of games won by Zhu Gong
	 * @param fanzeiWonNum the number of games won by Fan Zei
	 * @param neijianWonNum the number of games won by Nei Jian
	 */
	public WinPercentDetail(int numPlayed, int zhugongWonNum, int fanzeiWonNum,
			int neijianWonNum) {
		super();
		this.numPlayed = numPlayed;
		this.zhugongWonNum = zhugongWonNum;
		this.fanzeiWonNum = fanzeiWonNum;
		this.neijianWonNum = neijianWonNum;
	}
	
	
	/**
	 * Updates the number played and win number based on the result
	 * @param result the game result (zhugongsheng/fanzeisheng/neijiansheng)
	 */
	public void update(String result) {
		numPlayed++;
		if (result.equals(ThreeNationKillConstants.ZHUGONG_WIN)){
			zhugongWonNum++;
		}else if(result.equals(ThreeNationKillConstants.FANZEI_WIN)){
			fanzeiWonNum++;
		}else if (result.equals(ThreeNationKillConstants.NEIJIAN_WIN)){
			neijianWonNum++;
		}else{
			throw new IllegalArgumentException("Game result is invalid:"+result);	
		}
	}
	/**
	 * Returns the league's average won percentage by the role
	 * @param role the role played in a game (Zhu Gong/Zhong Chen/Nei Jian/Fan Zei)
	 * @return the league's average won percentage by the role
	 */
	public BigDecimal getWonPercent(Role role){
		if (role == Role.ZHU_GONG 
			|| role == Role.ZHONG_CHEN){
			return getZhugongWonPercent();
		}else if(role == Role.FAN_ZEI){
			return getFanzeiWonPercent();
		}else if (role == Role.NEI_JIAN){
			return getNeijianWonPercent();
		}else{
			throw new IllegalArgumentException("Role is invalid:"+role);	
		}
	}
	
	/**
	 * Returns the win percentage as Zhu Gong
	 * @return the win percentage as Zhu Gong
	 */
	public BigDecimal getZhugongWonPercent(){
		return CalculateUtils.percent(getZhugongWonNum(), getNumPlayed());
	}
	/**
	 * Returns the win percentage as Fan Zei
	 * @return the win percentage as Fan Zei
	 */
	public BigDecimal getFanzeiWonPercent(){
		return CalculateUtils.percent(getFanzeiWonNum(), getNumPlayed());
	}
	/**
	 * Returns the win percentage as Nei Jian
	 * @return the win percentage as Nei Jian
	 */
	public BigDecimal getNeijianWonPercent(){
		return CalculateUtils.percent(getNeijianWonNum(), getNumPlayed());
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Player Num="+getPlayerNum()+"\n");
		sb.append("Number played="+getNumPlayed()+"\n");
		sb.append("Zhu Gong Won num="+getZhugongWonNum());
		sb.append(" Fan Zei Won num="+getFanzeiWonNum());
		sb.append(" Nei Jian Won num="+getNeijianWonNum()+"\n");
		sb.append("Zhu Gong Won percent="+getZhugongWonPercent());
		sb.append(" Fan Zei Won percent="+getFanzeiWonPercent());
		sb.append(" Nei Jian Won percent="+getNeijianWonPercent());
		return sb.toString();
	}
	public int compareTo(WinPercentDetail o) {
		return playerNum - o.playerNum;
	}
	/* =================== *
	 * Setters and getters *
	 * ====================*/
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	public int getPlayerNum() {
		return playerNum;
	}
	public int getNumPlayed() {
		return numPlayed;
	}
	public int getZhugongWonNum() {
		return zhugongWonNum;
	}
	public int getFanzeiWonNum() {
		return fanzeiWonNum;
	}
	public int getNeijianWonNum() {
		return neijianWonNum;
	}
	
	
}
