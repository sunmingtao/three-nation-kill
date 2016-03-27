package com.smt.threenationkill.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.smt.threenationkill.util.CalculateUtils;

/**
 * This class represents the league's average win percentage
 * It has a list of WinPercentDetail objects, which represents 
 * league's average win percentage by the number of players in a game.
 * @author Mingtao Sun
 */
public class WinPercent {
	/**
	 * A list of WinPercentDetail objects, which represents 
	 * league's average win percentage by the number of players in a game.
	 */
	private List<WinPercentDetail> winPercentDetails;
	
	/**
	 * Constructor
	 */
	public WinPercent(){
		winPercentDetails = new ArrayList<WinPercentDetail>();
	}
	
	/**
	 * Adds a WinPercentDetail to the winPercentDetails list
	 * @param detail the WinPercentDetail to add
	 */
	public void addWinPercentDetail(WinPercentDetail detail){
		winPercentDetails.add(detail);
	}
	
	/**
	 * Returns a WinPercentDetail by the number of players in a game
	 * @param playerNum the number of players in a game
	 * @return a WinPercentDetail by the number of players in a game
	 *         null if such number of players cannot be matched in the 
	 *         winPercentDetails list
	 */
	public WinPercentDetail getWinPercentDetail(int playerNum){
		for (WinPercentDetail w: winPercentDetails){
			if (w.getPlayerNum() == playerNum){
				return w;
			}
		}
		return null;
	}
	
	/**
	 * Returns the number of games played
	 * @return the number of games played
	 */
	public int getNumPlayed() {
		int total = 0;
		for (WinPercentDetail w: winPercentDetails){
			total += w.getNumPlayed();
		}
		return total;
	}
	/**
	 * Returns the number of games won as Zhu Gong
	 * @return the number of games won as Zhu Gong
	 */
	public int getZhugongWonNum() {
		int total = 0;
		for (WinPercentDetail w: winPercentDetails){
			total += w.getZhugongWonNum();
		}
		return total;
	}
	/**
	 * Returns the number of games won as Fan Zei
	 * @return the number of games won as Fan Zei
	 */
	public int getFanzeiWonNum() {
		int total = 0;
		for (WinPercentDetail w: winPercentDetails){
			total += w.getFanzeiWonNum();
		}
		return total;
	}
	/**
	 * Returns the number of games won as Nei Jian 
	 * @return the number of games won as Nei Jian
	 */
	public int getNeijianWonNum() {
		int total = 0;
		for (WinPercentDetail w: winPercentDetails){
			total += w.getNeijianWonNum();
		}
		return total;
	}
	
	/**
	 * Returns the win percentage as Zhu Gong
	 * @return the win percentage as Zhu Gong
	 */
	public BigDecimal getZhugongWinPercent(){
		return CalculateUtils.percent(getZhugongWonNum(), getNumPlayed());
	}
	/**
	 * Returns the win percentage as Fan Zei
	 * @return the win percentage as Fan Zei
	 */
	public BigDecimal getFanzeiWinPercent(){
		return CalculateUtils.percent(getFanzeiWonNum(), getNumPlayed());
	}
	/**
	 * Returns the win percentage as Nei Jian
	 * @return the win percentage as Nei Jian
	 */
	public BigDecimal getNeijianWinPercent(){
		return CalculateUtils.percent(getNeijianWonNum(), getNumPlayed());
	}
	
	/* =================== *
	 * Setters and getters *
	 * ====================*/
	public List<WinPercentDetail> getWinPercentDetails() {
		//sort the detail by player num in asending order
		Collections.sort(winPercentDetails);
		return winPercentDetails;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Number played="+getNumPlayed()+"\n");
		sb.append("Zhu Gong Won num="+getZhugongWonNum());
		sb.append(" Fan Zei Won num="+getFanzeiWonNum());
		sb.append(" Nei Jian Won num="+getNeijianWonNum()+"\n");
		sb.append("Zhu Gong Won percent="+getZhugongWinPercent());
		sb.append(" Fan Zei Won percent="+getFanzeiWinPercent());
		sb.append(" Nei Jian Won percent="+getNeijianWinPercent());
		sb.append("\nDetails\n==============\n");
		for (WinPercentDetail detail: winPercentDetails){
			sb.append(detail.toString());
		}
		return sb.toString();
	}
		
}
