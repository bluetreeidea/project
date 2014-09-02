package com.example.androidtablayout;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class twitter_connect extends Activity {

	private ImageView email;
	private ImageView message;
	private ImageView search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 	setContentView(R.layout.twitter_search);
        email=(ImageView)findViewById(R.id.ImageView01);
        message=(ImageView)findViewById(R.id.ImageView02);
      //  search=(ImageView)findViewById(R.id.ImageView03);

        


	

    message.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			//	showMessage();	
			}
			
		});

	 
	 email.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//showemail();	
			}
			
		});
	 
	 
	
	 
}
	
	
	
	
}