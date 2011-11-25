package com.lge.scc;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class DailySummary extends Activity implements OnClickListener{

	private Button inputModifyButton;
	private String contentsHeader;
	private StringBuffer contents = new StringBuffer();
	private DailyRecord dailyRecord;
	private RatingBar rating;
	
	private Calendar date = Calendar.getInstance();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_summary);
        
        inputModifyButton = (Button)findViewById(R.id.modifyContentsButton);
        inputModifyButton.setOnClickListener(this);
        
        rating = (RatingBar)findViewById(R.id.ratingBar1);
        
        TextView tv = (TextView) findViewById(R.id.contentsSummary);
        
        FitnessApplication applicationData = (FitnessApplication)getApplication();
        
        Intent intent = getIntent();
        if (intent != null) {
        	date = (Calendar) intent.getSerializableExtra("date");
        }
        
        applicationData.date = date;
        dailyRecord = applicationData.getRecords().get(date);
        
        if(null != dailyRecord)
        	fillContents();
        
        Calendar now = Calendar.getInstance();
    	FitnessApplication applicationObject = (FitnessApplication)getApplication();
		int selectedMonth = applicationObject.getSelectedMonth();
           
        if(applicationData.getRecords().containsKey(date))
        	if(now.getTime().getMonth() == selectedMonth)
				tv.setText(contents);
        else
        	tv.setText("no Data");
	}

	private void fillContents() {
		DateFormat df = DateFormat.getDateInstance();
        contentsHeader = "날짜: " + df.format(date.getTime()) + '\n';
        contents.append(contentsHeader + '\n');
        contents.append("아침: " + dailyRecord.breakfast + '\n');
        contents.append("점심: " + dailyRecord.lunch + '\n');
        contents.append("저녁: " + dailyRecord.dinner + '\n');
        contents.append("기타: " + dailyRecord.extraMeal + '\n' + '\n');
        contents.append("스쿼트: " + dailyRecord.squat + '\n');
        contents.append("데드리프트: " + dailyRecord.deadLift + '\n');
        contents.append("벤치프레스: " + dailyRecord.benchPress + '\n');
        
        if(null != dailyRecord.squat || null != dailyRecord.deadLift || null != dailyRecord.benchPress)
        	if(null == dailyRecord.extraMeal)
        		rating.setRating(5.0f);
        	else
        		rating.setRating(3.0f);
        	
	}

	@Override
	public void onClick(View v) {
		Intent in = new Intent(this, FitnessDiaryActivity.class);
		in.putExtra("date", date);
		startActivity(in) ;
	}
}
