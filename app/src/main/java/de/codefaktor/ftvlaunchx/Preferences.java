/*
 * ### FTVLaunchX: Preferences
 *
 * Copyright © 2020 [Erik Abele](http://www.codefaktor.de/).
 *
 * Licensed under the
 * [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
 */

package de.codefaktor.ftvlaunchx;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Manages global preferences of application.
 */

public class Preferences {
	private static final String PREFS_ID = "GlobalPreferences";
	private static final String PREF_KEY_LAUNCHER_PKG = "LauncherPackage";

	public static String getLauncherPackage(Context ctx) {
		SharedPreferences sp = ctx.getSharedPreferences(PREFS_ID,
														Context.MODE_PRIVATE);

		return sp.getString(PREF_KEY_LAUNCHER_PKG,
							ctx.getApplicationContext().getPackageName());
	}

	public static boolean setLauncherPackage(Context ctx, String pkg) {
		SharedPreferences sp = ctx.getSharedPreferences(PREFS_ID,
														Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = sp.edit();

		editor.putString(PREF_KEY_LAUNCHER_PKG, pkg);

		return editor.commit();
	}
}
