package util;

import java.util.Random;

public interface Randomized {
    static int randomize(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    static boolean randomize() {
        return new Random().nextBoolean();
    }

    static double randomize(double min, double max) {
        return new Random().nextDouble() * (max - min) + min;
    }
}
