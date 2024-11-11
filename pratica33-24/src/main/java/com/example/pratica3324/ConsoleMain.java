package com.example.pratica3324;

import java.util.Scanner;

public class ConsoleMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Squadra squadra=new Squadra();
        int scelta;

        do {
            System.out.println("Scegli una fra le sceguenti opzioni: " +
                    "\n 0) aggiungere un giocatore alla squadra;" +
                    "\n 1) visualizzare tutti i giocatori della squadra;" +
                    "\n 2) modificare i dati di un giocatore a scelta;" +
                    "\n 3) cancellare un giocatore dalla squadra;" +
                    "\n 4) visualizzare i giocatori che hanno realizzato più di 5 goal;" +
                    "\n 5) visualizzare il nome del capitano;" +
                    "\n 6) assegnare il ruolo di capitano in modo casuale se non ancora presente;" +
                    "\n 7) visualizzare giocatori stranieri;" +
                    "\n 8) esci");
            scelta = Integer.parseInt(in.next());
            switch (scelta) {
                case 0:
                    if (squadra.getIndexInseriti() == squadra.getSquadraLength())
                        System.out.println("Impossibile squadra al completo");
                    else {
                        int gol;
                        String nome, nazionalita;
                        boolean isCapitano = false;

                        //Inserimento valori
                        System.out.println("Stai inserendo un nuovo giocatore: \n Inserisci il suo nome:");
                        nome = in.next();
                        do {
                            System.out.println("Inserire il numero di goal: ");
                            gol = Integer.parseInt(in.next());
                            if (gol < 0)
                                System.out.println("Inserire numero corretto di gol");
                        } while (gol < 0);
                        if (squadra.getIndexInseriti() == 0 || squadra.controllaCapitani() == -1) {
                            System.out.println("E' capitano? true/false");
                            isCapitano = Boolean.parseBoolean(in.next());
                        }
                        System.out.println("E' straniero? true/false");
                        if (Boolean.parseBoolean(in.next())){
                            System.out.println("Inserire la nazionalità");
                            nazionalita = in.next();
                            squadra.aggGioc(new GiocatoreStraniero(nome,isCapitano,gol,nazionalita));
                        } else
                            squadra.aggGioc(new Giocatore(nome,isCapitano,gol));
                    }
                    break;
                case 1:
                    if(squadra.getIndexInseriti()==0)
                        System.out.println("Nessun componente ancora inserito");
                    else{
                        System.out.println(squadra.toString());
                    }
                    break;
                case 2:
                    if(squadra.getIndexInseriti()==0)
                        System.out.println("Nessun componente ancora inserito");
                    else{
                        int gol, i;
                        String nome, nazionalita;
                        boolean isCapitano;

                        System.out.println("Stai ricercando un giocatore x la modifica: \n Inserisci il suo nome:");
                        nome= in.next();
                        do {
                            System.out.println("Inserire il numero di goal: ");
                            gol=Integer.parseInt(in.next());
                            if (gol<0)
                                System.out.println("Inserire numero corretto di gol");
                        }while (gol<0);
                        System.out.println("E' capitano? true/false");
                        isCapitano=Boolean.parseBoolean(in.next());
                        System.out.println("E' straniero? true/false");
                        if (Boolean.parseBoolean(in.next())){
                            System.out.println("Inserire la nazionalità");
                            nazionalita = in.next();
                            i=squadra.ricercaGioc(new GiocatoreStraniero(nome,isCapitano,gol,nazionalita));
                        } else
                            i=squadra.ricercaGioc(new Giocatore(nome,isCapitano,gol));

                        //Ricerca effetuata
                        if (i==-1)
                            System.out.println("Giocatore inesistente");
                        else{
                            int gol1;
                            String nome1, nazionalita1;
                            boolean isCapitano1=false;

                            System.out.println("Inserisci il suo nuovo nome:");
                            nome1= in.next();
                            do {
                                System.out.println("Inserire il suo nuovo numero di goal: ");
                                gol1=Integer.parseInt(in.next());
                                if (gol1<0)
                                    System.out.println("Inserire il suo nuovo numero corretto di gol");
                            }while (gol1<0);
                            if (squadra.controllaCapitani()==-1) {
                                System.out.println("E' capitano? true/false");
                                isCapitano1 = Boolean.parseBoolean(in.next());
                            }
                            System.out.println("E' straniero? true/false");
                            if (Boolean.parseBoolean(in.next())){
                                System.out.println("Inserire la nazionalità");
                                nazionalita1 = in.next();
                                squadra.modificaGioc(new GiocatoreStraniero(nome1,isCapitano1,gol1,nazionalita1),i);
                            } else
                                squadra.modificaGioc(new Giocatore(nome1,isCapitano1,gol1),i);
                        }
                    }
                    break;
                case 3:
                    if(squadra.controllaCapitani()==0)
                        System.out.println("Nessun componente ancora inserito");
                    else{
                        int gol, i;
                        String nome, nazionalita;
                        boolean isCapitano;

                        System.out.println("Stai cancellando un giocatore: \n Inserisci il suo nome:");
                        nome= in.next();
                        do {
                            System.out.println("Inserire il numero di goal: ");
                            gol=Integer.parseInt(in.next());
                            if (gol<0)
                                System.out.println("Inserire numero corretto di gol");
                        }while (gol<0);
                        System.out.println("E' capitano? true/false");
                        isCapitano = Boolean.parseBoolean(in.next());
                        System.out.println("E' straniero? true/false");
                        if (Boolean.parseBoolean(in.next())){
                            System.out.println("Inserire la nazionalità");
                            nazionalita = in.next();
                            i= squadra.ricercaGioc(new GiocatoreStraniero(nome,isCapitano,gol,nazionalita));
                        } else
                            i= squadra.ricercaGioc(new Giocatore(nome,isCapitano,gol));
                        if (i==-1)
                            System.out.println("Giocatore inesistente");
                        else{
                            squadra.cancellaGioc(i);
                        }
                    }
                    break;
                case 4:
                    if(squadra.getIndexInseriti()==0)
                        System.out.println("Nessun componente ancora inserito");
                    else{
                        System.out.println(squadra.stampa5Gol());
                    }
                    break;
                case 5:
                    if(squadra.getIndexInseriti()==0)
                        System.out.println("Nessun componente ancora inserito");
                    else{
                        int i=squadra.controllaCapitani();
                        if (i==-1)
                            System.out.println("Non ci sono capitani");
                        else
                            System.out.println(squadra.getGiocatore(i));
                    }
                    break;
                case 6:
                    if(squadra.getIndexInseriti()==0)
                        System.out.println("Nessun componente ancora inserito");
                    else{
                        int i=squadra.capitaniRandom();
                        if (i!=-1){
                            System.out.println("C'è gia un capitano");
                        }
                        System.out.println("Capitano:");
                        System.out.println(squadra.getGiocatore(squadra.controllaCapitani()));
                    }
                    break;
                case 7:
                    if(squadra.getIndexInseriti()==0)
                        System.out.println("Nessun componente ancora inserito");
                    else{
                        System.out.println(squadra.stampaStranieri());
                    }
                    break;
                case 8:
                    System.out.println("Ciao");
                    break;
                default:
                    System.out.println("Selezionare solo un'opzione tra quelle proposte.");
            }
        } while (scelta != 8);
    }
}