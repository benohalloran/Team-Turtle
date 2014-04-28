package com.pseudosudostudios.teamturtle;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class MainScreen extends ActionBarActivity {
	public static final int IOFrag = 0;
	public static final int TimeFrag = 1;
	public static final int BRACELET = 2;

	private FragmentAdapter adapt;
	private List<Fragment> frags;
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_frags);
		// title strip
		pager = (ViewPager) findViewById(R.id.list_pager);
		frags = new ArrayList<Fragment>(3);
		frags.add(new IOFrag());
		frags.add(new TimeInputFrag());
		frags.add(new BraceletReadFrag());
		adapt = new FragmentAdapter(getSupportFragmentManager(), frags);
		pager.setAdapter(adapt);
		showFragment(TimeFrag);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// force the IO frag to update any input or check off from the time
		// chunk screen
		try {
			IOFrag io = (com.pseudosudostudios.teamturtle.IOFrag) frags
					.get(IOFrag);
			io.notifyDataChange();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	public int getVisibleFrag() {
		return pager.getCurrentItem();
	}

	public void showFragment(int i) {
		pager.setCurrentItem(i);
	}
}
