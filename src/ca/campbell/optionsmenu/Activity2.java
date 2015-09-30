package ca.campbell.optionsmenu;
/**
 * Code is mostly duplicated in Activity2 and Activity3 for simplicity sake
 * with some differences
 * @author Tricia
 */

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.util.Linkify;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends Activity {
	private MediaPlayer mp;
	private static final String TAG = "OPTMENU2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// here we use the same UI as the MainActivity but we
		// manipulate it programatically so it looks different
		TextView tv = (TextView) findViewById(R.id.instrs);
		tv.setText(" Sounds courtesy of: http://www.acoustica.com/files/aclooplib/Sound%20Effects "
				+ "<br> No Options Menu in this Activity, click on the animal image for a Pop Up menu");
		tv.setAutoLinkMask(Linkify.ALL);
		tv.setLinksClickable(true);
		// hide one of the buttons
		Button bt = (Button) findViewById(R.id.button1);
		bt.setVisibility(View.INVISIBLE);
		// reveal the other button
		ImageButton ibt = (ImageButton) findViewById(R.id.button3);
		ibt.setVisibility(View.VISIBLE);
		Log.d(TAG, "in Activity2");	
	}

	/**
	 * The is the onClick attribute for the ImageButton of farm animals 
	 * @param view
	 */
	public void popUpMenu (View view) {
		PopupMenu popup = new PopupMenu(this, view);
		popup.setOnMenuItemClickListener(handleMenu);
		// we are using the same menu xml for this popup menu and
		// the options menu in activty3
		popup.inflate(R.menu.farmanimals);
		popup.show();
	}

	// No Options Menu here
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 */
    /**
     * here is where we handle the menu clicks
     */
	private PopupMenu.OnMenuItemClickListener handleMenu = new PopupMenu.OnMenuItemClickListener() {
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			// Handle pop up menu item clicks here. 
			int id = item.getItemId();
			switch (id) {
			case R.id.chickens:
				stopMediaPlayer();
				mp = MediaPlayer.create(Activity2.this, R.raw.chicken);
				mp.setLooping(true);
				mp.start();
				Toast.makeText(Activity2.this, "chickens", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "chickens");
				return true;

			case R.id.pigs:
				stopMediaPlayer();
				mp = MediaPlayer.create(Activity2.this, R.raw.pig);
				mp.setLooping(true);
				mp.start();
				Toast.makeText(Activity2.this, "pigs", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "pigs");
				return true;
			case R.id.cows:
				stopMediaPlayer();
				mp = MediaPlayer.create(Activity2.this, R.raw.cowmoo);
				mp.setLooping(true);
				mp.start();
				Toast.makeText(Activity2.this, "cows", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "cows");
				return true;
			case R.id.stop:
				stopMediaPlayer();
				Toast.makeText(Activity2.this, "stop the racket", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "stop the racket");
				return true;
			default:
				Log.d(TAG, "not implemented");
				return false;
			} // switch

		}  
	};

	public void stopMediaPlayer() {
		if (mp != null ) {
			mp.stop();
			mp.release();	
		}
	}
	public void newActivity3(View view) {
		startActivity(new Intent(this, Activity3.class));
	}
}
