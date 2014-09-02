package com.example.androidtablayout;

import java.io.File;

import com.example.microcam.MainActivity;
import com.example.microcam.MainActivity.VideoMergeTask;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class findpeopleemail extends TabActivity {
	ImageView img1;
	ImageView img2;

	 String user_id;
	 String token;
    /** Called when the activity is first created. */
	 
	 
	public static final String PREFS_NAME = "MyPrefsFile";

	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// final String user_id;
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpeoplemain);
        img1=(ImageView)findViewById(R.id.ImageView01);
        img2=(ImageView)findViewById(R.id.imageView2);


 //       Toast.makeText(AndroidTabLayoutActivity.this, user_id, Toast.LENGTH_LONG).show();

        TabHost tabHost = getTabHost();

        tabHost.setBackgroundColor(Color.BLACK);
        tabHost.getTabWidget().setStripEnabled(false);
        tabHost.getTabWidget().setDividerDrawable(null);

    
        img1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 

				finish();
				
					}
		});
        
        
        img2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
		        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
	
			finish();
			}
		});
        
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec(".");
        photospec.setIndicator(".", getResources().getDrawable(R.drawable.contact_tab));
        
        Intent photosIntent = new Intent(this, contact_connect.class);
     // photosIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      //  photosIntent.putExtra("userid", user_id);
        photospec.setContent(photosIntent);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
        
        
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec(",");
        // setting Title and Icon for the Tab
        songspec.setIndicator(",", getResources().getDrawable(R.drawable.twitter_tab));
        Intent songsIntent = new Intent(this, twitter_connect.class);
    //  songsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    //    songsIntent.putExtra("userid", user_id);
        songspec.setContent(songsIntent);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
       
      
        
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("_");
       videospec.setIndicator("_", getResources().getDrawable(R.drawable.search_tab));
       
       Intent videosIntent = new Intent(this, invitepeople.class);
   //     videosIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     //   videosIntent.putExtra("userid", user_id);
        videospec.setContent(videosIntent);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 

      
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
    }




}
