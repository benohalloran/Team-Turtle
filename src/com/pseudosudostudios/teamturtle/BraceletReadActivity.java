package com.pseudosudostudios.teamturtle;

import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BraceletReadActivity extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_bracelet_read, null);
		TextView tipOut = (TextView) v.findViewById(R.id.bracelet_tip_out);
		tipOut.setText(getRandomStressTip());
		return v;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (menu == null)
			inflater.inflate(R.menu.menu, menu);
		menu.removeItem(R.id.menu_stress);
	}

	private String getRandomStressTip() {
		String[] allTips = getResources().getStringArray(R.array.tips);
		Random rand = new Random();
		return allTips[rand.nextInt(allTips.length)];
	}
}
