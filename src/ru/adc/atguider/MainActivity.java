package ru.adc.atguider;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.ActionBar;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity 
implements OnMarkerClickListener{

	public ActionBar mainActionBar;
	public FrameLayout bottomButtonsFrame;
	//public boolean fragFocus;
	BottomButtonsFragment bottomButtons;
	android.app.FragmentTransaction fTrans;
	GoogleMap googleMapFrag;	
	public MarkerOptions marker;
	public String markerName;
	public Location myLocation;

	public MenuItem mainMenuItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		///check if googleMaps created
		//		googleMapFrag=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		//		if (googleMapFrag==null)
		//		{
		//			Toast.makeText(this, "Unable to create map! Please, check connection", Toast.LENGTH_SHORT).show();
		//		}

		mainActionBar=getActionBar();
		mainActionBar.setDisplayShowTitleEnabled(false);	

		bottomButtons=new BottomButtonsFragment();
		googleMapFrag=((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		googleMapFrag.setMyLocationEnabled(true);
		googleMapFrag.getUiSettings().setMyLocationButtonEnabled(true);
		googleMapFrag.getUiSettings().setZoomControlsEnabled(false);

		//googleMapFrag.animateCamera(CameraUpdateFactory.newLatLng())
		//		boolean gotMyLocation=false;
		//		while (!gotMyLocation)
		//		{
		//			myLocation = googleMapFrag.getMyLocation();
		//			if (myLocation!=null)
		//			{
		//				gotMyLocation=true;
		//			}
		//		}
		googleMapFrag.setOnMarkerClickListener(this);		

		///delete bottom buttons Fragment when not in focus???
		//bottomButtonsFrame=(FrameLayout)findViewById(R.id.buttomButtonsId);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_actionbar, menu);
		mainMenuItems=menu.findItem(R.id.mainActionsItems);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case R.id.action_addItem:
			///1) add marker in center of the map and allow to drag it
			//latitude and longitude
			try
			{
				myLocation=googleMapFrag.getMyLocation();
			} catch (Exception e) {
				Toast.makeText(this, "Could get current location", Toast.LENGTH_LONG).show();
			}
			if (myLocation!=null)
			{
				
				double latitude= myLocation.getLatitude();//0.0;
				double longitude = myLocation.getLongitude();//0.0;
				CameraPosition newCameraPosition=new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
				googleMapFrag.animateCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));
				markerName="Marker name";
				MarkerOptions marker=new MarkerOptions().position(new LatLng(latitude, longitude)).title(markerName);
				marker.draggable(true);
				marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
				try
				{
					googleMapFrag.addMarker(marker);

				} catch (Exception e) {
					Toast.makeText(this, e.hashCode(), Toast.LENGTH_LONG).show();
				}
			}

			////////////////////////////////////////currently just open "modify Info" activity
			//Intent markerInfoActivity=new Intent(this,MarkerInfoActivity.class);
			//startActivity(markerInfoActivity);
			break;
		case R.id.action_startBatchMode:
			////run program in the Batch mode
			//////
			fTrans=getFragmentManager().beginTransaction();
			fTrans.remove(bottomButtons);
			fTrans.addToBackStack(null);
			fTrans.commit();

			/////////
			if (myLocation!=null)
			{
				Toast.makeText(this, "Lat = " + myLocation.getLatitude() + "; Lon = " + myLocation.getLongitude(), Toast.LENGTH_LONG).show();
			}

			////check to run new Activity
			//			Intent markerInfoActivity=new Intent(this,MarkerInfoActivity.class);
			//			startActivity(markerInfoActivity);
			break;
		case R.id.action_settings:
			///open Settings activity

			break;
		}	                    
		return super.onOptionsItemSelected(item);
	}

	///2) bind marker info (position) with audiofile and send it to Marker_info activity to edit its name and type
	///2.1) if new marker audiofile been recorded than open edit activity automatically


	@Override
	/////////////Show bottom buttons if the marker is clicked
	public boolean onMarkerClick(Marker marker) {	
		bottomButtonsTrans(1);
		return false;		
	}


	public void bottomButtonsTrans(int actionRequired) ///1- show, 0 - hide
	{
		fTrans=getFragmentManager().beginTransaction();
		switch (actionRequired) {
		case 1: //
			////show bottom ActionBar with record/play/modify_info/delete buttons related to chosen/inserted marker							
			fTrans.replace(R.id.buttomButtonsId, bottomButtons);
			fTrans.addToBackStack(null);
			fTrans.commit();
			//fragFocus=true;
			break;		
		default:
			break;
		}
	}


}
