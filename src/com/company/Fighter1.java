package com.company;

import java.util.Random;

//we will use the observer design pattern in order to inform each fighter if they are stunned

//Observer -> the change of an object determines a new state for another one which has to be notified

public class Fighter1 implements Runnable{

    private boolean stuned = false;
    private boolean dodge = false;
    public Pokemon f1;
    public Fighter2 oponent;
    public boolean stun(){
        return stuned;
    }
    public void beingStunned(boolean s){
        this.stuned = true;
    }
    public void ataseazaOponent(Fighter2 a){
        this.oponent = a;
    }

    public Fighter1(Pokemon f1) {
        this.f1 = f1;
    }

    int cooldown2 = 0;
    int cooldown1 = 0;
    int attackType;
    int contor = 0;
    int attackValue = 0;
    int finished;
    boolean stunOponent = false;
    Random rand = new Random();

    public boolean isStuned() {
        return stuned;
    }

    public void actualizeazaStunned(boolean stuned) {
        this.stuned = stuned;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }


    @Override
    //the implementation of the fight using threads
    public void run() {
        while(this.f1.getHP() > 0  && this.oponent.f1.getHP() > 0){
            contor++;
            finished = 0;
            attackType = -1;
            this.setDodge(false);
            stunOponent = false;
            attackValue = 0;
            if(cooldown1 > 0) cooldown1--;
            if(cooldown2 > 0) cooldown2--;
            int rand1 = rand.nextInt(3);
            switch (rand1) {
                case 0:

                    if(f1.getAttack() != 0){
                        attackValue = this.f1.getAttack() - oponent.f1.getDefense();
                        attackType = 1;
                    }
                    else {
                        attackValue = this.f1.getSpecialAttack() - oponent.f1.getSpecialDefense();
                        attackType = 2;
                    }

                    break;
                case 1:
                    if(this.cooldown1 == 0){
                        attackType = 3;
                        cooldown1 = f1.getAbility1().getCooldown();
                        if(f1.getAbility1().isDodge() == true) this.setDodge(true);
                        attackValue = f1.getAbility1().getDamage();
                        if(f1.getAbility1().isStun())
                            stunOponent = true;
                    }
                    else{
                        if(cooldown1 > 0) cooldown1++;
                        if(cooldown2 > 0) cooldown2++;
                        continue;
                    }
                    break;
                case 2:
                    if(this.cooldown2 == 0){
                        attackType = 4;
                        cooldown2 = f1.getAbility2().getCooldown();
                        if(f1.getAbility2().isDodge() == true) this.setDodge(true);
                        attackValue = f1.getAbility2().getDamage();
                        if(f1.getAbility2().isStun())
                            stunOponent = true;
                    }
                    else{
                        if(cooldown1 > 0) cooldown1++;
                        if(cooldown2 > 0) cooldown2++;
                        continue;
                    }
                    break;
                default:
                    break;
            }
            if(attackType == -1) attackType = 0;
            if(this.stuned == true){
                this.actualizeazaStunned(false);
                attackType = 0;
                attackValue = 0;
            }
            if(oponent.isDodge() == false) {
                oponent.f1.setHP(oponent.f1.getHP() - attackValue);
            }
            try {
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(stunOponent == true && oponent.isDodge() == false){
                this.oponent.actualizeazaStunned(true);
            }

        }
    }
}