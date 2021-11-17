package com.decoste.votedroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.decoste.votedroid.bd.BD;
import com.decoste.votedroid.databinding.ActivityCreationQuestionBinding;
import com.decoste.votedroid.exceptions.MauvaiseQuestion;
import com.decoste.votedroid.modele.VDQuestion;
import com.decoste.votedroid.service.ServiceImplementation;

public class CreationQuestionActivity extends AppCompatActivity {
    private ServiceImplementation service;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.decoste.votedroid.databinding.ActivityCreationQuestionBinding binding = ActivityCreationQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        BD maBD = Room.databaseBuilder(getApplicationContext(), BD.class, "BDQuestion")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        service = ServiceImplementation.getInstance(maBD);

        AutoCompleteTextView source = findViewById(R.id.textQuestion);

        binding.btnPoserQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creerQuestion(source.getText().toString());
                Intent intent = new Intent(CreationQuestionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void creerQuestion(String _texteQuestion) {
        try {
            VDQuestion maQuestion = new VDQuestion();
            maQuestion.texteQuestion = _texteQuestion;
            service.creerQuestion(maQuestion);
        } catch (MauvaiseQuestion m) {
            Log.e("CREERQUESTION", "Impossible de créer la question : " + m.getMessage());
        }
    }
}
