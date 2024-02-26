package enemies.wolfs;

import enemies.Enemy;
import player.Player;
import player.debuffs.Debuff;
import player.debuffs.DebuffType;

import static util.Randomized.randomize;

public class AloneWolf extends Enemy {

    public AloneWolf() {

        super("Lobo solitario", 30, 3, 10, 10);
    }

    @Override
    public void attack(Player player) {

        switch (randomize(0, 2)) {

            case 0 -> simpleAttack(player);
            case 1 -> howl(player);
            case 2 -> bite(player);
        }
    }

    public void simpleAttack(Player player) {

        System.out.println("Lobo solitario ataca con" + getDamage() + " puntos de daño!");
        player.takeDamage(getDamage());
    }

    public void howl(Player player) {

        System.out.println("¡Lobo solitario aúlla!");
        if (player.getDebuffs().size() < 5) {
            player.getDebuffs().add(new Debuff(DebuffType.WEAKNESS));
        }
    }

    public void bite(Player player) {

        System.out.println("¡Lobo solitario muerde!");
        player.takeDamage(getDamage() + 5);
    }
}
