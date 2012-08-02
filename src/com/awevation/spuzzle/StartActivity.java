package com.awevation.spuzzle;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class StartActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin);
    }

    public void begin(View view) {
	Intent myIntent = new Intent(StartActivity.this, OpenGLActivity.class);
	StartActivity.this.startActivity(myIntent);
    }
}
