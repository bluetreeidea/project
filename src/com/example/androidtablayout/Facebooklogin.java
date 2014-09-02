package com.example.androidtablayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.FacebookError;


@SuppressWarnings("deprecation")
public class Facebooklogin extends Activity{
	
	
	private AsyncFacebookRunner mAsyncRunner;


	public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.comment);
       
                
}
	
	
	public  Facebooklogin() {
		  mAsyncRunner.request("me", new RequestListener() {
		   private String username;

		public void onComplete(String response, Object state) {
		    Log.d("Profile", response);
		    String json = response;
		    try {
		     // Facebook Profile JSON data
		     JSONObject profile = new JSONObject(json);
		     
		     // getting name of the user
		     final String name = profile.getString("name");
		     
		     // getting email of the user
		     final String email = profile.getString("email");
		     
		     // getting user name of the user
		     username = profile.getString("username");
		     
		     runOnUiThread(new Runnable() {

		      public void run() {
		   //    Toast.makeText(getApplicationContext(), "Name: " + name + "\nEmail: " + email +"\nUserName : "+username, Toast.LENGTH_LONG).show();
		      }

		     });

		     
		    } catch (JSONException e) {
		     e.printStackTrace();
		    }
		   }

		   public void onIOException(IOException e, Object state) {
		   }

		   public void onFileNotFoundException(FileNotFoundException e,
		     Object state) {
		   }

		   public void onMalformedURLException(MalformedURLException e,
		     Object state) {
		   }

		   public void onFacebookError(FacebookError e, Object state) {
		   }
		  });
		 }

}