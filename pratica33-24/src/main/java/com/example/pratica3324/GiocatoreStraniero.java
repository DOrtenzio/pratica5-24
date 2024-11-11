package com.example.pratica3324;

public class GiocatoreStraniero extends Giocatore{
    private String nazionalita;

    //Costruttore
    public GiocatoreStraniero(String nome,boolean capitano, int goal,String nazionalita){
        super(nome, capitano, goal);
        this.nazionalita=nazionalita;
    }
    public GiocatoreStraniero(String nome,boolean capitano, int goal){
        super(nome, capitano, goal);
        this.nazionalita="Non inserita";
    }
    public GiocatoreStraniero(){
        super(null, false, 0);
        this.nazionalita=null;
    }

    //get e set
    public String getNazionalita() { return nazionalita; }
    public void setNazionalita(String nazionalita) { this.nazionalita = nazionalita; }

    @Override
    public String toString() {
        return "GIOCATORE:\t Nome "+getNome()+"\t Gol: "+getGoal()+"\t E' Capitano? "+isCapitano()+"\t Nazionalità: "+getNazionalita();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GiocatoreStraniero){
            GiocatoreStraniero r=(GiocatoreStraniero) obj;
            return ( r.equals(obj) && r.getNazionalita().equals(this.nazionalita));
        }
        else return false;
    }
}
