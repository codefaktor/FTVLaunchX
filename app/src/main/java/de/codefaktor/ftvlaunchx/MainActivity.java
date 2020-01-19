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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
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
	private Spinner appsSpinner;
	private AppsAdapter appsAdapter;

	private void OpenHelpInBrowser() {
		Intent help = new Intent(Intent.ACTION_VIEW,
								 Uri.parse(getString(R.string.url_help)));
		startActivity(help);
	}

	private void UpdateServiceSettings() {
		TextView tvStatus = (TextView) findViewById(R.id.tvStatus);

		if (!Utilities.hasPermission(this, "WRITE_SECURE_SETTINGS")) {
			tvStatus.setVisibility(View.VISIBLE);
		} else {
			tvStatus.setVisibility(View.INVISIBLE);

			String packageName = getPackageName();

			Settings.Secure.putString(getContentResolver(),
				Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
				packageName + "/" + packageName + ".HomeService");

			Settings.Secure.putString(getContentResolver(),
				Settings.Secure.ACCESSIBILITY_ENABLED, "1");
		}
	}

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

		if (!Utilities.hasPermission(this, "WRITE_SECURE_SETTINGS")) {
			new AlertDialog.Builder(this)
				.setTitle(R.string.alert_title)
				.setMessage(R.string.alert_text)
				.setCancelable(true)
				.setNegativeButton(R.string.btn_help,
								   new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						OpenHelpInBrowser();
					}
				})
				.setPositiveButton(R.string.btn_okay,
								   new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						UpdateServiceSettings();
					}
				}).create().show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		UpdateServiceSettings();
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
