package com.example.androidtablayout;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class invitepeople extends Activity {

	private ImageView email;
	private ImageView message;
	private ImageView search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	 	setContentView(R.layout.email);
        email=(ImageView)findViewById(R.id.ImageView01);
        message=(ImageView)findViewById(R.id.ImageView02);
        search=(ImageView)findViewById(R.id.ImageView03);

        


	

    message.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				showMessage();	
			}
			
		});

	 
	 email.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				showemail();	
			}
			
		});
	 
	 
	 search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i1=new Intent(invitepeople.this,search.class);
			   	Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
			   	startActivity(i1, bndlanimation);
		
			
			
			}
			
		});
	 
}
	
	
	   public void showMessage() {
		   
		   String _messageText="hey buddy , Come and join on sedio";
		   String _messageNumber="";
		   
	        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
	        sendIntent.setData(Uri.parse("sms:"+_messageNumber));
	            sendIntent.putExtra("sms_body", _messageText); 
	            startActivity(sendIntent);
	    }
	   
	   
	   public void showemail() {

		  String to = "";
		  String subject = "Follow me on Sedio";
		  String message = "Follow me on Sedio: User id: toms Download SEDIO from http://googleplay.com/apps/sedio";

		  Intent email = new Intent(Intent.ACTION_SEND);
		  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
		  email.putExtra(Intent.EXTRA_SUBJECT, subject);
		  email.putExtra(Intent.EXTRA_TEXT, message);

		  //need this to prompts email client only
		  email.setType("message/rfc822");

		  startActivity(Intent.createChooser(email, "Choose an Email client :"));

		}
	
}