package com.example.androidtablayout;

import com.example.microcam.upload;

import android.R.string;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class uploadchannel extends Activityy implements OnClickListener{

	private static final String JOptionPane = null;
	private static final String channelch = null;
    private FrameLayout backframe;
    String channel_status;
    String passuserid,chdesc;
    String chvideo,chsnap,defchannel,setback=null,chswitch,chswitch2, chswitch3;
    int n;
	private String chbtn;
	private String cccccc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 ImageView comedy, art , cat, dog,family,nature,beauty,food, health,music,news,spl,wired;
         ImageView back;
    	 ImageView urban;
    	 ImageView sports;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uploadchannel); 
		
		   if( Build.VERSION.SDK_INT >= 9){
	            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	            StrictMode.setThreadPolicy(policy); 
	     }
		   
		   

		    Intent i= getIntent();
		 
	        passuserid= i.getStringExtra("useridch");
	        chdesc = i.getStringExtra("descch");
	       chsnap=i.getStringExtra("snapch");
	       chvideo=i.getStringExtra("videoch");
	        defchannel = i.getStringExtra("channelch");
	       
	        chswitch = i.getStringExtra("switchch");
	        chswitch2 = i.getStringExtra("switchch2");
	        chswitch3 = i.getStringExtra("switchch3");
	        
	        chbtn = i.getStringExtra("btvalue");



	        
	        
	     // Toast.makeText(uploadchannel.this, chswitch, Toast.LENGTH_LONG).show();
	     // Toast.makeText(uploadchannel.this, chswitch2 , Toast.LENGTH_LONG).show();
	    //  Toast.makeText(uploadchannel.this, chswitch3 , Toast.LENGTH_LONG).show();


	      // Toast.makeText(uploadchannel.this, chdesc , Toast.LENGTH_LONG).show();
	     //   Toast.makeText(uploadchannel.this, chsnap , Toast.LENGTH_LONG).show();
	    //    Toast.makeText(uploadchannel.this, chvideo , Toast.LENGTH_LONG).show();

	       

	        this.backframe = (FrameLayout) findViewById(R.id.frameLayout1);

	        comedy=(ImageView)findViewById(R.id.ch1);
	        art=(ImageView)findViewById(R.id.ch2);
	        cat=(ImageView)findViewById(R.id.ch3);
	        dog=(ImageView)findViewById(R.id.ch4);
	        family=(ImageView)findViewById(R.id.ch5);
	        nature=(ImageView)findViewById(R.id.ch6);
	        beauty=(ImageView)findViewById(R.id.ch7);
	        health=(ImageView)findViewById(R.id.ch8);
	        food=(ImageView)findViewById(R.id.ch9);
	        music=(ImageView)findViewById(R.id.ch10);
	        news=(ImageView)findViewById(R.id.ch11);
	        spl=(ImageView)findViewById(R.id.ch12);
	        wired=(ImageView)findViewById(R.id.ch13);
	        urban=(ImageView)findViewById(R.id.ch14);
	        sports=(ImageView)findViewById(R.id.ch15);

	        back=(ImageView)findViewById(R.id.back1);
	        
	        
	        comedy.setOnClickListener(this);
	        art.setOnClickListener(this);
	        cat.setOnClickListener(this);
	        dog.setOnClickListener(this);
	        family.setOnClickListener(this);
	        nature.setOnClickListener(this);
	        beauty.setOnClickListener(this);
	        health.setOnClickListener(this);
	        food.setOnClickListener(this);
	        music.setOnClickListener(this);
	        news.setOnClickListener(this);
	        spl.setOnClickListener(this);
	        wired.setOnClickListener(this);
	        urban.setOnClickListener( this);
	        sports.setOnClickListener(this);
	        back.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		switch(v.getId()){
        case R.id.ch1:
        {
        	  Intent i = new Intent(uploadchannel.this, com.example.microcam.upload.class);
        	  
    		 	  i.putExtra("userid", passuserid);
    		 	  i.putExtra("chvalue","Comedy");
    		 	 i.putExtra("snapchat",chsnap);
    		 	 i.putExtra("sangu",chvideo); 
    		 	 i.putExtra("desc",chdesc);
            	
    		 	 i.putExtra("switchstate",chswitch);
            	 i.putExtra("switchstate2",chswitch2);
            	 i.putExtra("switchstate3",chswitch3);
            	 i.putExtra("btnvaluech","plzon");




    		 	 
    		 	 startActivity(i);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Comedy" , Toast.LENGTH_LONG).show();
               finish();	
        }		                    	
        break;
        case R.id.ch2:

        {	
        	  Intent i1 = new Intent(uploadchannel.this, upload.class);
    		 	  i1.putExtra("userid", passuserid);
    		 	  i1.putExtra("chvalue","Art & Experimental");
    		 	 i1.putExtra("snapchat",chsnap);
    		 	 i1.putExtra("sangu",chvideo); 
    		 	 i1.putExtra("desc",chdesc);
    		 	 i1.putExtra("switchstate",chswitch);
            	 i1.putExtra("switchstate2",chswitch2);
            	 i1.putExtra("switchstate3",chswitch3);
            	 i1.putExtra("btnvaluech","plzon");

    		 	 
                startActivity(i1);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Art & Experimental" , Toast.LENGTH_LONG).show();

               finish();
        }	
        	//DO something
        break;
        case R.id.ch3:
             //DO something
        {	Intent i2 = new Intent(uploadchannel.this, upload.class);
    		 	  i2.putExtra("userid", passuserid);
    		 	  i2.putExtra("chvalue","Cat");
    		 	 i2.putExtra("snapchat",chsnap);
    		 	 i2.putExtra("sangu",chvideo); 
    		 	 i2.putExtra("desc",chdesc);
    		 	 i2.putExtra("switchstate",chswitch);
            	 i2.putExtra("switchstate2",chswitch2);
            	 i2.putExtra("switchstate3",chswitch3);
            	 i2.putExtra("btnvaluech","plzon");

                startActivity(i2);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Cat" , Toast.LENGTH_LONG).show();

                finish();	
        	
        }	
        break;
        
        case R.id.ch4:
             //DO something
        {	
        	Intent i3 = new Intent(uploadchannel.this, upload.class);
    		 	  i3.putExtra("userid", passuserid);
    		 	  i3.putExtra("chvalue","Dogs");
    		 	 i3.putExtra("snapchat",chsnap);
    		 	 i3.putExtra("sangu",chvideo); 
    		 	 i3.putExtra("desc",chdesc);
    		 	 i3.putExtra("switchstate",chswitch);
            	 i3.putExtra("switchstate2",chswitch2);
            	 i3.putExtra("switchstate3",chswitch3);
            	 i3.putExtra("btnvaluech","plzon");

                startActivity(i3);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Dogs" , Toast.LENGTH_LONG).show();

                finish();	
        }	
        break;
        case R.id.ch5:
             //DO something
        {	Intent i4 = new Intent(uploadchannel.this, upload.class);
    		 	  i4.putExtra("userid", passuserid);
    		 	  i4.putExtra("chvalue","Family");
    		 	 i4.putExtra("snapchat",chsnap);
    		 	 i4.putExtra("sangu",chvideo); 
    		 	 i4.putExtra("desc",chdesc);
    		 	 i4.putExtra("switchstate",chswitch);
            	 i4.putExtra("switchstate2",chswitch2);
            	 i4.putExtra("switchstate3",chswitch3);
            	 i4.putExtra("btnvaluech","plzon");

                startActivity(i4);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Family" , Toast.LENGTH_LONG).show();

                finish();	

        }
               break;
        case R.id.ch6:
             //DO something
        {	Intent i5 = new Intent(uploadchannel.this, upload.class);
    		 	  i5.putExtra("userid", passuserid);
    		 	  i5.putExtra("chvalue","Beauty & Fashion");
    		 	 i5.putExtra("snapchat",chsnap);
    		 	 i5.putExtra("sangu",chvideo); 
    		 	 i5.putExtra("desc",chdesc);
    		 	 i5.putExtra("switchstate",chswitch);
            	 i5.putExtra("switchstate2",chswitch2);
            	 i5.putExtra("switchstate3",chswitch3);
            	 i5.putExtra("btnvaluech","plzon");

                startActivity(i5);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Beauty & Fashion" , Toast.LENGTH_LONG).show();

                finish();	
               
        }
        break;
        
        case R.id.ch7:
            //DO something
        {  Intent i6 = new Intent(uploadchannel.this, upload.class);
    		 	  i6.putExtra("userid", passuserid);
    		 	  i6.putExtra("chvalue","Food");
    		 	 i6.putExtra("snapchat",chsnap);
    		 	 i6.putExtra("sangu",chvideo); 
    		 	 i6.putExtra("desc",chdesc);
    		 	 i6.putExtra("switchstate",chswitch);
            	 i6.putExtra("switchstate2",chswitch2);
            	 i6.putExtra("switchstate3",chswitch3);
            	 i6.putExtra("btnvaluech","plzon");

                startActivity(i6);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Food" , Toast.LENGTH_LONG).show();

               finish();	
        }	
        	
        	
        break;
        case R.id.ch8:
             //DO something
        	{Intent i7 = new Intent(uploadchannel.this, upload.class);
        		  i7.putExtra("userid", passuserid);
    		 	  i7.putExtra("chvalue","Health & Fitness");
    		 	 i7.putExtra("snapchat",chsnap);
    		 	 i7.putExtra("sangu",chvideo); 
    		 	 i7.putExtra("desc",chdesc);
    		 	 i7.putExtra("switchstate",chswitch);
            	 i7.putExtra("switchstate2",chswitch2);
            	 i7.putExtra("switchstate3",chswitch3);
            	 i7.putExtra("btnvaluech","plzon");

                startActivity(i7);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Health & Fitness" , Toast.LENGTH_LONG).show();

               finish();
        	}
        break;
        case R.id.ch9:
             //DO something
        {  Intent i8 = new Intent(uploadchannel.this, upload.class);
    		 	  i8.putExtra("userid", passuserid);
    		 	  i8.putExtra("chvalue","Nature");
    		 	 i8.putExtra("snapchat",chsnap);
    		 	 i8.putExtra("sangu",chvideo); 
    		 	 i8.putExtra("desc",chdesc);
    		 	 i8.putExtra("switchstate",chswitch);
            	 i8.putExtra("switchstate2",chswitch2);
            	 i8.putExtra("switchstate3",chswitch3);
            	 i8.putExtra("btnvaluech","plzon");

                startActivity(i8);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    		 	Toast.makeText(uploadchannel.this, "Nature" , Toast.LENGTH_LONG).show();
  
               finish();	
        }
               break;
        
        case R.id.ch10:
             //DO something
        {	Intent a1 = new Intent(uploadchannel.this, upload.class);
    		 	  a1.putExtra("userid", passuserid);
    		 	  a1.putExtra("chvalue","Music");
    		 	 a1.putExtra("snapchat",chsnap);
    		 	 a1.putExtra("sangu",chvideo); 
    		 	 a1.putExtra("desc",chdesc);
    		 	 a1.putExtra("switchstate",chswitch);
            	 a1.putExtra("switchstate2",chswitch2);
            	 a1.putExtra("switchstate3",chswitch3);
            	 a1.putExtra("btnvaluech","plzon");

                startActivity(a1);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Music" , Toast.LENGTH_LONG).show();

               finish();	
        	
        }	
        	
        break;
        case R.id.ch11:
             //DO something
        {	Intent a2 = new Intent(uploadchannel.this, upload.class);
    		 	  a2.putExtra("userid", passuserid);
    		 	  a2.putExtra("chvalue","News & Politics");
    		 	 a2.putExtra("snapchat",chsnap);
    		 	 a2.putExtra("sangu",chvideo); 
    		 	 a2.putExtra("desc",chdesc);
    		 	 a2.putExtra("switchstate",chswitch);
            	 a2.putExtra("switchstate2",chswitch2);
            	 a2.putExtra("switchstate3",chswitch3);
            	 a2.putExtra("btnvaluech","plzon");

                startActivity(a2);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "News & Politics" , Toast.LENGTH_LONG).show();

               finish();	}
        break;
        case R.id.ch12:
             //DO something
        	Intent a3 = new Intent(uploadchannel.this, upload.class);
    		 	  a3.putExtra("userid", passuserid);
    		 	  a3.putExtra("chvalue","Special FX");
    		 	 a3.putExtra("snapchat",chsnap);
    		 	 a3.putExtra("sangu",chvideo); 
    		 	 a3.putExtra("desc",chdesc);
    		 	 a3.putExtra("switchstate",chswitch);
            	 a3.putExtra("switchstate2",chswitch2);
            	 a3.putExtra("switchstate3",chswitch3);
            	 a3.putExtra("btnvaluech","plzon");

                startActivity(a3);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Special FX" , Toast.LENGTH_LONG).show();

               finish();	
        break;
        
        case R.id.ch13:
             //DO something
        	Intent a4 = new Intent(uploadchannel.this, upload.class);
    		 	  a4.putExtra("userid", passuserid);
    		 	  a4.putExtra("chvalue","Wired");
    		 	 a4.putExtra("snapchat",chsnap);
    		 	 a4.putExtra("sangu",chvideo); 
    		 	 a4.putExtra("desc",chdesc);
    		 	 a4.putExtra("switchstate",chswitch);
            	 a4.putExtra("switchstate2",chswitch2);
            	 a4.putExtra("switchstate3",chswitch3);
            	 a4.putExtra("btnvaluech","plzon");

                startActivity(a4);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Wired" , Toast.LENGTH_LONG).show();

               finish();	
        break;
        case R.id.ch14:
             //DO something
        	Intent a5 = new Intent(uploadchannel.this, upload.class);
    		 	  a5.putExtra("userid", passuserid);
    		 	  a5.putExtra("chvalue","Urban");
    		 	 a5.putExtra("snapchat",chsnap);
    		 	 a5.putExtra("sangu",chvideo); 
    		 	 a5.putExtra("desc",chdesc);
    		 	 a5.putExtra("switchstate",chswitch);
            	 a5.putExtra("switchstate2",chswitch2);
            	 a5.putExtra("switchstate3",chswitch3);
            	 a5.putExtra("btnvaluech","plzon");

                startActivity(a5);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Urban" , Toast.LENGTH_LONG).show();

               finish();	
        break;
        
        case R.id.ch15:
             //DO something
        	Intent a6 = new Intent(uploadchannel.this, upload.class);
    		 	  a6.putExtra("userid", passuserid);
    		 	  a6.putExtra("chvalue","Sports");
    		 	 a6.putExtra("snapchat",chsnap);
    		 	 a6.putExtra("sangu",chvideo); 
    		 	 a6.putExtra("desc",chdesc);
    		 	 a6.putExtra("switchstate",chswitch);
            	 a6.putExtra("switchstate2",chswitch2);
            	 a6.putExtra("switchstate3",chswitch3);
            	 a6.putExtra("btnvaluech","plzon");

                startActivity(a6);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 
    		 	Toast.makeText(uploadchannel.this, "Sports" , Toast.LENGTH_LONG).show();

               finish();	
        	
        	
        break;
        case R.id.back1:
        	cccccc= "gjfhgjdfh";

             //DO something
        	if (defchannel==null)
        	{
        		setback="Add to channel";
        		defchannel="   ";
        		cccccc= null ;
        	}
        	
        	  Intent back = new Intent(uploadchannel.this, upload.class);
        	  back.putExtra("userid", passuserid);
        	  back.putExtra("snapchat",chsnap);
 		 	 back.putExtra("sangu",chvideo); 
 		 	 back.putExtra("desc",chdesc);
		 	  back.putExtra("chvalue",defchannel);
		 	
		 	  back.putExtra("setchvalue",setback);
		 	 back.putExtra("switchstate",chswitch);
        	 back.putExtra("switchstate2",chswitch2);
        	 back.putExtra("switchstate3",chswitch3);
        	back.putExtra("btnvaluech",cccccc);


                startActivity(back);overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); 


               finish();	
        	
        break;
         
        
    }

		
	}
	
	
}