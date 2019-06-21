package it.pgArnaldo.Esame;

import java.util.Random;

public class ChoiceWithEffect extends  Choice {
    public static final String NEGATIVE = "Questa scelta vi è costata cara, la vostra vita cala di: ";
    public static final String POSITIVE = "Aveto scelto saggiamente, la vostra vita aumenta di: ";


    private int effect;

    public ChoiceWithEffect(String option) {
        super(option);
        this.effect = generateEffect();
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    private int generateEffect(){

        Random rndGenerator =  new Random(System.currentTimeMillis());
        // il più perchè randmo mi genera un num casuale da 0 a INITIAL_LIFE-1, ma lo 0 come effetto non è ammesso
        int genEffect = rndGenerator.nextInt(Player.INITIAL_LIFE) + 1;

        if(rndGenerator.nextInt() % 2 == 0)
            return  genEffect;
        return -genEffect;
    }

    @Override
    public String msg(){

        if(effect > 0)
            return POSITIVE + effect;
        return  NEGATIVE + effect;
    }

    public void executeEffect(Player player){
        //faccio il casting di option a ChoiceWithEffect
        player.setLife(player.getLife() + this.effect);
    }
}
