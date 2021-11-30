package com.decoste.votedroid.bd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.decoste.votedroid.modele.VDQuestion;
import com.decoste.votedroid.modele.VDVote;

@Database(entities = {VDQuestion.class, VDVote.class}, version = 2)
public abstract class BD extends RoomDatabase {
    public abstract MonDao monDao();
}
