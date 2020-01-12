/*
 * ### FTVLaunchX: Main Activity
 *
 * Copyright © 2020 [Erik Abele](http://www.codefaktor.de/).
 *
 * Licensed under the
 * [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
 */

package de.codefaktor.ftvlaunchx;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Implements the main activity of FTVLaunchX.
 */
public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");

		setContentView(R.layout.activity_main);

		TextView tvTitle = findViewById(R.id.tvTitle);

		tvTitle.setText(getString(R.string.app_name) + " " +
						getString(R.string.app_version));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy()");
	}
}
