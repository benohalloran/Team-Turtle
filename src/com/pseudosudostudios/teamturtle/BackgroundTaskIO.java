/****************************
 * deStress
 * Updated 4/28/14 by Ben O'Halloran
 ***************************/
package com.pseudosudostudios.teamturtle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/** Handles writing the data to disk in a background thread. */
public class BackgroundTaskIO extends AsyncTask<Object, Integer, Void> {
	public static final int WRITE = 0;
	public static final int READ = 1;

	@Override
	protected Void doInBackground(Object... params) {
		Context c = (Context) params[0];
		int mode = (Integer) params[1];
		File dir = c.getFilesDir();
		File out = new File(dir, "data.json");
		if (!out.exists()) {
			try {
				out.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			JSONArray array = new JSONArray();
			if (mode == WRITE) {
				for (Task t : Task.masterTaskList)
					try {
						array.put(t.toJSONObject());
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				FileWriter writer;
				try {
					writer = new FileWriter(out);
					writer.write(array.toString());
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (mode == READ) {
				try {
					BufferedReader read = new BufferedReader(
							new FileReader(out));
					StringBuffer buf = new StringBuffer();
					while (read.ready())
						buf.append(read.readLine());
					read.close();
					array = new JSONArray(buf.toString());
					for (int i = 0; i < array.length(); i++)
						new Task(array.getJSONObject(i));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else
				Log.d(getClass().getCanonicalName(), "Invalid mode: " + mode);
			return null;
		}
	}
}
