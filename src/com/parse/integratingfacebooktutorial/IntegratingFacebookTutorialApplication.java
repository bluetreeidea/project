package com.parse.integratingfacebooktutorial;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.example.androidtablayout.R;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;

public class IntegratingFacebookTutorialApplication extends Activity {

	static final String TAG = "MyApp";

	@Override
	public void onCreate(Bundle savedBundle) {
		super.onCreate(savedBundle);
		Parse.initialize(this, "ga1OW2egG0w0Cq1DsJkWpirY4ESBC9OohNDUgxCq",
				"RqzFot5C5JFPzOW4dOx3K3JsIrTg5cxy8iIxhEpf");
		// Set your Facebook App Id in strings.xml
		ParseFacebookUtils.initialize(getString(R.string.app_id));		
		Intent i=new Intent(IntegratingFacebookTutorialApplication.this,LoginActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		startActivity(i);
		finish();				
		
	}

}
