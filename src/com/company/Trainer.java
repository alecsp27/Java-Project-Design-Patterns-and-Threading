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

        File file2 = new File("D:\\Anul 2\\POO\\Proiect\\Input\\test2.txt");
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