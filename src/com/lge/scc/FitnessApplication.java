package com.lge.scc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.database.Cursor;
import android.util.Log;

public class FitnessApplication extends Application{
	
	Calendar date;
	DailyRecord dailyRecord;
	
	int selectedMonth;
	
	Map<Calendar, DailyRecord> dailyRecords;
	
	int getSelectedMonth()
	{
		return selectedMonth;
	}
	
	void setSelectedMonth(int month)
	{
		selectedMonth = month;
	}
	
	@Override
	public void onCreate() {
		dailyRecord = new DailyRecord();
		dailyRecords = new HashMap<Calendar, DailyRecord>();
		Calendar now = Calendar.getInstance();
		selectedMonth = now.getTime().getMonth();
		
		getDataFromDBToMap();
	}

	private void getDataFromDBToMap() {
		Contents foodData = new Contents(this);

		try {				
			Cursor cursor = foodData.getMeals();
			
			try {

				while(cursor.moveToNext()) {
					Calendar date = new GregorianCalendar();
					DailyRecord dailyRecord = new DailyRecord();

					String dateStr;
					
					dateStr = cursor.getString(cursor.getColumnIndex(Contents.C_DATE)); 
								         
			        try
			        {
			            SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");
			            date.setTime(formatter.parse(dateStr));
			        }
			        catch(ParseException e)
			        {
			            e.printStackTrace();
			        }
					
			        Log.i("date", dateStr); 
			       
			    
			        
					dailyRecord.breakfast = cursor.getString(cursor.getColumnIndex(Contents.C_BREAKFAST));
					dailyRecord.lunch = cursor.getString(cursor.getColumnIndex(Contents.C_LUNCH));
					dailyRecord.dinner = cursor.getString(cursor.getColumnIndex(Contents.C_DINNER));
					dailyRecord.extraMeal = cursor.getString(cursor.getColumnIndex(Contents.C_EXTRAMEAL));
					dailyRecord.squat = cursor.getString(cursor.getColumnIndex(Contents.C_SQUAT));
					dailyRecord.deadLift = cursor.getString(cursor.getColumnIndex(Contents.C_DEADLIFT));
					dailyRecord.benchPress = cursor.getString(cursor.getColumnIndex(Contents.C_BENCHPRESS));
					
					dailyRecords.put(date, dailyRecord);

					
				}
			} finally {
				cursor.close();
			}
			
		} finally {		
			foodData.close();
		}
	}
	
	public Map<Calendar, DailyRecord> getRecords() {
		return dailyRecords;
	}
}
