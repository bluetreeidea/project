package com.example.androidtablayout;

import com.example.androidtablayout.Profile.MyJavaScriptInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
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
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class Other_profile extends Activity {
	
    final MyJavaScriptInterface myJavaScriptInterface= new MyJavaScriptInterface(this);

	WebView webView;
	EditText Msg;
	Button btnSendMsg;
	/** Called when the activity is first created. */
	String userid,token_fb,accesskey,accesstoken,vid;
	String otherid;
	String otp;
	ImageView img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_profile_single);
        Intent o=getIntent();
        userid=o.getStringExtra("userid");
        otherid=o.getStringExtra("otherid");
        token_fb=o.getStringExtra("fb_token");
        accesskey=o.getStringExtra("accesskey");
        accesstoken=o.getStringExtra("accesstoken");
        vid=o.getStringExtra("vid");
       // comm=o.getStringExtra("comment");
        
        img=(ImageView)findViewById(R.id.images);
        img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
		  	overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 

			}
		});
        webView = (WebView)findViewById(R.id.webview);
        
         WebSettings webSettings = webView.getSettings();
         webSettings.setPluginState(WebSettings.PluginState.ON);
         webSettings.setJavaScriptEnabled(true);
         webSettings.setUseWideViewPort(false);
         webSettings.setLoadWithOverviewMode(true);
       // webView.setWebChromeClient(new chromeClient());
     //   webView.addJavascriptInterface(new sangu(), "locater");
        webView.addJavascriptInterface( myJavaScriptInterface, "Other_profile_native");
        webView.loadUrl("file:///android_asset/www/otherprofilescreen.html?user_id="+userid+"&other_id="+otherid+"&fb_token="+token_fb+"&accesskey="+accesskey+"&vid="+vid+"&accesstoken="+accesstoken);
        
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
	    
	    public String Postonfacebook1(){
	    	String str = getliveurl();
	    	return str;
		    	
		  } 
	    
	    public void showOtherProfile(String otherid, String userid){
			   
			   Intent i=new Intent(Other_profile.this,Other_profile.class);
			   i.putExtra("userid", userid);
			   i.putExtra("otherid", otherid);
			   i.putExtra("fb_token", token_fb);
			   i.putExtra("accesskey", accesskey);
		       i.putExtra("accesstoken", accesstoken);
		  	   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			   startActivity(i,bndlanimation);
		    	//finish();
			   
		   } 
	    
	  public void commentpage(String userid, String vid,String token_fb,String accesskey,String accesstoken){
		  
		  Intent i=new Intent(Other_profile.this,comment.class);
		  i.putExtra("back_btn", otp);
		  i.putExtra("userid", userid);
		  i.putExtra("vid", vid);
		  i.putExtra("fb_token", token_fb);
		  i.putExtra("accesskey", accesskey);
	      i.putExtra("accesstoken", accesstoken);
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
	      startActivity(i,bndlanimation);
	    //	finish(); 
	    	
	  }  
	  public void followrlist(String userid,String token_fb,String accesskey,String accesstoken){
		   
		   Intent i=new Intent(Other_profile.this,Profilelist.class);
		   i.putExtra("userid", userid);
		   i.putExtra("fb_token", token_fb);
			  i.putExtra("accesskey", accesskey);
		      i.putExtra("accesstoken", accesstoken);
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
	    	//finish();
		   
	   } 
	  
	  public void followinglist(String userid,String token_fb,String accesskey,String accesstoken){
		   
		   Intent i=new Intent(Other_profile.this,followinglist.class);
		   i.putExtra("userid", userid);
		   i.putExtra("fb_token", token_fb);
			  i.putExtra("accesskey", accesskey);
		      i.putExtra("accesstoken", accesstoken);
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
	    	//finish();
		   
	   } 
	  
	  public void likelist(String vid, String userid,String token_fb,String accesskey,String accesstoken){
		   
		   Intent i=new Intent(Other_profile.this,likelist.class);
		   i.putExtra("userid", userid);
		   i.putExtra("vid", vid);
		   i.putExtra("fb_token", token_fb);
			  i.putExtra("accesskey", accesskey);
		      i.putExtra("accesstoken", accesstoken);
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
	    	//finish();
		   
	   } 
	  
	  public void resediolist(String vid, String userid,String token_fb,String accesskey,String accesstoken){
		   
		   Intent i=new Intent(Other_profile.this,resediolist.class);
		   i.putExtra("userid", userid);
		   i.putExtra("vid", vid);
		   i.putExtra("fb_token", token_fb);
			  i.putExtra("accesskey", accesskey);
		      i.putExtra("accesstoken", accesstoken);
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
	  	//finish();
		   
	 } 
	  
	  public void Postonfacebook(String vidd,String userid,String title){
		  f="feed";
		  Intent i=new Intent(Other_profile.this,fbsharedes.class);
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
		  Intent i=new Intent(Other_profile.this,poston_twitter.class);
		  i.putExtra("back_btn",f);
		  i.putExtra("userid", userid);
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
    

}
