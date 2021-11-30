package com.decoste.votedroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.decoste.votedroid.bd.BD;
import com.decoste.votedroid.databinding.ActivityCreationQuestionBinding;
import com.decoste.votedroid.databinding.ActivityVoteBinding;
import com.decoste.votedroid.exceptions.MauvaisVote;
import com.decoste.votedroid.exceptions.MauvaiseQuestion;
import com.decoste.votedroid.modele.VDQuestion;
import com.decoste.votedroid.modele.VDVote;
import com.decoste.votedroid.service.ServiceImplementation;

public class VoteActivity extends AppCompatActivity {
    private ActivityVoteBinding binding;
    private ServiceImplementation service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        BD maBD = Room.databaseBuilder(getApplicationContext(), BD.class, "BDQuestion")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        service = ServiceImplementation.getInstance(maBD);

        AutoCompleteTextView sourceNomVoteur = findViewById(R.id.textVoteur);
        RatingBar sourceNote = findViewById(R.id.simpleRatingBar);

        binding.btnVoter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creerVote(sourceNomVoteur.getText().toString(), (int) sourceNote.getRating());
                Intent intent = new Intent(VoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void creerVote(String _nomVoteur, int _numStars) {
        long extras = getIntent().getLongExtra("idQ",0L);
        long value = extras;
        try {
            VDVote monVote = new VDVote();
            monVote.nomVoteur = _nomVoteur;
            monVote.note = _numStars;
            monVote.idQuestion = value;
            service.creerVote(monVote);
        } catch (MauvaisVote m) {
            Log.e("CREERVOTE", "Impossible de créer le vote : " + m.getMessage());
        }
    }
}
