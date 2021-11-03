package com.decoste.votedroid;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.decoste.votedroid.databinding.ActivityCreationQuestionBinding;
import com.decoste.votedroid.databinding.ActivityVoteBinding;

public class VoteActivity extends AppCompatActivity {
    private ActivityVoteBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
