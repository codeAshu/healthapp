package com.iiitb.healthapp.model;

public class Task {

	private String name;
	private boolean complete;
	private long id;


	public Task(String taskName){
		name = taskName;
	}
	
	public void setId(long id) {
		this.id = id;
		
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public void toggleComplete() {
		complete = !complete;
		
	}
	
}
