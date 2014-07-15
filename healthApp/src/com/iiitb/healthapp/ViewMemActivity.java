package com.iiitb.healthapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iiitb.healthapp.adaptor.TaskListAdapter;
import com.iiitb.healthapp.handler.ServiceHandler;
import com.iiitb.healthapp.model.Member;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewMemActivity extends ListActivity {

    private Button addButton;
	private TaskListAdapter adapter;
	private Button removeButton;
	private TextView flag;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);
        HealthApplication.fid=2; //Fid needs to be set by Ashutosh's Code. It is actually the FAMILY ID.
        setUpViews();
        
	
	}
	
 

	@Override
	protected void onResume() {
		super.onResume();
		setlist();
	
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	//	Intent i=new Intent( getApplicationContext(),DisplayQuestions.class); //display questions
	//	i.putExtra("paricipantID",participantId );
	//	TaskManagerApplication.mid = (adapter.getItem(position).getMid());
	//	Toast.makeText(this, Integer.toString(adapter.getItem(position).getId()), Toast.LENGTH_LONG).show();
	//	startActivity(i);
	}

	private void setUpViews() {
		flag = (TextView)findViewById(R.id.flag);
		addButton = (Button)findViewById(R.id.add_button);
		removeButton = (Button)findViewById(R.id.remove_button);
		
		setlist();
		
		
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ViewMemActivity.this, AddMemberActivity.class);
				startActivity(intent);
				
			}
		});
		removeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ViewMemActivity.this, AddDoctorActivity.class);
				startActivity(intent);
			}

		});
	}

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_tasks, menu);
        return true;
    }
    
    private ArrayList<Member> getMembers(String json) {
    	ArrayList<Member> memberlist= new ArrayList<Member>();
    	
    	Log.e("Response: ", "> " + json);
    	 
        if (json != null) {
            try {
                JSONObject jsonObj = new JSONObject(json);
                if (jsonObj != null) {
                    JSONArray members = jsonObj
                    			.getJSONArray("members");                       
 
                    for (int i = 0; i < members.length(); i++) {
                        JSONObject catObj = (JSONObject) members.get(i);
                        Member mem = new Member(catObj.getInt("mid"),
                                catObj.getString("name"),catObj.getString("age"),catObj.getString("sex"),catObj.getString("weight"),catObj.getString("height"),catObj.getString("blood"));
                        memberlist.add(mem);
                    }
                }
 
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
        } else {
            Log.e("JSON Data", "Didn't receive any data from server!");
        }
       
        return memberlist;
		
	}
    
    private void setlist() {
    	int fid = HealthApplication.fid;
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		
		ServiceHandler hand = new ServiceHandler();
        String out = hand.makeServiceCall(HealthApplication.url.toString()+"getmem.php?f_id="+Integer.toString(fid),ServiceHandler.GET);
        String fcheck = hand.makeServiceCall(HealthApplication.url.toString()+"checkdoc.php?f_id="+Integer.toString(fid),ServiceHandler.GET);
                
        ArrayList<Member> members = getMembers(out);
        
        adapter = new TaskListAdapter(members,this);
        setListAdapter(adapter);
        
        if((fcheck.toCharArray())[8]!='A')
        {
        	int docid = getDoctor(fcheck);
        	String docname = hand.makeServiceCall(HealthApplication.url.toString()+"getdoc.php?d_id="+Integer.toString(docid),ServiceHandler.GET);
        	docname = getdocname(docname);
        	
        	flag.setText(docname+" has been assigned to your family.");
        	removeButton.setClickable(false);
        }
		
	}



	private String getdocname(String docname) {
        JSONObject jsonObj;
        String temp="";
		try {
			jsonObj = new JSONObject(docname);
			temp = (jsonObj.getString("name"));
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

            
            
   return temp;
	}



	private int getDoctor(String fcheck)  {
		
 
                JSONObject jsonObj;
                int temp=-1;
				try {
					jsonObj = new JSONObject(fcheck);
					temp = Integer.parseInt(jsonObj.getString("did"));
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 
                    
                    
           return temp;
 
 
	}
    
}
