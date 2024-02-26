package player;

import characters.BasicCharacter;
import enemies.Enemy;
import items.armors.Armor;
import items.weapons.Weapon;
import player.debuffs.Debuff;
import util.Randomized;

import java.util.ArrayList;

public class Player extends BasicCharacter {

    private int strength;
    private int defense;
    private int intelligence;
    private int dexterity;
    private int luck;
    private int experience;
    private int level;
    private int gold;
    private ArrayList<Debuff> debuffs;
    private Weapon weapon;
    private Armor armor;

    public Player(String name) {
        super(name, 30, 10);
        randomizeStats();
        debuffs = new ArrayList<>(5);
        experience = 0;
        level = 1;
        gold = 0;
        weapon = null;
        armor = null;
    }

    public void randomizeStats() {

        int maxPoints = 30;
        int stat = Randomized.randomize(1, 5);
        while (maxPoints > 0) {
            switch (stat) {
                case 1 -> strength++;
                case 2 -> defense++;
                case 3 -> intelligence++;
                case 4 -> dexterity++;
                case 5 -> luck++;
            }
            maxPoints--;
            stat = Randomized.randomize(1, 5);
        }
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void equipArmor(Armor armor) {
        this.armor = armor;
    }

    public void attack(Enemy enemy) {

        if (weapon != null) {
            enemy.takeDamage(getDamage() + weapon.getAtk());
        } else {
            enemy.takeDamage(getDamage());
        }
        System.out.println(getName() + " attacks for " + getDamage() + " damage!");
        if (enemy.isDead()) {
            gainExperience(enemy.getExperience());
            gainGold(enemy.getGold());
        }
    }

    @Override
    public void displayData() {
        System.out.println("Name: " + getName());
        System.out.println("Level: " + level);
        System.out.println("Experience: " + experience + "/" + level * 10);
        System.out.println("Health: " + getMaxHp());
        System.out.println("Mana: " + getMaxMp());
        System.out.println("Strength: " + strength);
        System.out.println("Defense: " + defense);
        System.out.println("Intelligence: " + intelligence);
        System.out.println("Dexterity: " + dexterity);
        System.out.println("Luck: " + luck);
        System.out.println("Gold: " + gold);
    }

    public void takeDamage(int damage) {

        if (armor != null) {
            damage -= armor.getDef();
            if (damage < 0) {
                damage = 0;
            }
        }
        for (Debuff debuff : debuffs) {
            damage += debuff.getDamage();
            debuff.reduceDuration();
            if (debuff.getDuration() == 0) debuffs.remove(debuff);
        }
        super.takeDamage(damage);
        if (isDead()) {
            printDeath();
        }
    }

    public void gainExperience(int experience) {

        this.experience += experience;
        while (this.experience >= level * 10) {
            this.experience -= level * 10;
            level++;
            strength++;
            defense++;
            intelligence++;
            dexterity++;
            luck++;
            heal(10);
        }
    }

    public void gainGold(int gold) {
        this.gold += gold;
    }

    public void printInventory() {
        System.out.println("Weapon: " + (weapon != null ? weapon.getName() : "None"));
        System.out.println("Armor: " + (armor != null ? armor.getName() : "None"));
    }

    public String printActions() {
        return "Que harás frente a tu enemigo:\n1. atacar\n2. defender\n3. huir";
    }

    public void printEquipmentActions() {
        System.out.println("1. Equip Weapon");
        System.out.println("2. Equip Armor");
    }

    public void printLevelUp() {
        System.out.println("Congratulations! You have leveled up to level " + level + "!");
        System.out.println("You have gained 10 health and 1 point to all stats!");
    }

    public void printDeath() {
        System.out.println("You have died!");
    }

    public void printRun() {
        System.out.println("You have successfully ran away!");
    }

    public void printGold(int gold) {
        System.out.println("You have gained " + gold + " gold!");
    }

    public void printExperience(int experience) {
        System.out.println("You have gained " + experience + " experience!");
    }

    public void printHeal(int heal) {
        System.out.println("You have healed for " + heal + " health!");
    }

    public void printEquipWeapon(Weapon weapon) {
        System.out.println("You have equipped " + weapon.getName() + "!");
    }

    public void printEquipArmor(Armor armor) {
        System.out.println("You have equipped " + armor.getName() + "!");
    }

    public void printUnequipWeapon(Weapon weapon) {
        System.out.println("You have unequipped " + weapon.getName() + "!");
    }

    public void printUnequipArmor(Armor armor) {
        System.out.println("You have unequipped " + armor.getName() + "!");
    }

    //Getters and Setters

    public ArrayList<Debuff> getDebuffs() {
        return debuffs;
    }

    public void setDebuffs(ArrayList<Debuff> debuffs) {
        this.debuffs = debuffs;
    }

    public int getLevel() {
        return level;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExperience() {
        return experience;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getLuck() {
        return luck;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public String getWeaponName() {
        return weapon != null ? weapon.getName() : "None";
    }

    public String getArmorName() {
        return armor != null ? armor.getName() : "None";
    }

    public int getDamage() {
        return weapon != null ? strength + weapon.getAtk() : strength;
    }

    public String getName() {
        return super.getName();
    }
}
