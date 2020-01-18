/*
 * ### FTVLaunchX: Home Receiver
 *
 * Copyright © 2020 [Erik Abele](http://www.codefaktor.de/).
 *
 * Licensed under the
 * [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
 */

package de.codefaktor.ftvlaunchx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Implements HomeReceiver.
 */

public class HomeReceiver extends BroadcastReceiver {
	private static final String TAG = "HomeReceiver";

	@Override
	public void onReceive(Context ctx, Intent intent) {
		String launcherPackage = Preferences.getLauncherPackage(ctx);

		Log.i(TAG, "Launching alternative home <" + launcherPackage +
				   "> for " + intent.getAction());

		Utilities.launchPackage(ctx, launcherPackage);
	}
}
