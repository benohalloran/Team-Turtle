package com.pseudosudostudios.teamturtle;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class BraceletReadActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bracelet_read);
		TextView tipOut = (TextView) findViewById(R.id.bracelet_tip_out);
		tipOut.setText(getRandomStressTip());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		menu.removeItem(R.id.menu_stress);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent launchMainForAdd = new Intent(this, MainScreen.class);
		launchMainForAdd.putExtra(MainScreen.ADD_KEY, true);
		startActivity(launchMainForAdd);
		return true;
	}

	private String getRandomStressTip() {
		String[] allTips = getResources().getStringArray(R.array.tips);
		Random rand = new Random();
		return allTips[rand.nextInt(allTips.length)];
	}
}
