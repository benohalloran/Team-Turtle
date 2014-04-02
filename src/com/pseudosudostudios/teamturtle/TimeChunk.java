package com.pseudosudostudios.teamturtle;

import java.util.LinkedList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class TimeChunk extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_chunk);
		LinearLayout tasks = (LinearLayout) findViewById(R.id.chunk_linear);
		LinkedList<Task> taskList = getTasks();
		for (Task t : taskList) {
			CheckBox box = new CheckBox(this);
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

}