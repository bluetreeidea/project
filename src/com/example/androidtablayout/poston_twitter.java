package com.example.androidtablayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.androidtablayout.Feed.MyJavaScriptInterface;
import com.example.microcam.upload;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.drm.DrmErrorEvent;
import android.drm.DrmManagerClient;
import android.drm.DrmManagerClient.OnErrorListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

	public class poston_twitter extends Activity {
	ImageView bck,bck1;
	TextView bck3;
	 String prasi;
	private String vidd,userid,back;
	private JSONArray Facebook_jsonarray;
	private String Signup_status;
	private String Facebook_status;
	private String Str_Facebook;
	private String poster1;
	private String des= "nothing";
	private TextView pack;
	private EditText at;
	private String desc;
	private String get_status;
	private JSONArray get_jsonarray;
	private String Str_get;
	private String get_title;
	private String get_pstrr;
	JSONObject m;
	private ImageView img1;
	private String trm;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_twitter);   
       
        
        Intent o=getIntent();
        userid=o.getStringExtra("userid");
        vidd=o.getStringExtra("vidd");
   //     poster1=o.getStringExtra("poster");
   //     back=o.getStringExtra("back_btn");
        
       // Toast.makeText(poston_twitter.this, vidd , Toast.LENGTH_LONG).show();
     //   Toast.makeText(poston_twitter.this, userid , Toast.LENGTH_LONG).show();
       // Toast.makeText(Poston_facebook.this, poster1 , Toast.LENGTH_LONG).show();
        
        
        
        bck=(ImageView)findViewById(R.id.imageView2);
        bck1=(ImageView)findViewById(R.id.imageView3);
        bck3=(TextView)findViewById(R.id.textView1);
        pack=(TextView)findViewById(R.id.textView2);
        at=(EditText)findViewById(R.id.autoCompleteTextView1);

     //   img1=(ImageView)findViewById(R.id.imageView1);

        
        bck3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			finish();	
			overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
			}
			
		});
        
   	
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

    	pack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			new twupload().execute((Void) null);	
				
			}
			
		});
    	
    	poster();	
    	
    	at.setText(get_title);
      
 	//img1.setImageBitmap(BitmapFactory.decodeFile());

    	
  	 // Toast.makeText(getApplicationContext(), get_pstrr, Toast.LENGTH_LONG).show();
  	  
  	  
  	try {
  	  ImageView i = (ImageView)findViewById(R.id.imageView1);
  	  Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(get_pstrr).getContent());
  	
  	  i.setImageBitmap(bitmap); 
  	
  	} catch (MalformedURLException e) {
  	  e.printStackTrace();
  	} catch (IOException e) {
  	  e.printStackTrace();
  	}
  	  
  	  
	
    	
    }
    public class MyJavaScriptInterface {
		Context mContext;

	    MyJavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	
    }
    

    
	private void mani() {
		// TODO Auto-generated method stub
		//	String trm;

		  desc = at.getText().toString();
		  int length = at.getText().length();
		 
		
		if (length<=120)
		{
			 trm=desc;
			 
			
	         if (( desc == null || desc.trim().equals("")||desc.trim().equals("%20")))
	    {
          	Toast.makeText(poston_twitter.this, "please enter a title", Toast.LENGTH_LONG).show();
	    }
	         	         
	    else
	   
	    {
	           desc=desc.replaceAll("\\\"", "<hash_with_space>");
	           desc=desc.replaceAll("\\\\", "<hash_without_space>");
	           desc=desc.replaceAll("\n", "%20%20");
	           desc=desc.replaceAll("\t", "%20%20");
	           trm=desc.replaceAll(" ", "%20");
	           
	           
	    	fbshare();
	    }
	}
	
	
	else
	{
		Toast.makeText(poston_twitter.this, "Caption to be 120 character or less", Toast.LENGTH_SHORT).show();	
	}
	
}
    
    
    private void fbshare()
    {
   	 
       	try{
       		

 
  	     	URL fbshare_url = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/twitterupdate?user_id="+userid+"&v_id="+vidd+"&description="+trm);
       		
  	     	System.out.println ("http://demo.cogzideltemplates.com/client/vine-clone/mobile/twitterupdate?user_id="+userid+"&v_id="+vidd+"&description="+trm);
  	     	
  	     	
  	     	//http://demo.cogzideltemplates.com/client/vine-clone/mobile/twitterupdate?user_id=142&v_id=444&description=happy
  	     	// fbupdate?user_id=132&v_id=430&description=world
  	     	
  	     	BufferedReader Facebook_reader;
	    
	    String Facebook_inputline;
	    System.out.println("Status test 2");
	    Facebook_reader = new BufferedReader(new InputStreamReader(fbshare_url.openStream()));
			
			
			while ((Facebook_inputline = Facebook_reader.readLine())!= null) 
			{
				
				  Str_Facebook += Facebook_inputline;
				  System.out.println(Str_Facebook);
				
			}
		
			Facebook_jsonarray = new JSONArray(Str_Facebook);
		
	  for(int i=0;i<Facebook_jsonarray.length();i++){
		  JSONObject m = Facebook_jsonarray.getJSONObject(i);
		  Facebook_status =	m.getString("status");
		
		  System.out.println("Status"+Facebook_status);
		  
	  Toast.makeText(getApplicationContext(), Signup_status, Toast.LENGTH_LONG).show();
	                                                }
    
	  
	}
      
		catch (Exception e) {
			
			e.printStackTrace();
		  
		}
		
            	
       	
	
	}
    
    
    
    private class twupload extends AsyncTask <Void, Void, Void>  
    {
             private ProgressDialog Dialog = new ProgressDialog(poston_twitter.this);

             protected void onPreExecute()
             {
                 Dialog.setMessage("sharing on Twitter ...");
                 Dialog.show();
             }
             
             @Override
         protected Void doInBackground(Void... params) 
             {
            // Do your background data fetching here 
            try
            {
            	
            	mani();
            	
            	
            }
            catch(Exception e)
            {
            }
            
           	  
                return null;   
         }
         

             protected void onPostExecute(Void unused)    
             {
                 try
                 {
                  //   if(Dialog.isShowing())
                   //  {
               
                Dialog.dismiss();
 
                finish();
       			overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 

       	 
                    // }
                             // do your Display and data setting operation here
                	
                 }
                 catch(Exception e) 
                 {
                	 
                 }
    
}
    }
    
    
    
    private void poster()
    {
   	 
       	try{
       		

 
  	     	URL getshare_url = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/getvideo?v_id="+vidd);
       		
  	     	System.out.println ("http://demo.cogzideltemplates.com/client/vine-clone/mobile/getvideo?v_id="+vidd);
  	     	
  	     	
  	     	//http://demo.cogzideltemplates.com/client/vine-clone/mobile/twitterupdate?user_id=142&v_id=444&description=happy
  	     	// fbupdate?user_id=132&v_id=430&description=world
  	     	
  	     	BufferedReader get_reader;
	    
	    String get_inputline;
	    System.out.println("Status test 3");
	    get_reader = new BufferedReader(new InputStreamReader(getshare_url.openStream()));
			
			
			while ((get_inputline = get_reader.readLine())!= null) 
			{
				
				  Str_get = get_inputline;
				  System.out.println(Str_get);
				
			}
		
			get_jsonarray = new JSONArray(Str_get);
		
			  
		  JSONObject m = get_jsonarray.getJSONObject(0);
		
		  get_status =	m.getString("status");
		  get_title =	m.getString("videotitle");
		  get_pstrr =	m.getString("videoposter");
		
	                                                
	  	System.out.println("Status"+get_status);
		System.out.println("videotitle"+get_title);
		System.out.println("videoposter"+get_pstrr);
    
	}      
		catch (Exception e) {
			
			e.printStackTrace();
		  
		}
		
            	
       	
	
	}
    
    
    
}
