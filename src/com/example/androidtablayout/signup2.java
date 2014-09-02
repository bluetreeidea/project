package com.example.androidtablayout;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class signup2 extends Activity{
   
    EditText edt1,edt2,edt3;
     ImageView btn,b1,b2;
     TextView t1,t2,t3;
     String str,str1,str2,str3;
     URL Login_Url;
     String s4,h_id,h2;
     String Liveurl="";

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
         if( Build.VERSION.SDK_INT >= 9){
              StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
              StrictMode.setThreadPolicy(policy);
       }
         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         Liveurl = sharedPreferences.getString("liveurl", null);  
    edt1=(EditText)findViewById(R.id.username);
    edt2=(EditText)findViewById(R.id.password);
    edt3=(EditText)findViewById(R.id.phone);
   
    btn=(ImageView)findViewById(R.id.signin);
    b1=(ImageView)findViewById(R.id.imageView3);
    b2=(ImageView)findViewById(R.id.imageView2);
    Intent in = getIntent();
    h_id = in.getStringExtra("userid");
   
    edt1.requestFocus();

 
   
   
   
   
    b1.setOnClickListener(new OnClickListener() {
       
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
           
            Intent i=new Intent(signup2.this,signup1.class);
            Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
            startActivity(i,bndlanimation);
            //overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
            finish();
           
        }
    });
    b2.setOnClickListener(new OnClickListener() {
       
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent i=new Intent(signup2.this,signup1.class);
            Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
            startActivity(i,bndlanimation);
            //overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
            finish();
           
        }
    });
   
    btn.setOnClickListener(new OnClickListener() {
       
        private JSONArray login_jsonarray;
        private JSONObject login_jsonobj;
        private String login_status;
        private String login_userid;
        private String login_inputline;

        public void onClick(View v) {
            // TODO Auto-generated method stub
            System.out.println("Login_Url");
            str1=edt1.getText().toString();
            str2=edt2.getText().toString();
            str3=edt3.getText().toString();
           
            if(edt1.getText().toString().equals("")){
               
                edt1.setError("Required");

            }
            else if(edt2.getText().toString().equals("")){
               
                edt2.setError("Required");

            }
            else if(edt2.length()<=8){
               
                edt2.setError("Required minimum 8 character");

            }
           
       
           
             if((str2.length()>=8)&&(str1.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")))
            {
                               
               
            try {
               
                Login_Url= new URL(Liveurl+"mobile/signup_second_step?user_id="+h_id+"&email="+str1+"&password="+str2+"&phone_number="+str3);
                BufferedReader login_reader;
                String str_login="";
   
                login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));
               
                String s=Login_Url.toString();
               
                while ((login_inputline = login_reader.readLine())!= null)
                {
                   
                    str_login += login_inputline;
               

                   
                }
               
                System.out.print("login"+str_login);
               
                login_jsonarray = new JSONArray(str_login);
               
                       
                 
                 for(int i=0; i <login_jsonarray.length(); i++)
                    {
                     
                     
                login_jsonobj = login_jsonarray.getJSONObject(i);
                login_status =    login_jsonobj.getString("status");
                login_userid=  login_jsonobj.getString("user_id");
               
                    }
                 
                 
                 if(login_status.matches("success")){
                     
                     
                     loadSavedPreferences();
                     Intent i = new Intent(signup2.this, AndroidTabLayoutActivity.class);
                        i.putExtra("userid", h_id);
                        i.putExtra("userpoto", h2);
                        Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                        startActivity(i, bndlanimation);
                        finish();   
                 }
                 
                else if(login_status.matches("Email already registered")) {
                     Toast.makeText(getApplicationContext(), "Email Already Taken", Toast.LENGTH_LONG).show();
                     
                     }
           
               
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
               
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Login_Url);
           
           
               
            }
           
             else{
             Toast.makeText(getApplicationContext(), "Enter valid email id & password", Toast.LENGTH_LONG).show();
         
             }
       
           
        }
    });
   
        }
        public boolean onKeyDown(int keyCode, KeyEvent event){
            if(keyCode == KeyEvent.KEYCODE_BACK) {
                    Intent i = new Intent(signup2.this, signup1.class);             
                    Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
                    startActivity(i,bndlanimation);
                    return true;
            }
            return false;
        }

         private void loadSavedPreferences() {   
             //User has successfully logged in, save this information
             // We need an Editor object to make preference changes.
           
             SharedPreferences settings = getSharedPreferences(index.PREFS_NAME, 0);
             SharedPreferences.Editor editor = settings.edit();
     
             //Set "hasLoggedIn" to true
             editor.putString ("sinuser", h_id);
             // Commit the edits!
             editor.commit();
      
            }
       
       
}