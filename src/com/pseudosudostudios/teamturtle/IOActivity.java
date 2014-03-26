package com.pseudosudostudios.teamturtle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class IOActivity extends Fragment {
	List<String> listDataHeader;
	HashMap<String, Task> listDataChild;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.frag_io, null);
		ExpandableListView list = (ExpandableListView) root
				.findViewById(R.id.assignments_lists);
		prepareListData();
		list.setAdapter(new ExpandableListAdapter(getActivity(),
				listDataHeader, listDataChild));
		return root;
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, Task>();
		for (int i = 0; i < 20; i++) {
			listDataHeader.add(i + "");
			listDataChild.put(i + "", new Task(i + " name", i + " notes", i
					+ " course", i + " due"));
		}
	}

}
