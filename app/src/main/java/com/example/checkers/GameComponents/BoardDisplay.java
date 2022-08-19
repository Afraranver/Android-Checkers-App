package com.example.checkers.GameComponents;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.checkers.R;

public class BoardDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_display);

        GameManager.setDisplay(this);

        //Start game after the whole activity has been set up
        findViewById(R.id.parent_window).post(new Runnable() {
            @Override
            public void run() {
                fitBoardOnScreen();

                if(GameManager.isSavedGame()) GameManager.startSavedGame();
                else GameManager.startNewGame();
            }
        });

        Button btnExit = findViewById(R.id.btnExit);
        ImageView imgBack = findViewById(R.id.imgBack);
        ImageView imgRestart = findViewById(R.id.imgRestart);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(BoardDisplay.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Exit Game")
                        .setMessage("Are you sure you want to Exit this game?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        imgRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(BoardDisplay.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Restart Game")
                        .setMessage("Are you sure you want to Restart this game?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GameManager.restartGame();
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //TODO need to do the back action for both online and offline
    }

    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    protected void onPause(){
        super.onPause();
        try{
            if(isFinishing() && !GameManager.isDeletedGame()) GameManager.saveGame();
            GameManager.setIsDeletedGame(false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    public void onClick(View view){
        BoardSquare square = (BoardSquare) view;
        square.toggle();
        GameManager.passInput(square);
    }

    private void fitBoardOnScreen(){

        ConstraintLayout parentWindow = findViewById(R.id.parent_window);
        int screenWidth = parentWindow.getWidth();
        int buttonWidth = screenWidth/8;

        for(int rowIndex = 0; rowIndex <= 7; rowIndex++){
            for(int columnIndex = 0; columnIndex <= 7; columnIndex++){
                BoardSquare square = BoardSquareManager.getSquareAt(rowIndex, columnIndex);
                square.adjustSize(buttonWidth);
            }
        }
    }
}