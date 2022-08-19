package com.example.checkers.Othermode;

import android.app.DialogFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import com.example.checkers.Othermode.roomdb.Game;
import com.example.checkers.Othermode.roomdb.GameListAdapter;
import com.example.checkers.Othermode.roomdb.GameViewModel;
import com.example.checkers.R;

import java.util.List;


public class LoadGameActivity extends AppCompatActivity
        implements DeleteLoadSavedGameDialogFragment.NoticeDialogListener, View.OnClickListener
{
    public static final String EXTRA_LOAD_GAME = "ru.bstu.checkers.EXTRA_LOAD_GAME";
    public static final String EXTRA_TURN = "ru.bstu.checkers.EXTRA_TURN";
    public static final String EXTRA_WAYS = "ru.bstu.checkers.EXTRA_WAYS";
    private GameViewModel mGameViewModel;
    public String mSelectedGameName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadgame);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final GameListAdapter adapter = new GameListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mGameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        mGameViewModel.getAllGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable final List<Game> games) {
                // Update the cached copy of the words in the adapter.
                adapter.setGames(games);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }
    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onSavedGameDialogDeleteClick(DialogFragment dialog) {
        mGameViewModel.delete(mSelectedGameName);
    }

    @Override
    public void onSavedGameDialogLoadClick(DialogFragment dialog) {
        mGameViewModel.load(mSelectedGameName);
        // В этом месте можно запустить анимацию загрузки
    }

    @Override
    public void onClick(View v) {

    }
}
