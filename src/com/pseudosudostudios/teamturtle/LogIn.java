package com.pseudosudostudios.teamturtle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LogIn extends ActionBarActivity {
	// TODO disable rotate on this screen
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		Button logIn = (Button) findViewById(R.id.log_in_button);
		logIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(LogIn.this, MainScreen.class));
			}
		});
		new BackgroundTaskIO().execute(getApplication(), BackgroundTaskIO.READ);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
}
