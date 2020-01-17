/*
 * ### FTVLaunchX: Main Activity
 *
 * Copyright © 2020 [Erik Abele](http://www.codefaktor.de/).
 *
 * Licensed under the
 * [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
 */

package de.codefaktor.ftvlaunchx;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;

import de.codefaktor.ftvlaunchx.models.AppInfo;
import de.codefaktor.ftvlaunchx.ui.AppsAdapter;

/**
 * Implements the main activity of the application.
 */

public class MainActivity extends Activity implements OnItemSelectedListener {
	private static final String TAG = "MainActivity";

	private Spinner appsSpinner;
	private AppsAdapter appsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		TextView tvTitle = (TextView) findViewById(R.id.tvTitle);

		tvTitle.setText(getString(R.string.app_name) + " " +
						getString(R.string.app_version));

		appsSpinner = (Spinner) findViewById(R.id.sp_apps);
		appsSpinner.setOnItemSelectedListener(this);

		List<AppInfo> appInfos = Utilities.getInstalledApplications(this);

		appsAdapter = new AppsAdapter(this, appInfos);
		appsSpinner.setAdapter(appsAdapter);

		String launcherPackage = Preferences.getLauncherPackage(this);
		appsSpinner.setSelection(appsAdapter.getPosition(launcherPackage));
	}

	@Override
	protected void onResume() {
		super.onResume();

		TextView tvStatus = (TextView) findViewById(R.id.tvStatus);

		if (!Utilities.hasPermission(this, "WRITE_SECURE_SETTINGS")) {
			tvStatus.setVisibility(View.VISIBLE);
		} else {
			tvStatus.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent,
							   View view, int position, long id) {
		AppInfo appInfo = appsAdapter.getAppInfo(position);

		if (appInfo != null) {
			Preferences.setLauncherPackage(this, appInfo.getPackageName());
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}
