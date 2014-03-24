package com.pseudosudostudios.teamturtle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class MainScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		GridView grid = (GridView) findViewById(R.id.main_grid_view_buttons);
		grid.setAdapter(new ButtonAdapter(this));
		grid.invalidate();
		Log.d("Main", "built");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_screen, menu);
		menu.add("Time chunk");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.io_screen_menu) {
			startActivity(new Intent(this, IO_Screen.class));
			return true;
		} else if (id == R.id.chunk_menu) {
			startActivity(new Intent(this, TimeChunk.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
