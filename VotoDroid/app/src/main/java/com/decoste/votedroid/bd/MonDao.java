package com.decoste.votedroid.bd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.decoste.votedroid.Question;
import com.decoste.votedroid.modele.VDQuestion;
import com.decoste.votedroid.modele.VDVote;

import java.util.List;

@Dao
public interface MonDao {
    @Insert
    Long insertQuestion(VDQuestion v);

    //TODO Compléter les autres actions
    @Insert
    Long insertVote(VDVote v);

    @Query("SELECT * FROM VDQuestion")
    List<VDQuestion> getAll();

    @Query("SELECT * FROM VDVote")
    List<VDVote> getAllVotes();

}
