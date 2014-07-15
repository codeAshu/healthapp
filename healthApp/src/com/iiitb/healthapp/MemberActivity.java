package com.iiitb.healthapp;

import android.app.Activity;

public class MemberActivity extends Activity {

	protected HealthApplication getTaskManagerApplication() {
		HealthApplication tma = (HealthApplication)getApplication();
		return tma;
	}

}
