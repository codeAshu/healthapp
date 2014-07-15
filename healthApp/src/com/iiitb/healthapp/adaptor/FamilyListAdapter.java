package com.iiitb.healthapp.adaptor;

 import java.util.ArrayList;

import com.iiitb.healthapp.R;
import com.iiitb.healthapp.model.Family;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FamilyListAdapter extends BaseAdapter {

	private ArrayList<Family> fam;
	private Context context;
	
	public FamilyListAdapter(ArrayList<Family> tasks, Context context) {
		super();
		this.fam = tasks;
		this.context = context;
	}

	@Override
	public int getCount() {
		return fam.size();
	}

	@Override
	public Family getItem(int position) {
		return (null == fam)? null : fam.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SurveyListItem tli;
		if(null == convertView){
			tli = (SurveyListItem)View.inflate(context, R.layout.task_list_item, null);
		}
		else{
			tli = (SurveyListItem)convertView;
		}
		tli.setTask(fam.get(position));
		return tli;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

	public void toggleTaskCompleteAtPosition(int position) {
		Family t = fam.get(position);
		t.toggleComplete();
		notifyDataSetChanged();
		
	}

	public Long[] removeCompletedTasks() {
		ArrayList<Family> completedTasks = new ArrayList<Family>();
		ArrayList<String> ids = new ArrayList<String>();
		for(Family task : fam){
			if(task.isComplete()){
				completedTasks.add(task);
				ids.add(task.getId());
			}
		}
		fam.removeAll(completedTasks);
		notifyDataSetChanged();
		
		return ids.toArray(new Long[]{});
		
	}


}
