package com.bignerdranch.android.runtracker;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

public class RunManager {
	private static final String TAG = "RunManager";
	private static final String ACTION_LOCATION = "com.bignerdranch.android.runtracker.ACTION_LOCATION";
	
	private static RunManager sRunManager;
	private Context mAppContext;
	private LocationManager mLocationManager;
	
	private RunManager(Context context) {
		mAppContext = context;
		mLocationManager = (LocationManager)mAppContext.getSystemService(Context.LOCATION_SERVICE);
		
	}
	
	public static RunManager get(Context c) {
		Log.d(TAG, "get_start");
		if (sRunManager == null) {
			sRunManager = new RunManager(c.getApplicationContext());
		}
		
		return sRunManager;
	}
	
	private PendingIntent getLocationPendingIntent(boolean shouldCreate) {
		Intent broadcast = new Intent(ACTION_LOCATION);
		int flags = shouldCreate ? 0 : PendingIntent.FLAG_NO_CREATE;
		return PendingIntent.getBroadcast(mAppContext, 0, broadcast, flags);
		
	}
	
	public void startLocationUpdates() {
		String provider = LocationManager.GPS_PROVIDER;
		PendingIntent pi = getLocationPendingIntent(true);
		mLocationManager.requestLocationUpdates(provider, 0, 0, pi);
	}
	
	public void stopLocationUpdates() {
		PendingIntent pi = getLocationPendingIntent(false);
		if (pi != null) {
			mLocationManager.removeUpdates(pi);
			pi.cancel();
		}
	}
	
	public boolean isTrackingRun() {
		return getLocationPendingIntent(false) != null;
	}
	 
}
