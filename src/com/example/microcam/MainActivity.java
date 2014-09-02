package com.example.microcam;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.avos.minute.recorder.*;
import com.avos.minute.util.*;
import com.example.androidtablayout.AndroidTabLayoutActivity;
import com.example.androidtablayout.R;
import com.example.androidtablayout.index;
import com.example.androidtablayout.splash;

import java.util.concurrent.TimeUnit;

import org.apache.http.conn.ManagedClientConnection;


import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ProgressBar;
import android.widget.LinearLayout;

	@SuppressLint("HandlerLeak")
	public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static int LONG_PRESS_TIME = 70; // Time in miliseconds
    private long tsCanRecord = 7000;
    private static final String TASK_UPDATE_RECORD_PROGRESS = "record_progress";

	public static int total = 0;

    private FrameLayout cameraPreviewFrame;
    private LinearLayout portraitOverlay;
    private VideoView videoView;
    private FrameLayout videoPreviewOverlay;
    private Button nextButton;
    private Button cancelButton;
    private ProgressBar progressBar;
    private ProgressBar progressBar1;
    private Button goButton;
    private Button wentButton;

   
    
    private int mOrientation = -1;
    private OrientationEventListener mOrientationEventListener; 

    private RecordingManager mRecManager;
    private String videoFilepath = "";
    private String snapshotFilepath = "";

    private enum internalStatus {
        Recroding,
        Previewing,
    };
    private internalStatus status;
    private ProgressCounter progressCounter = null;
    
    private int fileSeq = -1;
    private String videoTempDir = null;
    final Handler _handler = new Handler();
    Runnable _longPressed = new Runnable() {
        public void run() {
            String videoFile = nextVideoFilepath();
            Log.d(TAG, "start recording (file=" + videoFile + ")...");
            mRecManager.startRecording(videoFile);
            progressCounter = new ProgressCounter(tsCanRecord, 70);
            progressCounter.start();
        }
    };

	private String user_id;
    
    

    private String nextVideoFilepath() {
    	System.out.println("Entry to nexvideoFilepath() function");
        if (videoTempDir == null) {
            File mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().getPath(), "vine");
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());
            System.out.println("time Stamp-------->"+timeStamp);
            fileSeq = 1;
            videoTempDir = mediaStorageDir + File.separator + "TEMP_" + timeStamp;
            File dir = new File(videoTempDir);
            dir.mkdirs();
        }
        System.out.println("video Temp directory Stamp-------->"+videoTempDir);
        fileSeq++;
        System.out.println("File sequence--------->"+fileSeq);
        return videoTempDir + File.separator + fileSeq + ".mp4";
    }

    private String getFinalVideoFilepath() {
    	System.out.println("Entry to get final video file path()");
        if (videoTempDir == null) {
        	
            File mediaStorageDir = new File(Environment
                    .getExternalStorageDirectory().getPath(), "vine");
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());
            fileSeq = 1;
            videoTempDir = mediaStorageDir + File.separator + "TEMP_" + timeStamp;
            File dir = new File(videoTempDir);
            dir.mkdirs();
        }
        System.out.println(" getfinalvideofilepath()"+videoTempDir);
        return videoTempDir + "-merged.mp4";        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        
        super.onCreate(savedInstanceState);
        loadactivity();
    }
     public void loadactivity(){
        
        setContentView(R.layout.activity_main);
        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy); 
     }
        
        //------
        Intent i=getIntent();
        user_id=i.getStringExtra("userid");
        
        
        
        this.cameraPreviewFrame = (FrameLayout) findViewById(R.id.camera_preview_frame);
        this.videoView = (VideoView) findViewById(R.id.video_preview_frame);
        this.videoPreviewOverlay = (FrameLayout) findViewById(R.id.video_preview_overlay);
        changePreviewOverlayHeight(this.videoPreviewOverlay);
        cameraPreviewFrame.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                case (MotionEvent.ACTION_DOWN):
                    Log.d(TAG, "Action was DOWN " + String.format("x:%g y:%g", event.getX(), event.getY()));
                    _handler.postDelayed(_longPressed, LONG_PRESS_TIME);
                    return true;
                case (MotionEvent.ACTION_MOVE):
                    return true;
              case (MotionEvent.ACTION_UP):
                case (MotionEvent.ACTION_CANCEL):
                    Log.d(TAG, "Action was UP or CANCEL");
                    _handler.removeCallbacks(_longPressed);
                    mRecManager.stopRecording();
                    if (null != progressCounter) {
                        progressCounter.cancel();
                    }
                    return true;
                case (MotionEvent.ACTION_OUTSIDE):
                    return true; 
                default:
                    return false;
                }
            }
        });
        this.portraitOverlay = (LinearLayout) findViewById(R.id.camera_portrait_overlay);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                if (orientation == ORIENTATION_UNKNOWN) {
                    return;
                }
//                Log.d(TAG, "set Orientation: " + orientation);
//                mRecManager.setOrientation(orientation);
                mOrientation = orientation;
            }
        };

        status = internalStatus.Recroding;
        nextButton = (Button) findViewById(R.id.next_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        goButton = (Button) findViewById(R.id.go_button);
        wentButton = (Button) findViewById(R.id.went_button);        
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               
                if (internalStatus.Recroding == status) {
                    //videoView
                 	//Intent intent = new Intent(MainActivity.this, URLConnectionReader.class);
                	  
                    Intent i = new Intent(MainActivity.this, AndroidTabLayoutActivity.class);
                	 i.putExtra("userid", user_id);

                     startActivity(i);
                 	
                } else if (internalStatus.Previewing == status) {
               
           //    Intent i = new Intent(MainActivity.this, MainActivity.class);
             //	 i.putExtra("userid", user_id);
               //   startActivity(i);
                  
               // 	new cancelprocess().execute((Void) null); 
                  
                	           }
            }
        });
        wentButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               
                if (internalStatus.Recroding == status) {
                    //videoView
                 	//Intent intent = new Intent(MainActivity.this, URLConnectionReader.class);
                	  
                 //   Intent i = new Intent(MainActivity.this, AndroidTabLayoutActivity.class);
                	// i.putExtra("userid", user_id);

                  //   startActivity(i);
                 	
                } else if (internalStatus.Previewing == status) {
               
              // Intent i = new Intent(MainActivity.this, MainActivity.class);
             	// i.putExtra("userid", user_id);
                 // startActivity(i);             
                	               	
                	AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);


    			    adb.setTitle("Discard Video");

    			    adb.setIcon(android.R.drawable.ic_dialog_alert);


    			    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    			        public void onClick(DialogInterface dialog, int which) {


    	                	new cancelprocess().execute((Void) null); 

    			      } });


    			    adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    			        public void onClick(DialogInterface dialog, int which) {

    			           dialog.cancel();
    			        	
    			        	
    			        	
    			      } });
    			    adb.show();
                	
                	
                	
                	
                //	finish();
                  
                	           }
            }
        });
        
        goButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrientationEventListener.disable();
                if (internalStatus.Recroding == status) {
                    //videoView
                   new VideoMergeTask().execute((Void) null);                
                   
                } else if (internalStatus.Previewing == status) {
                   /* Log.d(TAG, "begin to crop video");
                    VideoEngine engine = new VideoEngine();
                    engine.crop(videoFilepath, Utils.getFinalVideoPath(), 480, 480, 80);
                    Log.d(TAG, "end to crop video");
                    Toast.makeText(MainActivity.this, "Video process successed.", Toast.LENGTH_LONG).show();*/
                	
                    new performBackgroundTask().execute((Void) null); 

             	 //  Intent i = new Intent(MainActivity.this, upload.class);
                  
             	  // startActivity(i);
                    
                
                }
            }
        });
   
       
        
        
        this.mRecManager = RecordingManager.getInstance(MainActivity.this, cameraPreviewFrame);
        videoFilepath = Utils.getNextVideoPath();
        snapshotFilepath = Utils.getNextSnapshotPath();
        mRecManager.init(videoFilepath, snapshotFilepath);
        nextButton.setVisibility(View.GONE);
        progressBar1.setVisibility(View.GONE);
        goButton.setVisibility(View.GONE);        
        wentButton.setVisibility(View.GONE);
    }
    
     	public int playbackVideo(String videoFilepath) {
        cameraPreviewFrame.removeAllViews();
        cameraPreviewFrame.setVisibility(View.GONE);
        status = internalStatus.Previewing;
        //nextButton.setText(".");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) videoView.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.widthPixels * mRecManager.getVideoWidth() / mRecManager.getVideoHeight();
        Log.d(TAG, "layout width=" + params.width + ", height=" + params.height);
        videoView.setLayoutParams(params);
        videoView.setVisibility(View.VISIBLE);

        videoView.setVideoURI(Uri.parse(videoFilepath));
        videoView.start();
     
        nextButton.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
        wentButton.setVisibility(View.VISIBLE);

        
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrientationEventListener.disable();
                if (internalStatus.Recroding == status) {
                    //videoView	
             	   
             	   setProgressBarIndeterminateVisibility(true);
                	
             	//   Toast.makeText(MainActivity.this, "merg preview.", Toast.LENGTH_LONG).show();
                 new VideoMergeTask().execute((Void) null);

                } else if (internalStatus.Previewing == status) {
                   /* Log.d(TAG, "begin to crop video");
                    VideoEngine engine = new VideoEngine();
                    engine.crop(videoFilepath, Utils.getFinalVideoPath(), 480, 480, 80);
                    Log.d(TAG, "end to crop video");
             //       Toast.makeText(MainActivity.this, "Video process successed.", Toast.LENGTH_LONG).show(); */
                
             	  // Intent i = new Intent(MainActivity.this, upload.class);
                 //   startActivity(i);
                    new performBackgroundTask().execute((Void) null); 
                
                
                }
            }
        });        
        
             
        
        videoView.setOnErrorListener(new OnErrorListener() {
           @Override
           public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
              Log.d(TAG, "ERROR HERE");
             videoView.stopPlayback();
                videoView.setVisibility(View.GONE);
                return true;
            }
       });
     
        
        
            videoView.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer arg0) {
                videoView.start();
                Log.d(TAG, "vide prepared here");
                videoView.setVisibility(View.VISIBLE);
                videoView.measure(0, 0);
                Log.d(TAG,
                        "video size" + videoView.getMeasuredHeight() + ":"
                                + videoView.getMeasuredWidth());

            }

        });
        videoView.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                Log.d(TAG, "vide play again");
                videoView.start();
            }
        });
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();

        // Initialize the default project before init the RecordingManager
        if (mRecManager != null) {
            mRecManager.onResume();
        }

        mRecManager.setOrientationEventListener(mOrientationEventListener);
        mOrientationEventListener.enable();
    }

    @Override
    protected void onPause() {
        Log.v(TAG, "onPause");
        if (mRecManager != null) {
            mRecManager.onPause();
        }
        super.onPause();
        mOrientationEventListener.disable();
    }

    @Override
    protected void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    public int getOrientation() {
        return mOrientation;
    }
    public OrientationEventListener getOrientationEventListener() {
        return mOrientationEventListener;
    }

    /**
     * Adjust the overlay height depends on pixel density to show the camera
     * view in correct ratio.
     */
    @SuppressLint("NewApi")
	private void changePreviewOverlayHeight(FrameLayout frameLayout) {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        LinearLayout.LayoutParams LinearLayoutParams;
        LinearLayoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        LinearLayoutParams.height = width;
        frameLayout.setLayoutParams(LinearLayoutParams);
    }
    private Handler progressHandler = new Handler() {
        public void handleMessage(Message msg) {
            String task = (String) msg.obj;
            if (TASK_UPDATE_RECORD_PROGRESS.equals(task)) {
                progressBar.setProgress(msg.what);
                if (msg.what > 30 && nextButton.getVisibility() != View.VISIBLE) {
                   nextButton.setVisibility(View.GONE);
                   goButton.setVisibility(View.VISIBLE);

                }
            }
        }
    };

	public CountDownTimer cdt;

    class ProgressCounter extends CountDownTimer {
        protected int dTotal;

		public ProgressCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

		
	//--------------	
		
		
		
        public void onFinish() {
            if (internalStatus.Recroding == status) {
                try {
                	
                   
             //   Toast.makeText(MainActivity.this, "LOADING...", Toast.LENGTH_LONG).show();
                
                   
                   mRecManager.stopRecording();
                   cameraPreviewFrame.removeAllViews();
                   cameraPreviewFrame.setVisibility(View.GONE);

                   nextButton = (Button) findViewById(R.id.next_button);
                  
                   cancelButton = (Button) findViewById(R.id.cancel_button);
                               
                   goButton = (Button) findViewById(R.id.go_button);

                
                   
               //    Toast.makeText(MainActivity.this, "next gone", Toast.LENGTH_LONG).show();
                   
                
           /*        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
                   progressBar1.setProgress(total);
                   int twoMin = 2 * 15 * 100; // 6  in milli seconds

                   // CountDownTimer starts with 2 minutes and every onTick is 1 second 
                
                   
                   cdt = new CountDownTimer(twoMin, 100) { 
clearFiles
                       public void onTick(long millisUntilFinished) {

                           total = (int) ((dTotal / 30) * 100);
                           progressBar1.setProgress(total);
                           nextButton.setVisibility(View.GONE);
                    	   
                    	   progressBar1.setVisibility(View.VISIBLE);
                    	   
                           goButton.setVisibility(View.GONE);

                       }

                       public void onFinish() {
                            // DO something when 2 minutes is up
                    	   progressBar1.setVisibility(View.GONE);
                           
                    //      	Toast.makeText(MainActivity.this, "press preview to view the video.", Toast.LENGTH_LONG).show();
                             
                          	
                          //	nextButton.setVisibility(View.VISIBLE); */
                              
                            new VideoMergeTask().execute((Void) null); 
                /*      }
                       
                       
                   }.start();
                   
               */  

                   nextButton.setOnClickListener(new OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           mOrientationEventListener.disable();
                           if (internalStatus.Recroding == status) {
                               //videoView	
                        	   
                        	   setProgressBarIndeterminateVisibility(true);
                           	
                        	//   Toast.makeText(MainActivity.this, "merg preview.", Toast.LENGTH_LONG).show();
                            new VideoMergeTask().execute((Void) null);

                           } else if (internalStatus.Previewing == status) {
                              /* Log.d(TAG, "begin to crop video");
                               VideoEngine engine = new VideoEngine();
                               engine.crop(videoFilepath, Utils.getFinalVideoPath(), 480, 480, 80);
                               Log.d(TAG, "end to crop video");
                        //       Toast.makeText(MainActivity.this, "Video process successed.", Toast.LENGTH_LONG).show(); */
                           
                        	  // Intent i = new Intent(MainActivity.this, upload.class);
                            //   startActivity(i);
                               new performBackgroundTask().execute((Void) null); 
                           
                           
                           }
                       }
                   });

              
                    Log.d(TAG, "error in onfinish");   
               
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }   

        
        // TimeUnit.MILLISECONDS.sleep(10000);
        
        /*    try {
         	    Thread.sleep(1000);
         	} catch(InterruptedException ex) {
         	    Thread.currentThread().interrupt();
         	}
         	*/
       /*     final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    

                }
            }, 5000); */
        
        
        
        
        public void onTick(long millisUntilFinished) {
            tsCanRecord = millisUntilFinished;
            Message msg = new Message();
            msg.obj = TASK_UPDATE_RECORD_PROGRESS;
            msg.what = 100 - (int) millisUntilFinished / 70;
            progressHandler.sendMessage(msg);
        }
    }
    
    public class VideoMergeTask extends AsyncTask<Void, Void, Void> {
      
        private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);

    	@Override
        protected void onPreExecute() {
       // 	super.onPreExecute();
      //  loading.setVisibility(View.VISIBLE);
    		
    		 Dialog.setMessage("Please wait...");
             Dialog.show();
    		Dialog.setCanceledOnTouchOutside(false);
    
        }
        @Override
        protected Void doInBackground(Void... arg0) {
        	try{
        	
            String finalVideoFilepath = getFinalVideoFilepath();
            VideoUtils.MergeFiles(videoTempDir, finalVideoFilepath);
           
        	 }catch(Exception e){
        		 Log.d(TAG, "error in doinb");   
        	 }

        	    return null;
        	}

     

        @Override
        protected void onPostExecute(Void arg0) {
            String finalVideoFilepath = getFinalVideoFilepath();
            
            
            
            playbackVideo(finalVideoFilepath);
            setProgressBarIndeterminateVisibility(false);
            Dialog.dismiss();

            
        }
    }
    
    private class performBackgroundTask extends AsyncTask <Void, Void, Void>  
    {
             private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);

             protected void onPreExecute()
             {
                 Dialog.setMessage("Please wait...");
                 Dialog.show();
             }
             

             @Override
         protected Void doInBackground(Void... params) 
             {
            // Do your background data fetching here 
            	
           	  
                return null;   
         }
         

             protected void onPostExecute(Void unused)    
             {
                 try
                 {
                     if(Dialog.isShowing())
                     {
                //  	Toast.makeText(MainActivity.this, "press preview to view the video.", Toast.LENGTH_LONG).show();

                    	 Intent i = new Intent(MainActivity.this, upload.class);
                    	 i.putExtra("userid", user_id);

                    	 i.putExtra("sangu",(videoTempDir + "-merged.mp4" ));
                    	 i.putExtra("snapchat",snapshotFilepath);
                    	 i.putExtra("setchvalue","Add to Channel");
                    	 i.putExtra("chvalue","   ");
                    	 
                    	 i.putExtra("switchstate","yes");
                    	 
                    	 i.putExtra("switchstate2", "yes");
                    	 
                    	 i.putExtra("switchstate3", "yes");
                    	 


                    	//  Toast.makeText(MainActivity.this, snapshotFilepath, Toast.LENGTH_LONG).show();   	 
                    	 startActivity(i);
                         Dialog.dismiss();
                      //   finish();

                    	 
                     }
                             // do your Display and data setting operation here
                	
                 }
                 catch(Exception e) 
                 {
                	 
                 }
                       

    
}
    }
    
    private class cancelprocess extends AsyncTask <Void, Void, Void>  
    {
             private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);

             protected void onPreExecute()
             {
                 Dialog.setMessage("Please wait...");
                 Dialog.show();
             }
             

             @Override
         protected Void doInBackground(Void... params) 
             {
            // Do your background data fetching here 
           	  
           	  
                return null;   
         }
         

             protected void onPostExecute(Void unused)    
             {
                 try
                {
                     if(Dialog.isShowing())
                     {
                    	   clearFiles(videoTempDir);
                           clearFiles(snapshotFilepath);

                    	 Intent i = new Intent(MainActivity.this, MainActivity.class);
         				i.putExtra("userid", user_id);
                    	 startActivity(i); 
                         Dialog.dismiss();
                         
                      //   VideoUtils vu= new VideoUtils();
                      //   vu.clearFiles(videoFilepath);
                     //	Toast.makeText(MainActivity.this, videoTempDir, Toast.LENGTH_LONG).show();   	 
                     	//Toast.makeText(MainActivity.this, snapshotFilepath, Toast.LENGTH_LONG).show();   	 

                   
                      //  finish();

                     }
                             // do your Display and data setting operation here
                    
                 }
                 catch(Exception e) 
                 {
                	 
                 }
                       

   
}
    }
    
    
    public static boolean clearFiles(String speratedDirPath) {
        File videoSourceDirFile = new File(speratedDirPath);
        if (videoSourceDirFile != null
                && videoSourceDirFile.listFiles() != null) {
            File[] videoList = videoSourceDirFile.listFiles();
            for (File video : videoList) {
                video.delete();
            }
            videoSourceDirFile.delete();
        }
        return true;
    }
 
    
    
    public boolean onKeyDown(int keyCode, KeyEvent event){
	
    
    if (internalStatus.Recroding == status) {
        //videoView
        if(keyCode == KeyEvent.KEYCODE_BACK) {
	    	Intent i=new Intent(MainActivity.this,AndroidTabLayoutActivity.class);
			Bundle bndlanimation =ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.trans_right_in,R.anim.trans_right_out).toBundle();
			i.putExtra("userid", user_id);
            startActivity(i);
			startActivity(i,bndlanimation);
	            return true;
	    }
       
    } else if (internalStatus.Previewing == status) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {

        	
        	AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);


		    adb.setTitle("Discard Video");


		    adb.setIcon(android.R.drawable.ic_dialog_alert);


		    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {


                	new cancelprocess().execute((Void) null); 

		      } });


		    adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {

		           dialog.cancel();
		        	
		        	
		        	
		      } });
		    adb.show();
        
	    }
    
    }
    return false;
	}

    }	
	