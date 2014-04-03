package com.pseudosudostudios.teamturtle;

import java.util.LinkedList;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

public class TimeChunk extends ActionBarActivity implements
		OnCheckedChangeListener {
	private static final int NOTIFICATION_ID = 65498425;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_chunk);
		LinearLayout tasks = (LinearLayout) findViewById(R.id.chunk_linear);
		LinkedList<Task> taskList = getTasks();
		for (Task t : taskList) {
			CheckBox box = new CheckBox(this);
			box.setOnCheckedChangeListener(this);
			box.setText(t.name + (t.course == null ? "" : " for " + t.course));
			box.setChecked(false);
			tasks.addView(box);
		}
	}

	private LinkedList<Task> getTasks() {
		@SuppressWarnings("unchecked")
		LinkedList<Task> list = (LinkedList<Task>) Task.masterTaskList.clone();
		String[] defaultTasksList = getResources().getStringArray(
				R.array.default_tasks_name);
		String[] courses = getResources().getStringArray(R.array.courses);
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
				// Android version issues
			}
			NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			manager.notify(NOTIFICATION_ID, builder.build());
		}
	}

}