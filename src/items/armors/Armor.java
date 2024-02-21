package items.armors;

import items.Item;

public abstract class Armor extends Item {

    protected int def;

    public Armor(String name, String description) {
        super(name, description);
    }

    public abstract void effect();

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
