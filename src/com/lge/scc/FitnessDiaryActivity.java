package com.lge.scc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FitnessDiaryActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    
	Button foodInputButton;
	Button exerciseInputButton;
	Button updateButton;
	
	private Calendar date = Calendar.getInstance();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitstart);
        
        foodInputButton = (Button)findViewById(R.id.food);
        exerciseInputButton = (Button)findViewById(R.id.exercise);
        updateButton = (Button)findViewById(R.id.updateButton);
        
        foodInputButton.setOnClickListener(this);
        exerciseInputButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		if (v == foodInputButton) {
			Intent in = new Intent(this, DailyMealSummary.class);
			in.putExtra("date", date);
			startActivity(in) ;
		}
		else if (v == exerciseInputButton) {
			Intent in = new Intent(this, DailyExerciseSummary.class);
			in.putExtra("date", date);
			startActivity(in) ;
		}
		else if (v == updateButton) {
			DailyRecord dailyRecord = ((FitnessApplication)getApplication()).dailyRecord;
			
			Intent intent = getIntent();
	        
			if (intent != null) {
	        	date = (Calendar) intent.getSerializableExtra("date");
	        }
			
			DailyRecord drCopied = new DailyRecord();
			
			try {
				drCopied = (DailyRecord) dailyRecord.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Map<Calendar, DailyRecord> mapData = ((FitnessApplication)getApplication()).getRecords();
			
			mapData.put(date, drCopied);
			
			saveInDB(drCopied);
			
			initializeData(dailyRecord);
			
			Intent in = new Intent(this, GVCalendarActivity.class);
			startActivity(in) ;
		}
	}

	private void saveInDB(DailyRecord drCopied) {
		Contents foodData = new Contents(this);
		ContentValues values = new ContentValues();
		
		Date dateDB = date.getTime(); 
		SimpleDateFormat simpleDate = new SimpleDateFormat("yy-MM-dd");
		
		values.clear();
		values.put(Contents.C_DATE, simpleDate.format(dateDB));
		values.put(Contents.C_BREAKFAST, drCopied.breakfast);
		values.put(Contents.C_LUNCH, drCopied.lunch);
		values.put(Contents.C_DINNER, drCopied.dinner);
		values.put(Contents.C_EXTRAMEAL, drCopied.extraMeal);
		values.put(Contents.C_SQUAT, drCopied.squat);
		values.put(Contents.C_DEADLIFT, drCopied.deadLift);
		values.put(Contents.C_BENCHPRESS, drCopied.benchPress);
		
		foodData.insert(values);
		foodData.close();
	}

	private void initializeData(DailyRecord dr) {
		//initialize_drs();
		dr.breakfast="";
		dr.lunch="";
		dr.dinner="";
		dr.extraMeal="";
		dr.squat="";
		dr.deadLift="";
		dr.benchPress="";
	}  
    
}