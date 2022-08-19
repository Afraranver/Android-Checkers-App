package com.example.checkers.IndependentActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.checkers.GameComponents.BoardDisplay;
import com.example.checkers.GameComponents.GameManager;
import com.example.checkers.GameTypes.PassAndPlayGame;
import com.example.checkers.Othermode.GameActivity;
import com.example.checkers.R;

public class SelectOptionsActivity extends AppCompatActivity {
    private CheckBox checkBoxHajiTerbang, checkBoxForceJump;
    private ImageView imgEasy, imgMedium, imgHard;
    private Button btnGo;
    private String gameMode, gameDifficulty = "";
    private TextView txtDifficultTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_options);

        checkBoxHajiTerbang = findViewById(R.id.checkBoxHajiTerbang);
        checkBoxForceJump = findViewById(R.id.checkBoxForceJump);
        imgEasy = findViewById(R.id.imgEasy);
        imgMedium = findViewById(R.id.imgMedium);
        imgHard = findViewById(R.id.imgHard);
        btnGo = findViewById(R.id.btnGo);
        txtDifficultTitle = findViewById(R.id.txtDifficultTitle);

        // Get game mode from intent
        Intent i = getIntent();
        gameMode = i.getStringExtra("mode");

        gameDifficulty = "Easy";

        if (gameMode.equals("Multi-Player")){
            txtDifficultTitle.setVisibility(View.INVISIBLE);
            imgEasy.setVisibility(View.INVISIBLE);
            imgMedium.setVisibility(View.INVISIBLE);
            imgHard.setVisibility(View.INVISIBLE);
        }

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for single player battle with AI
                if (gameMode.equals("One-Player")) {
                    Intent i = new Intent(SelectOptionsActivity.this, OnlineGameSelectScreen.class);
                    i.putExtra("mode", "One-Player");
                    i.putExtra("Difficulty", gameDifficulty);
                    startActivity(i);
                    //for multiplayer
                }else{
//                    Intent intent = new Intent(SelectOptionsActivity.this, BoardDisplay.class);
//                    startActivity(intent);
//                    GameManager.setUpGame(new PassAndPlayGame());

                    startActivity(new Intent(SelectOptionsActivity.this, GameActivity.class));
                }


            }
        });

        imgEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgEasy.setImageResource(R.drawable.happy);
                imgMedium.setImageResource(R.drawable.meh);
                imgHard.setImageResource(R.drawable.frown);
                gameDifficulty = "Easy";
            }
        });

        imgMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgEasy.setImageResource(R.drawable.smile_face);
                imgMedium.setImageResource(R.drawable.confused);
                imgHard.setImageResource(R.drawable.frown);
                gameDifficulty = "Medium";
            }
        });

        imgHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgEasy.setImageResource(R.drawable.smile_face);
                imgMedium.setImageResource(R.drawable.meh);
                imgHard.setImageResource(R.drawable.sad);
                gameDifficulty = "Hard";
            }
        });


    }
}