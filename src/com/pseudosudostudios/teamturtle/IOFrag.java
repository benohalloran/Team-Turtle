package com.pseudosudostudios.teamturtle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

public class IOFrag extends Fragment implements View.OnClickListener {
	private Button due, add;
	private EditText name, notes;
	private Spinner course;
	private ExpandableListView list;
	private ExpandableListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.frag_io, null);
		list = (ExpandableListView) root.findViewById(R.id.assignments_lists);
		due = (Button) root.findViewById(R.id.new_due);
		add = (Button) root.findViewById(R.id.new_add);
		name = (EditText) root.findViewById(R.id.new_name);
		notes = (EditText) root.findViewById(R.id.new_notes);
		course = (Spinner) root.findViewById(R.id.new_spinner);

		due.setOnClickListener(this);
		add.setOnClickListener(this);
		adapter = new ExpandableListAdapter(getActivity());
		list.setAdapter(adapter);
		return root;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		if (menu == null)
			inflater.inflate(R.menu.menu, menu);
		menu.removeItem(R.id.menu_add);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_due:
			showDueDialogue();
			break;
		case R.id.new_add:
			addData();
			break;
		default:
			break;
		}
	}

	private void addData() {
		String strname = name.getText().toString();
		if (strname.isEmpty()) {
			Toast.makeText(getActivity(), R.string.toast_empty_name,
					Toast.LENGTH_SHORT).show();
			return;
		}
		Task t = new Task();
		t.name = strname;
		t.notes = notes.getText().toString();
		t.due = due.getText().toString();
		if (t.due == getString(R.string.due))
			t.due = null;
		Object c;
		if ((c = course.getSelectedItem()) != null)
			t.course = c.toString();
		else
			t.course = "";
		adapter.addTask(t);
		name.setText("");
		notes.setText("");
		due.setText("Due");
		Toast.makeText(getActivity(), "Added task " + strname,
				Toast.LENGTH_LONG).show();
		Log.d("tried to add new task", t.toString());
	}

	private void showDueDialogue() {
		final CaldroidFragment calfrag = new CaldroidFragment();
		Bundle args = new Bundle();
		Calendar cal = Calendar.getInstance();
		args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
		args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
		args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
		args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
		args.putString(CaldroidFragment.DIALOG_TITLE, "When's it due?");
		calfrag.setArguments(args);
		final Date data = new Date();
		final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yy");
		calfrag.setCaldroidListener(new CaldroidListener() {
			@Override
			public void onSelectDate(Date date, View view) {
				data.setTime(date.getTime());
				String s = formatter.format(date);
				Log.d("Calendar", s);
				due.setText(s);
				calfrag.dismiss();
			}

		});
		calfrag.show(getFragmentManager(), "calfrag");
	}

}
