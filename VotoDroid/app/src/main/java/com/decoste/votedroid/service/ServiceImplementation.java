package com.decoste.votedroid.service;

import com.decoste.votedroid.Question;
import com.decoste.votedroid.bd.BD;
import com.decoste.votedroid.exceptions.MauvaisVote;
import com.decoste.votedroid.exceptions.MauvaiseQuestion;
import com.decoste.votedroid.modele.VDQuestion;
import com.decoste.votedroid.modele.VDVote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceImplementation{

    private static ServiceImplementation single_instance = null;
    private BD maBD;

    public ServiceImplementation (BD maBD){
        this.maBD = maBD;
    }

    public static ServiceImplementation getInstance(BD maBD)
    {
        if (single_instance == null)
            single_instance = new ServiceImplementation(maBD);

        return single_instance;
    }


    public void creerQuestion(VDQuestion vdQuestion) throws MauvaiseQuestion {
        // Validation
        if (vdQuestion.texteQuestion == null || vdQuestion.texteQuestion.trim().length() == 0) throw new MauvaiseQuestion("Question vide");
        if (vdQuestion.texteQuestion.trim().length() < 5) throw new MauvaiseQuestion("Question trop courte");
        if (vdQuestion.texteQuestion.trim().length() > 255) throw new MauvaiseQuestion("Question trop longue");
        if (vdQuestion.idQuestion != null) throw new MauvaiseQuestion("Id non nul. La BD doit le gérer");

        // Doublon du texte de la question
        for (VDQuestion q : toutesLesQuestions()){
            if (q.texteQuestion.toUpperCase().equals(vdQuestion.texteQuestion.toUpperCase())){
                    throw new MauvaiseQuestion("Question existante");
            }
        }

        // Ajout
        vdQuestion.idQuestion = maBD.monDao().insertQuestion(vdQuestion);
    }

    
    public void creerVote(VDVote vdVote) throws MauvaisVote {
        List<VDVote> votes = maBD.monDao().getAllVotes();

        if (vdVote.nomVoteur == null || vdVote.nomVoteur.trim().length() == 0) throw new MauvaisVote("Nom voteur vide");
        if (votes.contains(vdVote.nomVoteur) && votes.contains(vdVote.idQuestion)) throw new MauvaisVote("Nom de voteur existe déjà");

        vdVote.idVote = maBD.monDao().insertVote(vdVote);
    }

    
    public List<VDQuestion> toutesLesQuestions() {
        ArrayList<VDQuestion> questions = (ArrayList<VDQuestion>) maBD.monDao().getAll();

        return questions;
    }

    
    public float moyenneVotes() {
        return 0;
    }

    
    public float ecartTypeVotes() {
        return 0;
    }

    
    public Map<Integer, Integer> distributionVotes() {
        return null;
    }
}
