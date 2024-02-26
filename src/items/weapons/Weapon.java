package items.weapons;

import items.Item;

public abstract class Weapon extends Item {
    protected String name;
    protected String description;
    protected int atk;

    public Weapon(String name, String description, int atk) {
        super(name, description);
        this.atk = atk;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAtk() {
        return atk;
    }
}
