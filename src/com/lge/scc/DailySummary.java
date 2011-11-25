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
        contentsHeader = "��¥: " + df.format(date.getTime()) + '\n';
        contents.append(contentsHeader + '\n');
        contents.append("��ħ: " + dailyRecord.breakfast + '\n');
        contents.append("����: " + dailyRecord.lunch + '\n');
        contents.append("����: " + dailyRecord.dinner + '\n');
        contents.append("��Ÿ: " + dailyRecord.extraMeal + '\n' + '\n');
        contents.append("����Ʈ: " + dailyRecord.squat + '\n');
        contents.append("���帮��Ʈ: " + dailyRecord.deadLift + '\n');
        contents.append("��ġ������: " + dailyRecord.benchPress + '\n');
        
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
