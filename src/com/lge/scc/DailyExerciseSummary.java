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

public class DailyExerciseSummary extends Activity implements OnClickListener {

	private static final int SQUAT = 0;
	private static final int DEADLIFT = 1;
	private static final int BENCHPRESS = 2;

	/** Called when the activity is first created. */

	private Button squatContentsButton;
	private Button deadliftContentsButton;
	private Button benchpressContentsButton;

	private TextView squatRetrieval;
	private TextView deadliftRetrieval;
	private TextView benchpressRetrieval;

	private Set<String> squat = new TreeSet<String>();
	private Set<String> deadlift = new TreeSet<String>();
	private Set<String> benchpress = new TreeSet<String>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_daily);

		squatContentsButton = (Button) findViewById(R.id.squatButton);
		deadliftContentsButton = (Button) findViewById(R.id.deadLiftButton);
		benchpressContentsButton = (Button) findViewById(R.id.benchPressButton);

		squatContentsButton.setOnClickListener(this);
		deadliftContentsButton.setOnClickListener(this);
		benchpressContentsButton.setOnClickListener(this);

		squatRetrieval = (TextView) findViewById(R.id.squatRetrieval);
		deadliftRetrieval = (TextView) findViewById(R.id.deadLiftRetrieval);
		benchpressRetrieval = (TextView) findViewById(R.id.benchPressRetrieval);

	}

	@Override
	public void onClick(View v) {

		if (v == squatContentsButton) {
			Intent in = new Intent(this, ExerciseContents.class);
			startActivityForResult(in, SQUAT);
		}
		
		else if (v == deadliftContentsButton) {
			Intent in = new Intent(this, ExerciseContents.class);
			startActivityForResult(in, DEADLIFT);
		}
		
		else if (v == benchpressContentsButton) {
			Intent in = new Intent(this, ExerciseContents.class);
			startActivityForResult(in, BENCHPRESS);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;
		
		FitnessApplication applicationData = (FitnessApplication)getApplication();
		
		if (requestCode == SQUAT) {
			String name = data.getStringExtra("name");
			if (!squat.contains(name)) {
				squat.add(name);
				applicationData.dailyRecord.squat = name;
				updateExercise(squatRetrieval, squat);
			}
		}
		
		else if (requestCode == DEADLIFT) {
			String name = data.getStringExtra("name");
			if (!deadlift.contains(name)) {
				deadlift.add(name);
				applicationData.dailyRecord.deadLift = name;
				updateExercise(deadliftRetrieval, deadlift);
			}
		}
		
		else if (requestCode == BENCHPRESS) {
			String name = data.getStringExtra("name");
			if (!benchpress.contains(name)) {
				benchpress.add(name);
				applicationData.dailyRecord.benchPress = name;
				updateExercise(benchpressRetrieval, benchpress);
			}
		}
	}
		
	private void updateExercise(TextView textView, Set<String> exercise) {
		textView.setText(exercise.toString());
	}
}
