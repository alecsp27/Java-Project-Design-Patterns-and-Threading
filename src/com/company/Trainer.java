package com.company;

import java.io.*;
import java.net.SocketImpl;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Integer.numberOfLeadingZeros;
import static java.lang.Integer.parseInt;

class Test{
    //method for the creation of pokemons using factory
    public Pokemon crearePokemon(String line){
        Factory f = new Factory();
        String[] arr = line.split("###");
        Pokemon p1 = null;
        switch (arr.length){
            case 1:
                p1 = f.creeazaPokemon(arr[0], null, null, null);
                break;
            case 2:
                p1 = f.creeazaPokemon(arr[0], arr[1], null, null);
                break;
            case 3:
                p1 = f.creeazaPokemon(arr[0], arr[1], arr[2], null);
                break;
            case 4:
                p1 = f.creeazaPokemon(arr[0], arr[1], arr[2], arr[3]);
                break;
        }
        return p1;

    }

    //reading from the input files the required information

    public void citireFisier(File file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Pokemon final1 = null;
            Pokemon final2 = null;
            Factory f = new Factory();
            line = br.readLine();
            Trainer t1 = new Trainer();
            String[] arr = line.split("###");
            t1.setName(arr[0]);
            t1.setAge(parseInt(arr[1]));
            line = br.readLine();
            Pokemon p1 = crearePokemon(line);
            line = br.readLine();
            Pokemon p2 = crearePokemon(line);
            line = br.readLine();
            Pokemon p3 = crearePokemon(line);
            line = br.readLine();
            Trainer t2 = new Trainer();
            String[] arr2 = line.split("###");
            t1.setName(arr2[0]);
            t1.setAge(parseInt(arr2[1]));
            line = br.readLine();
            Pokemon p4 = crearePokemon(line);
            line = br.readLine();
            Pokemon p5 = crearePokemon(line);
            line = br.readLine();
            Pokemon p6 = crearePokemon(line);
            Pokemon p7 = crearePokemon("Neutrel1");
            Pokemon p8 = crearePokemon("Neutrel2");
            FightTrainers f0 = new FightTrainers(p1, p4, p7, p8);
            FightTrainers f1 = new FightTrainers(p2, p5, p7, p8);
            FightTrainers f2 = new FightTrainers(p3, p6, p7, p8);
            int sumP1 = p1.getHP() + p1.getSpecialDefense() + p1.getDefense() + p1.getSpecialAttack() + p1.getAttack();
            int sumP2 = p2.getHP() + p2.getSpecialDefense() + p2.getDefense() + p2.getSpecialAttack() + p2.getAttack();
            int sumP3 = p3.getHP() + p3.getSpecialDefense() + p3.getDefense() + p3.getSpecialAttack() + p3.getAttack();
            int sumP4 = p4.getHP() + p4.getSpecialDefense() + p4.getDefense() + p4.getSpecialAttack() + p4.getAttack();
            int sumP5 = p5.getHP() + p5.getSpecialDefense() + p5.getDefense() + p5.getSpecialAttack() + p5.getAttack();
            int sumP6 = p6.getHP() + p6.getSpecialDefense() + p6.getDefense() + p6.getSpecialAttack() + p6.getAttack();
            int max1 = 0;
            if(max1 < sumP1){
                max1 = sumP1;
                final1 = p1;
            }
            if(max1 < sumP2){
                max1 = sumP1;
                final1 = p2;
            }
            if(max1 < sumP3){
                final1 = p3;
            }
            int max2 = 0;
            if(max2 < sumP4){
                max2 = sumP4;
                final2 = p4;
            }
            if(max2 < sumP5){
                max2 = sumP5;
                final2 = p5;
            }
            if(max2 < sumP6){
                final2 = p6;
            }
            FightTrainersFinal ffinal = FightTrainersFinal.Instanta(final1, final2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        File file2 = new File("D:\\POO\\Proiect\\Input\\test2.txt");
        Test t2 = new Test();
        t2.citireFisier(file2);

    }
}



class Trainer {
    private String name;
    private int age;
    private ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
    public void citireFisier(File file){

    }

}


//cooldwon1 is the cooldown time of the first ability
//cooldwon2 is the cooldown time of the second ability


//we will use the observer design pattern in order to inform each fighter if they are stunned

//Observer -> the change of an object determines a new state for another one which has to be notified

class Fighter1 implements Runnable{

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

//Singleton - used for the class that represents the fight between the best pokemon of the first trainer and the best
//pokemon of the second one


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

//implementation of fights with neutrel pokemons

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


class Pokemon{
    private String name;
    private int HP;
    private int attack;
    private int specialAttack;
    private int defense;
    private int specialDefense;
    private Ability ability1;
    private Ability ability2;
    private ArrayList<Item> items = new ArrayList<Item>();

    public ArrayList<Item> getItems() {
        return items;
    }

    //copyPokemon method -> used to create a copy of the pokemon that will be used in the actual fight (doing this
    // so we don't lose saved info) and the values of the items will be added to the skills

    public Pokemon copyPokemon(Pokemon a){
        Pokemon a1 = new Pokemon(a.getName(), a.getHP(), a.getAttack(), a.getSpecialAttack(),a.getDefense(), a.getSpecialDefense());
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getDefense() != 0) a1.setDefense(a1.getDefense() + items.get(i).getDefense());
            if(items.get(i).getHP() != 0) a1.setHP(a1.getHP() + items.get(i).getHP());
            if(items.get(i).getSpecialDefense() != 0) a1.setSpecialDefense(a1.getSpecialDefense() + items.get(i).getSpecialDefense());
            //in urm doua cazuri trebuie sa verificam si daca pokemon-ul nostru poate sa efectuze atac normal resp special
            if(items.get(i).getAttack() != 0 && a1.getAttack() != 0)
                a1.setAttack(a1.getAttack() + items.get(i).getAttack());
            if(items.get(i).getSpecialAttack() != 0 && a1.getSpecialAttack() != 0)
                a1.setSpecialAttack(a1.getSpecialAttack() + items.get(i).getSpecialAttack());
        }
        if(a.getAbility1() != null)
        a1.ability1 = new Ability(a.getAbility1().getDamage(), a.getAbility1().isStun(), a.getAbility1().isDodge(), a.getAbility1().getCooldown());
        if(a.getAbility2() != null)
        a1.ability2 = new Ability(a.getAbility2().getDamage(), a.getAbility2().isStun(), a.getAbility2().isDodge(), a.getAbility2().getCooldown());
        return a1;
    }

//using the builder design pattern, items of the pokemons will be created

    public void setItems(String item1, String item2, String item3) {
        String[] obiecte = new String[3];
        obiecte[0] = item1;
        obiecte[1] = item2;
        obiecte[2] = item3;
        for(String s : obiecte){
            if(s != null) {
                switch (s) {
                    case "Scut":
                        Item item = new Item.Builder()
                                .defense(2)
                                .specialDefense(2)
                                .build();
                        this.items.add(item);
                        break;
                    case "Vesta":
                        Item item02 = new Item.Builder()
                                .HP(10)
                                .build();
                        this.items.add(item02);
                        break;
                    case "Sabiuta":
                        Item item03 = new Item.Builder()
                                .attack(3)
                                .build();
                        this.items.add(item03);
                        break;
                    case "Bagheta Magica":
                        Item item04 = new Item.Builder()
                                .specialAttack(3)
                                .build();
                        this.items.add(item04);
                        break;
                    case "Vitamine":
                        Item item05 = new Item.Builder()
                                .HP(2)
                                .attack(2)
                                .build();
                        this.items.add(item05);
                        break;
                    case "Brad de Craciun":
                        Item item06 = new Item.Builder()
                                .attack(3)
                                .defense(1)
                                .build();
                        this.items.add(item06);
                        break;
                    case "Pelerina":
                        Item item07 = new Item.Builder()
                                .specialDefense(3)
                                .build();
                        this.items.add(item07);
                        this.items.add(new Item(0, 0, 0, 0, 3));
                        break;

                }
            }
        }
    }

    public Ability getAbility1() {
        return ability1;
    }

    public Pokemon(String name, int HP, int attack, int specialAttack, int defense, int specialDefense) {
        this.name = name;
        this.HP = HP;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
    }

    public void setAbility1(Ability ability1) {
        this.ability1 = ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public void setAbility2(Ability ability2) {
        this.ability2 = ability2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

}

//there will be a subclass of Pokemon for each type of fighter having the imposed characteristics

class Neutrel1 extends Pokemon{
    public Neutrel1(String item1, String item2, String item3){
        super("Neutrel1", 10, 3, 0, 1, 1);
        this.setItems(item1, item2, item3);
        this.setAbility1(null);
        this.setAbility2(null);

    }
}

class Neutrel2 extends Pokemon{
    public Neutrel2(String item1, String item2, String item3){
        super("Neutrel2", 20, 4, 0, 1, 1);
        this.setItems(item1, item2, item3);
        this.setAbility1(null);
        this.setAbility2(null);
    }
}

class Pikachu extends Pokemon{
    public Pikachu(String item1, String item2, String item3){
        super("Pikachu",35 , 0, 4, 2, 3);
        this.setItems(item1, item2, item3);
        Ability a1 = new Ability(6, false, false, 4);
        Ability a2 = new Ability(4, true, true, 5);
        this.setAbility1(a1);
        this.setAbility2(a2);
    }
}

class Bulbasaur extends Pokemon{
    public Bulbasaur(String item1, String item2, String item3){
        super("Bulbasaur", 42, 0, 5, 3, 1);
        this.setItems(item1, item2, item3);
        Ability a1 = new Ability(6, false, false, 4);
        Ability a2 = new Ability(5, false, false, 3);
        this.setAbility1(a1);
        this.setAbility2(a2);
    }
}

class Charmander extends Pokemon{
    public Charmander(String item1, String item2, String item3){
        super("Charmander", 50, 4, 0, 3, 2);
        this.setItems(item1, item2, item3);
        Ability a1 = new Ability(4, true, false, 4);
        Ability a2 = new Ability(7, false, false, 6);
        this.setAbility1(a1);
        this.setAbility2(a2);
    }
}

class Squirtle extends Pokemon{
    public Squirtle(String item1, String item2, String item3){
        super("Squirtle", 60, 0, 3, 5, 5);
        this.setItems(item1, item2, item3);
        Ability a1 = new Ability(4, false, false, 3);
        Ability a2 = new Ability(2, true, false, 2);
        this.setAbility1(a1);
        this.setAbility2(a2);
    }
}

class Snorlax extends Pokemon{
    public Snorlax(String item1, String item2, String item3){
        super("Snorlax", 62, 3, 0, 6, 4);
        this.setItems(item1, item2, item3);
        Ability a1 = new Ability(4, true, false, 5);
        Ability a2 = new Ability(0, false, true, 5);
        this.setAbility1(a1);
        this.setAbility2(a2);
    }
}

class Vulpix extends Pokemon{
    public Vulpix(String item1, String item2, String item3){
        super("Vulpix", 36, 5, 0, 2, 4);
        this.setItems(item1, item2, item3);
        Ability a1 = new Ability(8, true, false, 6);
        Ability a2 = new Ability(2, false, true, 7);
        this.setAbility1(a1);
        this.setAbility2(a2);
    }
}

class Eevee extends Pokemon{
    public Eevee(String item1, String item2, String item3){
        super("Eevee", 39, 0, 4, 3, 3);
        this.setItems(item1, item2, item3);
        Ability a1 = new Ability(5, false, false, 3);
        Ability a2 = new Ability(3, true, false, 3);
        this.setAbility1(a1);
        this.setAbility2(a2);
    }
}

class Jigglypuff extends Pokemon{
    public Jigglypuff(String item1, String item2, String item3){
        super("Jigglypuff", 34, 4, 0, 2, 3);
        this.setItems(item1, item2, item3);
        Ability a1 = new Ability(4, true, false, 4);
        Ability a2 = new Ability(3, true, false, 4);
        this.setAbility1(a1);
        this.setAbility2(a2);
    }
}

class Meowth extends Pokemon{
    public Meowth(String item1, String item2, String item3){
        super("Meowth", 41, 3, 0, 4, 2);
        this.setItems(item1, item2, item3);
        Ability a1 = new Ability(5, false, true, 4);
        Ability a2 = new Ability(1, false, true, 3);
        this.setAbility1(a1);
        this.setAbility2(a2);
    }
}

class Psyduck extends Pokemon{
    public Psyduck(String item1, String item2, String item3){
        super("Psyduck", 43, 3, 0, 3, 3);
        this.setItems(item1, item2, item3);
        Ability a1 = new Ability(2, false, false, 4);
        Ability a2 = new Ability(2, true, false, 5);
        this.setAbility1(a1);
        this.setAbility2(a2);
    }
}

class Factory{
    public Pokemon creeazaPokemon(String tip, String item1, String item2, String item3){
        switch (tip) {
            case "Neutrel1":
                return new Neutrel1(item1, item2, item3);
            case "Neutrel2":
                return new Neutrel2(item1, item2, item3);
            case "Pikachu":
                return new Pikachu(item1, item2, item3);
            case "Bulbasaur":
                return new Bulbasaur(item1, item2, item3);
            case "Charmander":
                return new Charmander(item1, item2, item3);
            case "Squirtle":
                return new Squirtle(item1, item2, item3);
            case "Snorlax":
                return new Snorlax(item1, item2, item3);
            case "Vulpix":
                return new Vulpix(item1, item2, item3);
            case "Eevee":
                return new Eevee(item1, item2, item3);
            case "Jigglypuff":
                return new Jigglypuff(item1, item2, item3);
            case "Meowth":
                return new Meowth(item1, item2, item3);
            case "Psyduck":
                return new Psyduck(item1, item2, item3);
            default:
                return null;
        }
    }
}

class Ability{
    private int damage;
    private boolean stun;
    private boolean dodge;
    private int cooldown;

    public Ability(int damage, boolean stun, boolean dodge, int cooldown) {
        this.damage = damage;
        this.stun = stun;
        this.dodge = dodge;
        this.cooldown = cooldown;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isStun() {
        return stun;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
}
//in order to create an item we are using the builder design pattern as many of its fields are empty

class Item{
    private int HP;
    private int attack;
    private int specialAttack;
    private int defense;
    private int specialDefense;

    public static class Builder{
        private int HP = 0;
        private int attack = 0;
        private int specialAttack = 0;
        private int defense = 0;
        private int specialDefense = 0;
        public Builder(){}
        public Builder HP(int hp){
            this.HP = hp;
            return this;
        }
        public Builder attack(int attack){
            this.attack = attack;
            return this;
        }
        public Builder specialAttack(int specialAttack){
            this.specialAttack = specialAttack;
            return this;
        }
        public Builder defense(int defense){
            this.defense = defense;
            return this;
        }
        public Builder specialDefense(int specialDefense){
            this.specialDefense = specialDefense;
            return this;
        }
        public Item build(){
            return new Item(this);
        }
    }

    private Item(Builder builder){
        this.attack = builder.attack;
        this.HP = builder.HP;
        this.specialAttack = builder.specialAttack;
        this.defense = builder.defense;
        this.specialDefense = builder.specialDefense;

    }

    public Item(int HP, int attack, int specialAttack, int defense, int specialDefense) {
        this.HP = HP;
        this.attack = attack;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }
}