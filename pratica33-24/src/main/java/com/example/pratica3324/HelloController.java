package com.example.pratica3324;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HelloController {
    private Squadra sq=new Squadra();
    private int i=0; //Temp

    @FXML
    private Label labelIn;
    @FXML
    private VBox insertBox;

    @FXML
    public void inserisciGioc() {
        insertBox.getChildren().clear();

        if (sq.getIndexInseriti() == sq.getSquadraLength()) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE: Impossibile inserire un nuovo giocatore, squadra al completo (22/22)");
        } else {
            CheckBox c1=new CheckBox("<--");
            Button cStraniero=new Button("E' straniero");

            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("AGGIUNTA GIOCATORE");

            //Label e textField
            Label l1=new Label("Inserire il nome giocatore:");
            TextField t1=new TextField();
            Label l2=new Label("Inserire i gol  effettuati dal giocatore:");
            TextField t2=new TextField();

            //Li metto nella schermata
            insertBox.getChildren().add(l1);
            insertBox.getChildren().add(t1);
            insertBox.getChildren().add(l2);
            insertBox.getChildren().add(t2);

            //Inserimento capitano solo se capitano non è già presente
            if (sq.getIndexInseriti() == 0 || sq.controllaCapitani() == -1) {
                Label l3=new Label("Sara' il tuo prossimo capitano? ");
                insertBox.getChildren().add(l3);
                insertBox.getChildren().add(c1);
            }
            Label lStraniero=new Label("Inserire la nazionalità del giocatore:");
            TextField tStraniero=new TextField();

            insertBox.getChildren().add(cStraniero);
            //Se straniero
            cStraniero.setOnMouseClicked( e -> {
                insertBox.getChildren().remove(cStraniero);
                insertBox.getChildren().add(lStraniero);
                insertBox.getChildren().add(tStraniero);
            });

            //Bottone finale
            Button b1=new Button("Inserisci");
            insertBox.getChildren().add(b1);
            b1.setOnMouseClicked( e -> {
                if (!insertBox.getChildren().contains(cStraniero))
                    sq.aggGioc(new GiocatoreStraniero(t1.getText(), isCapitano(c1), Integer.parseInt(t2.getText()), tStraniero.getText()));
                else
                    sq.aggGioc(new Giocatore(t1.getText(), isCapitano(c1), Integer.parseInt(t2.getText())));
                insertBox.getChildren().clear();
                labelIn.setText("INSERITO CORRETTAMENTE");
            });
        }
    }
    @FXML
    public boolean isCapitano(CheckBox c1){
        if (sq.getIndexInseriti() == 0 || sq.controllaCapitani() == -1){
            return c1.isSelected();
        }
        return false;

    }

    @FXML
    public void vediGioc() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("GIOCATORI DELLA SQUADRA:");
            Label l1=new Label(sq.toString());
            insertBox.getChildren().add(l1);
        }
    }
    @FXML
    public void modificaGioc() {
        i=0;
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
                //Ricerca giocatore
                Button cStraniero=new Button("E' straniero?");
                CheckBox c1=new CheckBox("<--");

                labelIn.setStyle("-fx-text-fill: #0a0a0a");
                labelIn.setText("MODIFICA:");

                //Label e textField
                Label l1=new Label("Inserire nome giocatore:");
                TextField t1=new TextField();
                Label l2=new Label("Inserire gol giocatore:");
                TextField t2=new TextField();

                //Li metto nella schermata
                insertBox.getChildren().add(l1);
                insertBox.getChildren().add(t1);
                insertBox.getChildren().add(l2);
                insertBox.getChildren().add(t2);

                //Inserimento valori
                if (sq.controllaCapitani() != -1) { //Quindi se c'è un capitano altrimenti è inutile chiederlo no?
                    Label l3=new Label("E' capitano?");
                    insertBox.getChildren().add(l3);
                    insertBox.getChildren().add(c1);
                }

                Label lStraniero=new Label("Inserire la nazionalità del giocatore:");
                TextField tStraniero=new TextField();

                insertBox.getChildren().add(cStraniero);
                //Se straniero
                cStraniero.setOnMouseClicked( e -> {
                    insertBox.getChildren().remove(cStraniero);
                    insertBox.getChildren().add(lStraniero);
                    insertBox.getChildren().add(tStraniero);
                });

                //Bottone finale
                Button b1=new Button("Ricerca");
                insertBox.getChildren().add(b1);
                b1.setOnMouseClicked( e -> { //All'azione
                    if (!insertBox.getChildren().contains(cStraniero))
                        i=sq.ricercaGioc(new GiocatoreStraniero(t1.getText(), isCapitano(c1), Integer.parseInt(t2.getText()),tStraniero.getText()));
                    else
                        i=sq.ricercaGioc(new Giocatore(t1.getText(), isCapitano(c1), Integer.parseInt(t2.getText())));
                    if (i==-1) {
                        insertBox.getChildren().clear();
                        labelIn.setStyle("-fx-text-fill: #FB0008");
                        labelIn.setText("GIOCATORE INESISTENTE");
                    } else {
                        CheckBox c2=new CheckBox("<--");
                        labelIn.setStyle("-fx-text-fill: #0a0a0a");
                        labelIn.setText("MODIFICA DEL GIOCATORE RICHIESTO:");

                        //Cotrollo se è un capitano o no
                        boolean controlloCapitano,isStraniero;
                        if (!insertBox.getChildren().contains(cStraniero)) {
                            controlloCapitano = sq.isCapitanoSingolo(sq.ricercaGioc(new GiocatoreStraniero(t1.getText(), isCapitano(c1), Integer.parseInt(t2.getText()), tStraniero.getText())));
                            isStraniero=true;
                        }else {
                            isStraniero = false;
                            controlloCapitano=sq.isCapitanoSingolo(sq.ricercaGioc(new Giocatore(t1.getText(),isCapitano(c1), Integer.parseInt(t2.getText()))));
                        }

                        insertBox.getChildren().clear();
                        //Label e textField
                        Label l11=new Label("Inserire il nuovo nome del giocatore:");
                        TextField t11=new TextField();
                        Label l21=new Label("Inserire il nuovo numero di gol del giocatore:");
                        TextField t21=new TextField();

                        //Li metto nella schermata
                        insertBox.getChildren().add(l11);
                        insertBox.getChildren().add(t11);
                        insertBox.getChildren().add(l21);
                        insertBox.getChildren().add(t21);

                        //Inserimento valori

                        //Se lo è
                        if (sq.controllaCapitani() == -1 || controlloCapitano) {
                            Label l3=new Label("E' capitano?");
                            insertBox.getChildren().add(l3);
                            insertBox.getChildren().add(c2);
                        }

                        Label l2Straniero=new Label("Inserire la nazionalità del giocatore:");
                        TextField t2Straniero=new TextField();
                        if (isStraniero){
                            insertBox.getChildren().addAll(l2Straniero,t2Straniero);
                        }
                        //Bottone finale
                        Button b2=new Button("Conferma");
                        insertBox.getChildren().add(b2);
                        b2.setOnMouseClicked( b -> { //All'azione
                            if (isStraniero)
                                sq.modificaGioc(new GiocatoreStraniero(t11.getText(), isCapitano(c2), Integer.parseInt(t21.getText()),t2Straniero.getText()),i);
                            else
                                sq.modificaGioc(new Giocatore(t11.getText(), isCapitano(c2), Integer.parseInt(t21.getText())),i);
                            insertBox.getChildren().clear();
                            labelIn.setText("MODIFICA DEL GIOCATORE EFFETTUATA");
                        });
                    }
                });
        }
    }
    @FXML
    public void cancellaGiocatore() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            //Ricerca giocatore
            CheckBox c1=new CheckBox("<--");
            Button cStraniero=new Button("E' straniero?");
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("CANCELLAZIONE:");

            insertBox.getChildren().clear();
            //Label e textField
            Label l1=new Label("Inserire il nome del giocatore:");
            TextField t1=new TextField();
            Label l2=new Label("Inserire i gol del giocatore:");
            TextField t2=new TextField();

            //Li metto nella schermata
            insertBox.getChildren().add(l1);
            insertBox.getChildren().add(t1);
            insertBox.getChildren().add(l2);
            insertBox.getChildren().add(t2);

            //Inserimento valori
            if (sq.controllaCapitani() != -1) {
                Label l3=new Label("E' capitano?");
                insertBox.getChildren().add(l3);
                insertBox.getChildren().add(c1);
            }
            Label lStraniero=new Label("Inserire la nazionalità del giocatore:");
            TextField tStraniero=new TextField();

            insertBox.getChildren().add(cStraniero);
            //Se straniero
            cStraniero.setOnMouseClicked( e -> {
                insertBox.getChildren().remove(cStraniero);
                insertBox.getChildren().add(lStraniero);
                insertBox.getChildren().add(tStraniero);
            });

            //Bottone finale
            Button b1=new Button("Cerca");
            insertBox.getChildren().add(b1);
            b1.setOnMouseClicked( e -> { //All'azione
                if (!insertBox.getChildren().contains(cStraniero))
                    i=sq.ricercaGioc(new GiocatoreStraniero(t1.getText(), isCapitano(c1), Integer.parseInt(t2.getText()),tStraniero.getText()));
                else
                    i=sq.ricercaGioc(new Giocatore(t1.getText(), isCapitano(c1), Integer.parseInt(t2.getText())));

                insertBox.getChildren().clear();
                if (i==-1) {
                    insertBox.getChildren().clear();
                    labelIn.setStyle("-fx-text-fill: #FB0008");
                    labelIn.setText("GIOCATORE INESISTENTE");
                } else {
                    labelIn.setStyle("-fx-text-fill: #0a0a0a");
                    labelIn.setText("Cancellazione:");
                    sq.cancellaGioc(i);
                    sq.setIndexInseriti(sq.getIndexInseriti()-1);
                    //Label e textField
                    Label l11=new Label("CANCELLATO CORRETTAMENTE");

                    //Li metto nella schermata
                    insertBox.getChildren().add(l11);
                }
            });
        }
    }
    @FXML
    public void vediGioc5Gol() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("GIOCATORI CON PIU' DI 5 GOL:");
            Label l1=new Label(sq.stampa5Gol());
            insertBox.getChildren().add(l1);
        }
    }
    @FXML
    public void vediGiocStranieri() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("GIOCATORI STRANIERI:");
            Label l1=new Label(sq.stampaStranieri());
            insertBox.getChildren().add(l1);
        }
    }
    @FXML
    public void vediCapitano() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("CONTROLLO CAPITANI:");
            Label l1=new Label();
            insertBox.getChildren().add(l1);
            i= sq.controllaCapitani();
            if (i==-1)
                l1.setText("Non ci sono capitani");
            else
                l1.setText(sq.getGiocatore(i).toString());
        }
    }
    @FXML
    public void capitanoCasuale() {
        insertBox.getChildren().clear();
        if (sq.getIndexInseriti() == 0) {
            labelIn.setStyle("-fx-text-fill: #FB0008");
            labelIn.setText("ERRORE : Nessun giocatore ancora inserito.");
        } else {
            labelIn.setStyle("-fx-text-fill: #0a0a0a");
            labelIn.setText("CAPITANO CASUALE");
            Label l1=new Label();
            insertBox.getChildren().add(l1);
            Label l2=new Label();
            insertBox.getChildren().add(l2);
            i=sq.capitaniRandom();
            if (i!=-1)
                l1.setText("Ci sono già capitani");
            else
                l1.setText("Capitano nuovo:");
            l2.setText(sq.getGiocatore(sq.controllaCapitani()).toString());
        }
    }
}
