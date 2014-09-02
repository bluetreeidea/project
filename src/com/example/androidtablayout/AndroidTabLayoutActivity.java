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

public class AndroidTabLayoutActivity extends TabActivity {
	ImageView img1;
	ImageView img2;
	ImageView img3;
	ImageView img4;
	ImageView camera;
	ImageView search;
	View view1,view2;
	 String user_id,accesskey,accesstoken;
	 String token;
    /** Called when the activity is first created. */
	 
	 
	    public static final String PREFS_NAME = "MyPrefsFile";

	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// final String user_id;
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //LinearLayout popupTabs = (LinearLayout) findViewById(R.id.popupTabs);
        /*img1=(ImageView)findViewById(R.id.fed);
        img2=(ImageView)findViewById(R.id.activ);
        img3=(ImageView)findViewById(R.id.exp);
        img4=(ImageView)findViewById(R.id.pro);*/
        Intent i=getIntent();
        user_id=i.getStringExtra("userid");
        token=i.getStringExtra("fb_token");
        accesskey=i.getStringExtra("accesskey");
        accesstoken=i.getStringExtra("accesstoken");
 //       Toast.makeText(AndroidTabLayoutActivity.this, user_id, Toast.LENGTH_LONG).show();
        camera=(ImageView)findViewById(R.id.camera);
        search=(ImageView)findViewById(R.id.search);
        
        TabHost tabHost = getTabHost();

        tabHost.setBackgroundColor(Color.BLACK);
tabHost.getTabWidget().setStripEnabled(false);
tabHost.getTabWidget().setDividerDrawable(null);

        camera.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
                new cameracal().execute((Void) null);

					}
		});
        search.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				Intent i1=new Intent(AndroidTabLayoutActivity.this,search.class);
				    i1.putExtra("userid", user_id);
			        i1.putExtra("fb_token", token);
			        i1.putExtra("accesskey", accesskey);
			        i1.putExtra("accesstoken", accesstoken);
				   	Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
				   startActivity(i1, bndlanimation);
			}
		});
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec(".");
        photospec.setIndicator(".", getResources().getDrawable(R.drawable.icon_photos_tab));
        
        Intent photosIntent = new Intent(this, Feed.class);
     //   photosIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        photosIntent.putExtra("userid", user_id);
        photosIntent.putExtra("fb_token", token);
        photosIntent.putExtra("accesskey", accesskey);
        photosIntent.putExtra("accesstoken", accesstoken);
        photospec.setContent(photosIntent);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
        
        
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec(",");
        // setting Title and Icon for the Tab
      songspec.setIndicator(",", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent songsIntent = new Intent(this, Activityy.class);
    //    songsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        songsIntent.putExtra("userid", user_id);
        songsIntent.putExtra("fb_token", token);
        songsIntent.putExtra("accesskey", accesskey);
        songsIntent.putExtra("accesstoken", accesstoken);
        songspec.setContent(songsIntent);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
       
      
        
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("_");
       videospec.setIndicator("_", getResources().getDrawable(R.drawable.icon_videos_tab));
      
       
       Intent videosIntent = new Intent(this, Explore.class);
   //     videosIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        videosIntent.putExtra("userid", user_id);
        videosIntent.putExtra("fb_token", token);
        videosIntent.putExtra("accesskey", accesskey);
        videosIntent.putExtra("accesstoken", accesstoken);
        videospec.setContent(videosIntent);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 

        TabSpec mp3spec = tabHost.newTabSpec(".,");
       mp3spec.setIndicator(".,", getResources().getDrawable(R.drawable.icon_profile_tab));
        Intent mp3Intent = new Intent(this, Profile.class);
        //mp3Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mp3Intent.putExtra("userid", user_id);
        mp3Intent.putExtra("fb_token", token);
        mp3Intent.putExtra("accesskey", accesskey);
        mp3Intent.putExtra("accesstoken", accesstoken);
        mp3spec.setContent(mp3Intent);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
        tabHost.addTab(mp3spec);
    }


private class cameracal extends AsyncTask <Void, Void, Void>  
{
         private ProgressDialog Dialog = new ProgressDialog(AndroidTabLayoutActivity.this);

         protected void onPreExecute()
         {
             Dialog.setMessage("Initializing camera...");
             Dialog.show();
             Dialog.setCanceledOnTouchOutside(false);
         }
         

         @Override
     protected Void doInBackground(Void... params) 
         {
        // Do your background data fetching here 
       	  
       	  
            return null;   
     }
     

         protected void onPostExecute(Void unused)    
         {
             try
             {
                 if(Dialog.isShowing())
                 {
                		Intent i=new Intent(AndroidTabLayoutActivity.this,MainActivity.class);
        				i.putExtra("userid", user_id);
        				i.putExtra("fb_token", token);
        				 startActivity(i);
        				Dialog.dismiss();
        				 
                 }
                       
                
             }
             catch(Exception e) 
             {
            	 
             }
                   

             SharedPreferences preferences = getPreferences(MODE_PRIVATE);
             int storedPreference = preferences.getInt("storedInt", 0);
}
}
@Override
public boolean onCreateOptionsMenu(Menu menu)
{
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.layout.menu, menu);
    return true;
}
@Override
public boolean onOptionsItemSelected(MenuItem item)
{
    
    switch (item.getItemId())
    {
    case R.id.logout:
    	// Single menu item is selected do something
    	// Ex: launching new activity/screen or show alert message
    	
    	loadSavedPreferences();
        Toast.makeText(AndroidTabLayoutActivity.this, "Logout", Toast.LENGTH_SHORT).show();
        clearApplicationData();
        Intent i=new Intent(AndroidTabLayoutActivity.this,splash.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
        
        return true;
    case R.id.menu_save:
    	Toast.makeText(AndroidTabLayoutActivity.this, "Clear Cache", Toast.LENGTH_SHORT).show();
    	//onDestroy();
    	clearApplicationData();
        return true;
  
    default:
        return super.onOptionsItemSelected(item);
       
    }
}
public void clearApplicationData() {
    File cache = getCacheDir();
    File appDir = new File(cache.getParent());
    if (appDir.exists()) {
        String[] children = appDir.list();
        for (String s : children) {
            if (!s.equals("lib")) {
                deleteDir(new File(appDir, s));
                Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
            }
        }
    }
}

public static boolean deleteDir(File dir) {
    if (dir != null && dir.isDirectory()) {
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
            boolean success = deleteDir(new File(dir, children[i]));
            if (!success) {
                return false;
            }
        }
    }

    return dir.delete();
}
private void loadSavedPreferences() {    
    //User has successfully logged in, save this information
    // We need an Editor object to make preference changes.
   
    SharedPreferences settings = getSharedPreferences(index.PREFS_NAME, 0);
    SharedPreferences.Editor editor = settings.edit();
    editor.clear();
    editor.commit();

	}

}
