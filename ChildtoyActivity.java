package ti.childtoy; 

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ChildtoyActivity extends Activity {
	
    /** Called when the activity is first created. */
   ChildtoyActivity repeater= this;
   
   ImageView imageView;
   Handler setter;
   int show=0;
	
   private Runnable setImage = new Runnable() {
		   public void run() {
			  
			   long millisElapsed = SystemClock.uptimeMillis(); 
		       
		       
		        	
		       if (show==0){
		        	imageView.setImageResource(R.drawable.gar);
		        	show++;
		       }
		        		
		       else if (show==1){
		    	   imageView.setImageResource(R.drawable.gar1);
		    	   show++;
		       }
		       
		       else if (show==2){
		    	   imageView.setImageResource(R.drawable.gar2);
		    	   show++;
		       }
		       
		       else if (show==3){
		    	   imageView.setImageResource(R.drawable.gar3);
		    	   show++;
		       }
		       
		       else if (show==4){
		    	   imageView.setImageResource(R.drawable.gar4);
		    	   show++;
		       }

		       else if (show==5){
		    	   imageView.setImageResource(R.drawable.gar5);
		    	   show++;
		       }

		       else if (show==6){
		    	   imageView.setImageResource(R.drawable.gar6);
		    	   show++;
		       }

		       else if (show==7){
		        	imageView.setImageResource(R.drawable.gar7);
		        	show=0;
		       }
		        	
		       setter.postAtTime((Runnable) this, millisElapsed + 5000);
		       
		   }  
 };
	enum Appstate {
		Start,
		Onebutton,
		Twobuttons,
		Threebuttons,
		Wrong
	}
	Appstate state = Appstate.Start;
	
	
	
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imageView = (ImageView)findViewById(R.id.image1);
        
        setter = new Handler();
        setter.removeCallbacks(setImage);
        setter.postDelayed(setImage, 100);
        
        Button bOne = (Button) findViewById(R.id.button1);
        Button bTwo = (Button) findViewById(R.id.button2);
        Button bThree = (Button) findViewById(R.id.button3);
        Button bFour = (Button) findViewById(R.id.button4);
      
        
       
        
        bOne.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				
				if(state == Appstate.Start) {
					state = Appstate.Onebutton;
				}
				else if(state == Appstate.Wrong){
					 Toast.makeText(ChildtoyActivity.this, "Wrong Combo", Toast.LENGTH_SHORT).show();
					state = Appstate.Start;
				}
				else if(state == Appstate.Onebutton) {
					state = Appstate.Wrong;
				}
				else if(state == Appstate.Twobuttons) {
					state = Appstate.Wrong;
				}
				else if(state == Appstate.Threebuttons) {
					state = Appstate.Wrong;
				}
			
			}
        
        
        });
        bFour.setOnClickListener(new OnClickListener() {

			
			public void onClick(View arg0) {
				if(state == Appstate.Onebutton) {
					state = Appstate.Twobuttons;
				}
				else if(state == Appstate.Wrong){
					 Toast.makeText(ChildtoyActivity.this, "Wrong Combo", Toast.LENGTH_SHORT).show();
					state = Appstate.Start;
				}
				else if(state == Appstate.Start) {
					state = Appstate.Wrong;
				}
				else if(state == Appstate.Twobuttons) {
					state = Appstate.Wrong;
				}
				else if(state == Appstate.Threebuttons) {
					state = Appstate.Wrong;
				}

			}
        });
        
        bThree.setOnClickListener(new OnClickListener() {

		
			public void onClick(View arg0) {
				if(state == Appstate.Twobuttons) {				
					state = Appstate.Threebuttons;
				}
				else if(state == Appstate.Wrong){
					 Toast.makeText(ChildtoyActivity.this, "Wrong Combo", Toast.LENGTH_SHORT).show();
					state = Appstate.Start;
				}
				else if(state == Appstate.Start) {
					state = Appstate.Wrong;
				}
				else if(state == Appstate.Onebutton || 
						state == Appstate.Threebuttons){
					state = Appstate.Wrong;
				}
				
			}
        });
        
        bTwo.setOnClickListener(new OnClickListener() {

    		
			public void onClick(View arg0) {
				if(state == Appstate.Threebuttons) {
					repeater.finish();
				}
				else if(state == Appstate.Wrong){
					 Toast.makeText(ChildtoyActivity.this, "Wrong Combo", Toast.LENGTH_SHORT).show();
					state = Appstate.Start;
				}
				else if(state == Appstate.Start) {
					state = Appstate.Wrong;
				}
				else if(state == Appstate.Onebutton || 
				        state == Appstate.Twobuttons){
					state = Appstate.Wrong;
				}
				
			}
        });
        
       
        
    }


	
}
