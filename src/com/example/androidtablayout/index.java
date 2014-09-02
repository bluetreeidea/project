package com.example.androidtablayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class index extends Activity  
{
    View view1,view2;
    ImageView img;
    ImageView img1,b1,b2;
    EditText Email,Password;
    TextView text,text1;
    String Get_email,Get_Password;
    Button Login;
    URL Login_Url;
    String reader;
    String login_inputline;
    String login_status;
    JSONArray login_jsonarray;
    JSONObject login_jsonobj,login_Error;
    String status;
    String LoginUserName,LoginPhoneNumber,LoginImageurl;
    String Liveurl="";
    // SessionManager session;
    public    static String User_id;
    TextView Forgot_password_link;
    SharedPreferences appSharedPrefs;
    SharedPreferences.Editor prefsEditor;
    ImageView logo;
    ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    
    public static final String PREFS_NAME = "MyPrefsFile";

    
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
      
        setContentView(R.layout.index);
       
        
        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
     }
       
        appSharedPrefs  = this.getSharedPreferences("LoginPage", MODE_PRIVATE);
         prefsEditor = appSharedPrefs.edit();
         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         Liveurl = sharedPreferences.getString("liveurl", null);
        
        
        
        Email = (EditText)findViewById(R.id.username);
        Password= (EditText)findViewById(R.id.password);
        img=(ImageView)findViewById(R.id.signin);
        b1=(ImageView)findViewById(R.id.imageView2);
        b2=(ImageView)findViewById(R.id.imageView3);

         b1.setOnClickListener(new OnClickListener() {
                
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @SuppressLint("NewApi")
                @Override
                public void onClick(View v) {
                    
                    Intent i=new Intent(index.this,splash.class);
                    //overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
                    Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
                    startActivity(i,bndlanimation);
                    finish();
                    
                }
            });
            b2.setOnClickListener(new OnClickListener() {
                
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @SuppressLint("NewApi")
                @Override
                public void onClick(View v) {
                    Intent i1=new Intent(index.this,splash.class);
                    //overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
                    Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
                    startActivity(i1,bndlanimation);
                    finish();
                    
                }
            });
       
            img.setOnClickListener( new OnClickListener()
            {                
                
                public void onClick(View v)
                {                        
    
                    
                    Get_email =    Email.getText().toString();
                    Get_Password = Password.getText().toString();
            
            if(Email.getText().toString().equals(""))
            {
                Email.setError("Required");
                
            }else if(Password.getText().toString().equals(""))
            {
                Password.setError("Required");
            }
            else if(Get_email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && Get_email.length() > 0)
            {
            
                
                
                try
                {
                    
                    
                    Login_Url = new URL(Liveurl+"mobile/login?email="+Get_email+"&password="+Get_Password);
                    
                    BufferedReader login_reader;
                    String str_login="";            
                   login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));
                    
                    
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

                        
                        if(login_status.matches("success"))
                        {
                            

                            User_id =login_jsonobj.getString("id");
                            
                            System.out.println("id"+User_id);
                            
                            login_Progress();
                        
                            
                          
                        }
                        else if (login_status.matches("Email and Password does not match"))
                        {
                            Toast toast=Toast.makeText(getApplicationContext(), "The username or password is incorrect", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        
                        }else{
                            img.setClickable(true);
                            Toast toast=Toast.makeText(getApplicationContext(), "Kindly double check your credential", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                        
                        
                            }
                        
                
                }
                catch (MalformedURLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            else
            {
            img.setClickable(true);
                Toast toast=Toast.makeText(getApplicationContext(), "Should Enter A Valid E-Mail ID", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            
            
                }
            });
        
        
    }
    
    public int login_Progress()
    {
        loadSavedPreferences();

          
        Intent Intent_imageview = new Intent(index.this, AndroidTabLayoutActivity.class);
        Intent_imageview.putExtra("userid", User_id);
        startActivity(Intent_imageview);
        img.setClickable(true);
        return 100;
    }
    
    private void loadSavedPreferences() {    
         //User has successfully logged in, save this information
         // We need an Editor object to make preference changes.
        
         SharedPreferences settings = getSharedPreferences(index.PREFS_NAME, 0);
         SharedPreferences.Editor editor = settings.edit();
 
         //Set "hasLoggedIn" to true
         editor.putBoolean("hasLoggedIn", true);
         editor.putString ("euser", User_id);
         // Commit the edits!
         editor.commit();
   
        }

}