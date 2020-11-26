

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Assignment5Activity extends Activity {

	private Handler mHandler;
	private Button playButton;
	private TextView text1;

	
	//! TODO: place these as configuration parameters (in a XML file)
    private final int PULSE_FREQ = 18000;  //18KHz
    private final int SAMPLING_FREQ = 44100; //44.1K, desirable to be greater than 18K*2=36K
    private final int PULSE_FREQ_THRESHOLD = 1000; // the threshold in "pulse freq +/- threshold"
    private final int PULSE_DURATION = 5; // 5 seconds
	
	private NoiseDetector noiseDetector;
	private NoiseMaker noiseMaker;
	
	enum Appstate {
		listening,
		playing
	}
	Appstate state = Appstate.listening;
	
	private Runnable listen = new Runnable() {
		   public void run() {
			   Appstate state = Appstate.listening;
		       long millisElapsed = SystemClock.uptimeMillis();
		       Complex [] freqs = noiseDetector.listenTone();
		       if(noiseDetector.signalExists(freqs)) {
		    	   text1.setText("Heard it"); 
		       }
		       else {
		    	   text1.setText("Did not hear it");		    	   
		       }
		       mHandler.postAtTime(this, millisElapsed + 1000); //1 seconds 
		   }
		};
		
		private Runnable play = new Runnable() {
			   public void run() {
			       long millisElapsed = SystemClock.uptimeMillis();
			     
			       noiseMaker.playTone(); //in milliseconds
			       
			       mHandler.postAtTime(this, millisElapsed + 1000); //1 seconds 
			   }
			};
			
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        noiseMaker = new NoiseMaker(PULSE_FREQ, SAMPLING_FREQ, PULSE_DURATION); 
        noiseDetector = new NoiseDetector(PULSE_FREQ, SAMPLING_FREQ, PULSE_FREQ_THRESHOLD); 
        
        playButton = (Button)findViewById(R.id.playButton);
        text1 = (TextView)findViewById(R.id.text1);
        playButton.setOnClickListener(new OnClickListener() {

		
			public void onClick(View arg0) {
					state=appstate.playing
				 	mHandler = new Handler();
			        mHandler.removeCallbacks(listen);
			        mHandler.postDelayed(listen, 100);
				
				
			}
			
        });
        
        mHandler = new Handler();
        mHandler.removeCallbacks(listen);
        mHandler.postDelayed(listen, 100);
    }
    //create program that will determine the time in between beeps, and the distance between them.
    
    /** Called when the activity finishes. */
    @Override
    public void onDestroy(){
    	super.onDestroy();
    
    }
}