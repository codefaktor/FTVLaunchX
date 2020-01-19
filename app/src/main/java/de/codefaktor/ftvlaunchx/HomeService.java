/*
 * ### FTVLaunchX: Home Service
 *
 * Copyright © 2020 [Erik Abele](http://www.codefaktor.de/).
 *
 * Licensed under the
 * [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
 */

package de.codefaktor.ftvlaunchx;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.KeyEvent;

/**
 * Implements HomeService.
 */

public class HomeService extends AccessibilityService {
	private static final String TAG = "HomeService";

	private static boolean isMenuKeyPressed = false;

	private static HomeReceiver homeReceiver = null;

	// Called when the system detects an AccessibilityEvent that matches the
	// event filtering parameters specified by this accessibility service.

	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
	}

	// Called when the system wants to interrupt the feedback this service
	// is providing.

	@Override
	public void onInterrupt() {
	}

	// Called for any key events before they are passed to the rest of
	// the system.
	//
	// If true then the event will be consumed and not delivered to
	// applications, otherwise it will be delivered as usual.

	@Override
	protected boolean onKeyEvent(KeyEvent event) {
		int action = event.getAction();

		switch (event.getKeyCode()) {
			case KeyEvent.KEYCODE_HOME:
				if (!isMenuKeyPressed) {
					if (action == KeyEvent.ACTION_DOWN) {
						Context ctx = getApplicationContext();

						String lPackage = Preferences.getLauncherPackage(ctx);

						Log.i(TAG, "Launching alternative home <" + lPackage +
								   "> for " + event);

						Utilities.launchPackage(ctx, lPackage);
					}

					return true;
				}

				break;

			case KeyEvent.KEYCODE_MENU:
				isMenuKeyPressed = (action == KeyEvent.ACTION_DOWN);

				break;
		}

		return false;
	}

	// Called when the system successfully connects to this accessibility
	// service.

	@Override
	protected void onServiceConnected() {
		Log.i(TAG, "System did connect accessibility service...");

		final IntentFilter filter = new IntentFilter();

		filter.addAction(Intent.ACTION_SCREEN_ON);

		homeReceiver = new HomeReceiver();

		getApplicationContext().registerReceiver(homeReceiver, filter);
	}

	// Called when the system is about to shutdown this accessibility
	// service.

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "System did unbind from accessibility service...");

		getApplicationContext().unregisterReceiver(homeReceiver);

		homeReceiver = null;

		return false;
	}
}
