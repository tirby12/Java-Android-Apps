package str.pack;



import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StandWalkRunActivity extends Activity {
    /** Called when the activity is first created. */
	
	private SensorManager sensorManager;
	private Handler mHandler;
	private TextView tView, tView2, tView3;
	
	private AccelerometerReader reader;
	private float ax, ay, az, Vmag, Mu;
	private float[] Amag = new float[10];
	int i=0;

	private Runnable mUpdateTimeTask = new Runnable() {
		   public void run() {
		       long millisElapsed = SystemClock.uptimeMillis();

		       ax = reader.getAx(); 
		       ay = reader.getAy();
		       az = reader.getAz();
		       Amag[i%10] = (float) Math.sqrt((ax*ax)+(ay*ay)+(az*az))-9;
		       
		       if(i>=9){
		    	   for (int j=0; j<10; j++)
			       {
			    	   Mu+= Amag[j];
			       }
			       Mu= (Mu/10);
			       for (int k=0; k<10; k++)
			       {
			    	   Vmag=(Amag[k]-Mu)*(Amag[k]-Mu);
			       }    
		       }
		       
		       Vmag=(Vmag/10);
		       if(Vmag>10)
		       {
		    	   tView3.setText("Running"); 
		       }
		       else if(Vmag>3&&Vmag<10)
		       {
		    	   tView3.setText("walking");
		       }
		       else
		    	   tView3.setText("Standing");
		       
		       i++;
		       tView.setText(String.valueOf(Amag[i%10]));
		       tView2.setText(String.valueOf(Vmag));
		       mHandler.postAtTime(this, millisElapsed + 200);
		   }
		};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tView = (TextView) findViewById(R.id.textView1);
        tView2 = (TextView) findViewById(R.id.textView2);
        tView3 = (TextView) findViewById(R.id.textView3);
        Button b = (Button) findViewById(R.id.button1);
        
        reader = new AccelerometerReader();     
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(reader, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        
        mHandler = new Handler();
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 100);
        
        b.setOnClickListener(new OnClickListener() {

    		
			public void onClick(View arg0) {
				
				StandWalkRunActivity.this.finish();
				
				
			}
        });
    }
}