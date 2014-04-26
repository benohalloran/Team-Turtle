package com.pseudosudostudios.teamturtle;

import java.util.List;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

public class TimeChunk extends ActionBarActivity implements
		OnCheckedChangeListener {
	private static final int NOTIFICATION_ID = 65498425; // random constant int

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_chunk);
		LinearLayout tasks = (LinearLayout) findViewById(R.id.chunk_linear);
		List<Task> taskList = getTasks();
		for (Task t : taskList) {
			CheckBox box = new CheckBox(this);
			box.setOnCheckedChangeListener(this);
			box.setText(t.name + (t.course == null ? "" : " for " + t.course));
			box.setChecked(false);
			box.setTextSize(20);
			tasks.addView(box);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		menu.removeItem(R.id.menu_stress);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent launchMainForAdd = new Intent(this, MainScreen.class);
		launchMainForAdd.putExtra(MainScreen.ADD_KEY, true);
		startActivity(launchMainForAdd);
		return true;
	}

	private List<Task> getTasks() {
		@SuppressWarnings("unchecked")
		List<Task> list = Task.masterTaskList;
		String[] defaultTasksList = getResources().getStringArray(
				R.array.default_tasks_name);
		String[] courses = getResources().getStringArray(
				R.array.default_courses);
		for (int i = 0; list.size() < courses.length; i++) {
			list.add(new Task(defaultTasksList[i], null, courses[i], null));
		}
		return list;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			// send notification to take a brake
			NotificationCompat.Builder builder = new NotificationCompat.Builder(
					this);
			builder.setSmallIcon(R.drawable.clock);
			builder.setContentTitle("Break Time!");
			builder.setContentText(getString(R.string.notification_text));
			try {
				builder.setStyle(new NotificationCompat.BigTextStyle()
						.bigText(getString(R.string.notification_text)));
			} catch (Exception e) {
				// Android version issues, ignore since
			}
			NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			manager.notify(NOTIFICATION_ID, builder.build());
			buttonView.setPaintFlags(buttonView.getPaintFlags()
					| Paint.STRIKE_THRU_TEXT_FLAG);
		} else
			buttonView.setPaintFlags(buttonView.getPaintFlags()
					& ~Paint.STRIKE_THRU_TEXT_FLAG);
	}

}