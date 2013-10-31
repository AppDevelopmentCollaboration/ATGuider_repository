package ru.adc.atguider;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MarkerInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marker_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.marker_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId())
		{
		case (R.id.action_accept):
			//////сохранить информацию о маркере в базе данных и отправить всё на удалённый сервер

			break;

		case (R.id.action_reject):
			////закрыть окно без всяких изменений
			
			break;

		}
		return super.onOptionsItemSelected(item);
	}


}
