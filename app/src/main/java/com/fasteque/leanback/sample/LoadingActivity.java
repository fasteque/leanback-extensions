package com.fasteque.leanback.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.fasteque.leanback.widget.app.LoadingFragment;

/*
 * LoadingActivity shows how to use LoadingFragment.
 */
public class LoadingActivity extends Activity {

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LoadingFragment loadingFragment = LoadingFragment.newInstance(Color.BLUE, Color.WHITE, 256, 256);
		getFragmentManager().beginTransaction().add(R.id.main_browse_fragment, loadingFragment).commit();
	}
}
