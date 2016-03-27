package com.smt.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import junit.framework.TestCase;

public class SimpleTest extends TestCase {
	
	String fileName = "src/main/resources/test.txt";
	
	public void testChineseCharacter(){
		String a = "ÀÏËï";
		String b = "ÀÏËï";
		assertTrue(a.equals(b));
	}
	
	public void testCalander(){
		Calendar c = new GregorianCalendar(2010, 8, 4);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println(sf.format(c.getTime()));
		try {
			Date date = sf.parse("2010/12/30");
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testReadFile() throws Exception{
		BufferedReader bufferedReader 
			= new BufferedReader(new FileReader(fileName));
		String line = null;
        
        while ((line = bufferedReader.readLine()) != null) {
            //Process the data, here we just print it out
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()){
            	System.out.println(st.nextToken());
            }
        }
	}
	
	public void testMax(){
		int a = 2;
		int b = 3;
		assertTrue(max(a, b) == b);
	}
	private int max(int a, int b){
		int r[]={a,b};
		return r[(a-b)>>>31];
	} 
	
}
