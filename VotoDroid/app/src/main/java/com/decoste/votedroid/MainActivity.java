package com.decoste.votedroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.decoste.votedroid.bd.BD;
import com.decoste.votedroid.databinding.ActivityMainBinding;
import com.decoste.votedroid.modele.VDQuestion;
import com.decoste.votedroid.service.ServiceImplementation;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ServiceImplementation service;
    private BD maBD;

    QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreationQuestionActivity.class);
                startActivity(intent);
            }
        });

        BD maBD = Room.databaseBuilder(getApplicationContext(), BD.class, "BDQuestion")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        service = ServiceImplementation.getInstance(maBD);

        this.initRecycler();
        this.remplirRecycle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.SupprimerQuestions:
                Toast.makeText(getApplicationContext(), "Supprimer toutes les quetions selectionné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.SupprimerVotes:
                Toast.makeText(getApplicationContext(), "Supprimer tous les votes selectionné", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new QuestionAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void remplirRecycle(){
        List<VDQuestion> questions = service.toutesLesQuestions();
        for (int i = 0; i < questions.size(); i++){
            VDQuestion q = questions.get(i);
            q.texteQuestion = questions.get(i).texteQuestion;
            q.idQuestion = questions.get(i).idQuestion;
            adapter.list.add(q);
        }
        adapter.notifyDataSetChanged();
    }
}