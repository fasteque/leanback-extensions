package com.fasteque.leanback.sample;

import android.app.Activity;
import android.os.Bundle;

/*
 * MainActivity class that loads MainFragment.
 */
public class MainActivity extends Activity {

	// HACK: this is a bad practice! Done it just to have a minimal logic for the demo.
	public static int LOADING_PHASE = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
