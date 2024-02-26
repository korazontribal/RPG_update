package enemies.goblins;

import enemies.Enemy;
import player.Player;

import static util.Randomized.randomize;

public class RookieGoblin extends Enemy {
    public RookieGoblin() {
        super("Rookie Goblin", 20, 2, 5, 5);
    }

    @Override
    public void attack(Player player) {
        switch (randomize(0, 2)) {
            case 0 -> {
                System.out.println("Rookie Goblin attacks for " + getDamage() + " damage!");
                player.takeDamage(getDamage());
            }
            case 1 -> runAway();
            case 2 -> stealGold(player);
        }
        System.out.println("Rookie Goblin attacks for " + getDamage() + " damage!");
    }

    public void runAway() {
        System.out.println("Rookie Goblin runs away!");
        this.setHealth(0);
    }

    public void stealGold(Player player) {
        System.out.println("Rookie Goblin steals 5 gold!");
        player.setGold(player.getGold() - 5);
    }
}
