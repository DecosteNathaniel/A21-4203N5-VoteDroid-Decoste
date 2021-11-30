package com.decoste.votedroid;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.decoste.votedroid.modele.VDQuestion;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {
    public List<VDQuestion> list;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {


        // each data item is just a string in this case
        public TextView tvQuestion;
        public TextView tvButton;

        public MyViewHolder(LinearLayout v) {
            super(v);
            tvQuestion = v.findViewById(R.id.tvQuestion);
            tvButton = v.findViewById(R.id.tvButton);

        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public QuestionAdapter() {
        list = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public QuestionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        VDQuestion questionCourante = list.get(position);

        holder.tvQuestion.setText(questionCourante.texteQuestion); // TODO setText sur un integer crash

        holder.tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ResultatActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long idQuestion = questionCourante.idQuestion;
                Intent intent = new Intent(v.getContext(), VoteActivity.class);
                intent.putExtra("idQ", idQuestion);
                v.getContext().startActivity(intent);
            }
        });
    }

    // renvoie la taille de la liste
    @Override
    public int getItemCount() {
        return list.size();
    }
}
