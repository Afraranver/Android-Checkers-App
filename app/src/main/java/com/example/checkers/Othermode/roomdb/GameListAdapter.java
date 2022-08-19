package com.example.checkers.Othermode.roomdb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.checkers.Othermode.DeleteLoadSavedGameDialogFragment;
import com.example.checkers.Othermode.LoadGameActivity;
import com.example.checkers.Othermode.MyApplication;
import com.example.checkers.R;

import java.util.List;




public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {
    class GameViewHolder extends RecyclerView.ViewHolder {
        private final TextView gameItemView;

        private GameViewHolder(View itemView) {
            super(itemView);
            gameItemView = itemView.findViewById(R.id.tv_loadgame_item);
        }
    }

    private final LayoutInflater mInflater;
    private List<Game> mGames; // Cached copy of games

    public GameListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.loadgame_item, parent, false);
        TextView tv =  itemView.findViewById(R.id.tv_loadgame_item);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)v;
                LoadGameActivity lga = (LoadGameActivity)(MyApplication.getCurrentActivity());
                lga.mSelectedGameName = tv.getText().toString();
                DeleteLoadSavedGameDialogFragment dialog = new DeleteLoadSavedGameDialogFragment();
                dialog.show(MyApplication.getCurrentActivity().getFragmentManager(), MyApplication.getCurrentActivity().getResources().getString(R.string.DLSGDialog));
            }
        });
        return new GameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        if (mGames != null) {
            Game current = mGames.get(position);
            holder.gameItemView.setText(current.mName);
        } else {
            // Covers the case of data not being ready yet.
            holder.gameItemView.setText("No Game");
        }
    }

    public void setGames(List<Game> games){
        mGames = games;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mGames has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mGames != null)
            return mGames.size();
        else return 0;
    }
}
