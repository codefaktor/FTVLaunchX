/*
 * ### FTVLaunchX: Model - AppInfo
 *
 * Copyright © 2020 [Erik Abele](http://www.codefaktor.de/).
 *
 * Licensed under the
 * [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
 */

package de.codefaktor.ftvlaunchx.models;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**
 * Holds information about an application.
 */

public class AppInfo {
	private ApplicationInfo appInfo;
	private Drawable icon;
	private String name;

	public AppInfo(Context ctx, ApplicationInfo appInfo) {
		PackageManager pm = ctx.getPackageManager();

		this.appInfo = appInfo;

		this.icon = pm.getApplicationIcon(appInfo);
		this.name = pm.getApplicationLabel(appInfo).toString();
	}

	public Drawable getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	public String getPackageName() {
		return appInfo.packageName;
	}

	public boolean isSystemApp() {
		if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1 ||
			(appInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return getName() + " [" + getPackageName() + "]";
	}
}
