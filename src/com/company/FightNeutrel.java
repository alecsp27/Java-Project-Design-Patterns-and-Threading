package com.company;

//implementation of fights with neutrel pokemons

import java.io.IOException;
import java.util.Random;

class FightNeutrel{
    public FightNeutrel(Pokemon a, Pokemon b) throws IOException {
        Random rand = new Random();
        Pokemon a1 = a.copyPokemon(a);
        Pokemon b1 = b.copyPokemon(b);
        int cooldown1 = 0;
        int cooldown2 = 0;
        boolean stuned = false;
        //pokemon b will be neutrel
        while(a1.getHP() > 0 && b1.getHP() > 0) {
            if(cooldown1 > 0) cooldown1--;
            if(cooldown2 > 0) cooldown2--;
            int rand1 = rand.nextInt(3);
            switch (rand1) {
                case 0:
                    if (a1.getAttack() != 0) {
                        b1.setHP(b1.getHP() + b1.getDefense() - a1.getAttack());
                    }
                    else {
                        b1.setHP(b1.getHP() + b1.getSpecialDefense() - a1.getSpecialAttack());
                    }
                    if(stuned == false)
                        a1.setHP(a1.getHP() + a1.getDefense() - b1.getAttack());
                    if(a1.getAttack() != 0)
                        System.out.println(a1.getName() + " atac normal  / " + b1.getName() + " atac normal -> Rezultat:");
                    else
                        System.out.println(a1.getName() + " atac special  / " + b1.getName() + " atac normal -> Rezultat:");
                    System.out.print("\ta. " + a1.getName() + " HP " + a1.getHP());
                    if (cooldown1 != 0) System.out.print(", abilitate 1 cooldown " + cooldown1);
                    if (cooldown2 != 0) System.out.print(", abilitate 2 cooldown " + cooldown2);
                    System.out.println();
                    System.out.println("\tb. " + b1.getName() + " HP " + b1.getHP());

                    break;
                case 1:
                    if(cooldown1 == 0) {
                        cooldown1 = a1.getAbility1().getCooldown();
                        if (a1.getAbility1().isDodge()) {
                            b1.setHP(b1.getHP() - a1.getAbility1().getDamage());
                            if (stuned == true) stuned = false;
                        } else {
                            b1.setHP(b1.getHP() - a1.getAbility1().getDamage());
                            if (stuned == true) stuned = false;
                            else
                                a1.setHP(a1.getHP() + a1.getDefense() - b1.getAttack());
                        }
                        if (a1.getAbility1().isStun() == true) stuned = true;
                        System.out.println(a1.getName() + " abilitatea 1 / " + b1.getName() + " atac normal -> Rezultat:");
                        System.out.print("\ta. " + a1.getName() + " HP " + a1.getHP());
                        if (cooldown1 != 0) System.out.print(", abilitate 1 cooldown " + cooldown1);
                        if (cooldown2 != 0) System.out.print(", abilitate 2 cooldown " + cooldown2);
                        System.out.println();
                        System.out.println("\tb. " + b1.getName() + " HP " + b1.getHP());

                    }
                    else{
                        if(cooldown1 > 0) cooldown1++;
                        if(cooldown2 > 0) cooldown2++;
                        continue;
                    }

                    break;
                case 2:
                    if(cooldown2 == 0) {
                        cooldown2 = a1.getAbility2().getCooldown();
                        if (a1.getAbility2().isDodge()) {
                            b1.setHP(b1.getHP() - a1.getAbility2().getDamage());
                            if (stuned == true) stuned = false;
                        } else {
                            b1.setHP(b1.getHP() - a1.getAbility1().getDamage());
                            if (stuned == true) stuned = false;
                            else
                                a1.setHP(a1.getHP() + a1.getDefense() - b1.getAttack());
                        }
                        if (a1.getAbility2().isStun() == true) stuned = true;
                        System.out.println(a1.getName() + " abilitatea 1 / " + b1.getName() + " atac normal -> Rezultat:");
                        System.out.print("\ta. " + a1.getName() + " HP " + a1.getHP());
                        if (cooldown1 != 0) System.out.print(", abilitate 1 cooldown " + cooldown1);
                        if (cooldown2 != 0) System.out.print(", abilitate 2 cooldown " + cooldown2);
                        System.out.println();
                        System.out.println("\tb. " + b1.getName() + " HP " + b1.getHP());

                    }
                    else{
                        if(cooldown1 > 0) cooldown1++;
                        if(cooldown2 > 0) cooldown2++;
                        continue;
                    }
                default:
                    break;
            }
        }
    }
}