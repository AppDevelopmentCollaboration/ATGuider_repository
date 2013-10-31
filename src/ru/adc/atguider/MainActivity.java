package ru.adc.atguider;

import android.app.ActionBar;
import android.content.Intent;
//import android.app.Fragment;
//import android.app.FragmentTransaction;
//import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

	public ActionBar mainActionBar;
	BottomButtonsFragment bottomButtons;
	android.app.FragmentTransaction fTrans;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mainActionBar=getActionBar();
		mainActionBar.setDisplayShowTitleEnabled(false);	
		
		bottomButtons=new BottomButtonsFragment();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_actionbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.action_addItem:
			////show bottom ActionBar with record/play/modify_info/delete buttons
			fTrans=getFragmentManager().beginTransaction();
			fTrans.add(R.id.buttomButtonsId, bottomButtons);
			fTrans.commit();
			
			////////////////////////////////////////currently just open "modify Info" activity
			//Intent markerInfoActivity=new Intent(this,MarkerInfoActivity.class);
			//startActivity(markerInfoActivity);
			break;
		case R.id.action_startBatchMode:
			////run program in the Batch mode
			//////
			////check to run new Activity
			Intent markerInfoActivity=new Intent(this,MarkerInfoActivity.class);
			startActivity(markerInfoActivity);
			break;
		case R.id.action_settings:
			///open Settings activity
			
			break;
		}	                    
		return super.onOptionsItemSelected(item);
	}

}
