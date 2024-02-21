import items.armors.helmets.WoodHelmet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>(1, 1);
        vector.add(52);
        vector.add(17);
        vector.add(81);
        vector.add(11);
        System.out.println(vector.size());
        System.out.println(vector);
        vector.sort(null);
        System.out.println(vector);
    }
}
