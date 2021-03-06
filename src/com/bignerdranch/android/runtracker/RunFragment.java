package com.bignerdranch.android.runtracker;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RunFragment extends Fragment {
	private static final String TAG = "RunFragment";
	private RunManager mRunManager;
	private Button mStartButton, mStopButton;
	private TextView mStartedTextView, mLatitudeTextView, mLongitudeTextView, mAltitudeTextView, mDurationTextView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate_start");
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mRunManager = RunManager.get(getActivity());
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView_start");
		View view = inflater.inflate(R.layout.fragment_run, container, false);
		
		
		mStartedTextView = (TextView) view.findViewById(R.id.run_startedTextView);
		mLatitudeTextView = (TextView) view.findViewById(R.id.run_latitudeTextView);
		mLongitudeTextView = (TextView) view.findViewById(R.id.run_longitudeTextView);
		mAltitudeTextView = (TextView) view.findViewById(R.id.run_altitudeTextView);
		mDurationTextView = (TextView) view.findViewById(R.id.run_durationTextView);
		
		mStartButton = (Button) view.findViewById(R.id.run_startButton);
		mStartButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mRunManager.startLocationUpdates();
				updateUI();
				
			}
		});
		mStopButton = (Button) view.findViewById(R.id.run_stopButton);
		mStopButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mRunManager.stopLocationUpdates();
				updateUI();
				
			}
		});
		updateUI();
		return view;
	}
	
	private void updateUI() {
		boolean started = mRunManager.isTrackingRun();
		mStartButton.setEnabled(!started);
		mStopButton.setEnabled(started);
	}

}
