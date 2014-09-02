package com.example.androidtablayout;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class search extends Activity {
	
	WebView webView;
	EditText Msg;
	Button btnSendMsg;
	String userid;
	String token_fb;
	String back;
	String comm;
	
	ImageView img,im1,im2,im3;
	
	final MyJavaScriptInterface myJavaScriptInterface= new MyJavaScriptInterface(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchscreen);
        Intent o=getIntent();
        userid=o.getStringExtra("userid");
        token_fb=o.getStringExtra("fb_token");
      //  d=Integer.parseInt("back");
       // d=o.getIntExtra(f, back_btn);
         Msg=(EditText)findViewById(R.id.TextView1);
         im1=(ImageView)findViewById(R.id.imageView1);
         im2=(ImageView)findViewById(R.id.imageView2);
         im3=(ImageView)findViewById(R.id.imageView3);
         webView = (WebView)findViewById(R.id.webView1);
         WebSettings webSettings = webView.getSettings();
         webSettings.setPluginState(WebSettings.PluginState.ON);
         webSettings.setJavaScriptEnabled(true);
         webSettings.setUseWideViewPort(false);
         webSettings.setLoadWithOverviewMode(true);
         webView.setWebChromeClient(new WebChromeClient());
         webView.addJavascriptInterface( myJavaScriptInterface, "search_native");
         webView.loadUrl("file:///android_asset/www/explorescreen.html?user_id="+userid+"&fb_token="+token_fb);
         im3.setOnClickListener(new OnClickListener() {
 			
     		//	private String sang;

     			@Override
     			public void onClick(View v) {
     				// TODO Auto-generated method stub
     		
     				finish();
     				overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
     				// Details.this.overridePendingTransition(R.anim.trans_right_in,R.anim.trans_right_out);
     				
     							}
     		});
         im2.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub

 				finish();
 				overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
 				
 			}
 		});
         
        im1.setOnClickListener(new OnClickListener() {
 			@JavascriptInterface
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				
 				 String msgToSend=Msg.getText().toString();
 		        msgToSend=msgToSend.trim();
 		        comm=msgToSend;
 		        msgToSend=msgToSend.replaceAll("#","");
 		       //  Toast.makeText(getApplicationContext(), msgToSend , Toast.LENGTH_SHORT).show();
 		         webView.loadUrl("javascript:callFromActivity(\""+msgToSend+"\")");
 		         
 			}
 		}); 
        
        
        
        
	}	
    public String getliveurl(){
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	 String restoredText = sharedPreferences.getString("liveurl", null);
	return restoredText;
	 
}

	
   public class MyJavaScriptInterface {
		Context mContext;
		private String f;

	    MyJavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	    
	    public void showOtherProfile(String otherid, String userid)
      
	    {
			   
			   Intent i=new Intent(search.this,Other_profile.class);
			   i.putExtra("userid", userid);
			   i.putExtra("otherid", otherid);
		  	   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			   startActivity(i,bndlanimation);
		    	//finish();
			   
		   } 
	    public String Postonfacebook1(){
	    	String str = getliveurl();
	    	return str;
		    	
		  } 
	    
	    public void showtag(String otherid, String videoid)
	      
	    {
			   
			   Intent i=new Intent(search.this,tagsearch.class);
			   i.putExtra("userid", userid);
			   i.putExtra("otherid", otherid);
			   i.putExtra("videoid", videoid);
			   i.putExtra("Tag",comm);
		  	   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			   startActivity(i,bndlanimation);
		    	//finish();
			   
		   } 
	    public void Postonfacebook(String userid,String vidd,String title){
			  f="feed";
			  Intent i=new Intent(search.this,fbsharedes.class);
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
			  Intent i=new Intent(search.this,poston_twitter.class);
			  i.putExtra("back_btn",f);
			  i.putExtra("userid", userid);
			  i.putExtra("vidd", vidd);
			
			  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			  startActivity(i, bndlanimation);
		    //  	finish(); 
		    	
		  }  
	    
	    
	    
	}
        
    
    
    
    
}
