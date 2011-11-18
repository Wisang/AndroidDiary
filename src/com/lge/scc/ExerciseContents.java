package com.lge.scc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ExerciseContents extends Activity implements OnClickListener {
	Button saveExerciseContents;
	private EditText mTextWeight;
	private EditText mTextSet;
	private EditText mTextRepetition;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_exercise);
        
        mTextWeight = (EditText)findViewById(R.id.TextWeight);
        mTextSet = (EditText)findViewById(R.id.TextSet);
        mTextRepetition = (EditText)findViewById(R.id.TextRepetition);
        
        saveExerciseContents = (Button)findViewById(R.id.exerciseContentsSaveButton);
        saveExerciseContents.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		StringBuffer exerciseSummay = new StringBuffer();
		
		Intent data = new Intent();
		
		exerciseSummay.append(mTextWeight.getText().toString());
		exerciseSummay.append("kg ");
		exerciseSummay.append(mTextSet.getText().toString());
		exerciseSummay.append("¼¼Æ® ");
		exerciseSummay.append(mTextRepetition.getText().toString());
		exerciseSummay.append("È¸ ");

		data.putExtra("weight", mTextWeight.getText().toString());
		data.putExtra("set", mTextSet.getText().toString());
		data.putExtra("repeat", mTextRepetition.getText().toString());

		data.putExtra("name", exerciseSummay.toString());
		setResult(RESULT_OK, data);
		finish();
	}
}
