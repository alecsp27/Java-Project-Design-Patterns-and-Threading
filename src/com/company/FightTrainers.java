package com.company;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class FightTrainers{
    void increase(Pokemon x){
        x.setHP(x.getHP() + 1);
        if(x.getAttack() != 0)
            x.setAttack(x.getAttack() + 1);
        if(x.getSpecialAttack() != 0)
            x.setSpecialAttack(x.getSpecialAttack() + 1);
        x.setDefense(x.getDefense() + 1);
        x.setSpecialDefense(x.getSpecialDefense() + 1);
    }
    public FightTrainers(Pokemon a, Pokemon b, Pokemon n1, Pokemon n2) throws IOException {
        Pokemon a1 = a.copyPokemon(a);
        Pokemon b1 = b.copyPokemon(b);
        Random rand = new Random();
        int rand1 = 0;
        while(rand1 != 2){
            a1 = a.copyPokemon(a);
            b1 = b.copyPokemon(b);

            rand1 = rand.nextInt(3);
            // rand1 == 0 -> fight with a Neutrel1 pokemon
            // rand1 == 1 -> fight with a Neutrel2 pokemon
            // rand1 == 2 -> fight with a pokemon that belongs to the other trainer
            switch (rand1) {
                case 0:
                    System.out.println("Luptator1: " + a1.getName() + " HP: " + a1.getHP() + " Attack:" + a1.getAttack()
                            + " Special Attack: " + a1.getSpecialAttack() + " Defense: " + a1.getDefense() +
                            " SpecialDefense: " + a1.getSpecialDefense());
                    System.out.println("Luptator 2: " + n1.getName() + " HP: " + n1.getHP() + " Attack:" + n1.getAttack()
                            + " Special Attack: " + n1.getSpecialAttack() + " Defense: " + n1.getDefense() +
                            " SpecialDefense: " + n1.getSpecialDefense());
                    FightNeutrel ff = new FightNeutrel(a1, n1);
                    if (a1.getHP() > n1.getHP()) {
                        System.out.println(a1.getName() + " castiga lupta");
                        increase(a);
                        System.out.println("Imbunatatire atribute: " + a.getName() + " HP: " + a.getHP() + " Attack: " + a.getAttack()
                                + " Special Attack: " + a.getSpecialAttack() + " Defense: " + a.getDefense() +
                                " SpecialDefense: " + a.getSpecialDefense());
                    } else
                        System.out.println(n1.getName() + " castiga lupta");
                    System.out.println();
                    System.out.println("Luptator1: " + b1.getName() + " HP: " + b1.getHP() + " Attack:" + b1.getAttack()
                            + " Special Attack: " + b1.getSpecialAttack() + " Defense: " + b1.getDefense() +
                            " SpecialDefense: " + b1.getSpecialDefense());
                    System.out.println("Luptator2: " + n1.getName() + " HP: " + n1.getHP() + " Attack:" + n1.getAttack()
                            + " Special Attack: " + n1.getSpecialAttack() + " Defense: " + n1.getDefense() +
                            " SpecialDefense: " + n1.getSpecialDefense());
                    FightNeutrel ff2 = new FightNeutrel(b1, n1);
                    if (b1.getHP() > n1.getHP()) {
                        System.out.println(b1.getName() + " castiga lupta");
                        increase(b);
                        System.out.println("Imbunatatire atribute: " + b.getName() + " HP: " + b.getHP() + " Attack:" + b.getAttack()
                                + " Special Attack: " + b.getSpecialAttack() + " Defense: " + b.getDefense() +
                                " SpecialDefense: " + b.getSpecialDefense());
                    } else
                        System.out.println(n1.getName() + " castiga lupta");
                    System.out.println();
                    break;
                case 1:
                    System.out.println("Luptator1: " + a1.getName() + " HP: " + a1.getHP() + " Attack:" + a1.getAttack()
                            + " Special Attack: " + a1.getSpecialAttack() + " Defense: " + a1.getDefense() +
                            " SpecialDefense: " + a1.getSpecialDefense());
                    System.out.println("Luptator 2: " + n2.getName() + " HP: " + n2.getHP() + " Attack:" + n2.getAttack()
                            + " Special Attack: " + n2.getSpecialAttack() + " Defense: " + n2.getDefense() +
                            " SpecialDefense: " + n2.getSpecialDefense());
                    FightNeutrel ff3 = new FightNeutrel(a1, n2);
                    if (a1.getHP() > n2.getHP()) {
                        System.out.println(a1.getName() + " castiga lupta");
                        increase(a);
                        System.out.println("Imbunatatire atribute: " + a.getName() + " HP: " + a.getHP() + " Attack: " + a.getAttack()
                                + " Special Attack: " + a.getSpecialAttack() + " Defense: " + a.getDefense() +
                                " SpecialDefense: " + a.getSpecialDefense());
                    } else
                        System.out.println(n2.getName() + " castiga lupta");
                    System.out.println();
                    System.out.println("Luptator1: " + b1.getName() + " HP: " + b1.getHP() + " Attack:" + b1.getAttack()
                            + " Special Attack: " + b1.getSpecialAttack() + " Defense: " + b1.getDefense() +
                            " SpecialDefense: " + b1.getSpecialDefense());
                    System.out.println("Luptator2: " + n2.getName() + " HP: " + n2.getHP() + " Attack:" + n2.getAttack()
                            + " Special Attack: " + n2.getSpecialAttack() + " Defense: " + n2.getDefense() +
                            " SpecialDefense: " + n2.getSpecialDefense());
                    FightNeutrel ff4 = new FightNeutrel(b1, n2);
                    if (b1.getHP() > n2.getHP()) {
                        System.out.println(b1.getName() + " castiga lupta");
                        increase(b);
                        System.out.println("Imbunatatire atribute: " + b.getName() + " HP: " + b.getHP() + " Attack:" + b.getAttack()
                                + " Special Attack: " + b.getSpecialAttack() + " Defense: " + b.getDefense() +
                                " SpecialDefense: " + b.getSpecialDefense());
                    } else
                        System.out.println(n1.getName() + " castiga lupta");
                    System.out.println();
                    break;
                case 2:
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

                    //creating an executor in order to implement a threadpool

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
                    break;

            }

        }

    }
}