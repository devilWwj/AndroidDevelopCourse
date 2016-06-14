package com.csdn.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {
	

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Log.i("info", "onActivityCreated");
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i("info", "onAttach");

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("info", "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.i("info", "onCreateView");
		View view = inflater.inflate(R.layout.myfragment, null);
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("info", "onDestroy");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i("info", "onDestroyView");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i("info", "onDetach");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i("info", "onPause");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i("info", "onResume");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i("info", "onStart");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i("info", "onStop");
	}
	
}
