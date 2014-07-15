package com.iiitb.healthapp.views;

import com.iiitb.healthapp.R;
import com.iiitb.healthapp.model.Member;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;


public class TaskListItem extends LinearLayout {
	
	private Member member;
	private TextView name;
	private TextView age;
	private TextView sex;
	
	public TaskListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		name = (TextView)findViewById(R.id.name);
		age = (TextView)findViewById(R.id.age);
		sex = (TextView)findViewById(R.id.sex);
	}



	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
		this.name.setText(member.getName());
		String temp = member.getAge()+" Years "+member.getHeight()+" cm "+member.getWeight()+" Kgs "+member.getBlood();
		this.age.setText(temp);
		
		this.sex.setText(member.getSex());
	}

}
