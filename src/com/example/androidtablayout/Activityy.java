package com.example.androidtablayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class Activityy extends Activity {
	private static final ScheduledExecutorService worker = 
			  Executors.newSingleThreadScheduledExecutor();
	 String prasi;
	 Context context;	 
	//  int size=17;
	 public int ab;
	 SQLiteDatabase db1=null;
     String DBNAME="Notification.db";
	 private static final int NOTIFY_ME_ID=1337;
	 SessionManager session;
final MyJavaScriptInterface myJavaScriptInterface= new MyJavaScriptInterface(this);
	 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songs_layout);      
        db1=openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
        db1.execSQL("CREATE TABLE IF NOT EXISTS notification(NAME VARCHAR);");
        try {
			loadActivity();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
 public void loadActivity() throws InterruptedException{
	 WebView web=(WebView)findViewById(R.id.webview);
       Intent i=getIntent();
       prasi=i.getStringExtra("userid");      
   	WebSettings settings=web.getSettings();
   	web.getSettings().setJavaScriptEnabled(true);
   	web.getSettings().setDomStorageEnabled(true);
   	web.setBackgroundColor(0);
   	web.setBackgroundResource(R.drawable.smiley);
   	web.loadUrl("file:///android_asset/www/activityscreen.html?user_id="+prasi);  
   	web.addJavascriptInterface( myJavaScriptInterface, "Activityy_native");
   	web.setWebChromeClient(new WebChromeClient());
   	web.loadUrl("file:///android_asset/www/activityscreen.html?user_id="+prasi); 
   	//Thread.sleep(1000);
   //	loadActivity();
   Runnable task = new Runnable() {
        public void run() {
          
        	try {
				loadActivity();
				//Toast.makeText(getApplicationContext(), "Welcome to thread", Toast.LENGTH_LONG).show();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
      };
      worker.schedule(task,30, TimeUnit.SECONDS);
      
 }
	public String getliveurl(){
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	 String restoredText = sharedPreferences.getString("liveurl", null);
	System.out.println("liver url of activityy screen");
	return restoredText;
	 
}
    public class MyJavaScriptInterface {
  		
		Context mContext;

  	    MyJavaScriptInterface(Context c) {
  	        mContext = c;
  	    }
  	  @JavascriptInterface
  	    public String Postonfacebook1(){
  		    	String str = getliveurl();
  		    	return str;
  			    	
  			  } 
  	    @JavascriptInterface
  	    public void showOtherProfile(String otherid, String userid){
  			   
  			   Intent i=new Intent(Activityy.this,Other_profile.class);
  			   i.putExtra("userid", userid);
  			   i.putExtra("otherid", otherid);
  		  	   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
  			   startActivity(i,bndlanimation);
  		    	//finish();
  			   
  		   } 
  	    @JavascriptInterface
  	     	  public void activity_detail(String otherid, String userid, String vid){
  		  
  		  Intent i=new Intent(Activityy.this,Activity_detail.class);
  		  i.putExtra("userid",userid);
  		  i.putExtra("otherid",otherid);
  		  i.putExtra("vid",vid);
  		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
  	      startActivity(i,bndlanimation);
  	    //	finish();   	    	
  	  }    
  	  public void notify(int len,String[] data) throws JSONException{  
	    	String light=test();
	    	String vibrate=test2();
	    	String sound=test3();
	    	//Toast.makeText(getApplicationContext(), "shared preference value in notify-------->  "+light, Toast.LENGTH_LONG).show();
	    //	int counter=samp(len);	    
	    	//Toast.makeText(getApplicationContext(), "Lenth of notification--->"+len, Toast.LENGTH_LONG).show();
	    	// Toast.makeText(getApplicationContext(), "counter value--->"+counter, Toast.LENGTH_LONG).show();    	 
	 
	   	//int str=data.length;  	    	
	    	//Toast.makeText(getApplicationContext(), "length of arra value  "+str, Toast.LENGTH_LONG).show(); 
	     	  	     
	     Cursor c=db1.rawQuery("SELECT * FROM notification", null);
	     if(c!=null){
	    	 if(c.moveToFirst()){
	    		 do{
	    		 String name=c.getString(c.getColumnIndex("NAME"));
	    		 System.out.println(name);
	    	 }while(c.moveToNext());}
	    	 ab=c.getCount();
	    	 System.out.println("the length of column"+ab);
	    	// Toast.makeText(getApplicationContext(), "Thw count value is  "+ab, Toast.LENGTH_LONG).show();
	     }
	    	
	     
		//int size=preferences.getInt("username", 0);
	    	if(len!=ab){	    		
	    		int dif=len-ab;
	    		//Toast.makeText(getApplicationContext(), "differ value--->"+dif, Toast.LENGTH_LONG).show();
	    		for(int j=0;j<dif;j++){
	    		//Toast.makeText(getApplicationContext(), "Entry to notification  "+data[j], Toast.LENGTH_LONG).show();
	    	final NotificationManager mgr=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);		
			Notification note=new Notification(R.drawable.icon,data[j],System.currentTimeMillis());
			
			// This pending intent will open after notification click
			PendingIntent i = PendingIntent.getActivity(getBaseContext(), 0, new Intent(getBaseContext(), splash.class),0);
			
			note.setLatestEventInfo(getBaseContext(), "Notification From Sedio","New Message from    "+data[j], i);
			note.defaults |=Notification.DEFAULT_LIGHTS;
			note.defaults |=Notification.DEFAULT_VIBRATE;
			note.defaults |=Notification.DEFAULT_SOUND;
							
			//After uncomment this line you will see number of notification arrived
			note.number=2;
			mgr.notify(NOTIFY_ME_ID, note);
			
			//note.defaults |= Notification.DEFAULT_ALL;			
			//size++;
			db1.execSQL("INSERT INTO notification (NAME) VALUES ('"+data[j]+"');");
			if(light.equals("true"))
			{
				//Toast.makeText(getApplicationContext(), "sucessfull on the screen lights", Toast.LENGTH_LONG).show();
				/*PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);		
		         boolean isScreenOn = pm.isScreenOn();		
		         Log.e("screen on.................................", ""+isScreenOn);		
		         if(isScreenOn==false)
		         {		
			        WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.ON_AFTER_RELEASE,"MyLock");		
			        wl.acquire(10000);
			        WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyCpuLock");		
			        wl_cpu.acquire(10000);
		         }*/
				note.defaults |=Notification.DEFAULT_LIGHTS;
           }
			if(vibrate.equals("true")){
				//Toast.makeText(getApplicationContext(), "sucessfull on the vibration", Toast.LENGTH_LONG).show();
				/*AudioManager am;
				am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
				am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);*/
				note.defaults |=Notification.DEFAULT_VIBRATE;
			}
			if(sound.equals("true")){
				note.defaults |= Notification.DEFAULT_SOUND;
			}

		}
	    		//ab=ab+dif;
	    		//int samp1(counter);
	    		//SharedPreferences.Editor editor = app_preferences.edit();
	    	  //  editor.putInt("counter",counter=counter+dif);
	    	  //  editor.commit();
	    		//Toast.makeText(getApplicationContext(), "modify counter value"+ab, Toast.LENGTH_LONG).show();
	    	}
	    
	    }

	
  	}
	private int samp(int len) {
		// TODO Auto-generated method stub
		 SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
    	 int counter = app_preferences.getInt("counter", len);
		return counter;
	}
    private String test() {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		String restoredText = sharedPreferences.getString("light", null);
		// Toast.makeText(getApplicationContext(),"Test method in activityy  "+ restoredText, Toast.LENGTH_LONG).show();
		return restoredText;
	}
    private String test2() {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		String restoredText1 = sharedPreferences.getString("vibrate", null);
		// Toast.makeText(getApplicationContext(),"Test method in activityy  "+ restoredText1, Toast.LENGTH_LONG).show();
		return restoredText1;
	}
    private String test3() {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		String restoredText3 = sharedPreferences.getString("sound", null);
		// Toast.makeText(getApplicationContext(),"Test method in activityy  "+ restoredText3, Toast.LENGTH_LONG).show();
		return restoredText3;
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
            Toast.makeText(Activityy.this, "Logout", Toast.LENGTH_SHORT).show();
            clearApplicationData();
            
            Intent i=new Intent(Activityy.this,splash.class);
            startActivity(i);
            finish();
            return true;
        case R.id.menu_save:
        	Toast.makeText(Activityy.this, "Clear Cache", Toast.LENGTH_SHORT).show();
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
}
