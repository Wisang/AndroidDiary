package com.lge.scc;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.Application;

public class FitnessApplication extends Application{
	
	Calendar date;
	DailyRecord dailyRecord;
	
	Map<Calendar, DailyRecord> dailyRecords;
	
	@Override
	public void onCreate() {
		dailyRecord = new DailyRecord();
		dailyRecords = new HashMap<Calendar, DailyRecord>();
	}
	
	public Map<Calendar, DailyRecord> getRecords() {
		return dailyRecords;
	}
}
