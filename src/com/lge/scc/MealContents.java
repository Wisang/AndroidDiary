package com.lge.scc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MealContents extends Activity implements OnClickListener{
	Button saveMealContents;
	private EditText mContentsText;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_meal);
        
        mContentsText = (EditText)findViewById(R.id.TextMealContents);
        saveMealContents = (Button)findViewById(R.id.mealContentsSaveButton);
        saveMealContents.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		Intent data = new Intent();
		data.putExtra("name", mContentsText.getText().toString());
		setResult(RESULT_OK, data);
		finish();
	}
}
