package com.example.pratica3324;

public class Squadra {
    private Giocatore[] squadra;
    private int indexInseriti;

    //Costruttore
    public Squadra(){
        this.squadra = new Giocatore[22];
        this.indexInseriti = 0;
    }

    //Get e set
    public Giocatore[] getSquadra() { return this.squadra; }
    public void setSquadra(Giocatore[] squadra) { this.squadra = squadra; }
    public int getIndexInseriti() { return this.indexInseriti; }
    public void setIndexInseriti(int indexInseriti) { this.indexInseriti = indexInseriti; }
    public int getSquadraLength() { return this.squadra.length; }
    public boolean isCapitanoSingolo(int indice) { return this.squadra[indice].isCapitano(); }

    //Metodi
    public String toString(int indice) {
        return this.squadra[indice].toString();
    }

    public int aggGioc(String nome, int gol, boolean capitano) {
        this.squadra[indexInseriti] = new Giocatore(nome, capitano, gol);
        return this.indexInseriti + 1;
    }
    public int aggGiocStraniero(String nome, int gol, boolean capitano, String nazionalita) {
        this.squadra[indexInseriti] = new GiocatoreStraniero(nome, capitano, gol,nazionalita);
        return this.indexInseriti + 1;
    }

    public String stampa(){
        String s="Squadra: \n";
        for (int i=0;i<this.indexInseriti;i++){
            s=s+this.squadra[i].toString()+"\n";
        }
        s=s+"\n----------";
        return s;
    }

    public int ricercaGioc(Object obj){
        if (obj instanceof GiocatoreStraniero) {
            GiocatoreStraniero r = (GiocatoreStraniero) obj;
            for (int i=0;i<this.indexInseriti;i++){
                if (this.squadra[i] instanceof GiocatoreStraniero){
                    if ( this.squadra[i].getNome().equalsIgnoreCase(r.getNome()) && this.squadra[i].getGoal()==r.getGoal() && this.squadra[i].isCapitano()==r.isCapitano() && r.getNazionalita().equalsIgnoreCase(((GiocatoreStraniero) this.squadra[i]).getNazionalita()) )
                        return i;
                }
            }
            return -1;
        }else if(obj instanceof Giocatore) {
            Giocatore r = (Giocatore) obj;
            for (int i=0;i<this.indexInseriti;i++){
                if ( this.squadra[i].getNome().equalsIgnoreCase(r.getNome()) && this.squadra[i].getGoal()==r.getGoal() && this.squadra[i].isCapitano()==r.isCapitano() )
                    return i;
            }
            return -1;
        }
        return -1;
    }

    public void modificaGioc(Object obj,int indice){
        if (obj instanceof GiocatoreStraniero) {
            GiocatoreStraniero r = (GiocatoreStraniero) obj;
            GiocatoreStraniero giocatoreStraniero = (GiocatoreStraniero) (this.squadra[indice]);

            giocatoreStraniero.setNome(r.getNome());
            giocatoreStraniero.setGoal(r.getGoal());
            giocatoreStraniero.setCapitano(r.isCapitano());
            giocatoreStraniero.setNazionalita(r.getNazionalita());
        }else {
            Giocatore r = (Giocatore) obj;
            Giocatore giocatore= this.squadra[indice];

            giocatore.setNome(r.getNome());
            giocatore.setGoal(r.getGoal());
            giocatore.setCapitano(r.isCapitano());

        }
    }

    public void cancellaGioc(int indice){
        for (int l=indice;l<this.indexInseriti-1;l++){
            this.squadra[l]=this.squadra[l+1];
        }
    }

    public String stampa5Gol(){
        String s="Giocatori con più di 5 gol: \n";
        for (int i=0;i<this.indexInseriti;i++){
            if (this.squadra[i].getGoal()>=5)
                s=s+this.squadra[i].toString()+"\n";
        }
        return s;
    }

    public String stampaStranieri(){
        String s="Giocatori stranieri: \n";
        for (int i=0;i<this.indexInseriti;i++){
            if (this.squadra[i] instanceof GiocatoreStraniero)
                s=s+this.squadra[i].toString()+"\n";
        }
        return s;
    }

    public int controllaCapitani() {
        for (int i = 0; i < this.indexInseriti; i++) {
            if (this.squadra[i].isCapitano())
                return i;
        }
        return -1;
    }

    public int capitaniRandom() {
        int i=controllaCapitani();
        if (i==-1) {
            i=(int) (Math.random() * this.indexInseriti);
            this.squadra[i].setCapitano(true);
            return -1; //Inserito
        }
        else
            return i; //Non inserito e do posizione attuale
    }
}
