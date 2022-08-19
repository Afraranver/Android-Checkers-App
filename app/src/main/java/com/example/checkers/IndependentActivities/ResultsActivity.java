package com.example.checkers.IndependentActivities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkers.DBObjects;
import com.example.checkers.R;
import com.example.checkers.myDbAdapter;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        myDbAdapter helper = new myDbAdapter(ResultsActivity.this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("Name", "");

        TextView txtEasyWin = findViewById(R.id.txtEasyWin);
        TextView txtEasyLose = findViewById(R.id.txtEasyLose);
        TextView txtMediumWin = findViewById(R.id.txtMediumWin);
        TextView txtMediumLose = findViewById(R.id.txtMediumLose);
        TextView txtHardWin = findViewById(R.id.txtHardWin);
        TextView txtHardLose = findViewById(R.id.txtHardLose);

        ImageView imgGoBack = findViewById(R.id.imgGoBack);
        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ArrayList<DBObjects> getAllData = helper.getData();

        for(int i = 0; i < getAllData.size(); i++){
            if(getAllData.get(i).getName().equals(userName)){
                txtEasyWin.setText(String.valueOf(getAllData.get(i).getEasyWin()));
                txtEasyLose.setText(String.valueOf(getAllData.get(i).getEasyLose()));
                txtMediumWin.setText(String.valueOf(getAllData.get(i).getMediumWin()));
                txtMediumLose.setText(String.valueOf(getAllData.get(i).getMediumLose()));
                txtHardWin.setText(String.valueOf(getAllData.get(i).getHardWin()));
                txtHardLose.setText(String.valueOf(getAllData.get(i).getHardLose()));
            }
        }
    }
}