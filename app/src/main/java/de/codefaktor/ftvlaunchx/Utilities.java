/*
 * ### FTVLaunchX: Utilities
 *
 * Copyright © 2020 [Erik Abele](http://www.codefaktor.de/).
 *
 * Licensed under the
 * [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
 */

package de.codefaktor.ftvlaunchx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import de.codefaktor.ftvlaunchx.models.AppInfo;

/**
 * Provides several utilities.
 */

public class Utilities {
	private static long lastLaunch = 0;

	/*
	 * Determines whether the given permission has been granted.
	 *
	 * @param ctx        Context of application.
	 * @param permission The permission to check for.
	 * @return           True if the given permission has been granted,
	 *                   false otherwise.
	 */

	public static boolean hasPermission(Context ctx, String permission) {
		int result = ctx.checkCallingOrSelfPermission(
			"android.permission." + permission
		);

		return (result == PackageManager.PERMISSION_GRANTED);
	}

	/*
	 * Gets all installed non-system applications.
	 *
	 * @param ctx Context of application.
	 * @return    A sorted list of all installed non-system applications
	 *            in the form of AppInfo models.
	 */

	public static List<AppInfo> getInstalledApplications(Context ctx) {
		PackageManager pm = ctx.getPackageManager();

		List<ApplicationInfo> installedApps = pm.getInstalledApplications(0);

		Collections.sort(installedApps,
						 new ApplicationInfo.DisplayNameComparator(pm));

		List<AppInfo> appInfos = new ArrayList<AppInfo>();

		for (ApplicationInfo installedApp : installedApps) {
			AppInfo app = new AppInfo(ctx, installedApp);

			if (!app.isSystemApp()) {
				appInfos.add(app);
			}
		}

		return appInfos;
	}

	/*
	 * Gets the launch intent for a package, or, if not available (anymore)
	 * the launch intent for the application itself.
	 *
	 * @param ctx Context of application.
	 * @param pkg Name of package. Passing null will get the
	 *            launch intent for the application itself.
	 * @return    The launch intent for the package.
	 */

	public static Intent getLaunchIntentForPackage(Context ctx, String pkg) {
		PackageManager pm = ctx.getPackageManager();

		if (pkg == null) {
			pkg = ctx.getApplicationContext().getPackageName();
		}

		Intent intent = pm.getLeanbackLaunchIntentForPackage(pkg);

		if (intent == null) {
			intent = pm.getLaunchIntentForPackage(pkg);
		}

		if (intent == null) {
			intent = getLaunchIntentForPackage(ctx, null);
		}

		return intent;
	}

	/*
	 * Launches the default activity of the given package.
	 *
	 * @param ctx Context of application.
	 * @param pkg Name of package to launch.
	 */

	public static void launchPackage(Context ctx, String pkg) {
		long time = System.currentTimeMillis();

		if ((time - lastLaunch) < 200) {
			return;
		}

		lastLaunch = time;

		Intent lIntent = getLaunchIntentForPackage(ctx, pkg);

		lIntent.setFlags(
			Intent.FLAG_ACTIVITY_CLEAR_TOP |
			Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS |
			Intent.FLAG_ACTIVITY_NEW_TASK |
			Intent.FLAG_ACTIVITY_REORDER_TO_FRONT |
			Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
		);

		PendingIntent pIntent = PendingIntent.getActivity(ctx, 0,
														  lIntent, 0);
		try {
			pIntent.send();
		} catch (PendingIntent.CanceledException e) {
			e.printStackTrace();
		}
	}
}
