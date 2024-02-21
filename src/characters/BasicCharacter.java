package characters;

public abstract class BasicCharacter {

    protected String name;
    protected int hp;
    protected int mp;
    protected int maxHp;
    protected int maxMp;

    public BasicCharacter(String name, int hp, int mp) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.maxHp = hp;
        this.maxMp = mp;
    }

    public abstract void displayData();
}
