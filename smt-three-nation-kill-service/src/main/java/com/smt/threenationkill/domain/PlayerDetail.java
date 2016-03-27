package com.smt.threenationkill.domain;

import java.util.List;

/**
 * This class represents all the players involved in a game and their roles
 * @author Mingtao Sun
 */
public class PlayerDetail {
	/** Zhu Gong */
	private String zhugong;
	/** A list of Zhong Chen */
	private List<String> zhongchen;
	/** A list of Nei Jian */
	private List<String> neijian;
	/** A list of Fan Zei */
	private List<String> fanzei;
	
	/* =================== *
	 * Setters and getters *
	 * ====================*/
	public String getZhugong() {
		return zhugong;
	}
	public void setZhugong(String zhugong) {
		this.zhugong = zhugong;
	}
	public List<String> getZhongchen() {
		return zhongchen;
	}
	public void setZhongchen(List<String> zhongchen) {
		this.zhongchen = zhongchen;
	}
	public List<String> getNeijian() {
		return neijian;
	}
	public void setNeijian(List<String> neijian) {
		this.neijian = neijian;
	}
	public List<String> getFanzei() {
		return fanzei;
	}
	public void setFanzei(List<String> fanzei) {
		this.fanzei = fanzei;
	}
}
