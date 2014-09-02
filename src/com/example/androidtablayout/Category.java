package com.example.androidtablayout;

import com.example.androidtablayout.Feed.MyJavaScriptInterface;

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
import android.view.LayoutInflater.Filter;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

public class Category extends Activity {
	ImageView bck,bck1;
	 String prasi,token_fb,accesskey,accesstoken;
	 String cat;
    final MyJavaScriptInterface myJavaScriptInterface= new MyJavaScriptInterface(this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel);   
       
        Intent i=getIntent();
        accesskey=i.getStringExtra("accesskey");
        accesstoken=i.getStringExtra("accesstoken");
        token_fb=i.getStringExtra("fb_token");
        prasi=i.getStringExtra("userid");
        cat=i.getStringExtra("category");
        WebView web=(WebView)findViewById(R.id.webview);
        bck=(ImageView)findViewById(R.id.imageView2);
        bck1=(ImageView)findViewById(R.id.imageView3);
    	WebSettings settings=web.getSettings();
    	web.getSettings().setJavaScriptEnabled(true);
    	web.getSettings().setDomStorageEnabled(true);
    	web.addJavascriptInterface( myJavaScriptInterface, "Category_native");
    	web.loadUrl("file:///android_asset/www/searchscreen.html?catagory="+cat+"&user_id="+prasi);  
    	web.setWebChromeClient(new chromeClient());
    	
    	bck1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();	
				overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
				
			}
		});
    	
    	bck.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			finish();	
			overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
			}
			
		});
    }
    public class MyJavaScriptInterface {
		Context mContext;

	    MyJavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	    public void commentpage(String userid, String vid){
			  Intent i=new Intent(Category.this,comment.class);
			  i.putExtra("userid", userid);
			  i.putExtra("vid", vid);
			  i.putExtra("fb_token", token_fb);
			   i.putExtra("accesskey", accesskey);
		       i.putExtra("accesstoken", accesstoken);
			  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			  startActivity(i, bndlanimation);
		    //  	finish(); 
		    	
		  }  
	    
	   public void showOtherProfile(String otherid, String userid){
		   
		   Intent i=new Intent(Category.this,Other_profile.class);
		   i.putExtra("userid", userid);
		   i.putExtra("otherid", otherid);
		   i.putExtra("fb_token", token_fb);
		   i.putExtra("accesskey", accesskey);
	       i.putExtra("accesstoken", accesstoken);
		   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
	    	//finish();
		   
	   } 
public void likelist(String vid, String userid){
		   
		   Intent i=new Intent(Category.this,likelist.class);
		   i.putExtra("userid", userid);
		   i.putExtra("vid", vid);
		   i.putExtra("fb_token", token_fb);
		   i.putExtra("accesskey", accesskey);
	       i.putExtra("accesstoken", accesstoken);
		  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
		   startActivity(i, bndlanimation);
	    	//finish();
		   
	   } 
	    
 public void resediolist(String vid, String userid){
	   
	   Intent i=new Intent(Category.this,resediolist.class);
	   i.putExtra("userid", userid);
	   i.putExtra("vid", vid);
	   i.putExtra("fb_token", token_fb);
	   i.putExtra("accesskey", accesskey);
       i.putExtra("accesstoken", accesstoken);
	  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
	   startActivity(i, bndlanimation);
  	//finish();
	   
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
