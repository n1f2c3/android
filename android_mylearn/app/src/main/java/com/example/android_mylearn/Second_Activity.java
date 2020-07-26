package com.example.android_mylearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Second_Activity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        textView1=(TextView)findViewById(R.id.textView2);
        Intent i =getIntent();
        textView1.setText(i.getStringExtra("username"));
        textView2=(TextView)findViewById(R.id.textView3);
        Bundle data=i.getExtras();

        textView2.setText(data.getString("usernamesss"));
//        FragmentManager fm=getSupportFragmentManager();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//// Replace the contents of the container with the new fragment
//        ft.replace(R.id.your_placeholder, new PlusOneFragment());
//// or ft.add(R.id.your_placeholder, new FooFragment());
//// Complete the changes added above
//        ft.commit();

    }
}
