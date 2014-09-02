package com.example.androidtablayout;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import com.example.androidtablayout.Profile.MyJavaScriptInterface;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile_settings extends Activity
{
	WebView webView;
	EditText Msg;
	Button btnSendMsg;
     private static final int CAM_REQUREST = 1313; 
	 private static int RESULT_LOAD_IMAGE = 1;
     String userid;
	 String vid;
	 String comm;
	 ImageView img;
	 TextView save;
	 String condition;
	 SessionManager session;
final MyJavaScriptInterface myJavaScriptInterface= new MyJavaScriptInterface(this);
  Bitmap bitmap_profile_image;
  Object imagepath;
  String stry;
  String msgToSend;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        if( Build.VERSION.SDK_INT >= 9){
	          StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	          StrictMode.setThreadPolicy(policy); 
	   }
        Intent o=getIntent();
        userid=o.getStringExtra("userid");
        img=(ImageView)findViewById(R.id.images);
        webView = (WebView)findViewById(R.id.webview);
        save=(TextView)findViewById(R.id.textView1);
        save.setOnClickListener(new OnClickListener() {
			
			@JavascriptInterface
			public void onClick(View v) {
				          save_btn();
				
			}
		});
        img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 finish();
				 overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
				
			}
		});
         WebSettings webSettings = webView.getSettings();
         webSettings.setPluginState(WebSettings.PluginState.ON);
         webSettings.setJavaScriptEnabled(true);
         webSettings.setUseWideViewPort(false);
         webSettings.setLoadWithOverviewMode(true);
         webView.setWebChromeClient(new WebChromeClient());
         webView.addJavascriptInterface( myJavaScriptInterface, "other_native");
         webView.loadUrl("file:///android_asset/www/settings.html?user_id="+userid);
	}
   
    @JavascriptInterface
	protected void save_btn() {
		
		webView.loadUrl("javascript:save_san()");
    	
	}

	public class MyJavaScriptInterface {
		Context mContext;

	    MyJavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    
	    
	    public void save_native(){
	    	
	    	
	   // 	showCustomAlert();
	    	
	  	 Toast.makeText(getApplicationContext(), "Profile updated successfully", Toast.LENGTH_LONG).show();
	    	finish();
	    	overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
	    	
	    }
	    
	    @JavascriptInterface
	    public void settings(){
	    	
	    	AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(Profile_settings.this);

		    alertDialog2.setTitle("");

		    alertDialog2.setMessage("Please select an image..");

		    alertDialog2.setIcon(R.drawable.act_camera);

		    alertDialog2.setPositiveButton("CAMERA.",
		            new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int which) {
		                	condition="from_camera";
		                	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);            
		        			startActivityForResult(cameraIntent, CAM_REQUREST); 	
		                }
		            });
		    
		            alertDialog2.setNegativeButton("GALLERY.",
		            new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							condition="from_gallery";
							
		                	Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		                    
		                    startActivityForResult(i, RESULT_LOAD_IMAGE);
		                    
		                    
		                }
		            });

		    alertDialog2.show();
	    	
		                    } 
	    
	    @JavascriptInterface
	    
	    public void logout(){
			   
	    	session.logoutUser();
	    	Intent i1=new Intent(Profile_settings.this,splash.class);
			//overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
	    	Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			startActivity(i1,bndlanimation);
			finish();
			   
		                     } }
	public void sangu() {
		
		webView.loadUrl("javascript:callFromActivity(\""+msgToSend+"\")");
	}
        
	public void showCustomAlert() {
		// TODO Auto-generated method stub
		
		
		 Context context = getApplicationContext();
	        // Create layout inflator object to inflate toast.xml file
	        LayoutInflater inflater = getLayoutInflater();
	          
	        // Call toast.xml file for toast layout 
	        View toastRoot = inflater.inflate(R.layout.toast, null);
	          
	        Toast toast = new Toast(context);
	         
	        // Set layout to toast 
	        toast.setView(toastRoot);
	        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
	                0, 0);
	        toast.setDuration(Toast.LENGTH_LONG);
	        toast.show();
		
		
		
		
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {  
    super.onActivityResult(requestCode, resultCode, data);  
  
          if (requestCode == CAM_REQUREST) 
          {  
              bitmap_profile_image = (Bitmap) data.getExtras().get("data");  
              imagepath =  ImageWrite(bitmap_profile_image); 
          } 
          
        else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) 
          {
              Uri selectedImage = data.getData();
              String[] filePathColumn = { MediaStore.Images.Media.DATA };
   
              Cursor cursor = getContentResolver().query(selectedImage,
                      filePathColumn, null, null, null);
              cursor.moveToFirst();
   
              int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
              String picturePath = cursor.getString(columnIndex);
              cursor.close();
              imagepath =  ImageWrite(BitmapFactory.decodeFile(picturePath));
             
           
          } 
        else{
        	
        	
        }    
          
          Imageuploading();  
          
          
	  }
	
	public String ImageWrite(Bitmap bitmap1)
	{
		
		 String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
	        OutputStream outStream = null;
	        File file = new File(extStorageDirectory, "slectimage.PNG");
	            
	        try 
	        {
	    
	            outStream = new FileOutputStream(file);
	            bitmap1.compress(Bitmap.CompressFormat.PNG, 100, outStream);
	            outStream.flush();
	            outStream.close();

	           

	        } 
	        catch (FileNotFoundException e) 
	        {
	            e.printStackTrace();
	            
	        } catch (IOException e) 
	        {
	            e.printStackTrace();
	           
	        }
	    	String imageInSD = "/sdcard/slectimage.PNG";
		
		    return imageInSD;
		
	}
	
	protected void Imageuploading() {
		// TODO Auto-generated method stub
		
		 try{
	     		
	     		
			   	Log.e("SANGUUU", "dfdf");
			      
			   	HttpURLConnection connection = null;
			   	DataOutputStream outputStream = null;
			   	DataInputStream inputStream = null;

			   
			   	
			   	
			   	String pathToOurFile = (String) imagepath; 
			   	
				//String urlServer = "http://demo.cogzideltemplates.com/client/snapchat-clone/index.php/user/image_upload";
				String urlServer = "http://demo.cogzideltemplates.com/client/vine-clone/mobile/image_upload";
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
			   	
			//	Toast.makeText(getApplicationContext(),  serverResponseMessage, Toast.LENGTH_LONG).show();
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
		//		Toast.makeText(getApplicationContext(),  Str1_imageurl, Toast.LENGTH_LONG).show();

			       stry=Str1_imageurl.trim();
			       msgToSend = stry;
					 
					 }
					 catch(Exception e){
						 
							e.printStackTrace();

					 }
					 
			sangu();
		
	}
	
	
	
}
	
	
        
    
    
    
    
