package main;

import java.util.HashMap;

public class RoomDict {

    private static HashMap dict;

    public RoomDict() {
        dict = new HashMap();
        if (Game.getType() == "Ork Ship") {
            dict.put(1, new int[] {3, 0, 0, 0});
            dict.put(2, new int[] {2, 1, 0, 0});
            dict.put(3, new int[] {0, 3, 0, 0});
            dict.put(4, new int[] {1, 1, 1, 0});
            dict.put(5, new int[] {1, 1, 2, 0});
        } else if (Game.getType() == "Necron Hulk") {
            dict.put(1, new int[] {1, 0, 0, 0});
            dict.put(2, new int[] {2, 0, 0, 0});
            dict.put(3, new int[] {0, 1, 0, 0});
            dict.put(4, new int[] {0, 0, 1, 0});
            dict.put(5, new int[] {2, 0, 1, 0});
        } else if (Game.getType() == "Tyranid Hive") {
            dict.put(1, new int[] {10, 0, 0, 0});
            dict.put(2, new int[] {5, 5, 0, 0});
            dict.put(3, new int[] {5, 5, 2, 0});
            dict.put(4, new int[] {10, 10, 1, 0});
            dict.put(5, new int[] {5, 5, 5, 0});
        }
    }

    public static HashMap getDict() {
        return dict;
    }
    public static int[] get(int key) {
        return (int[]) dict.get(key);
    }
}
