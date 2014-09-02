package com.example.androidtablayout;

import com.example.androidtablayout.Feed.MyJavaScriptInterface;
import com.example.androidtablayout.Feed.chromeClient;
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
import android.view.LayoutInflater.Filter;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

	public class fbsharedes extends Activity {
	ImageView bck,bck1;
	TextView bck3;
	 String prasi;
	private String cat,vidd,userid,back,pst,title,image;
   
	final MyJavaScriptInterface myJavaScriptInterface= new MyJavaScriptInterface(this);
	
	private TextView post;
	private WebView web;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_fb);   
       
        
        Intent o=getIntent();
        vidd=o.getStringExtra("vidd");
        pst=o.getStringExtra("pst");
        userid=o.getStringExtra("userid");
        title=o.getStringExtra("title");

        
      //  Toast.makeText(poston_twitter.this, vidd , Toast.LENGTH_LONG).show();
   //     Toast.makeText(Poston_facebook.this, userid , Toast.LENGTH_LONG).show();
        
        
        
        web=(WebView)findViewById(R.id.webView1);
        bck=(ImageView)findViewById(R.id.imageView2);
        bck1=(ImageView)findViewById(R.id.imageView3);
        bck3=(TextView)findViewById(R.id.textView1);
        post=(TextView)findViewById(R.id.textView2);

        bck3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			finish();	
			overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
			}
			
		});
        
    
        
      WebSettings settings=web.getSettings();
    	web.getSettings().setJavaScriptEnabled(true);
    	web.getSettings().setDomStorageEnabled(true);
    web.addJavascriptInterface( myJavaScriptInterface, "face_native");  	
    	web.setWebChromeClient(new WebChromeClient());
    	//web.loadUrl("file:///android_asset/www/Twitt.html");  
    	web.loadUrl("file:///android_asset/www/moreoption.html?user_id="+userid+"&vide="+vidd+"&title="+title);  
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
    	
	post.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				post_button();
			}
			
		});
    	
    	

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
	    public String Postonfacebook1(){
	    	String str = getliveurl();
	    	return str;
		    	
		  } 

	    @JavascriptInterface
	    public void post_native1(){
		    	
	 	  	 Toast.makeText(getApplicationContext(), "shared successfully", Toast.LENGTH_LONG).show();
	 	    	finish();
	 	    	overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
	 	    	
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
    
    
    
    @JavascriptInterface
  	protected void post_button() {
  		
  		web.loadUrl("javascript:post_san()");
      	
  	}
      
   
      
    
}
