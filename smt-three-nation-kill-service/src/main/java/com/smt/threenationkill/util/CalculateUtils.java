package com.smt.threenationkill.util;

import java.math.BigDecimal;

/**
 * This class contains utility methods to do math calculations on BigDecimal and Integer
 * @author Mingtao Sun
 */
public class CalculateUtils {
	
	/**
	 * Calculates num divided by total in percent form
	 * e.g. percent(5, 100)= 5.00
	 * @param num the number to divide
	 * @param total the number to be divided
	 * @return num divided by total in percent form
	 */
	public static BigDecimal percent(int num, int total){
		BigDecimal bNum = new BigDecimal(num).multiply(new BigDecimal("100"));
		return divide(bNum, total);
	}
	
	/**
	 * Calculates num divided by total3
	 * e.g. divide(5, 100)= 0.05
	 * @param num the number to divide
	 * @param total the number to be divided
	 * @return num divided by total
	 */
	public static BigDecimal divide(BigDecimal num, int total){
		if (total == 0){
			throw new IllegalArgumentException("Total cannot be ZERO when dividing");
		}
		BigDecimal bTotal = new BigDecimal(total);
		return num.divide(bTotal, 2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Calculates num multiply i
	 * @param num the number
	 * @param i the number
	 * @return num multiply i
	 */
	public static BigDecimal multiply(BigDecimal num, int i){
		return num.multiply(new BigDecimal(i)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * Calculates i multiply num
	 * @param i the number
	 * @param num the number
	 * @return i multiply num
	 */
	public static BigDecimal multiply(int i, BigDecimal num){
		return multiply(num, i);
	}
	
}
