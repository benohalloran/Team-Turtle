package com.pseudosudostudios.teamturtle;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainScreen extends ActionBarActivity {
	public static final int TimeFrag = 0;
	public static final int IOFrag = 1;

	private FragmentAdapter adapt;
	private List<Fragment> frags;
	private ViewPager pager;

	// TODO remove the plus when the add fragment is visible

	// hide keyboard when how much time screen pops up
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_frags);
		pager = (ViewPager) findViewById(R.id.list_pager);
		frags = new ArrayList<Fragment>(2);
		frags.add(new TimeInputFrag());
		frags.add(new IOFrag());
		adapt = new FragmentAdapter(getSupportFragmentManager(), frags);
		pager.setAdapter(adapt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		if (getVisibleFrag() == IOFrag) {
			menu.removeItem(R.id.menu_add);
		}
		return true;
	}

	private int getVisibleFrag() {
		return pager.getCurrentItem();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_stress) {
			startActivity(new Intent(this, BraceletReadActivity.class));
			return true;
		} else if (id == R.id.menu_add) {
			showFragment(IOFrag);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showFragment(int i) {
		pager.setCurrentItem(i);
	}
}
