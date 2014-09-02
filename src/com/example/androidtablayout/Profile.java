package com.example.androidtablayout;
/*
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class Profile extends Activity {
	 String prasi;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        WebView web=(WebView)findViewById(R.id.webview);
        Intent i=getIntent();
        prasi=i.getStringExtra("userid");
        Toast.makeText(Profile.this, prasi, Toast.LENGTH_LONG).show();
    	WebSettings settings=web.getSettings();
    	web.getSettings().setJavaScriptEnabled(true);
    	web.loadUrl("file:///android_asset/www/profilescreen.html?user_id="+prasi);
    }
}
*/




import java.io.File;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.drm.DrmErrorEvent;
import android.drm.DrmManagerClient;
import android.drm.DrmManagerClient.OnErrorListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

@SuppressLint("ShowToast")
public class Profile extends Activity {
  	  String prasi,token_fb,accesskey,accesstoken,vid;
  	SessionManager session;
	private static final String TAG = null;
	private WebView mWebView;

    final MyJavaScriptInterface myJavaScriptInterface= new MyJavaScriptInterface(this);

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
	
        WebView webView = (WebView) findViewById(R.id.webview);
 //---      
        
        Intent i=getIntent();
        prasi=i.getStringExtra("userid");
        token_fb=i.getStringExtra("fb_token");
        accesskey=i.getStringExtra("accesskey");
        accesstoken=i.getStringExtra("accesstoken");
     //   Toast.makeText(Profile.this, prasi, Toast.LENGTH_LONG).show();
        
    //------  
        WebSettings webSettings = webView.getSettings();
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(false);
        webSettings.setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new chromeClient());
        
        webView.setBackgroundColor(0);
        webView.setBackgroundResource(R.drawable.smiley);
    	//Toast.makeText(Profile.this, "Please be patient-App get loading", Toast.LENGTH_LONG).show();

        webView.addJavascriptInterface( myJavaScriptInterface, "Profile_native");
        webView.loadUrl("file:///android_asset/www/profilescreen.html?user_id="+prasi+"&fb_token="+token_fb+"&accesskey="+accesskey+"&accesstoken="+accesstoken);
	}
	
	public String getliveurl(){
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	 String restoredText = sharedPreferences.getString("liveurl", null);
	System.out.println("liver url of activityy screen");
	return restoredText;
	 
}
	
	public class MyJavaScriptInterface {
		Context mContext;
		private String f;

	    MyJavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	    
	    public void settings(String userid){
			   
			   Intent i=new Intent(Profile.this,Profile_settings.class);
			   i.putExtra("userid", userid);
			   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			   startActivity(i, bndlanimation);
			  
			                   } 
	    public String Postonfacebook1(){
	    	String str = getliveurl();
	    	return str;
		    	
		  }
	    
	    public void tst()  {
			   
	 	   Intent i=new Intent(Profile.this,findpeopleemail.class);
		   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
			  
			                } 
	    
	    public void showOtherProfile(String otherid, String userid){
			   
			   Intent i=new Intent(Profile.this,Other_profile.class);
			   i.putExtra("userid", userid);
			   i.putExtra("otherid", otherid);
			   i.putExtra("fb_token", token_fb);
			   i.putExtra("accesskey", accesskey);
		        i.putExtra("accesstoken", accesstoken);
		        i.putExtra("vid", vid);
			   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			   startActivity(i, bndlanimation);
		    	//finish();
			   
		                    } 
	    
	  public void commentpage(String userid, String vid){
		  
		  Intent i=new Intent(Profile.this,comment.class);
		  i.putExtra("back_btn", "profile");
		  i.putExtra("userid", userid);
		  i.putExtra("vid", vid);
		  
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		  startActivity(i, bndlanimation); 
	    //	finish(); 
	    	
	  }  
	  public void followrlist(String userid){
		   
		   Intent i=new Intent(Profile.this,Profilelist.class);
		   i.putExtra("userid", userid);
		  
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
	    	//finish();
		   
	   } 
	  
	  public void followinglist(String userid){
		   
		   Intent i=new Intent(Profile.this,followinglist.class);
		   i.putExtra("userid", userid);
		 
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
	    	//finish();
		   
	   } 
	  
	  public void likelist(String vid, String userid){
		   
		   Intent i=new Intent(Profile.this,likelist.class);
		   i.putExtra("userid", userid);
		   i.putExtra("vid", vid);
		 
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
	    	//finish();
		   
	   } 
	  
	  public void resediolist(String vid, String userid){
		   
		   Intent i=new Intent(Profile.this,resediolist.class);
		   i.putExtra("userid", userid);
		   i.putExtra("vid", vid);
		  
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
	  	//finish();
		   
	 } 
	    
	  public void Postonfacebook(String vidd,String userid,String title){
		  f="feed";
		  Intent i=new Intent(Profile.this,fbsharedes.class);
		  i.putExtra("back_btn",f);
		 i.putExtra("userid", userid);
		  i.putExtra("vidd", vidd);
		  i.putExtra("title", title);
		
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		  startActivity(i, bndlanimation);
	    //  	finish(); 
		  
	    	
	  }  
    @JavascriptInterface
    
    public void Postontwitter(String vidd){
		  f="feed";
		  Intent i=new Intent(Profile.this,poston_twitter.class);
		  i.putExtra("back_btn",f);
		  i.putExtra("userid", prasi);
		  i.putExtra("vidd", vidd);
		
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		  startActivity(i, bndlanimation);
	    //  	finish(); 
	    	
	  }  
    
	  
	  
	  
	}
	
        public class chromeClient extends WebChromeClient implements OnCompletionListener,  
        OnErrorListener{
            private WebView wv;
            private VideoView mVideoView;
            private LinearLayout mContentView;
            private FrameLayout mCustomViewContainer;
            private WebChromeClient.CustomViewCallback mCustomViewCallback;
            FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new   
        FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                if (view instanceof FrameLayout) {
                    wv = (WebView)findViewById(R.id.webview);
                    mCustomViewContainer = (FrameLayout) view;
                    mCustomViewCallback = callback;
                    mContentView = (LinearLayout)findViewById(R.id.linearlayout);
                    if (mCustomViewContainer.getFocusedChild() instanceof VideoView) {
                        mVideoView = (VideoView) mCustomViewContainer.getFocusedChild();
                       // frame.removeView(video);
                        mContentView.setVisibility(View.GONE);
                        mCustomViewContainer.setVisibility(View.VISIBLE);
                        setContentView(mCustomViewContainer);
                        mVideoView.setOnCompletionListener(this);
                        mVideoView.setOnErrorListener((android.media.MediaPlayer.OnErrorListener) this);
                        mVideoView.start();

                    }
                }
            }

            public void onHideCustomView() {
                if (mVideoView == null){
                    return;
                }else{
                // Hide the custom view.
                mVideoView.setVisibility(View.GONE);
                // Remove the custom view from its container.
                mCustomViewContainer.removeView(mVideoView);
                mVideoView = null;
                mCustomViewContainer.setVisibility(View.GONE);
                mCustomViewCallback.onCustomViewHidden();
                // Show the content view.
                mContentView.setVisibility(View.VISIBLE);
                }
            }


            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mCustomViewContainer.setVisibility(View.GONE);
                onHideCustomView();
                setContentView(mContentView);
            }

            public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
                setContentView(mContentView);
                return true;
            }
        
         

			@Override
			public void onError(DrmManagerClient client, DrmErrorEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			
			   public void onPrepared(MediaPlayer mp) {

			        mCustomViewCallback.onCustomViewHidden();
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
            	//session.logoutUser();
                Toast.makeText(Profile.this, "Logout", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Profile.this,splash.class);
                clearApplicationData();
                startActivity(i);
                finish();
                return true;
            case R.id.menu_save:
            	Toast.makeText(Profile.this, "Clear Cache", Toast.LENGTH_SHORT).show();
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
	
