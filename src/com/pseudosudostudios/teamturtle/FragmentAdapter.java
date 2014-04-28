package com.pseudosudostudios.teamturtle;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
	private List<Fragment> list;

	public FragmentAdapter(FragmentManager fm, List<Fragment> frags) {
		super(fm);
		list = frags;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Fragment getItem(int i) {
		return list.get(i);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case MainScreen.TimeFrag:
			return "Time";
		case MainScreen.IOFrag:
			return "Tasks";
		case MainScreen.BRACELET:
			return "Today's Stress";
		default:
			return super.getPageTitle(position);
		}
	}
}
