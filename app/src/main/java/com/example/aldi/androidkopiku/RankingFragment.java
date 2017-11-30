package com.example.aldi.androidkopiku;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aldi.androidkopiku.Common.Common;
import com.example.aldi.androidkopiku.Interface.ItemClickListener;
import com.example.aldi.androidkopiku.Interface.RankingCallBack;
import com.example.aldi.androidkopiku.ViewHolder.RankingViewHolder;
import com.example.aldi.androidkopiku.model.QuestionScore;
import com.example.aldi.androidkopiku.model.Ranking;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.jar.Attributes;


public class RankingFragment extends Fragment {
    View myFragment;
    RecyclerView rankingList;
    LinearLayoutManager layoutManager;
    FirebaseRecyclerAdapter<Ranking,RankingViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference questionScore,rankingTbl;
    int sum=0;

    public static RankingFragment newInstance(){
        RankingFragment rankingFragment =new RankingFragment();
        return rankingFragment;


    }
//press ctrl o

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        questionScore = database.getReference("Question_Score");
        rankingTbl = database.getReference("Ranking");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment=inflater.inflate(R.layout.fragment_ranking,container,false);
        //init View
        rankingList = (RecyclerView)myFragment.findViewById(R.id.rankinglist);
        layoutManager = new LinearLayoutManager(getActivity());
        rankingList.setHasFixedSize(true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rankingList.setLayoutManager(layoutManager);



        updateScore(Common.CurrentUser.getName(), new RankingCallBack<Ranking>() {
            @Override
            public void callBack(Ranking ranking) {
                rankingTbl.child(ranking.getName())
                        .setValue(ranking);
                //showRanking();
            }
        });

        //set adapter
        adapter = new FirebaseRecyclerAdapter<Ranking, RankingViewHolder>(
                Ranking.class,
                R.layout.layout_ranking,
                RankingViewHolder.class,
                rankingTbl.orderByChild("score")
        ) {
            @Override
            protected void populateViewHolder(RankingViewHolder viewHolder, Ranking model, int position) {
                viewHolder.textName.setText(model.getName());
                viewHolder.textScore.setText(String.valueOf(model.getScore()));

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        rankingList.setAdapter(adapter);
        return myFragment;
    }



    private void updateScore(final String name, final RankingCallBack<Ranking> callback) {
        questionScore.orderByChild("user").equalTo(name)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data:dataSnapshot.getChildren())
                        {
                            QuestionScore quest = data.getValue(QuestionScore.class);
                            sum+=Integer.parseInt(quest.getScore());
                        }
                        Ranking ranking = new Ranking(name,sum);
                        callback.callBack(ranking);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
