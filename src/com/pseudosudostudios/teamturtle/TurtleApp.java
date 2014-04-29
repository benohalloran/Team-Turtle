/****************************
 * deStress
 * Updated 4/28/14 by Ben O'Halloran
 ***************************/
package com.pseudosudostudios.teamturtle;

import android.app.Application;

/**
 * App which creates the {@link BackgroundTaskIO} thread for file reading/writing when
 * ever when ever the application is created or destroyed
 */
public class TurtleApp extends Application {
	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
		try {
			new BackgroundTaskIO().execute(this, BackgroundTaskIO.WRITE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			new BackgroundTaskIO().execute(this, BackgroundTaskIO.READ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
