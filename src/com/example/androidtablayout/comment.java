package com.example.androidtablayout;



import com.example.androidtablayout.Profile.MyJavaScriptInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class comment extends Activity
{
	WebView webView;
	EditText Msg;
	Button btnSendMsg;
	/** Called when the activity is first created. */
	 String userid;
	 String vid,f,otp;
	String back;
	 String comm;
	 ImageView img,im1,im2,im3;
	 int d;
	 
	 
	 final MyJavaScriptInterface myJavaScriptInterface= new MyJavaScriptInterface(this);
	 
    @Override
    @JavascriptInterface

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);
        Intent o=getIntent();
        userid=o.getStringExtra("userid");
        vid=o.getStringExtra("vid");
        back=o.getStringExtra("back_btn");
      //  d=Integer.parseInt("back");
       // d=o.getIntExtra(f, back_btn);
        
        Toast.makeText(comment.this, vid , Toast.LENGTH_LONG).show();

        
        img=(ImageView)findViewById(R.id.images);
        im1=(ImageView)findViewById(R.id.imageView1);
        im2=(ImageView)findViewById(R.id.imageView2);
        im3=(ImageView)findViewById(R.id.imageView3);
        webView = (WebView)findViewById(R.id.webview);
        webView.setBackgroundResource(R.drawable.smiley);
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
         webView.addJavascriptInterface( myJavaScriptInterface, "Comment_native");
         webView.loadUrl("file:///android_asset/www/comment.html?user_id="+userid+"&v_id="+vid);
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
	    
	    public void showOtherProfile(String otherid, String userid){
			   
			   Intent i=new Intent(comment.this,Other_profile.class);
			   i.putExtra("userid", userid);
			   i.putExtra("otherid", otherid);
			   Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			   startActivity(i, bndlanimation);
		    	//finish();
			   
		                    } 

	    
	    
	}
        
        
    }
    
    
    
