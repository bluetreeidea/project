package com.example.androidtablayout;



import com.example.androidtablayout.Feed.chromeClient;
import com.example.androidtablayout.Profile.MyJavaScriptInterface;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class moreoption extends Activity
{
	WebView webView;
	EditText Msg;
	Button btnSendMsg;
	/** Called when the activity is first created. */
	 String prasi,token_f;
	 String vid,f,otp,fbid;
	String back;
	 String comm;
	 ImageView img,im1,im2,im3;
	 int d;
	 
	 
	 final MyJavaScriptInterface myJavaScriptInterface= new MyJavaScriptInterface(this);
	 
    @Override
    @JavascriptInterface

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.likelist);
        Intent o=getIntent();
        prasi=o.getStringExtra("userid");
        vid=o.getStringExtra("vid");
        back=o.getStringExtra("back_btn");
        token_f=o.getStringExtra("fb_tk");
        Toast.makeText(moreoption.this, token_f , Toast.LENGTH_LONG).show();
      //  d=Integer.parseInt("back");
       // d=o.getIntExtra(f, back_btn);
        
        img=(ImageView)findViewById(R.id.images);
        im2=(ImageView)findViewById(R.id.imageView2);
        im3=(ImageView)findViewById(R.id.imageView3);
        webView = (WebView)findViewById(R.id.webview);
        img.setOnClickListener(new OnClickListener() {
			
		//	private String sang;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
				finish();
				overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
				// Details.this.overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
				
							}
		});
        
         WebSettings webSettings = webView.getSettings();
         webSettings.setPluginState(WebSettings.PluginState.ON);
         webSettings.setJavaScriptEnabled(true);
         webSettings.setUseWideViewPort(false);
         webSettings.setLoadWithOverviewMode(true);

         webView.addJavascriptInterface( myJavaScriptInterface, "moreoption_native");
         webView.loadUrl("file:///android_asset/www/moreoption.html?user_id="+prasi+"&v_id="+vid+"&fb_tok="+token_f);
         
         webView.setWebChromeClient(new WebChromeClient());

	}
	
    
    
	public class MyJavaScriptInterface {
		Context mContext;

	    MyJavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    

	    
	    public void showOtherProfile(String otherid, String userid){
			   
			   Intent i=new Intent(moreoption.this,Other_profile.class);
			   i.putExtra("userid", userid);
			   i.putExtra("otherid", otherid);
			   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			   startActivity(i, bndlanimation);
		    	//finish();
			   
		                    } 

	    
	    
	}
        
        
    }