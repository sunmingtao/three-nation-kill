package com.smt.threenationkill.domain;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * This class is the business object of GamePO
 * @author Mingtao Sun
 */
public class Game extends GamePO{
	/** Zhu Gong */
	private Player zhugong;
	/** A list of Zhong Chen */
	private List<Player> zhongchen;
	/** A list of Nei Jian */
	private List<Player> neijian;
	/** A list of Fan Zei */
	private List<Player> fanzei;
	/**
	 * Constructor
	 */
	public Game(){}
	
	/**
	 * Constructor
	 * @param gamePO the GamePO 
	 */
	public Game(GamePO gamePO){
		setId(gamePO.getId());
		setDate(gamePO.getDate());
		setPlayerNum(gamePO.getPlayerNum());
		setResult(gamePO.getResult());
	}
	
	/* =================== *
	 * Setters and getters *
	 * ====================*/
	public Player getZhugong() {
		return zhugong;
	}
	public void setZhugong(Player zhugong) {
		this.zhugong = zhugong;
	}
	public List<Player> getZhongchen() {
		return zhongchen;
	}
	public void setZhongchen(List<Player> zhongchen) {
		this.zhongchen = zhongchen;
	}
	public List<Player> getNeijian() {
		return neijian;
	}
	public void setNeijian(List<Player> neijian) {
		this.neijian = neijian;
	}
	public List<Player> getFanzei() {
		return fanzei;
	}
	public void setFanzei(List<Player> fanzei) {
		this.fanzei = fanzei;
	}
	public String toString(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String result = "";
		result = getId() + " " + sf.format(getDate()) + " " 
				+ getPlayerNum() + " " + getZhugong()+ " ";
		if (getZhongchen() != null){
			for(Player player: getZhongchen()){
				result = result+player+"/";
			}
			result = result.substring(0, result.length()-1) + " ";
		}
		if (getNeijian() != null){
			for(Player player: getNeijian()){
				result = result+player+"/";
			}
			result = result.substring(0, result.length()-1) + " ";
		}
		if (getFanzei() != null){
			for(Player player: getFanzei()){
				result = result+player+"/";
			}
			result = result.substring(0, result.length()-1) + " " +getResult(); 
		}
		return result;
	}
}
