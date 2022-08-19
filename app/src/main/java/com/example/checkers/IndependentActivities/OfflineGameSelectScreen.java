package com.example.checkers.IndependentActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.checkers.GameComponents.BoardDisplay;
import com.example.checkers.GameComponents.GameManager;
import com.example.checkers.GameComponents.IODataManager;
import com.example.checkers.GameTypes.AIGame;
import com.example.checkers.GameTypes.GeneralGame;
import com.example.checkers.GameTypes.PassAndPlayGame;
import com.example.checkers.R;

import java.io.File;

public class OfflineGameSelectScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_game_select_screen);

        if(saveExists()){
            Button button = findViewById(R.id.button_resume);
            button.setEnabled(true);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();


    }

    @Override
    protected void onPause(){
        super.onPause();

    }

    public boolean saveExists(){
        File file = getApplicationContext().getFileStreamPath("offline_game.dat");
        return file.exists();
    }

    public void startPassAndPlayGame(View view){
        Intent intent = new Intent(this, BoardDisplay.class);
        startActivity(intent);
        GameManager.setUpGame(new PassAndPlayGame());
    }

    public void resumeGame(View view){
        Intent intent = new Intent(this, BoardDisplay.class);
        startActivity(intent);

        GeneralGame savedGame = IODataManager.open("offline_game.dat", getApplicationContext());
        if(savedGame != null) GameManager.loadGame(savedGame);
        else return;
    }

    public void startAIGame(View view){
        Intent intent = new Intent(this, BoardDisplay.class);
        startActivity(intent);
        GameManager.setUpGame(new AIGame());
    }
}
