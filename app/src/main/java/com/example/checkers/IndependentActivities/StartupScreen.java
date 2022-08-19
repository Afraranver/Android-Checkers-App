package com.example.checkers.IndependentActivities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.checkers.GameComponents.GameManager;
import com.example.checkers.R;
import com.example.checkers.myDbAdapter;

public class StartupScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_screen);
        GameManager.setApplicationContext(getApplicationContext());

        ImageView imgGotoNext = findViewById(R.id.imgGotoNext);
        final EditText edtUserName = findViewById(R.id.edtUserName);

        final myDbAdapter helper = new myDbAdapter(StartupScreen.this);
        imgGotoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if user's name length is greater than 0, if yes proceed to next page and save
                // the name in xml file in the app data folder, else show Toast
                if (edtUserName.getText().toString().length() > 0){
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(StartupScreen.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Name",edtUserName.getText().toString());
                    editor.apply();

                    boolean checkifUserExist = helper.Exists(edtUserName.getText().toString());
                    if(!checkifUserExist){
                        helper.insertDataSave(edtUserName.getText().toString(), 0, 0, 0, 0, 0, 0);
                    }

                    startActivity(new Intent(StartupScreen.this, SelectMode.class));
                    finish();

                }else{
                    Toast.makeText(StartupScreen.this, "Please Enter your name", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(StartupScreen.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit Game")
                .setMessage("Are you sure you want to Exit this game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}