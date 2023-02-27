package com.company;

import java.io.FileWriter;
import java.util.Random;

//we will use the observer design pattern in order to inform each fighter if they are stunned

//Observer -> the change of an object determines a new state for another one which has to be notified

class Fighter2 implements Runnable{
    private boolean stuned = false;
    private boolean dodge = false;
    public Pokemon f1;
    public Fighter1 oponent;
    public FileWriter myWriter;
    public boolean stun(){
        return stuned;
    }

    public Fighter2(Pokemon f1) {
        this.f1 = f1;
    }

    public void beingStunned(boolean s){
        this.stuned = true;
    }
    public void ataseazaOponent(Fighter1 a){
        this.oponent = a;
    }
    int cooldown2 = 0;
    int cooldown1 = 0;
    int finished = 0;
    int attackValue = 0;
    int finished1 = 0;
    boolean isDone = false;
    Random rand = new Random();
    int contor = 0;
    int attackType = 0;
    boolean stunOponent = false;
    public boolean isStuned() {
        return stuned;
    }

    public void setStuned(boolean stuned) {
        this.stuned = stuned;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public void actualizeazaStunned(boolean stuned) {
        this.stuned = stuned;
    }

    @Override
    public void run() {
        while(this.f1.getHP() > 0 && this.oponent.f1.getHP() > 0) {
            finished = 0;
            finished1 = 0;
            this.setDodge(false);
            attackType = -1;
            attackValue = 0;
            stunOponent = false;
            if (cooldown1 > 0) cooldown1--;
            if (cooldown2 > 0) cooldown2--;
            int rand1 = rand.nextInt(3);
            switch (rand1) {
                case 0:

                    if (f1.getAttack() != 0) {
                        attackValue = this.f1.getAttack() - oponent.f1.getDefense();
                        attackType = 1;
                    } else {
                        attackValue = this.f1.getSpecialAttack() - oponent.f1.getSpecialDefense();
                        attackType = 2;
                    }

                    break;
                case 1:
                    if (this.cooldown1 == 0) {
                        attackType = 3;
                        cooldown1 = f1.getAbility1().getCooldown();
                        if (f1.getAbility1().isDodge() == true) this.setDodge(true);
                        attackValue = f1.getAbility1().getDamage();
                        if (f1.getAbility1().isStun())
                            stunOponent = true;
                    } else {
                        if (cooldown1 > 0) cooldown1++;
                        if (cooldown2 > 0) cooldown2++;
                        continue;
                    }
                    break;
                case 2:
                    if (this.cooldown2 == 0) {
                        attackType = 4;
                        cooldown2 = f1.getAbility2().getCooldown();
                        if (f1.getAbility2().isDodge() == true) this.setDodge(true);
                        attackValue = f1.getAbility2().getDamage();
                        if (f1.getAbility2().isStun())
                            stunOponent = true;
                    } else {
                        if (cooldown1 > 0) cooldown1++;
                        if (cooldown2 > 0) cooldown2++;
                        continue;
                    }
                    break;
                default:
                    break;
            }
            isDone = true;
            if (attackType == -1) attackType = 0;
            finished1 = 1;
            if (this.stuned == true) {
                this.actualizeazaStunned(false);
                attackType = 0;
                attackValue = 0;
            }

            if (oponent.isDodge() == false) {
                oponent.f1.setHP(oponent.f1.getHP() - attackValue);
            }
            if(this.myWriter == null) {
                switch (attackType) {
                    case 0:
                        System.out.print(this.f1.getName() + " nimic /  ");
                        break;
                    case 1:
                        System.out.print(this.f1.getName() + " atac normal / ");
                        break;
                    case 2:
                        System.out.print(this.f1.getName() + " atac special / ");
                        break;
                    case 3:
                        System.out.print(this.f1.getName() + " abilitate 1 / ");
                        break;
                    case 4:
                        System.out.print(this.f1.getName() + " abilitate 2 / ");
                        break;
                    default:
                        break;
                }
                switch (oponent.attackType) {
                    case 0:
                        System.out.println(oponent.f1.getName() + " nimic ->Rezultat: ");
                        break;
                    case 1:
                        System.out.println(oponent.f1.getName() + " atac normal ->Rezultat: ");
                        break;
                    case 2:
                        System.out.println(oponent.f1.getName() + " atac special ->Rezultat:");
                        break;
                    case 3:
                        System.out.println(oponent.f1.getName() + " abilitate 1 ->Rezultat:");
                        break;
                    case 4:
                        System.out.println(oponent.f1.getName() + " abilitate 2 ->Rezultat:");
                        break;
                    default:
                        break;
                }
                System.out.print("\ta. " + this.f1.getName() + " HP " + this.f1.getHP());
                if (cooldown1 != 0) System.out.print(", abilitate 1 cooldown " + cooldown1);
                if (cooldown2 != 0) System.out.print(", abilitate 2 cooldown " + cooldown2);
                System.out.println();
                System.out.print("\tb. " + this.oponent.f1.getName() + " HP " + this.oponent.f1.getHP());
                if (oponent.cooldown1 != 0) System.out.print(", abilitate 1 cooldown " + oponent.cooldown1);
                if (oponent.cooldown2 != 0) System.out.print(", abilitate 2 cooldown " + oponent.cooldown2);
                System.out.println();
            }

            finished = 1;
            try {
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stunOponent == true  && oponent.isDodge() == false) {
                this.oponent.actualizeazaStunned(true);
                finished = 1;
            }

        }

    }
}