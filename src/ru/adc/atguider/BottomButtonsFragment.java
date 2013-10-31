package ru.adc.atguider;

import android.app.Fragment;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BottomButtonsFragment extends Fragment{

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		return inflater.inflate(R.layout.main_bottom_buttons, null);
	}
	
}
