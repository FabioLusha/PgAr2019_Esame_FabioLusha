package it.pgArnaldo.Esame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * La classe rappresenta un nodo il quale rappresenta una scelta.
 * il nodo contiene la sua descrizione e la scelta che lo può portare ad esso. Infatti determino le scelte di un nodo
 * in base alle scelte contentue nei nodi a qui il nodo in questione è collegato.
 */
public class Node {
    private static int choice = 0;
    private static int effect = 0;

    public static final int MAX_CONNECTIONS_PER_NODE = 4;
    public static final double NODES = 37;

    private static int idGenertor = 0;
    private static int nodes = 1;
    //importante l'uso di instanceof per detreminare capire il tipo di un nodo
    private int iD;
    private String description;
    private List<Node> adjacents;
    private Choice option;

    /*
    una casella si identifica vuota solo se ha un solo nodo adiacente;
    una casella si idenifica finale solo se non ha nessun nodo adiacente.
     */


    public Node(String description){
        this.iD = idGenertor;
        this.description = description;
        this.adjacents = new LinkedList<>();
        this.option = new Choice("null");
        idGenertor++;
    }

    public Node(String description, Choice option){
        this(description);
        this.option = option;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfAdjacents(){
        return adjacents.size();
    }

    public List<Node> getAdjacents() {
        return adjacents;
    }

    public void setAdjacents(List<Node> adjacents) {
        this.adjacents = adjacents;
    }

    public Choice getOption() {
        return option;
    }

    public void setOption(Choice option) {
        this.option = option;
    }

    public void addNewAdjNode(String description){
        this.adjacents.add(new Node(description));
    }

    public String showOptions(){
        StringBuilder str = new StringBuilder();
        for( Node tmp : adjacents){
            str.append(tmp.iD + ")" + " " + tmp.getOption().toString() + "\n");
        }

        return str.toString();
    }


/*
    public int countSingleNodes(){


            for(Node tmp : this.getAdjacents()) {
                if(tmp.getNumberOfAdjacents() == 0)
                    counter++;
                tmp.countSingleNodes();
            }
        return counter;
    }

    public int countSingleConnection(){
      //  int counter = 0;


            for(Node tmp : this.getAdjacents()) {
                if(tmp.getNumberOfAdjacents() == 1)
                    counterE++;
                tmp.countSingleConnection();
            }
        return counterE;
    }

    public int countMoreThanONeConnection(){


            for(Node tmp : this.getAdjacents()) {
                if (tmp.getNumberOfAdjacents() > 1)
                    counterC++;
                tmp.countMoreThanONeConnection();
            }
        return counterC;
    }

    public int countMore(){



            for(Node tmp : this.getAdjacents()) {
                counterN++;
                tmp.countMore();

            }
        return counterN;
    }
*/
    public void defineEffects(){

        for(Node tmp: this.adjacents) {

            if (choice >= effect) {
                tmp.setOption(new ChoiceWithEffect(RandomString.getAlphaNumericString(10)));
                effect++;
            } else {
                tmp.setOption((new Choice(RandomString.getAlphaNumericString(10))));
                choice++;
            }
        }

    }


    public void generateOtherChoices(){
        Random rnd = new Random(System.currentTimeMillis());

        //se si tratta del nodo start voglio almeno 2 scelte
        if(this.getiD() == 0) {
            for (int i = 0; i < rnd.nextInt(MAX_CONNECTIONS_PER_NODE -2 ) + 2; i++) {
                this.addNewAdjNode(RandomString.getAlphaNumericString(10));
            }
        }
        else{
            for (int i = 0; i < rnd.nextInt(MAX_CONNECTIONS_PER_NODE); i++) {
                this.addNewAdjNode(RandomString.getAlphaNumericString(10));
            }
        }
        if(this.getNumberOfAdjacents() > 1)
            this.defineEffects();
    }

    public boolean isFinal(){
        if(this.getNumberOfAdjacents() < 1) return true;
        return false;
    }

    public boolean isLonely(){
        if(this.getNumberOfAdjacents() == 1) return true;
        return false;
    }
}
