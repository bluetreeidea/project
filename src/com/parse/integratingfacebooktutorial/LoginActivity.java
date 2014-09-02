package com.parse.integratingfacebooktutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.example.androidtablayout.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.integratingfacebooktutorial.IntegratingFacebookTutorialApplication;

public class LoginActivity extends Activity {

	
	private Dialog progressDialog;
	ProgressBar brp1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		 setContentView(R.layout.main12);
    //     brp1=(ProgressBar)findViewById(R.id.progressBar1);
		//onLoginButtonClicked();
		// Check if there is a currently logged in user
		// and they are linked to a Facebook account.
		
					
		ParseUser currentUser = ParseUser.getCurrentUser();
		if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
			// Go to the user info activity
			showUserDetailsActivity();
		  }
		else
			onLoginButtonClicked();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}

	private void onLoginButtonClicked() {
		
		LoginActivity.this.progressDialog = ProgressDialog.show(
				LoginActivity.this, "", "Logging in...", true);
/*		List<String> permissions1 = Arrays.asList("email","basic_info", "user_about_me",
				"user_relationships", "user_birthday", "user_location");
		// List<String> permissions = Arrays.asList("publish_stream, publish_actions"); */
		
		List<String> permissions = new ArrayList <String>();
		permissions.add("email");
		permissions.add("basic_info");
		permissions.add("user_about_me");
		permissions.add("user_relationships");
		permissions.add("user_birthday");
		permissions.add("user_location");
	 // permissions.add("publish_stream");
		final String PREF_FILE_NAME = "PrefFile";
		   SharedPreferences preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
		
		ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException err) {
				LoginActivity.this.progressDialog.dismiss();		
				
				
				
				if (user == null) {
					Log.d(IntegratingFacebookTutorialApplication.TAG,
							"Uh oh. The user cancelled the Facebook login.");
					finish();
					
				}else if (user.isNew()) {
					Log.d(IntegratingFacebookTutorialApplication.TAG,
							"User signed up and logged in through Facebook!");
					showUserDetailsActivity();
				} 
				else {
					Log.d(IntegratingFacebookTutorialApplication.TAG,
							"User logged in through Facebook!");
					showUserDetailsActivity();
				}
				
		//		showUserDetailsActivity();
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				SharedPreferences.Editor editor = preferences.edit();
				int storedPreference = 0;
				editor.putInt("storedInt", storedPreference); // value to store
				editor.commit();
			}
		});
	}

	
	private void showUserDetailsActivity() {
		System.out.println("Entry to  USer Activtiy data'");
		Intent intent = new Intent(this, UserDetailsActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(intent);
		finish();
	}
	
}