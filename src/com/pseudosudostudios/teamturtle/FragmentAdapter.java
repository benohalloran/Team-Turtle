package com.pseudosudostudios.teamturtle;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
	public static final int TIME_FRAG = 0;
	public static final int IO_FRAG = 1;
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
		case TIME_FRAG:
			return "Time";
		case IO_FRAG:
			return "Tasks";
		default:
			return super.getPageTitle(position);
		}
	}
}
