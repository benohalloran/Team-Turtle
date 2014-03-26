package com.pseudosudostudios.teamturtle;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

public class MainScreen extends FragmentActivity {
	FragmentAdapter adapt;
	List<Fragment> frags;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_frags);
		ViewPager pager = (ViewPager) findViewById(R.id.list_pager);
		frags = new ArrayList<Fragment>();
		frags.add(new InputFrag());
		frags.add(new IOActivity());
		adapt = new FragmentAdapter(getSupportFragmentManager(), frags);
		pager.setAdapter(adapt);
		Log.d("Main", "built");
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

}
