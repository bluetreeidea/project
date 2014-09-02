package com.example.androidtablayout;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


import org.brickred.socialauth.Photo;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import com.parse.ParseFacebookUtils;
import com.parse.ParseTwitterUtils;
import com.parse.integratingfacebooktutorial.IntegratingFacebookTutorialApplication;
import com.parse.integratingfacebooktutorial.LoginActivity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

@SuppressLint({ "NewApi", "ShowToast" })
public class splash extends Activity{
  
    ImageView fliq_Logo;
    Button btn_Login,facebook_login,twiit_login;
    TextView T1,t2;
  
    VideoView vd;
    Uri uri;
    //public static final String PREFS_NAME = "LoginPrefs";
  
    private ConnectionDetector cd;
    AlertDialogManager alert = new AlertDialogManager();
    private String s1;
    private String key;
    private String secret1;
    private String g;
    private URL Login_Url;
    private String login_inputline;
    private JSONArray login_jsonarray;
    private JSONObject login_jsonobj;
    private String login_status;
    private String User_id;
    private String accesskey;
    private String accesstoken;
    private String geteee;
    private String getfbid;
    private String getfbtkn;
    private String gettusr;
    private String gettkey;
    private String getfttkn;
    private String suuserid;
    static String TWITTER_CONSUMER_KEY = "pVmI9GQvqBl639Ns3uM9qw"; // place your cosumer key here
    static String TWITTER_CONSUMER_SECRET = "Q28X8uoMMGItTI0TQl25cNkqMPhZWEwpGDVnbWnpXto"; // place your consumer secret here
  
    private static SocialAuthAdapter adapter;
    Profile profileMap;
    List<Photo> photosList;

    // Android Components
    ListView listview;
    AlertDialog dialog;
    TextView title;
    ProgressDialog mDialog;

    // Variables
    boolean status;
    String providerName;
    public static int pos;
    private static final int SELECT_PHOTO = 100;
    public static Bitmap bitmap;
    Context context = this;
  
    String LiveUrl="http://demo.cogzideltemplates.com/client/vine-clone/";
  
    //static String TWITTER_CONSUMER_KEY = "byKbQ4NgRr0JjG5KWpXckg"; // place your cosumer key here
    //static String TWITTER_CONSUMER_SECRET = "yhmK1Lm7oMfzlsf8D5IqoRSEIXhEdeB99lxIZeu5GEE";
    // Preference Constants
    static String PREFERENCE_NAME = "twitter_oauth";
    static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
    static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
    static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";

    static final String TWITTER_CALLBACK_URL = "http://cloudcat.meliosystems.com/";

    // Twitter oauth urls
    static final String URL_TWITTER_AUTH = "auth_url";
    static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
    static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";
    private static SharedPreferences mSharedPreferences;
  
  
  
    private static Twitter twitter;
    private static RequestToken requestToken;
      public static String twitter_token, twitter_secret;
  
        public static final String PREFS_NAME = "MyPrefsFile";

  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage_bg);
        adapter = new SocialAuthAdapter(new ResponseListener());
          if( Build.VERSION.SDK_INT >= 9){
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
         }
          
          SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
     	 Editor editor = sharedPreferences.edit();
     	 editor.putString("liveurl", LiveUrl);									 
     	 editor.commit();
     	String restoredText = sharedPreferences.getString("liveurl", null);
     	 
     	 Toast.makeText(getApplicationContext(), "Live url value"+restoredText, Toast.LENGTH_LONG);
     	 System.out.println("Liveurl value--->"+restoredText);
     	 
            fliq_Logo=(ImageView) findViewById(R.id.logo_img);
            btn_Login=(Button) findViewById(R.id.login_newBtn);
            twiit_login=(Button) findViewById(R.id.Button01);

            T1=(TextView)findViewById(R.id.textView1);
            t2=(TextView)findViewById(R.id.textView2);  
            facebook_login=(Button)findViewById(R.id.button1);
                 
          
            final String TAG = "PRANJAL";
            boolean isImage = false;
            String reviewImageLink;
            MediaController mc;          
             vd = (VideoView) findViewById(R.id.VideoView);
             uri = Uri.parse("android.resource://"+getPackageName() + "/"+R.raw.spll);
            mc = new MediaController(this);
            //vd.setMediaController(mc);
            vd.setVideoURI(uri);                 
            vd.setOnPreparedListener (new OnPreparedListener() {                  
                public void onPrepared(MediaPlayer mp) {
                    vd.start();
                    mp.setLooping(true);
                  
                }
            });
          
          
          
          
          
          
         // Shared Preferences
            mSharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);

          
            cd = new ConnectionDetector(getApplicationContext());

            // Check if Internet present
            if (!cd.isConnectingToInternet()) {
                // Internet Connection is not present
                alert.showAlertDialog(splash.this, "Internet Connection Error",
                        "Please connect to working Internet connection", false);
                // stop executing code by return
                return;
            }
          
          
            /*if(TWITTER_CONSUMER_KEY.trim().length() == 0 || TWITTER_CONSUMER_SECRET.trim().length() == 0){
                // Internet Connection is not present
                alert.showAlertDialog(splash.this, "Twitter oAuth tokens", "Please set your twitter oauth tokens first!", false);
                // stop executing code by return
                return;
            }*/
          
            twiit_login.setOnClickListener(new OnClickListener() {
              
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    //adapter = new SocialAuthAdapter(new ResponseListener());
                    Log.i("click", "1");
                    adapter.addCallBack(Provider.FOURSQUARE,
                            "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");
                    Log.i("click3","1");
                    adapter.authorize(context,Provider.TWITTER);
                    Log.i("click", "2");
                   
                    //loginToTwitter();                              
                }
            });
          
          
          
          
      //---- SharedPreferences email
          
          
            SharedPreferences settings = getSharedPreferences(index.PREFS_NAME,1);
                //Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
                boolean trLoggedIn = settings.getBoolean("trLoggedIn", false);
              
                String PREF_KEY = settings.getString("euser", geteee);
              
                String fbu_id = settings.getString("fuser", getfbid);
                String fb_tkn = settings.getString("ftkn", getfbtkn);
             
                String tr_id = settings.getString("tuser", gettusr);
                String tr_key = settings.getString("tacceskey", gettkey);
                String tr_tkn = settings.getString("taccestkn", getfttkn);
                String su_user = settings.getString("sinuser", suuserid);


          
              
              //  Toast.makeText(getApplicationContext(), fb_tkn, Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(), tr_key, Toast.LENGTH_LONG).show();

              
                if(fb_tkn != null)
                {
                    //Go directly to main activity
                    Intent i = new Intent(splash.this, AndroidTabLayoutActivity.class);
                    i.putExtra("userid", fbu_id);
                    i.putExtra("fb_token", fb_tkn);
                    Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                    startActivity(i, bndlanimation);
                              
                }
              
              
              
             //   Toast.makeText(getApplicationContext(), PREF_KEY, Toast.LENGTH_LONG).show();
 
           
                if(PREF_KEY != null)
                {
                    //Go directly to main activity
                    Intent i = new Intent(splash.this, AndroidTabLayoutActivity.class);
                    i.putExtra("userid", PREF_KEY);
                    Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                    startActivity(i, bndlanimation);
                              
                }
          
                if(trLoggedIn)
                {
                      // Toast.makeText(getApplicationContext(),"Logged-in Sucessfully via Twitter", Toast.LENGTH_LONG).show();  
                   
                   
                       Intent Intent_imageview = new Intent(splash.this, AndroidTabLayoutActivity.class);
                       Intent_imageview.putExtra("userid", tr_id);
                       Intent_imageview.putExtra("accesskey", tr_key);
                       Intent_imageview.putExtra("accesstoken", tr_tkn);
                       startActivity(Intent_imageview);
                       finish();
                   
                }
          
                if(su_user != null)
                {
                    //Go directly to main activity
                    Intent i = new Intent(splash.this, AndroidTabLayoutActivity.class);
                    i.putExtra("userid", su_user);
                    Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                    startActivity(i, bndlanimation);
                              
                }
          
          
            facebook_login.setOnClickListener(new OnClickListener() {
              
                public void onClick(View v) {
                  
                    Intent i = new Intent(splash.this,IntegratingFacebookTutorialApplication.class);
                  //  Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                    //startActivity(i, bndlanimation);
                    startActivity(i);
                    finish();
                  
                }
            });
          
            btn_Login.setOnClickListener(new OnClickListener() {
              
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                  
                    Intent i = new Intent(splash.this, signup1.class);
                    Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                    startActivity(i, bndlanimation);
              
                  
                  
                }
            });
          
        
            t2.setOnClickListener(new OnClickListener() {
              
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                  
                    Intent i = new Intent(splash.this, index.class);
                    Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                    startActivity(i, bndlanimation);
                              
                  
                }
            });
          
      //------------------------------------------------    
        /*    if (!isTwitterLoggedInAlready()) {
              

                Uri uri = getIntent().getData();
              
                Log.e("sahgutwi" , ">" + uri);

                if (uri != null && uri.toString().startsWith(TWITTER_CALLBACK_URL)) {
                    // oAuth verifier
                    String verifier = uri.getQueryParameter(URL_TWITTER_OAUTH_VERIFIER);
                  
                    Toast.makeText(getApplicationContext(),verifier, Toast.LENGTH_LONG).show();
                  
                    try {
                        // Get the access token
                        AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
                      
                        // Shared Preferences
                        Editor e = mSharedPreferences.edit();
                      
                      
                        // After getting access token, access token secret
                        // store them in application preferences
                      
                        e.putString(PREF_KEY_OAUTH_TOKEN, accessToken.getToken());
                        e.putString(PREF_KEY_OAUTH_SECRET,accessToken.getTokenSecret());
                      
                          e.putBoolean(PREF_KEY_TWITTER_LOGIN, true);
                            e.commit();
                        // Store login status - true
                    //String tkn=ParseTwitterUtils.getTwitter().getAuthToken();
                //    String secret=ParseTwitterUtils.getTwitter().getAuthTokenSecret();
                      
                      
                        e.putBoolean(PREF_KEY_TWITTER_LOGIN, true);
                        e.commit(); // save changes

                        /*String bitmap_profile_image ="https://api.twitter.com/"+accessToken.getToken()+"/"+accessToken.getTokenSecret()+"/picture?type=large";
                        System.out.println(bitmap_profile_image);*/
                    /*    Log.e("Twitter OAuth Token", "> " + accessToken.getToken());
                        Log.e("Twitter OAuth Token", "> " + accessToken.getTokenSecret());
  
                          long userID = accessToken.getUserId();
                        User user = twitter.showUser(userID);
                        key=accessToken.getToken();
                        secret1=accessToken.getTokenSecret();
                      
                        /*String accessID=accessToken.getToken();
                        User user1=twitter.showUser(accessID);
                        String secretID=accessToken.getTokenSecret();
                        User user2=twitter.showUser(secretID);
                      
                        s1=user.getScreenName();
                        g=Long.toString(userID);
                        String username=user.getName();
                        mani(s1,g,key,secret1);
                     
                      
                    } catch (Exception e) {
                        // Check log for login errors
                        Log.e("Twitter Login Error", "> " + e.getMessage());
                    }
                }
            }*/
      
    }

    private void mani(String s12, String g2,String key1,String secret12,String sqw) {
        // TODO Auto-generated method stub
        //http://demo.cogzideltemplates.com/client/vine-clone/mobile/tlogin?user_id= &screen_name= &twitter_access_key= &twitter_access_secret=
     
        //String sqw="http://demo.cogzideltemplates.com//client//vine-clone//uploads//images//130905no_image.jpg";
        //String i=t.getProfileImageURL().toString();tlogin
        //System.out.println("image-------->" + img);
        try{  
            Login_Url       = new URL("http://demo.cogzideltemplates.com/client/vine-clone/mobile/tlogin?user_id="+g2+"&screen_name="+s12+"&twitter_access_key="+key1+"&twitter_access_secret="+secret12+"&profile_image="+sqw);
            System.out.println("image url-------->" + Login_Url);
            BufferedReader login_reader;    String str_login="";

            login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));

            while ((login_inputline = login_reader.readLine())!= null)
            {
              
                str_login += login_inputline;
          
          //  Toast.makeText(getApplicationContext(), str_login, Toast.LENGTH_LONG).show();

              
            }
        System.out.print("login"+str_login);
          
                login_jsonarray = new JSONArray(str_login);
              
            {
                int i1=0;    
                 login_jsonobj = login_jsonarray.getJSONObject(i1);
                 login_status = login_jsonobj.getString("status");
                 User_id = login_jsonobj.getString("id");
                 //accesskey=login_jsonobj.getString("twitter_access_key");
                 //accesstoken=login_jsonobj.getString("twitter_access_secret");
                
              
            }
            System.out.println("json value ---------->"+login_status+User_id+accesskey+accesstoken);
          
           /*Toast.makeText(getApplicationContext(),User_id, Toast.LENGTH_LONG).show();  
            Toast.makeText(getApplicationContext(),login_status, Toast.LENGTH_LONG).show();        */  


        }  
        catch(MalformedURLException e){
          
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Toast.makeText(getApplicationContext(),key, Toast.LENGTH_LONG).show();  
        //Toast.makeText(getApplicationContext(),secret1, Toast.LENGTH_LONG).show();  
      
         if(User_id != null)
         {
               // Toast.makeText(getApplicationContext(),"Logged-in Sucessfully via Twitter", Toast.LENGTH_LONG).show();  
            
                 loadSavedPreferences();
                Intent Intent_imageview = new Intent(splash.this, AndroidTabLayoutActivity.class);
                Intent_imageview.putExtra("userid", User_id);
                Intent_imageview.putExtra("accesskey", key1);
                Intent_imageview.putExtra("accesstoken", secret12);
                startActivity(Intent_imageview);
                finish();
            
         }
      
         else{
            
             Toast.makeText(getApplicationContext(),"Logged-in Failure", Toast.LENGTH_LONG).show();  
            
         }
        
        
    }

    /*protected void loginToTwitter() {

        // Check if already logged in
      

      
        if (!isTwitterLoggedInAlready())
      
        {
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
            builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
            Configuration configuration = builder.build();
          
            TwitterFactory factory = new TwitterFactory(configuration);
            twitter = factory.getInstance();
          
            try {
                requestToken = twitter.getOAuthRequestToken(TWITTER_CALLBACK_URL);
                this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL())));
          
            }
            catch (TwitterException e) {
                e.printStackTrace();
            }
          
          
        } else {
            // user already logged into twitter
            //Toast.makeText(getApplicationContext(),"Already Logged into twitter ", Toast.LENGTH_LONG).show();
        /*    Editor e = mSharedPreferences.edit();
            e.remove(PREF_KEY_OAUTH_TOKEN);
            e.remove(PREF_KEY_OAUTH_SECRET);
            e.remove(PREF_KEY_TWITTER_LOGIN);
            e.commit();
      
      
        }
      
    }*/
  
  
    private void logoutFromTwitter() {

        // Clear the shared preferences
        Editor e = mSharedPreferences.edit();
        e.remove(PREF_KEY_OAUTH_TOKEN);
        e.remove(PREF_KEY_OAUTH_SECRET);
        e.remove(PREF_KEY_TWITTER_LOGIN);
        e.commit();

            }
  
    private boolean isTwitterLoggedInAlready() {
        // return twitter login status from Shared Preferences
        return mSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);    }  
    protected void onResume() {
        super.onResume();
    }
     private void loadSavedPreferences() {  
         //User has successfully logged in, save this information
         // We need an Editor object to make preference changes.
      
         SharedPreferences settings = getSharedPreferences(index.PREFS_NAME, 0);
         SharedPreferences.Editor editor = settings.edit();
 
         //Set "hasLoggedIn" to true
         editor.putBoolean("trLoggedIn", true);

         editor.putString ("tuser", User_id);
         editor.putString ("tacceskey", accesskey);
         editor.putString ("taccestkn", accesstoken);


       
         // Commit the edits!
         editor.commit();
 
        }
  
    
    
    
    
    
    
    
    
    
    
    
     //----------------------------------------------------------------- Twitter login functionality----------------------------->>>>>
    

        public static SocialAuthAdapter getSocialAuthAdapter() {
            return adapter;
        }

        // To receive the response after authentication
        private final class ResponseListener implements DialogListener {

            @Override
            public void onComplete(Bundle values) {

                Log.d("Custom-UI", "Successful");

                // Changing Sign In Text to Sign Out
                //View v = listview.getChildAt(pos - listview.getFirstVisiblePosition());
                //TextView pText = (TextView) v.findViewById(R.id.signstatus);
                //pText.setText("Sign Out");

                // Get the provider
                providerName = values.getString(SocialAuthAdapter.PROVIDER);
                Log.d("Custom-UI", "providername = " + providerName);
              
                Toast.makeText(splash.this, providerName + " connected", Toast.LENGTH_SHORT).show();
                Log.i("get profile ", "before");
                adapter.getUserProfileAsync(new ProfileDataListener());
                Log.i("get profile ", "after");
                int res = getResources().getIdentifier(providerName + "_array", "array", splash.this.getPackageName());
                //Events(0, providerName);

                AlertDialog.Builder builder = new AlertDialog.Builder(splash.this);
                builder.setTitle("Select Options");
                builder.setCancelable(true);
                builder.setIcon(android.R.drawable.ic_menu_more);

                mDialog = new ProgressDialog(splash.this);
                mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mDialog.setMessage("Loading...");

                builder.setSingleChoiceItems(new DialogAdapter(splash.this, R.layout.provider_options, getResources()
                        .getStringArray(res)), 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        Log.i("item", ""+item);
                        //Events(item, providerName);
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                //dialog.show();

            }

            @Override
            public void onError(SocialAuthError error) {
                Log.d("Custom-UI", "Error");
                error.printStackTrace();
            }

            @Override
            public void onCancel() {
                Log.d("Custom-UI", "Cancelled");
            }

            @Override
            public void onBack() {
                Log.d("Custom-UI", "Dialog Closed by pressing Back Key");

            }
        }

        // To receive the profile response after authentication
        private final class ProfileDataListener implements SocialAuthListener<Profile> {

            @Override
            public void onExecute(String provider, Profile t) {

                Log.d("Custom-UI", "Receiving Data");
                mDialog.dismiss();
                Profile profileMap = t;
                //loadSavedPreferences();
                String token=adapter.getCurrentProvider().getAccessGrant().getSecret();
                System.out.println("Seceret value is--------------->"+token);
                String key=adapter.getCurrentProvider().getAccessGrant().getKey();
                System.out.println("key is----------->"+ key);
                String []part=key.split("-");
                String part1=part[0];
                String part2=part[1].toString();
                System.out.println("after split");
                System.out.println("userid" + part1);
                System.out.println("secret" + part2);
                String id=profileMap.getValidatedId();
                String i=profileMap.getEmail();
                String screen=profileMap.getFullName();
                String sqw=profileMap.getProfileImageURL().toString();
                System.out.println("screen---->" + screen);
                System.out.println("id------->" + id);
                System.out.println("secret---->" + token);
                //System.out.println("image----->" + img);
                mani(screen,id,part2,token,sqw);
                //intent.putExtra("provider", provider);
                //intent.putExtra("profile", profileMap);
                //startActivity(intent);
            }

            @Override
            public void onError(SocialAuthError e) {

            }
        }

        // To get status of message after authentication
        private final class MessageListener implements SocialAuthListener<Integer> {
            @Override
            public void onExecute(String provider, Integer t) {
                Integer status = t;
                if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
                    Toast.makeText(splash.this, "Message posted on" + provider, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(splash.this, "Message not posted" + provider, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(SocialAuthError e) {

            }
        }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
            super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

            switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                        bitmap = BitmapFactory.decodeStream(imageStream);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        /**
         * CustomAdapter for showing List. On clicking any item , it calls
         * authorize() method to authenticate provider
         */

        public class DialogAdapter extends BaseAdapter {
            // Android Components
            private final LayoutInflater mInflater;
            private final Context ctx;
            private Drawable mIcon;
            String[] drawables;
            String[] options;

            public DialogAdapter(Context context, int textViewResourceId, String[] providers) {
                // Cache the LayoutInflate to avoid asking for a new one each time.
                ctx = context;
                mInflater = LayoutInflater.from(ctx);
                options = providers;
            }

            /**
             * The number of items in the list is determined by the number of
             * speeches in our array.
             */
            @Override
            public int getCount() {
                return options.length;
            }

            /**
             * Since the data comes from an array, just returning the index is
             * sufficent to get at the data. If we were using a more complex data
             * structure, we would return whatever object represents one row in the
             * list.
             */
            @Override
            public Object getItem(int position) {
                return position;
            }

            /**
             * Use the array index as a unique id.
             */
            @Override
            public long getItemId(int position) {
                return position;
            }

            /**
             * Make a view to hold each row.
             *
             * @see android.widget.ListAdapter#getView(int, android.view.View,
             *      android.view.ViewGroup)
             */
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                // A ViewHolder keeps references to children views to avoid
                // unneccessary
                // calls to findViewById() on each row.
                ViewHolder holder;

                // When convertView is not null, we can reuse it directly, there is
                // no
                // need to reinflate it. We only inflate a new View when the
                // convertView
                // supplied by ListView is null.
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.provider_options, null);

                    // Creates a ViewHolder and store references to the two children
                    // views
                    // we want to bind data to.
                    holder = new ViewHolder();
                    holder.text = (TextView) convertView.findViewById(R.id.providerText);
                    holder.icon = (ImageView) convertView.findViewById(R.id.provider);

                    convertView.setTag(holder);
                } else {
                    // Get the ViewHolder back to get fast access to the TextView
                    // and the ImageView.
                    holder = (ViewHolder) convertView.getTag();
                }

                String drawables[] = ctx.getResources().getStringArray(R.array.drawable_array);

                mIcon = ctx.getResources().getDrawable(
                        ctx.getResources().getIdentifier(drawables[position], "drawable", ctx.getPackageName()));

                // Bind the data efficiently with the holder
                holder.text.setText(options[position]);
                if (options[position].equalsIgnoreCase("career"))
                    holder.icon.setImageResource(R.drawable.career);
                else
                    holder.icon.setImageDrawable(mIcon);

                return convertView;
            }

            class ViewHolder {
                TextView text;
                ImageView icon;
            }
        }

//------------------------------------------------------------------End twitter function---------------------------------------->    
    
    
    
    
    
    
    

}