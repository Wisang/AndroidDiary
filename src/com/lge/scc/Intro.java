package com.lge.scc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Intro extends Activity implements OnClickListener {
	
	Button startCalendarButton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);

		startCalendarButton = (Button) findViewById(R.id.startCalendar);
		
		startCalendarButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
					
			Intent in = new Intent(this, GVCalendarActivity.class);
			startActivityForResult(in, 0);
	}
}
