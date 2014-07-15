package com.iiitb.healthapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.*;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.iiitb.healthapp.data.JSONParser;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class NewVisitActivity extends Activity  {

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	EditText Symptoms;
	EditText Medication;
	EditText Doses;
	EditText Comments;
	int   day  ;
	int   month;
	int   year ;
	EditText formDate;
	EditText toDate;
	String familyId;
	String memId;
	TextView mDateTextView;

	// url to create new product
	private static String url_create_product = "http://10.0.2.2/healthapp/enter_visit.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_visit);

		//	Bundle bundle = getIntent().getExtras();
		//    familyId = bundle.getString("familyId");
		//    memId = bundle.getString("memId");


		// Edit Text
		Symptoms = (EditText) findViewById(R.id.symptoms);
		Medication = (EditText) findViewById(R.id.medicin);
		Doses = (EditText) findViewById(R.id.doses);
		Comments = (EditText) findViewById(R.id.comments);
		formDate = (EditText) findViewById(R.id.from_date);
		toDate = (EditText) findViewById(R.id.to_date);
	
		
		// Create button
		Button btnCreateProduct = (Button) findViewById(R.id.enter);

		// button click event
		btnCreateProduct.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				new CreateNewProduct().execute();
			}
		});
	}

	
	
	
	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewVisitActivity.this);
			pDialog.setMessage("Entering Details..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			String syms = Symptoms.getText().toString();
			String meds = Medication.getText().toString();
			String doses = Doses.getText().toString();
			String comments = Comments.getText().toString();
			String fromd = formDate.getText().toString();;
			String tod = toDate.getText().toString();;

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("famId", Integer.toString(HealthApplication.fid)));
			params.add(new BasicNameValuePair("memId", Integer.toString(HealthApplication.mid)));
			params.add(new BasicNameValuePair("symptoms", syms));
			params.add(new BasicNameValuePair("medicins", meds));
			params.add(new BasicNameValuePair("doses", doses));
			params.add(new BasicNameValuePair("comments", comments));
			params.add(new BasicNameValuePair("fromDate", fromd));
			params.add(new BasicNameValuePair("toDate", tod));

			System.out.println(params.toString());

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);

			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Log.d("Create Response", json.toString());

					Intent i = new Intent(getApplicationContext(), DocViewFamilyActivity.class);
					startActivity(i);

					// closing this screen
					finish();
				} else {
					// failed to create product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}

}
