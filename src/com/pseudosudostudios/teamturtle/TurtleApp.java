package com.pseudosudostudios.teamturtle;

import java.util.concurrent.ExecutionException;

import android.app.Application;

public class TurtleApp extends Application {
	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
		try {
			new BackgroundTaskIO().execute(this, BackgroundTaskIO.WRITE).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
