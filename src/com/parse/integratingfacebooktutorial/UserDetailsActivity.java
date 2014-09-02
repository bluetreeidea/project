package com.parse.integratingfacebooktutorial;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidtablayout.AndroidTabLayoutActivity;
import com.example.androidtablayout.R;
import com.example.androidtablayout.index;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class UserDetailsActivity extends Activity {

	String tkn;
	ProgressBar bb;
    String bitmap_profile_image;

	private URL Login_Url;
	public String login_inputline;
	private JSONArray login_jsonarray;
	private JSONObject login_jsonobj;
	private String login_status;
	private String User_id;
		
    public static final String PREFS_NAME = "MyPrefsFile";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy); 
     }*/

		setContentView(R.layout.userdetails);
 		bb=(ProgressBar)findViewById(R.id.progressBar1);
		
	// Fetch Facebook user info if the session is active
		Session session = ParseFacebookUtils.getSession();
		if (session != null && session.isOpened()) {
			makeMeRequest();
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			// Check if the user is currently logged
			// and show any cached content
		} else {
			// If the user is not logged in, go to the
			// activity showing the login view.
			startLoginActivity();
		}
	}

	private void makeMeRequest() {
		Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
				new Request.GraphUserCallback() {	

					public void onCompleted(GraphUser user, Response response) {
						if (user != null) {
							// Create a JSON object to hold the profile info
							JSONObject userProfile = new JSONObject();
							try {
								// Populate the JSON object
								tkn=ParseFacebookUtils.getSession().getAccessToken();
								userProfile.put("facebookId", user.getId());
								bitmap_profile_image = "https://graph.facebook.com/"+user.getId()+"/picture?type=large";
								//Toast.makeText(getApplicationContext(), bitmap_profile_image,Toast.LENGTH_LONG).show();
                                System.out.println(bitmap_profile_image);
								mani(user.asMap().get("email").toString(),user.getId(),user.getLastName(),user.getFirstName(),bitmap_profile_image,tkn);
						    
								// Show the user info
							} catch (JSONException e) {
								Log.d(IntegratingFacebookTutorialApplication.TAG,
										"Error parsing returned user data.");
							}

						} else if (response.getError() != null) {
							if ((response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_RETRY)
									|| (response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_REOPEN_SESSION)) {
								Log.d(IntegratingFacebookTutorialApplication.TAG,
										"The facebook session was invalidated.");
															} else {
								Log.d(IntegratingFacebookTutorialApplication.TAG,
										"Some other error: "
												+ response.getError()
														.getErrorMessage());
							}
						}
					}
				});
		request.executeAsync();

	}


	
	protected void mani(String email, String id, String lastName,String firstName,String pimg,String token) {
		// TODO Auto-generated method stub
		
		try{
        		
					//Login_Url  	 = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/flogin?user_id="+id+"&first_name="+firstName+"&last_name="+lastName+"&email="+string+"&pimage="+pimg+"&token="+token);
			//Login_Url  	 = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/flogin?user_id="+id+"&first_name="+firstName+"&last_name="+lastName+"&email="+string+"&pimage="+pimg+"&token="+token);
			
			//Login_Url  	 = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/flogin?user_id="+id+"&first_name="+firstName+"&token="+token);
			
			//Login_Url  	 = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/flogin?user_id="+id+"&first_name="+firstName+"&token="+token+"&last_name="+lastName+"email="+email+"&pimage="+pimg);
			Login_Url=new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/flogin?user_id="+id+"&first_name="+firstName+"&token="+token+"&last_name="+lastName+"&email="+email+"&pimage="+pimg);
			
			//		demo.cogzideltemplates.com/client/vine-clone/mobile/flogin?user_id='+userid+'&first_name='+first_name+'&last_name='+last_name+'&email='+user_email
					System.out.print("login"+Login_Url);
					BufferedReader login_reader;
					String str_login="";
					String br="";
		        	   login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));
			
						System.out.print("login"+login_reader);
						
						//Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show(); 
				   //Toast.makeText(getApplicationContext(), "login_reader", Toast.LENGTH_LONG).show(); 
					
					while (( br = login_reader.readLine())!= null) 
					{						
						str_login += br;
					
					 //f Toast.makeText(getApplicationContext(), str_login, Toast.LENGTH_LONG).show();
					  System.out.println("Welcome"+str_login);
						
					}
				System.out.print("login sanjay"+str_login);
					
						login_jsonarray = new JSONArray(str_login);					 
						login_jsonobj = login_jsonarray.getJSONObject(0);
						login_status =	login_jsonobj.getString("status");
						System.out.print("login sanjay"+login_status);
						User_id =login_jsonobj.getString("id");
						//Toast.makeText(getApplicationContext(), login_status, Toast.LENGTH_LONG).show();	
					
		            
		            //	Toast.makeText(getApplicationContext(), "Entry to login sucess login", Toast.LENGTH_LONG).show();
					  loadSavedPreferences();
					  System.out.println("Entry to user Details Activity");
					Intent Intent_imageview = new Intent(UserDetailsActivity.this, AndroidTabLayoutActivity.class);
			    	Intent_imageview.putExtra("userid", User_id);
			    	Intent_imageview.putExtra("fb_token", tkn);
				    startActivity(Intent_imageview);
				    finish();
		            
					
					
		}
		catch (Exception e){
			System.out.println("Exception"+e);
			
			
		}
		
	}

	private void startLoginActivity() {
		Intent intent = new Intent(this, LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}
	
	
	
	private void loadSavedPreferences() {  
        //User has successfully logged in, save this information
        // We need an Editor object to make preference changes.
       
        SharedPreferences settings = getSharedPreferences(index.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        //Set "hasLoggedIn" to true
        editor.putBoolean("hasLoggedIn", true);
        editor.putString ("fuser", User_id);
        editor.putString ("ftkn", tkn);
        
        // Commit the edits!
        editor.commit();


    }
}