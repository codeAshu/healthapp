
package com.iiitb.healthapp;

import org.json.JSONException;
import org.json.JSONObject;

import com.iiitb.healthapp.data.DatabaseHandler;
import com.iiitb.healthapp.data.DocFunctions;
import com.iiitb.healthapp.data.UserFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterDocActivity extends Activity {
	Button btnRegister;
	TextView loginScreen;
	EditText inputFullName;
	EditText inputEmail;
	EditText inputPassword;
	EditText type;
	TextView registerErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	
	private static final String KEY_TYPE = "type";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_doc);

		// Importing all assets like buttons, text fields
		inputFullName = (EditText) findViewById(R.id.registerName_doc);
		inputEmail = (EditText) findViewById(R.id.registerEmail_doc);
		inputPassword = (EditText) findViewById(R.id.registerPassword_doc);
		type = (EditText) findViewById(R.id.type_doc);
		btnRegister = (Button) findViewById(R.id.btnRegister_doc);
		loginScreen = (TextView) findViewById(R.id.link_to_login_doc);
		
		registerErrorMsg = (TextView) findViewById(R.id.register_error_doc);

		// Register Button Click event
		btnRegister.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View view) {
				String name = inputFullName.getText().toString();
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				String speciality = type.getText().toString();
				
				DocFunctions docFunction = new DocFunctions();
				JSONObject json = docFunction.registerUser(name, email, password,speciality);

				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						registerErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS); 

						if(Integer.parseInt(res) == 1){
							// user successfully registred
						
							// Store user details in SQLite Database
							DatabaseHandler db = new DatabaseHandler(getApplicationContext());
							JSONObject json_user = json.getJSONObject("doc");

							// Clear all previous data in database
							docFunction.logoutUser(getApplicationContext());
							db.addDoc(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL),json_user.getString(KEY_TYPE), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
							
							
							// Launch Dashboard Screen
							Intent dashboard = new Intent(getApplicationContext(), DashboardActivity.class);
							// Close all views before launching Dashboard
							dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(dashboard);
							
						
							
							// Close Registration Screen
							finish();
						}else{
							res = json.getString(KEY_ERROR);
							if(Integer.parseInt(res) == 2)
								registerErrorMsg.setText("This Email is already registered!");
							else
								registerErrorMsg.setText("Error occured in registration");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		// Link to Login Screen
		loginScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						LoginDocActivity.class);
				startActivity(i);
				// Close Registration View
				finish();
			}
		});
	}
}
