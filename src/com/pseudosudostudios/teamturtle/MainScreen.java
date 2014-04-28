package com.pseudosudostudios.teamturtle;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainScreen extends ActionBarActivity {
	public static final int IOFrag = 0;
	public static final int TimeFrag = 1;
	public static final int BRACELET = 2;

	public static final String ADD_KEY = "add";
	private FragmentAdapter adapt;
	private List<Fragment> frags;
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_frags);
		// title strip
		pager = (ViewPager) findViewById(R.id.list_pager);
		frags = new ArrayList<Fragment>(2);
		frags.add(new IOFrag());
		frags.add(new TimeInputFrag());
		frags.add(new BraceletReadActivity());
		adapt = new FragmentAdapter(getSupportFragmentManager(), frags);
		pager.setAdapter(adapt);
		Bundle extras;
		if ((extras = getIntent().getExtras()) != null) {
			if (extras.containsKey(ADD_KEY)) {
				showFragment(IOFrag);
			}
		}
		showFragment(TimeFrag);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	public int getVisibleFrag() {
		return pager.getCurrentItem();
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			IOFrag io = (com.pseudosudostudios.teamturtle.IOFrag) frags
					.get(IOFrag);
			io.notifyDataChange();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_stress) {
			showFragment(BRACELET);// startActivity(new Intent(this,
									// BraceletReadActivity.class));
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
