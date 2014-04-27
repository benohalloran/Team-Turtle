package com.pseudosudostudios.teamturtle;

import java.util.List;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

public class TimeChunk extends ActionBarActivity implements
		OnCheckedChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_chunk);
		LinearLayout layout = (LinearLayout) findViewById(R.id.chunk_linear);
		List<Task> taskList = Task.masterTaskList;
		for (Task t : taskList) {
			CheckBox box = new CheckBox(this);
			box.setOnCheckedChangeListener(this);
			box.setText(t.name + (t.course == null ? "" : " for " + t.course));
			box.setChecked(false);
			box.setTextSize(20);
			box.setId(t.hashCode());
			if (t.done)
				box.setPaintFlags(box.getPaintFlags()
						| Paint.STRIKE_THRU_TEXT_FLAG);
			else
				box.setPaintFlags(box.getPaintFlags()
						& ~Paint.STRIKE_THRU_TEXT_FLAG);
			layout.addView(box);
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

}