package com.smt.threenationkill.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.smt.threenationkill.constant.ThreeNationKillConstants;

/**
 * This class maps to a game record on a file
 * @author Mingtao Sun
 */
public class GamePO {
	/** ID of the game, serves as Primary Key */
	private Long id;
	/** The date of the game played */
	private Date date;
	/** The number of players */
	private int playerNum;
	/** Game result (zhugongsheng/fanzeisheng/neijiansheng */
	private String result;
	/** 
	 * This object contains information about all the 
	 * players and their roles 
	 */
	private PlayerDetail playerDetail;
	
	/**
	 * Returns the result to be displayed to the user
	 * @return the result to be displayed to the user
	 */
	public String getDisplayResult(){
		String result = null;
		if (ThreeNationKillConstants.ZHUGONG_WIN.equals(getResult())){
			result = "主公胜";
		} else if (ThreeNationKillConstants.FANZEI_WIN.equals(getResult())){
			result = "反贼胜";
		} else if (ThreeNationKillConstants.NEIJIAN_WIN.equals(getResult())){
			result = "内奸胜";
		}
		return result;
	}
	
	/**
	 * Checks whether this GamePO is valid game data
	 * @return true if this GamePO is valid game data
	 */
	public boolean validate(){
		//Check if any role is missing
		if (playerDetail.getZhugong() != null || playerDetail.getZhongchen().size() == 0 ||
				playerDetail.getFanzei().size() == 0 || playerDetail.getNeijian().size() == 0){
			//Check if total number of players matches player number read from the file 
			int totalNum = playerDetail.getZhongchen().size() + playerDetail.getFanzei().size() 
				+ playerDetail.getNeijian().size() + 1;
			if (totalNum != playerNum){
				return false;
			}
			//Check if there are any duplicate names
			Set<String> nameSet = new HashSet<String>();
			nameSet.add(playerDetail.getZhugong());
			for (String name: playerDetail.getZhongchen()){
				nameSet.add(name);
			}
			for (String name: playerDetail.getFanzei()){
				nameSet.add(name);
			}
			for (String name: playerDetail.getNeijian()){
				nameSet.add(name);
			}
			return playerNum == nameSet.size();
		}
		return false;
	}
	
	/* =================== *
	 * Setters and getters *
	 * ====================*/
	public PlayerDetail getPlayerDetail() {
		return playerDetail;
	}
	public void setPlayerDetail(PlayerDetail playerDetail) {
		this.playerDetail = playerDetail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String toString(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder sb = new StringBuilder(sf.format(getDate()) + " " + getPlayerNum() + " ");
		if (getPlayerDetail()!= null){
			sb.append(getPlayerDetail().getZhugong() + " ");
			for(String name: getPlayerDetail().getZhongchen()){
				sb.append(name+"/");
			}
			sb.deleteCharAt(sb.length()-1).append(" ");
			for(String name: getPlayerDetail().getNeijian()){
				sb.append(name+"/");
			}
			sb.deleteCharAt(sb.length()-1).append(" ");
			for(String name: getPlayerDetail().getFanzei()){
				sb.append(name+"/");
			}
			sb.deleteCharAt(sb.length()-1).append(" ").append(getResult());
		}
		return sb.toString();
	}
	
}
