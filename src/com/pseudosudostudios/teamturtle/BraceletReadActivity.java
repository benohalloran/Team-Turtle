package com.pseudosudostudios.teamturtle;

import java.util.Random;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class BraceletReadActivity extends ActionBarActivity {
	// TODO stress prevention tips
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bracelet_read);
		TextView tipOut = (TextView) findViewById(R.id.bracelet_tip_out);
		tipOut.setText(getRandomStressTip());
	}

	private String getRandomStressTip() {
		String[] allTips = getResources().getStringArray(R.array.tips);
		Random rand = new Random();
		return allTips[rand.nextInt(allTips.length)];
	}
}
