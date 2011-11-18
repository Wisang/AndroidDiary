package com.lge.scc;

import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DailyMealSummary extends Activity implements OnClickListener{
	private static final int BREAKFAST = 0;
	private static final int LUNCH = 1;
	private static final int DINNER = 2;
	private static final int EXTRAMEALS = 3;
	
	/** Called when the activity is first created. */
	
	private Button breakfastContentsButton;
	private Button lunchContentsButton;
	private Button dinnerContentsButton;
	private Button extraMealContentsButton;
	
	private TextView breakfastRetrieval;
	private TextView lunchRetrieval;
	private TextView dinnerRetrieval;
	private TextView extraMealsRetrieval;
	
	private Set<String> breakfast = new TreeSet<String>();
	private Set<String> lunch = new TreeSet<String>();
	private Set<String> dinner = new TreeSet<String>();
	private Set<String> extraMeal = new TreeSet<String>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_daily);
        
        breakfastContentsButton = (Button)findViewById(R.id.breakfastContentsButton);
        lunchContentsButton = (Button)findViewById(R.id.lunchContentsButton);
        dinnerContentsButton = (Button)findViewById(R.id.dinnerContentsButton);
        extraMealContentsButton = (Button)findViewById(R.id.extraMealsContentsButton);
        
        breakfastContentsButton.setOnClickListener(this);
        lunchContentsButton.setOnClickListener(this);
        dinnerContentsButton.setOnClickListener(this);
        extraMealContentsButton.setOnClickListener(this);
        
        breakfastRetrieval = (TextView)findViewById(R.id.morningMealsRetrieval);
        lunchRetrieval = (TextView)findViewById(R.id.lunchMealsRetrieval); 
        dinnerRetrieval = (TextView)findViewById(R.id.dinnerMealsRetrieval);
    	extraMealsRetrieval = (TextView)findViewById(R.id.extraMealsRetrieval);
    }

	@Override
	public void onClick(View v) {
		
		if(v == breakfastContentsButton)
		{
			Intent in = new Intent(this, MealContents.class);
			startActivityForResult(in, BREAKFAST) ;
		}
		
		else if(v == lunchContentsButton)
		{
			Intent in = new Intent(this, MealContents.class);
			startActivityForResult(in, LUNCH) ;
		}
		
		else if(v == dinnerContentsButton)
		{
			Intent in = new Intent(this, MealContents.class);
			startActivityForResult(in, DINNER) ;
		}
		
		else if(v == extraMealContentsButton)
		{
			Intent in = new Intent(this, MealContents.class);
			startActivityForResult(in, EXTRAMEALS) ;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;

		/*
		FoodData foodData = new FoodData(this);
		ContentValues values = new ContentValues();
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date date = calendar.getTime(); 
		SimpleDateFormat simpleDate = new SimpleDateFormat("yy-MM-dd");
		
		values.clear();
		values.put(FoodData.C_DATE, simpleDate.format(date));
		values.put(FoodData.C_WHEN, requestCode);
		values.put(FoodData.C_NAME, data.getStringExtra("name"));

		Log.i("meal", values.getAsString(FoodData.C_DATE));
		Log.i("meal", values.getAsString(FoodData.C_WHEN));
		Log.i("meal", values.getAsString(FoodData.C_NAME));
		
		foodData.insert(values);
		foodData.close();
		*/
		
		FitnessApplication applicationData = (FitnessApplication)getApplication();
				
		if (requestCode == BREAKFAST) {
			String name = data.getStringExtra("name");
			if (!breakfast.contains(name)) {
				breakfast.add(name);
				applicationData.dailyRecord.breakfast = name;
				updateMeals(breakfastRetrieval, breakfast);
			}
		}
		
		else if (requestCode == LUNCH) {
			String name = data.getStringExtra("name");
			if (!lunch.contains(name)) {
				lunch.add(name);
				applicationData.dailyRecord.lunch = name;
				updateMeals(lunchRetrieval, lunch);
			}
		}
		
		else if (requestCode == DINNER) {
			String name = data.getStringExtra("name");
			if (!dinner.contains(name)) {
				dinner.add(name);
				applicationData.dailyRecord.dinner = name;
				updateMeals(dinnerRetrieval, dinner);
			}
		}
		
		else if (requestCode == EXTRAMEALS) {
			String name = data.getStringExtra("name");
			if (!extraMeal.contains(name)) {
				extraMeal.add(name);
				applicationData.dailyRecord.extraMeal = name;
				updateMeals(extraMealsRetrieval, extraMeal);
			}
		}
	}

	/*
	@Override
	protected void onResume() {
		
		FoodData foodData = new FoodData(this);

		super.onResume();
		
		try {				
			Cursor cursor = foodData.getMeals();
			
			try {
				while(cursor.moveToNext()) {
					int meals = cursor.getInt(cursor.getColumnIndex(FoodData.C_WHEN));
					
					if (meals == BREAKFAST)
						breakfast.add(cursor.getString(cursor.getColumnIndex(FoodData.C_NAME)));
					else if (meals == LUNCH)
						lunch.add(cursor.getString(cursor.getColumnIndex(FoodData.C_NAME)));
					else if (meals == DINNER)
						dinner.add(cursor.getString(cursor.getColumnIndex(FoodData.C_NAME)));
					else if (meals == EXTRAMEALS)
						extraMeal.add(cursor.getString(cursor.getColumnIndex(FoodData.C_NAME)));
				}
			} finally {
				cursor.close();
			}
			
			updateMeals(breakfastRetrieval, breakfast);
			updateMeals(lunchRetrieval, lunch);
			updateMeals(dinnerRetrieval, dinner);
			updateMeals(extraMealsRetrieval, extraMeal);
		} finally {		
			foodData.close();
		}
		
	}
	*/
	
	private void updateMeals(TextView textView,	Set<String> meals) {
		textView.setText(meals.toString());
	}  
}
