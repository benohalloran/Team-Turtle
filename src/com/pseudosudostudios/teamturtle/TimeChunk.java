package com.pseudosudostudios.teamturtle;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class TimeChunk extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_chunck);
		LinearLayout tasks = (LinearLayout) findViewById(R.id.chunk_linear);
		for (int i = 0; i < 20; i++) {
			CheckBox box = new CheckBox(this);
			box.setText(i + " task name");
			box.setChecked(false);
			tasks.addView(box);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
