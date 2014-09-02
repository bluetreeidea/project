package com.example.microcam;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.androidtablayout.AndroidTabLayoutActivity;
import com.example.androidtablayout.R;
import com.example.androidtablayout.uploadchannel;
import com.example.microcam.MainActivity.VideoMergeTask;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



public class upload extends Activity {
	  Spinner spinner1;
	 String prasi;
	 String videomani;
	 ImageView img1,img2,img3,img4;
	 TextView t1;
	 EditText at,at2;
	 Switch st;
	 ToggleButton tButton, tButton2, tButton3;
	 String vine_status;
	 String fb_status;
	 String tw_status;
	 String desc;
	 String channel_status,maniya,schannel,channelch;
	 Bitmap bMapRotate;
	 String poster;
	 EditText chennal_text;
	 String desccc=null;
	 String setchannel;
	 String switchs , switchs2 , switchs3 ;
 
	 
	private String imageurl;
	private String Str_Signup;
	private String Str_Facebook;
	private String Str_Twitter;

	private JSONArray Signup_jsonarray;
	private JSONArray Facebook_jsonarray;
	private JSONArray Twitter_jsonarray;

	
	private String Signup_status;
	private String Facebook_status;
	
	private String passuserid;
	private String snapchat;
	private String stry;
	String deff="Add to Channel";
	private String Twitter_status;
	private ImageView red;
	private String buttoncolour=null;
	public static final String PREFS_NAME = "MyPrefsFile";
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploader);   
        
        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy); 
     }
        Intent o= getIntent();
        videomani = o.getStringExtra("sangu");
        passuserid = o.getStringExtra("userid");
        snapchat=o.getStringExtra("snapchat");
        schannel=o.getStringExtra("chvalue");
        desccc=o.getStringExtra("desc");
        setchannel=o.getStringExtra("setchvalue");
        
        switchs=o.getStringExtra("switchstate");
       
        switchs2=o.getStringExtra("switchstate2");
        
        switchs3=o.getStringExtra("switchstate3");
        buttoncolour=o.getStringExtra("btnvaluech");


	
        //at.setText(String.valueOf(desccc));
    
       Toast.makeText(upload.this, switchs , Toast.LENGTH_LONG).show();
       Toast.makeText(upload.this, switchs2 , Toast.LENGTH_LONG).show();
       Toast.makeText(upload.this, switchs3 , Toast.LENGTH_LONG).show();

        // Toast.makeText(upload.this, videomani , Toast.LENGTH_LONG).show();
        // Toast.makeText(upload.this, snapchat , Toast.LENGTH_LONG).show();
        //  Toast.makeText(upload.this, desccc , Toast.LENGTH_LONG).show();
        


       
        Log.e("Debug","PATH FOR CHECK "+videomani);
        
        img1=(ImageView)findViewById(R.id.imageView1);
        img2=(ImageView)findViewById(R.id.images);
        img3=(ImageView)findViewById(R.id.camera);
        img4=(ImageView)findViewById(R.id.ImageView01);
        
        red=(ImageView)findViewById(R.id.ImageView08);

        t1=(TextView)findViewById(R.id.TextView06);
        at=(EditText)findViewById(R.id.autoCompleteTextView1);
      
        chennal_text=(EditText)findViewById(R.id.editchennal);
        at2=(EditText)findViewById(R.id.mainchannel);

        red.setVisibility(View.GONE);


        
       // spinner1=(Spinner)findViewById(R.id.spinner1);
        //spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        
    //  st=(Switch)findViewById(R.id.switch1);
     // st.setChecked(false);
       
       
   /*   st.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
			
				if (isChecked) {
					vine_status="no";
					
					} else {

						vine_status="yes";
					}
			}
		});
      */
        
        
        //----------------- switch sedio
      
      tButton = (ToggleButton) findViewById(R.id.toggleButton1); 
      
      
      if(switchs != null){
    	  
          tButton.setChecked(true);
	    	switchs="yes";
	    	vine_status="yes";

      }
      else if (switchs == null){
    	  
          tButton.setChecked(false);
          switchs=null;
          vine_status="no";
         
      }
   
      
      
      tButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    	   @Override
    	   public void onCheckedChanged(CompoundButton buttonView,
    	  
    			   boolean isChecked) {
    	    
    	    if(isChecked){
				
    	    	vine_status="yes";
  	          	switchs="yes";
  	       // Toast.makeText(upload.this, switchs , Toast.LENGTH_LONG).show();
   
    	    }else{
			
    	    	vine_status="no";
    	        switchs=null;
    	        //  Toast.makeText(upload.this, switchs , Toast.LENGTH_LONG).show();
    	    }

    	   }
    	  });
    	 
      
      //---------------switch fb share
      
      
      
      tButton2 = (ToggleButton) findViewById(R.id.toggleButton2); 
      
      if(switchs2 != null){
    	  
          tButton2.setChecked(false);
          fb_status=null;	   
          switchs2="yes";
      }
      else{
    	  
          tButton2.setChecked(true);
	      fb_status="yes";
          switchs2=null;

          
      }
      
      tButton2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    	   @Override
    	   public void onCheckedChanged(CompoundButton buttonView,
    	     boolean isChecked) {
    	    
    	    if(isChecked){
    	    	
    	    	fb_status="yes";
    	    	switchs2=null;
    	      //    Toast.makeText(upload.this, switchs2 , Toast.LENGTH_LONG).show();

    	    }else{
    	    	
    	    	fb_status=null;
    	    	switchs2="yes";
    	     //     Toast.makeText(upload.this, switchs2 , Toast.LENGTH_LONG).show();

    	    }

    	   }
    	  });
      
     //-------------- twitter switch
     
      
 tButton3 = (ToggleButton) findViewById(R.id.toggleButton3); 
      
      if(switchs3 != null){
    	  
          tButton3.setChecked(false);
          tw_status=null;	   
          switchs3="yes";

      }
      else{
    	  
          tButton3.setChecked(true);
          tw_status="yes";
          switchs3=null;

          
      }
      
      tButton3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    	   @Override
    	   public void onCheckedChanged(CompoundButton buttonView,
    	     boolean isChecked) {
    	    
    	    if(isChecked){
    	    	tw_status="yes";
    	    	
    	    	switchs3=null;
    	         // Toast.makeText(upload.this, switchs3 , Toast.LENGTH_LONG).show();

    	    	
    	    }else{
    	    	tw_status=null;
    	    	
    	    	 switchs3="yes";
    	         // Toast.makeText(upload.this, switchs3 , Toast.LENGTH_LONG).show();

    	    }

    	   }
    	  });
      
      
      
           
      //---------------
       
        img4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
			}
		});
        
      
   
        img1.setRotation(90);
        

        img1.setImageBitmap(BitmapFactory.decodeFile(snapchat));
        
        img3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//new uploadertask().execute((Void) null);  URLEncoder.encode(desc, "UTF-8")
        		//Toast.makeText(getApplicationContext(), vine_status, Toast.LENGTH_LONG).show();
        		Toast.makeText(getApplicationContext(), fb_status, Toast.LENGTH_LONG).show();

				mani(stry);
			
				
			}
		});
      
        
        t1.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				
				String desc=at.getText().toString();

				
				  Intent i = new Intent(upload.this,uploadchannel.class);
       			  i.putExtra("useridch", passuserid);
       			  i.putExtra("descch",desc);
       			  i.putExtra("snapch",snapchat);
       			  i.putExtra("videoch",videomani);
       			  i.putExtra("channelch", schannel);
       			  i.putExtra("switchch", switchs);
       			  i.putExtra("switchch2", switchs2);
       			  i.putExtra("switchch3", switchs3);
       			  i.putExtra("btvalue", buttoncolour);

       			  


       		
       			  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                  startActivity(i,bndlanimation);
				 
				finish();
			}
		});



at.setText(desccc);
                                                                                                                                                                                                                                                       
//t1.setText(channel_status);

chennal_text.setText(schannel);

//t1.setText(channel_status);

at2.setText(setchannel);

if(buttoncolour != null)
{
    red.setVisibility(View.VISIBLE);

	
}

    }
    // http://demo.cogzideltemplates.com/client/vine-clone/mobile/uploadVideo?user_id=21&title=vikki_video&url=http://no_image.png&channel=comedy&post_to_vine=no&post_to_fb=yes&post_to_tw=yes
	@SuppressWarnings("deprecation")
	protected void sangu() {
		// TODO Auto-generated method stub
		

		try {
			
				Log.e("SAngu", "Sangu");
				

	HttpURLConnection connection = null;
	DataOutputStream outputStream = null;
	DataInputStream inputStream = null;

	String urlServer = "http://demo.cogzideltemplates.com/client/vine-clone/mobile/video_upload";
	String lineEnd = "\r\n";
	String twoHyphens = "--";
	String boundary =  "*****";

	int bytesRead, bytesAvailable, bufferSize;
	byte[] buffer;
	int maxBufferSize = 1*1024*1024;

	FileInputStream fileInputStream = new FileInputStream(new File(videomani));

	URL url = new URL(urlServer);
	connection = (HttpURLConnection) url.openConnection();


	connection.setDoInput(true);
	connection.setDoOutput(true);
	connection.setUseCaches(false);


	connection.setRequestMethod("POST");

	connection.setRequestProperty("Connection", "Keep-Alive");
	connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

	outputStream = new DataOutputStream( connection.getOutputStream() );
	outputStream.writeBytes(twoHyphens + boundary + lineEnd);
	outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + videomani +"\"" + lineEnd);
	outputStream.writeBytes(lineEnd);

	bytesAvailable = fileInputStream.available();
	bufferSize = Math.min(bytesAvailable, maxBufferSize);
	buffer = new byte[bufferSize];


	bytesRead = fileInputStream.read(buffer, 0, bufferSize);

	while (bytesRead > 0)
	{
	outputStream.write(buffer, 0, bufferSize);
	bytesAvailable = fileInputStream.available();
	bufferSize = Math.min(bytesAvailable, maxBufferSize);
	bytesRead = fileInputStream.read(buffer, 0, bufferSize);
	}

	outputStream.writeBytes(lineEnd);
	outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


	int serverResponseCode = connection.getResponseCode();
	String serverResponseMessage = connection.getResponseMessage();

	System.out.println("Video"+serverResponseMessage);
	DataInputStream inStream = null;
	inStream = new DataInputStream ( connection.getInputStream() );
	String str="";

	while ((  str = inStream.readLine()) != null)
	{
	Log.e("Debug","Server Response "+str);
	imageurl = str.trim();
	Log.e("Debug","Server Response imageurl "+imageurl);
	}
	inStream.close();
	fileInputStream.close();
	outputStream.flush();
	outputStream.close();
	Imageuploading();
	//mani();
	
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}  
	
	
	protected void Imageuploading() {
		
	
		
		 try{
	     		
	     		
			   	Log.e("Snapchat imade", "Snapchat imade");
			      
			   	HttpURLConnection connection = null;
			   	DataOutputStream outputStream = null;
			   	DataInputStream inputStream = null;

			   	String pathToOurFile = snapchat; 
			   	
			  //String urlServer = "http://demo.cogzideltemplates.com/client/snapchat-clone/index.php/user/image_upload";
				String urlServer = "http://demo.cogzideltemplates.com/client/vine-clone/mobile/video_image_upload";
			   	String lineEnd = "\r\n";
			   	String twoHyphens = "--";
			   	String boundary =  "*****";

			   	int bytesRead, bytesAvailable, bufferSize;
			   	byte[] buffer;
			   	int maxBufferSize = 1*1024*1024;

			   	FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile));

			   	URL url = new URL(urlServer);
			   	connection = (HttpURLConnection) url.openConnection();

			   	// Allow Inputs & Outputs
			   	connection.setDoInput(true);
			   	connection.setDoOutput(true);
			   	connection.setUseCaches(false);

			   	// Enable POST method
			   	connection.setRequestMethod("POST");

			   	connection.setRequestProperty("Connection", "Keep-Alive");
			   	connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

			   	outputStream = new DataOutputStream( connection.getOutputStream() );
			   	outputStream.writeBytes(twoHyphens + boundary + lineEnd);
			   	outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pathToOurFile +"\"" + lineEnd);
			   	outputStream.writeBytes(lineEnd);

			   	bytesAvailable = fileInputStream.available();
			   	bufferSize = Math.min(bytesAvailable, maxBufferSize);
			   	buffer = new byte[bufferSize];

			   	// Read file
			   	bytesRead = fileInputStream.read(buffer, 0, bufferSize);

			   	while (bytesRead > 0)
			   	{
			   	outputStream.write(buffer, 0, bufferSize);
			   	bytesAvailable = fileInputStream.available();
			   	bufferSize = Math.min(bytesAvailable, maxBufferSize);
			   	bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			   	}

			   	outputStream.writeBytes(lineEnd);
			   	outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			   	// Responses from the server (code and message)
			   int serverResponseCode = connection.getResponseCode();
			   	String serverResponseMessage = connection.getResponseMessage();
			   	System.out.println("image"+serverResponseMessage);
			   	
			   	fileInputStream.close();
			   	outputStream.flush();
			   	outputStream.close();
				
			   	DataInputStream inputStream1 = null;
				inputStream1 = new DataInputStream (connection.getInputStream());
			    String str="";
			    String Str1_imageurl="";
			    
			    while ((  str = inputStream1.readLine()) != null)
			    {
			 	   Log.e("Debug","Server Response "+str);
			 	   
			        Str1_imageurl = str;
			        Log.e("Debug","Server Response String imageurl"+str);
			    }
			    inputStream1.close();
			    System.out.println("image url"+Str1_imageurl);
			    stry=Str1_imageurl.trim();
					 
					 }
					 catch(Exception e){
						 
							e.printStackTrace();

					 }
				
					 
		 prasanna(stry);
		
	}
	
	


	private void mani(String stry) {
		// TODO Auto-generated method stub
		String trm;
        String trm2;

        String addch="Add to channel";

        
        
        channel_status=schannel;

		 desc=at.getText().toString();
		  int length = at.getText().length();
		 
		
		if (length<=120)
		{
			 trm=desc;
			 trm2=channel_status;
			 
			
	         if (( trm == null || trm.trim().equals("")||trm.trim().equals("%20")))
	    {
          	Toast.makeText(upload.this, "please enter a title", Toast.LENGTH_LONG).show();
	    }
	         	         
	    else
	   
	    {
	           desc=desc.replaceAll("\\\"", "<hash_with_space>");
	           desc=desc.replaceAll("\\\\", "<hash_without_space>");
	           desc=desc.replaceAll("\n", "%20%20");
	           desc=desc.replaceAll("\t", "%20%20");
	           trm=desc.replaceAll(" ", "%20");
	          // channel_status=String.valueOf(spinner1.getSelectedItem());
	           
	           trm2=channel_status.replaceAll(" ", "%20");
	           
	   	   // System.out.println(channel_status);
	    	new uploadertask().execute((Void) null);   
	    }
	}
	
	
	else
	{
		Toast.makeText(upload.this, "Caption to be 120 character or less", Toast.LENGTH_SHORT).show();	
	}
	
}
     private void prasanna(String stry)
     {
    	 
        	try{
        		
        		
    //   URL signup_url = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/uploadVideo?user_id="+passuserid+"&title="+URLEncoder.encode(desc, "UTF-8")+"&url="+imageurl+"&channel="+URLEncoder.encode(channel_status, "UTF-8")+"&post_to_vine="+vine_status+"&post_to_fb="+fb_status+"&post_to_tw="+tw_status+"&video_poster="+stry);
      
    //   System.out.println("http://demo.cogzideltemplates.com/client/vine-clone/mobile/uploadVideo?user_id="+passuserid+"&title="+URLEncoder.encode(desc, "UTF-8")+"&url="+imageurl+"&channel="+URLEncoder.encode(desc, "UTF-8")+"&post_to_vine="+vine_status+"&post_to_fb="+fb_status+"&post_to_tw="+tw_status+"&video_poster="+stry);
	
        	//	http://demo.cogzideltemplates.com/client/vine-clone/mobile/uploadVideo?user_id=118&title=march&video_poster=http://demo.cogzideltemplates.com/client/vine-clone/images/a3c45eeb7f25bde1b089efd22f5a4631.jpg&url=http://demo.cogzideltemplates.com/client/vine-clone/video/1f232066d576eedb3e8d0fc2110b1068.mp4&channel=cat&post_to_sedio=yes&post_to_fb=yes&post_to_tw=yes
     
       //URL signup_url = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/uploadVideo?user_id="+passuserid+"&title="+URLEncoder.encode(desc, "UTF-8")+"&video_poster=="+stry+"&url="+imageurl+"&channel="+URLEncoder.encode(channel_status, "UTF-8")+"&post_to_sedio=yes&post_to_fb=yes&post_to_tw=yes&post_off="+vine_status); 
     
        		// URL signup_url = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/uploadVideo?user_id="+passuserid+"&title="+URLEncoder.encode(desc, "UTF-8")+"&video_poster=="+stry+"&url="+imageurl+"&channel="+URLEncoder.encode(channel_status, "UTF-8")+"&post_to_sedio="+vine_status+"&post_to_fb="+fb_status+"&post_to_tw="+tw_status);
      
        		// System.out.println ("http://demo.cogzideltemplates.com/client/vine-clone/mobile/uploadVideo?user_id="+passuserid+"&title="+URLEncoder.encode(desc, "UTF-8")+"&url="+imageurl+"&channel="+URLEncoder.encode(channel_status, "UTF-8")+"&post_to_sedio="+vine_status+"&post_to_fb="+fb_status+"&post_to_tw="+tw_status); 

    URL signup_url = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/uploadvideonew?user_id="+passuserid+"&title="+URLEncoder.encode(desc, "UTF-8")+"&video_poster="+stry+"&url="+imageurl+"&channel="+URLEncoder.encode(channel_status, "UTF-8")+"&post_to_sedio="+vine_status);  
        	     	System.out.println ("http://demo.cogzideltemplates.com/client/vine-clone/mobile/uploadvideonew?user_id="+passuserid+"&title="+desc+"&video_poster="+stry+"&url="+imageurl+"&channel="+URLEncoder.encode(channel_status, "UTF-8")+"&post_to_sedio="+vine_status);  
  	
        	 
        	     	
        	     	
        	     	
   	     	BufferedReader SignUp_reader;
	    
	    String Signup_inputline;
	    System.out.println("Status test 1");
		SignUp_reader = new BufferedReader(new InputStreamReader(signup_url.openStream()));
			
			
			while ((Signup_inputline = SignUp_reader.readLine())!= null) 
			{
				
				  Str_Signup += Signup_inputline;
				  System.out.println(Str_Signup);
				
			}
		
		Signup_jsonarray = new JSONArray(Str_Signup);
		
	  for(int i=0;i<Signup_jsonarray.length();i++){
		  JSONObject m = Signup_jsonarray.getJSONObject(i);
		  Signup_status =	m.getString("status");
			System.out.println("Status"+Signup_status);
		  
//		  Toast.makeText(getApplicationContext(), Signup_status, Toast.LENGTH_LONG).show();
	                                                }
	 
	
	  
	  
	}
       
		catch (Exception e) {
			
			e.printStackTrace();
		  
		}
        	
         if(tw_status != null){
      	
          	twshare(passuserid); 

        	  	}
         
         if(fb_status != null){
           	
           	
         	fbshare(passuserid); 
         	
         	  	}
      	
        
        	
        	
	
	}
    private class uploadertask extends AsyncTask <Void, Void, Void>  
    {
             private ProgressDialog Dialog = new ProgressDialog(upload.this);

             protected void onPreExecute()
             {
                 Dialog.setMessage("Uploading...");
                 Dialog.show();
                 Dialog.setCanceledOnTouchOutside(false);
             }
             

             @Override
         protected Void doInBackground(Void... params) 
             {
            // Do your background data fetching here 
            try
            {
            	sangu();
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
                     if(Dialog.isShowing())
                     {
                  	Toast.makeText(upload.this, "Upload Sucessful", Toast.LENGTH_LONG).show();
                    Dialog.dismiss();
                    Intent i = new Intent(upload.this, AndroidTabLayoutActivity.class);
         			i.putExtra("userid", passuserid);
                    startActivity(i);
                  //  finish();
                     
                    	 
                     }
                             // do your Display and data setting operation here
                	
                 }
                 catch(Exception e) 
                 {
                	 
                 }
                       

    
}
    }
    
    
    private class canceltask extends AsyncTask <Void, Void, Void>  
    {
             private ProgressDialog Dialog = new ProgressDialog(upload.this);

             protected void onPreExecute()
             {
                 Dialog.setMessage("Uploading...");
                 Dialog.show();
             }
             

             @Override
         protected Void doInBackground(Void... params) 
             {
            // Do your background data fetching here 
            try
            {
            	
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
                     if(Dialog.isShowing())
                     {
               
                    Dialog.dismiss();
                    Intent i = new Intent(upload.this, AndroidTabLayoutActivity.class);
         			i.putExtra("userid", passuserid);
                    startActivity(i);
                  //  finish();

                    	 
                     }
                             // do your Display and data setting operation here
                	
                 }
                 catch(Exception e) 
                 {
                	 
                 }
    
}
    }
    
    
    /*
   
    private void fbshare()
    {
   	 
       	try{
       		

 
  	     	URL fbshare_url = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/fbsharenew?user_id="+passuserid);
       		
  	     	System.out.println ("http://demo.cogzideltemplates.com/client/vine-clone/mobile/fbsharenew?user_id="+passuserid);

  	     	
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
		  
//		  Toast.makeText(getApplicationContext(), Signup_status, Toast.LENGTH_LONG).show();
	                                                }
    
	  
	  
	}
      
		catch (Exception e) {
			
			e.printStackTrace();
		  
		}
		
            	
       	
	
	}
    
    
    */
    
    
    
    
    private void fbshare(String passuserid)
    {
   	 
       	try{
       	
       		URL fbshare_url = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/fbsharenew?user_id="+passuserid);
       		
  	     	System.out.println ("http://demo.cogzideltemplates.com/client/vine-clone/mobile/fbsharenew?user_id="+passuserid);
  	     	
  	     	

       	     	
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
		  
			//	Toast.makeText(getApplicationContext(), Facebook_status, Toast.LENGTH_LONG).show();
	  
	  
	                                                }
	  
	}
       	
		catch (Exception e) {
			
			e.printStackTrace();
		  
		}

		Toast.makeText(getApplicationContext(), Facebook_status, Toast.LENGTH_LONG).show();
 	
	
	}
    
    
    
    private void twshare(String passuserid)
    {
   	 
       	try{
       	
       		URL twshare_url = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/twittersharenew?user_id="+passuserid);
       		
  	     	System.out.println ("http://demo.cogzideltemplates.com/client/vine-clone/mobile/twittersharenew?user_id="+passuserid);
  	     	
  	     	

       	     	
  	     	BufferedReader Twitter_reader;
	    
	    
  	     	String Twitter_inputline;
	    
  	     	System.out.println("Status test 3");
  	     	Twitter_reader = new BufferedReader(new InputStreamReader(twshare_url.openStream()));
			
			while ((Twitter_inputline = Twitter_reader.readLine())!= null) 
			{
				
				  Str_Twitter += Twitter_inputline;
				  System.out.println(Str_Twitter);
			}
		
			Twitter_jsonarray = new JSONArray(Str_Twitter);
	  
				for(int i=0;i<Twitter_jsonarray.length();i++){
				JSONObject m = Twitter_jsonarray.getJSONObject(i);
				Twitter_status =	m.getString("status");
				System.out.println("Status11"+Twitter_status);
		  
			//	Toast.makeText(getApplicationContext(), Facebook_status, Toast.LENGTH_LONG).show();
	  
	  
	                                                }
	  
	}
       	
		catch (Exception e) {
			
			e.printStackTrace();
		  
		}

		Toast.makeText(getApplicationContext(), Twitter_status, Toast.LENGTH_LONG).show();
 	
	
	}
    
    
	}
	