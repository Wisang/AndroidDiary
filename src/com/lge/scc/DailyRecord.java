package com.lge.scc;


public class DailyRecord implements Cloneable {
	String breakfast;
	String lunch;
	String dinner;
	String extraMeal;
	
	String squat;
	String deadLift;
	String benchPress;
	
	public Object clone() throws CloneNotSupportedException {
		DailyRecord cl = (DailyRecord)super.clone();
		return cl;
	}
}
