package game;

import enemies.Enemy;
import enemies.goblins.RookieGoblin;
import enemies.wolfs.AloneWolf;
import player.Player;
import util.Randomized;

import java.util.ArrayList;

import static util.Randomized.randomizeNumber;

public class Game {
    private Player player;
    private ArrayList<Enemy> enemies;
    public Game(Player player) {

        this.player = player;
        enemies = new ArrayList<>(3);
    }

    private void randomizeEnemies() {

        for (int i = 0; i < 3; i++) {

            switch (Randomized.randomizeNumber(0, 1)) {
                case 0 -> enemies.add(new RookieGoblin());
                case 1 -> enemies.add(new AloneWolf());
            }
        }
    }
    public void start() {

        randomizeEnemies();
        System.out.println("You are fighting " + enemies.size() + " enemies!");
        int selection = 0;
        player.printActions();
        for (Enemy enemy : enemies) {
            while (!player.isDead() && !enemy.isDead()) {
                player.attack(enemy);
                if (!enemy.isDead()) {
                    enemy.attack(player);
                }
            }
            if (player.isDead()) {
                System.out.println("You died!");
                break;
            }
            player.heal(5);
            player.recoverMp(5);
        }
        if (!player.isDead()) {
            System.out.println("You won!");
        }
    }
}
