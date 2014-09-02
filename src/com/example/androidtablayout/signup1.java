package com.example.androidtablayout;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class signup1 extends splash {
   
     String str,str1;
     URL Login_Url;
     ImageView sedioheader,next,john,edit,b1,b2;
     EditText name;
     protected String login_inputline;
     protected JSONArray login_jsonarray;
     protected JSONObject login_jsonobj;
     protected String login_status;
     protected String login_userid;
     private Bitmap bitmap_profile_image;
     private String imagepath;
      private String stry;
      String Liveurl="";
    private ImageView imVCature_pic;
     private static final int CAM_REQUREST = 1313;
     private static int RESULT_LOAD_IMAGE = 1;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullname);
         if( Build.VERSION.SDK_INT >= 9){
              StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
              StrictMode.setThreadPolicy(policy);
       }
         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         Liveurl = sharedPreferences.getString("liveurl", null);        
         
        sedioheader=(ImageView)findViewById(R.id.Sedio);
        next=(ImageView)findViewById(R.id.signin);
        john=(ImageView)findViewById(R.id.profile);
        edit=(ImageView)findViewById(R.id.edit_photo);
        name=(EditText)findViewById(R.id.edit_fullname);
        b1=(ImageView)findViewById(R.id.imageView2);
        b2=(ImageView)findViewById(R.id.imageView3);

         b1.setOnClickListener(new OnClickListener() {
               
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent i=new Intent(signup1.this,splash.class);
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
                    Intent i1=new Intent(signup1.this,splash.class);
                     Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
                    startActivity(i1,bndlanimation);
                    //overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
                    finish();
                   
                   
                }
            });
           
       
       
       
        john.setOnClickListener(new OnClickListener() {
           
            public void onClick(View v) {
               
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(signup1.this);

                alertDialog2.setTitle("");

                alertDialog2.setMessage("Please select an image..");

                alertDialog2.setIcon(R.drawable.act_camera);

                alertDialog2.setPositiveButton("CAMERA.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);           
                            //    startActivityForResult(cameraIntent, CAM_REQUREST);
                               
                                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                 /*create instance of File with name img.jpg*/
                                 File file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
                                 /*put uri as extra in intent object*/
                                 intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                 /*start activity for result pass intent as argument and request code */
                                 startActivityForResult(intent, 1);
       
                            }
                        });
                              
                        alertDialog2.setNegativeButton("GALLERY.",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                               
                                startActivityForResult(i, RESULT_LOAD_IMAGE);
                               
                            }
                        });

                alertDialog2.show();
               
               
               
               
            }
        });
       
        /*name.addTextChangedListener(new TextWatcher() {
           
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                enable();
            }
           
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
                // TODO Auto-generated method stub
               
            }
           
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
               
            }
        });*/
       
       
       
        next.setOnClickListener(new OnClickListener() {
           
            public void onClick(View v) {
               
                System.out.println("Login_Url");
               
                 if((name.getText().toString().trim().equals(""))||(name.getText().toString().trim().length()<3))
                    {
               
                     name.setError("Enter full name.");
                    // Intent i = new Intent(signup1.this, signup1.class);
                    // startActivity(i);
                       
                      }
               
                 else
                 {
               
                str1=name.getText().toString();
               
                Imageuploading();
               
               
                try {
                    // mobile/signup_first_step?fullname='+fullname+'&profileimage='"http://www.theunrealtimes.com/wp-content/uploads/2012/08/danny-morrison.jpg";
                    Login_Url= new URL(Liveurl+"mobile/signup_first_step?fullname="+str1+"&profileimage="+stry);
                    BufferedReader login_reader;
                    String str_login="";
       
                    login_reader = new BufferedReader(new InputStreamReader(Login_Url.openStream()));
                   
                    String s=Login_Url.toString();
                   

            //        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                   
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
               
                Intent i = new Intent(signup1.this, signup2.class);
                i.putExtra("userid", login_userid);
                Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_left_in,R.anim.trans_left_out).toBundle();
                startActivity(i, bndlanimation);
                finish();
               
                 }
               
            }
        });
       
       
       
       
       
    }

/*    protected void enable() {
        // TODO Auto-generated method stub
        boolean isReady =name.getText().toString().trim().length()>=3;
        if (isReady) {
              next.setEnabled(true);
           } else {
              next.setEnabled(false);
            }
          }*/
   

    protected void Imageuploading() {
        // TODO Auto-generated method stub
       
         try{
                
                
                   Log.e("dfdfd", "dfdf");
                 
                   HttpURLConnection connection = null;
                   DataOutputStream outputStream = null;
                   DataInputStream inputStream = null;

                   String pathToOurFile = imagepath;
                  
              //String urlServer = "http://demo.cogzideltemplates.com/client/snapchat-clone/index.php/user/image_upload";
                String urlServer = "http://demo.cogzideltemplates.com/client/vine-clone/mobile/image_upload";
                   String lineEnd = "\r\n";
                   String twoHyphens = "--";
                   String boundary =  "*****";

                   int bytesRead, bytesAvailable, bufferSize;
                   byte[] buffer;
                   int maxBufferSize = 1*1024*1024;

                   FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile));

                   URL url = new URL(urlServer);
                   connection = (HttpURLConnection) url.openConnection();

                   // Allow Inputs & Outputs
                   connection.setDoInput(true);
                   connection.setDoOutput(true);
                   connection.setUseCaches(false);

                   // Enable POST method
                   connection.setRequestMethod("POST");

                   connection.setRequestProperty("Connection", "Keep-Alive");
                   connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

                   outputStream = new DataOutputStream( connection.getOutputStream() );
                   outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                   outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pathToOurFile +"\"" + lineEnd);
                   outputStream.writeBytes(lineEnd);

                   bytesAvailable = fileInputStream.available();
                   bufferSize = Math.min(bytesAvailable, maxBufferSize);
                   buffer = new byte[bufferSize];

                   // Read file
                   bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                   while (bytesRead > 0)
                   {
                   outputStream.write(buffer, 0, bufferSize);
                   bytesAvailable = fileInputStream.available();
                   bufferSize = Math.min(bytesAvailable, maxBufferSize);
                   bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                   }

                   outputStream.writeBytes(lineEnd);
                   outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                   // Responses from the server (code and message)
               int serverResponseCode = connection.getResponseCode();
                   String serverResponseMessage = connection.getResponseMessage();
                  
                Toast.makeText(getApplicationContext(),  serverResponseMessage, Toast.LENGTH_LONG).show();
                   System.out.println("image"+serverResponseMessage);
                  
                   fileInputStream.close();
                   outputStream.flush();
                   outputStream.close();
               
                   DataInputStream inputStream1 = null;
                inputStream1 = new DataInputStream (connection.getInputStream());
                String str="";
                String Str1_imageurl="";
               
                while ((  str = inputStream1.readLine()) != null)
                {
                    Log.e("Debug","Server Response "+str);
                   
                    Str1_imageurl = str;
                    Log.e("Debug","Server Response String imageurl"+str);
                }
                inputStream1.close();
                System.out.println("image url"+Str1_imageurl);
                Toast.makeText(getApplicationContext(),  Str1_imageurl, Toast.LENGTH_LONG).show();
                stry=Str1_imageurl.trim();
                     
                     }
                     catch(Exception e){
                         
                            e.printStackTrace();

                     }
                     
       
       
    }
   
   
   
/*    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    { 
    super.onActivityResult(requestCode, resultCode, data); 
 
          if (requestCode == CAM_REQUREST)
          { 
              bitmap_profile_image = (Bitmap) data.getExtras().get("data"); 
              john.setImageBitmap(bitmap_profile_image);
              imagepath =  ImageWrite(bitmap_profile_image);
          
          }
         
        else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data)
          {
              Uri selectedImage = data.getData();
              String[] filePathColumn = { MediaStore.Images.Media.DATA };
  
              Cursor cursor = getContentResolver().query(selectedImage,
                      filePathColumn, null, null, null);
              cursor.moveToFirst();
  
              int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
              String picturePath = cursor.getString(columnIndex);
              cursor.close();
              
              //ImageView imageView = (ImageView) findViewById(R.id.imgView);
              john.setImageBitmap(BitmapFactory.decodeFile(picturePath));
              imagepath =  ImageWrite(BitmapFactory.decodeFile(picturePath));
           
          
          }
         
         
         
      }*/

 public String ImageWrite(Bitmap bitmap1)
    {
       
         String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            OutputStream outStream = null;
            File file = new File(extStorageDirectory, "slectimage.PNG");
               
            try
            {
       
                outStream = new FileOutputStream(file);
                bitmap1.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                outStream.flush();
                outStream.close();

              

            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
               
            } catch (IOException e)
            {
                e.printStackTrace();
              
            }
            String imageInSD = "/sdcard/slectimage.PNG";
       
            return imageInSD;
       
    }
   
 
 private void initializeControls() {
     Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        /*create instance of File with name img.jpg*/
        File file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
        /*put uri as extra in intent object*/
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        /*start activity for result pass intent as argument and request code */
        startActivityForResult(intent, 1);

 
 }
 
 /*
 
 protected void onActivityResult1(int requestCode, int resultCode, Intent data) {
  super.onActivityResult(requestCode, resultCode, data);
  //if request code is same we pass as argument in startActivityForResult
  if(requestCode==1){
   //create instance of File with same name we created before to get image from storage
   File file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
   //Crop the captured image using an other intent
   try {
    the user's device may not support cropping
    cropCapturedImage(Uri.fromFile(file));
   }
   catch(ActivityNotFoundException aNFE){
    //display an error message if user device doesn't support
    String errorMessage = "Sorry - your device doesn't support the crop action!";
    Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
    toast.show();
   }
  }
  if(requestCode==2){
   //Create an instance of bundle and get the returned data
   Bundle extras = data.getExtras();
   //get the cropped bitmap from extras
   Bitmap thePic = extras.getParcelable("data");
   //set image bitmap to image view
 //  san=thePic;
   john.setImageBitmap(thePic);
   imagepath =  ImageWrite(thePic);
  
 
    Toast.makeText(getApplicationContext(), "Coffe", Toast.LENGTH_LONG).show();

  
  }
 }
 
 */
 
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      //if request code is same we pass as argument in startActivityForResult
      if(requestCode==1){
       //create instance of File with same name we created before to get image from storage
       File file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
       //Crop the captured image using an other intent
       try {
          
        /*the user's device may not support cropping*/
           cropCapturedImage(Uri.fromFile(file));
       
       }
       catch(ActivityNotFoundException aNFE){
        //display an error message if user device doesn't support
        String errorMessage = "Sorry - your device doesn't support the crop action!";
        Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
        toast.show();
       }
      }
      if(requestCode==2){
       //Create an instance of bundle and get the returned data
       Bundle extras = data.getExtras();
       //get the cropped bitmap from extras
       Bitmap thePic = extras.getParcelable("data");
       //set image bitmap to image view
     //  san=thePic;
      
       john.setImageBitmap(thePic);
       imagepath =  ImageWrite(thePic);
      
       Toast.makeText(getApplicationContext(), "Coffe", Toast.LENGTH_LONG).show();

      
      }
     
        else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data)
        {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
           
            //create instance of File with same name we created before to get image from storage\
            //Crop the captured image using an other intent
            try {
               
             /*the user's device may not support cropping*/
                cropCapturedImage(Uri.parse(picturePath));
            
            }
            catch(ActivityNotFoundException aNFE){
             //display an error message if user device doesn't support
             String errorMessage = "Sorry - your device doesn't support the crop action!";
             Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
             toast.show();
            }
          
           
           
        
        }
     
     
     
     
     
     
     }
 
 
 
 
 //create helping method cropCapturedImage(Uri picUri)
 public void cropCapturedImage(Uri picUri){
  //call the standard crop action intent
  Intent cropIntent = new Intent("com.android.camera.action.CROP");
  //indicate image type and Uri of image
  cropIntent.setDataAndType(picUri, "image/*");
  //set crop properties
  cropIntent.putExtra("crop", "true");
  //indicate aspect of desired crop
  cropIntent.putExtra("aspectX", 1);
  cropIntent.putExtra("aspectY", 1);
  //indicate output X and Y
  cropIntent.putExtra("outputX", 256);
  cropIntent.putExtra("outputY", 256);
  //retrieve data on return
  cropIntent.putExtra("return-data", true);
  //start the activity - we handle returning in onActivityResult
  startActivityForResult(cropIntent, 2);
 }
   

}
