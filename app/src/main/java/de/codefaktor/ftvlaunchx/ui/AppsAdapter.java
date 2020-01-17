/*
 * ### FTVLaunchX: UI - AppsAdapter
 *
 * Copyright © 2020 [Erik Abele](http://www.codefaktor.de/).
 *
 * Licensed under the
 * [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
 */

package de.codefaktor.ftvlaunchx.ui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.codefaktor.ftvlaunchx.models.AppInfo;
import de.codefaktor.ftvlaunchx.R;

/**
 * Provides the views for a list of AppInfo models.
 */

public class AppsAdapter extends ArrayAdapter<AppInfo> {
	private List<AppInfo> appInfos;

	private static class ViewHolder {
		ImageView icon;
		TextView name;
	}

	public AppsAdapter(Context context, List<AppInfo> appInfos) {
		super(context, R.layout.list_item_app, appInfos);

		this.appInfos = appInfos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AppInfo appInfo = getItem(position);

		ViewHolder vHolder;

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());

			convertView = inflater.inflate(R.layout.list_item_app,
										   parent, false);

			vHolder = new ViewHolder();

			vHolder.icon = (ImageView) convertView.findViewById(R.id.ivIcon);
			vHolder.name = (TextView) convertView.findViewById(R.id.tvName);

			convertView.setTag(vHolder);
		} else {
			vHolder = (ViewHolder) convertView.getTag();
		}

		vHolder.icon.setImageDrawable(appInfo.getIcon());
		vHolder.name.setText(appInfo.getName());

		return convertView;
	}

	@Override
	public View getDropDownView(int position,
								View convertView, ViewGroup parent) {
		return getView(position, convertView, parent);
	}

	/*
	 * Gets the AppInfo model at the given list position.
	 *
	 * @param position The position.
	 * @return         The AppInfo model at the given position.
	 */

	public AppInfo getAppInfo(int position) {
		if ((position >= 0) && (position < appInfos.size())) {
			return appInfos.get(position);
		}

		return null;
	}

	/*
	 * Gets the list position of the given package name.
	 *
	 * @param pkgName The package name to find.
	 * @return        The position of the given package name.
	 */

	public int getPosition(String pkgName) {
		for (int i = 0; i < appInfos.size(); i++) {
			if (appInfos.get(i).getPackageName().equalsIgnoreCase(pkgName)) {
				return i;
			}
		}

		return -1;
	}
}
