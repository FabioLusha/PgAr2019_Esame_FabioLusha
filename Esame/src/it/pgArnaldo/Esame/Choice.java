package it.pgArnaldo.Esame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Choice {
    public static final int STRING_LENGTH = 10;
    public static final String MSG = "La tua scelta non ha avuto conseguenze su di te";

    private String option;

    public Choice(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String toString(){
        return option;
    }

    public String msg(){
        return MSG;
    }

    public List<String>  generateListOfOptions(){

        List<String> optionsList = new ArrayList<>();
        Random rnd = new Random(System.currentTimeMillis());
        int size = rnd.nextInt(Node.MAX_CONNECTIONS_PER_NODE) + 1;

        for(int i = 0; i < size; i++){
            optionsList.add(RandomString.getAlphaNumericString(STRING_LENGTH));
        }

        return optionsList;
    }
}
