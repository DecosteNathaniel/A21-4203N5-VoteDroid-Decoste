package com.decoste.votedroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.decoste.votedroid.databinding.ActivityCreationQuestionBinding;

public class CreationQuestionActivity extends AppCompatActivity {
    private ActivityCreationQuestionBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreationQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
