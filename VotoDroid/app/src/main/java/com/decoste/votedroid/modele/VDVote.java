package com.decoste.votedroid.modele;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static com.google.gson.internal.$Gson$Types.arrayOf;
@Entity(foreignKeys = {@ForeignKey(entity = VDQuestion.class,
        parentColumns = "idQuestion",
        childColumns = "idQuestion",
        onDelete = ForeignKey.CASCADE)
})
//@Entity
public class VDVote{
        //TODO Champs à définir
        @PrimaryKey(autoGenerate = true)
        public long idVote;

        public String nomVoteur;

        public long idQuestion;

        public long note;
        }


