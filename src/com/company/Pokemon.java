package com.company;

import java.util.ArrayList;

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