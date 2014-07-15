package com.iiitb.healthapp.adaptor;

import java.util.ArrayList;
import com.iiitb.healthapp.R;
import com.iiitb.healthapp.model.Member;
import com.iiitb.healthapp.views.TaskListItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TaskListAdapter extends BaseAdapter {

	private ArrayList<Member> Members;
	private Context context;
	
	public TaskListAdapter(ArrayList<Member> Members, Context context) {
		super();
		this.Members = Members;
		this.context = context;
	}

	@Override
	public int getCount() {
		return Members.size();
	}

	@Override
	public Member getItem(int position) {
		return (null == Members)? null : Members.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TaskListItem tli;
		if(null == convertView){
			tli = (TaskListItem)View.inflate(context, R.layout.task_list_item, null);
		}
		else{
			tli = (TaskListItem)convertView;
		}
		tli.setMember(Members.get(position));
		return tli;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

	/*public void toggleTaskCompleteAtPosition(int position) {
		Member t = Members.get(position);
		notifyDataSetChanged();
		
	}*/

	/*public Long[] removeCompletedTasks() {
		ArrayList<Task> completedTasks = new ArrayList<Task>();
		ArrayList<Long> ids = new ArrayList<Long>();
		for(Task task : tasks){
			if(task.isComplete()){
				completedTasks.add(task);
				ids.add(task.getId());
			}
		}
		tasks.removeAll(completedTasks);
		notifyDataSetChanged();
		
		return ids.toArray(new Long[]{});
		
	}*/


}
