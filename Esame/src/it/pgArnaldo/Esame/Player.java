package it.pgArnaldo.Esame;

public class Player {
    public static final int INITIAL_LIFE = 100;

    private String name;
    private int life;
    private int numOfMakedChoices;
    private Node currentPos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player() {
        this.life = INITIAL_LIFE;
        this.numOfMakedChoices = 0;
    }

    public Player(String name) {
        this();
        this.name = name;
    }



    public int getNumOfMakedChoices() {
        return numOfMakedChoices;
    }

    public void setNumOfMakedChoices(int numOfMakedChoices) {
        this.numOfMakedChoices = numOfMakedChoices;
    }

    public Node getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(Node currentPos) {
        this.currentPos = currentPos;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isAlive(){
        if(this.life >0 ) return true;
        return false;
    }
}
