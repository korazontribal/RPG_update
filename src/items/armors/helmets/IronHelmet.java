package items.armors.helmets;

import items.armors.Armor;

import javax.swing.*;

public class IronHelmet extends Armor {
    private int str;
    private int counter;

    public IronHelmet(int str) {
        this.description = "Casco lujoso de hierro endeble";
        this.def = 5;
        this.name = "Casco de Hierro";
        this.str = str;
        this.counter = 0;
    }

    public void riseDefense() {
        def += 5;
        counter = 3;
    }

    @Override
    public void effect() {
        JOptionPane.showMessageDialog(null,
                "Este casco permité aumentar la defensa del usuario por tres turnos.");
    }
}
