package it.pgArnaldo.Esame;

import java.util.*;

public class gameMain {

    public static final String START = "Qua inizia la tua avventura";

    public static void main(String[] args){
        Node start = new Node(START);
        start.generateOtherChoices();
        int playersChoice;

        List<Player> players = new ArrayList<>();

        int numPlayers = InputDati.leggiIntero("Quanti giocatori ci sono (min 2 , max 4): ", 2, 4);

        for(int i = 0; i < numPlayers; i++){
            players.add(new Player(InputDati.leggiStringa("Giocatore " + (i+1) + ", inserisci il tuo nome!")));
            players.get(i).setCurrentPos(start);
        }

        while (continueGameing(players)){
            for(int i = 0; i < players.size(); i++){
                Player currentPlayer = players.get(i);
                Node currentNode = currentPlayer.getCurrentPos();

                if(currentNode.isLonely()){
                    currentNode = currentNode.getAdjacents().get(0);
                    currentNode.generateOtherChoices();
                }
                //controllo dovuto neccessariamente per le 4 line di codice sopra; visto che si generano nuove scelte
                //oppure non se ne genera nessuna
                if(currentNode.isFinal())
                    break;

                System.out.println(currentNode.showOptions());
                playersChoice = InputDati.leggiIntero("Giocatore " + (i+1) +" fai la tua scelta (inserisci l'indice): ");

                //cerco il nodo scelto dal giocatore
                for(Node tmp : currentNode.getAdjacents()){

                    if(tmp.getiD() == playersChoice) {
                        currentNode = tmp;
                        currentPlayer.setCurrentPos(currentNode);
                    }
                }
                currentNode.generateOtherChoices();
                //se si è raggiunto un nodo finale interrompo il ciclo for e passo alla condizione del ciclo while
                //il quale deve interrompersi
                if(currentNode.isFinal())
                    break;

                //se la scelta fatta ha un effetto lo eseguo
                if(currentNode.getOption() instanceof ChoiceWithEffect) {
                    ChoiceWithEffect tmp = (ChoiceWithEffect) currentNode.getOption();
                    tmp.executeEffect(currentPlayer);
                }

                currentNode.getOption().msg();
            }
        }

        if(areAllDead(players))
            System.out.println("GAME OVER");
        else {
                for(int i = 0; i < players.size(); i++){
                    if(players.get(i).getCurrentPos().isFinal())
                        System.out.println(players.get(i).getName() + "Hai vinto");
                }
        }


    }

    public static void giocata(Player player){

    }

    public static boolean continueGameing(List<Player> players){
        boolean condition = false;


        for(int i = 0; i < players.size(); i++){
            //se un giocatore è arrivato alla fine il gioco finisce
            if(players.get(i).getCurrentPos().isFinal())
                return false;

            //finchè c'è ne uno vivo
            condition = (condition || players.get(i).isAlive());

        }

        return condition;

    }

    public static boolean areAllDead(List<Player> players){
        boolean condition = false;


        for(int i = 0; i < players.size(); i++){

            //finchè c'è ne uno vivo
            condition = (condition || players.get(i).isAlive());

        }

        return !condition;
    }

}