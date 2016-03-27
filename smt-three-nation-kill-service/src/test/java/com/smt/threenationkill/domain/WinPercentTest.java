package com.smt.threenationkill.domain;

import junit.framework.TestCase;

public class WinPercentTest extends TestCase {
	public void testWinPercent(){
		WinPercentDetail w = new WinPercentDetail(100, 60, 30, 10);
		System.out.println(w);
	}
	
}
