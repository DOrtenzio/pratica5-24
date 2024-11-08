package com.example.pratica3324;

public class GiocatoreStraniero extends Giocatore{
    private String nazionalita;

    //Costruttore
    public GiocatoreStraniero(String nome,boolean capitano, int goal,String nazionalita){
        super(nome, capitano, goal);
        this.nazionalita=nazionalita;
    }

    //get e set
    public String getNazionalita() { return nazionalita; }
    public void setNazionalita(String nazionalita) { this.nazionalita = nazionalita; }

    //To string
    public String toString() {
        return "GIOCATORE:\t Nome "+getNome()+" Gol: "+getNome()+" E' Capitano? "+isCapitano()+" Nazionalità: "+getNazionalita();
    }

    //Equals
    public boolean equals(Object obj) {
        if (obj instanceof GiocatoreStraniero){
            GiocatoreStraniero r=(GiocatoreStraniero) obj;
            return ( r.equals(obj) && r.getNazionalita().equals(this.nazionalita));
        }
        else return false;
    }
}
