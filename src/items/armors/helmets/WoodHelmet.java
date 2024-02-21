package items.armors.helmets;

import items.armors.Armor;

import javax.swing.*;

public class WoodHelmet extends Armor {

    public WoodHelmet() {
        super("", "");
        this.description = "Casco sencillo de madera";
        this.def = 1;
        this.name = "Casco de Madera";
    }

    @Override
    public void effect() {
        JOptionPane.showMessageDialog(null, "Sin Efectos");
    }
}
