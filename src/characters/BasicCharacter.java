package characters;

import javax.swing.*;

public class BasicCharacter {

    private String name;
    private int hp;
    private int mp;
    private int maxHp;
    private int maxMp;

    public BasicCharacter(String name, int hp, int mp) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.maxHp = hp;
        this.maxMp = mp;
    }

    public void displayData() {

        String output = String.format("Name: %s\n", name);
        output += String.format("\tHP: %d/%d\n", hp, maxHp);
        output += String.format("\tMP: %d/%d\n", mp, maxMp);
        JOptionPane.showMessageDialog(null, output);
    }
}
