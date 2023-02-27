package com.company;

//Singleton - used for the class that represents the fight between the best pokemon of the first trainer and the best
//pokemon of the second one


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class FightTrainersFinal{

    private static FightTrainersFinal instantaUnica;
    private FightTrainersFinal(){}
    //increase method used to increase each skill after a wined fight
    static void increase(Pokemon x){
        x.setHP(x.getHP() + 1);
        if(x.getAttack() != 0)
            x.setAttack(x.getAttack() + 1);
        if(x.getSpecialAttack() != 0)
            x.setSpecialAttack(x.getSpecialAttack() + 1);
        x.setDefense(x.getDefense() + 1);
        x.setSpecialDefense(x.getSpecialDefense() + 1);
    }
    public static FightTrainersFinal Instanta(Pokemon a, Pokemon b) {
        if(instantaUnica == null)
            instantaUnica = new FightTrainersFinal();
        Pokemon a1 = a.copyPokemon(a);
        Pokemon b1 = b.copyPokemon(b);

        //printing the details of each fighter

        System.out.println("Luptator1: " + a1.getName() + " HP: " + a1.getHP() + " Attack:" + a1.getAttack()
                + " Special Attack: " + a1.getSpecialAttack() + " Defense: " + a1.getDefense() +
                " SpecialDefense: " + a1.getSpecialDefense());
        System.out.println("Luptator2: " + b1.getName() + " HP: " + b1.getHP() + " Attack:" + b1.getAttack()
                + " Special Attack: " + b1.getSpecialAttack() + " Defense: " + b1.getDefense() +
                " SpecialDefense: " + b1.getSpecialDefense());
        Fighter1 f1 = new Fighter1(a1);
        Fighter2 f2 = new Fighter2(b1);
        f1.ataseazaOponent(f2);
        f2.ataseazaOponent(f1);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(f1);
        executor.execute(f2);
        executor.shutdown();
        while (executor.isTerminated() == false) continue;
        if (executor.isTerminated()) {
            if (f1.f1.getHP() < f1.oponent.f1.getHP()) {
                System.out.println(b1.getName() + " castiga lupta");
                increase(b);
                System.out.println("Imbunatatire atribute: " + b.getName() + " HP: " + b.getHP() + " Attack:" + b.getAttack()
                        + " Special Attack: " + b.getSpecialAttack() + " Defense: " + b.getDefense() +
                        " SpecialDefense: " + b.getSpecialDefense());
            } else {
                System.out.println(a1.getName() + " castiga lupta");
                increase(a);
                System.out.println("Imbunatatire atribute: " + a.getName() + " HP: " + a.getHP() + " Attack:" + a.getAttack()
                        + " Special Attack: " + a.getSpecialAttack() + " Defense: " + a.getDefense() +
                        " SpecialDefense: " + a.getSpecialDefense());
            }
        }


        return instantaUnica;
    }
}