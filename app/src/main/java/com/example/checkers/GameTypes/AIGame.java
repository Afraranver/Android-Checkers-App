package com.example.checkers.GameTypes;

import com.example.checkers.GameComponents.UIManager;

public class AIGame extends GeneralGame {

    @Override
    public void newGame() {

    }

    @Override
    public void loadGame() {

    }

    @Override
    public void checkForWinner() {

    }

    @Override
    public void postMove() {
        super.postMove();
        UIManager.blockInput();
        switchTurns();
    }
}
