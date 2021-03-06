package com.iiitb.healthapp;
 
import com.iiitb.healthapp.data.UserFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends Activity {
	UserFunctions userFunctions;
	Button btnLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         * Dashboard Screen for the application
         * */        
        // Check login status in database
        userFunctions = new UserFunctions();
        
        	setContentView(R.layout.dummy);
        	btnLogout = (Button) findViewById(R.id.btnLogout);
        	
        	btnLogout.setOnClickListener(new View.OnClickListener() {
    			
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				userFunctions.logoutUser(getApplicationContext());
    				Intent mainAct = new Intent(getApplicationContext(), MainActivity.class);
    				mainAct.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	        	startActivity(mainAct);
    	        	// Closing dashboard screen
    	        	finish();
    			}
    		});
        	
         
        
        
        
    }
}