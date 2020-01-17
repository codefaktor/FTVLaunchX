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

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import de.codefaktor.ftvlaunchx.models.AppInfo;

/**
 * Provides several utilities.
 */

public class Utilities {
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
}
