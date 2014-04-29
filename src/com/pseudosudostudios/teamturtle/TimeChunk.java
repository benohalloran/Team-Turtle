/****************************
 * deStress
 * Updated 4/28/14 by Ben O'Halloran
 ***************************/
package com.pseudosudostudios.teamturtle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

/**
 * Displays what the user should work on during the amount of time the entered
 * on the {@link TimeInputFrag} screen
 */
public class TimeChunk extends ActionBarActivity implements
		OnCheckedChangeListener, OnClickListener {
	private Button due, add;
	private EditText name, notes;
	private Spinner course;
	private LinearLayout layout;
	private AlertDialog popup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_chunk);
		layout = (LinearLayout) findViewById(R.id.chunk_linear);
		for (Task t : Task.masterTaskList) {
			addTaskToLayout(t);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View root = inflater.inflate(R.layout.new_assigment, null);
		add = (Button) root.findViewById(R.id.new_add);
		due = (Button) root.findViewById(R.id.new_due);
		name = (EditText) root.findViewById(R.id.new_name);
		notes = (EditText) root.findViewById(R.id.new_notes);
		course = (Spinner) root.findViewById(R.id.new_spinner);
		due.setOnClickListener(this);
		add.setOnClickListener(this);
		AlertDialog.Builder build = new AlertDialog.Builder(this).setView(root)
				.setCancelable(true).setTitle(R.string.menu_add_str);
		popup = build.show();
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			buttonView.setPaintFlags(buttonView.getPaintFlags()
					| Paint.STRIKE_THRU_TEXT_FLAG);
		} else
			buttonView.setPaintFlags(buttonView.getPaintFlags()
					& ~Paint.STRIKE_THRU_TEXT_FLAG);
		Task clicked = Task.findTaskByHash(buttonView.getId());
		if (clicked != null)
			clicked.setDone(isChecked);
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
		if (strname.equals("")) {
			Toast.makeText(this, R.string.toast_empty_name, Toast.LENGTH_SHORT)
					.show();
			return;
		}
		Task t = new Task(strname, notes.getText().toString(),
				course.getSelectedItem() == null ? "" : course
						.getSelectedItem().toString(), due.getText().toString());
		addTaskToLayout(t);
		name.setText("");
		notes.setText("");
		due.setText("Due");
		Toast.makeText(this, "Added task " + strname, Toast.LENGTH_LONG).show();
		if (popup != null && popup.isShowing())
			try {
				popup.dismiss();
			} catch (Exception e) {
			}
	}

	private void addTaskToLayout(Task t) {
		CheckBox box = new CheckBox(this);
		box.setOnCheckedChangeListener(this);
		box.setText(t.name + (t.course == null ? "" : " for " + t.course));
		box.setChecked(t.done);
		box.setTextSize(20);
		box.setId(t.hashCode());
		if (t.done)
			box.setPaintFlags(box.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		else
			box.setPaintFlags(box.getPaintFlags()
					& ~Paint.STRIKE_THRU_TEXT_FLAG);
		layout.addView(box);
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
		calfrag.show(getSupportFragmentManager(), "calfrag");
	}

}