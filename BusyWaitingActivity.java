package edu.vsu.cps;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;

public class BusyWaitingActivity extends Activity {
	
	Handler mHandler;
	TextView text1;
	Button button1;
	
	private Runnable mUpdateTimeTask = new Runnable() {
		   public void run() {
			   long millisElapsed = SystemClock.uptimeMillis();

	           if(button1.isPressed()) {
	        	   text1.setText("aaaaa");
	           }
	           else {
	        	   text1.setText("bbbb");
	           }		       
		     
	           //
	           // Question: What are the pros/cons of posting this with a smaller
	           // or larger time period?
		       mHandler.postAtTime(this, millisElapsed + 5000); //post in 0.1 second
		   }
		};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    	button1 = (Button) findViewById(R.id.button1);
        text1 = (TextView) findViewById(R.id.textView1);

        mHandler = new Handler();
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 10000);
    }
    
}