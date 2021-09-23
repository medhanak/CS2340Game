package main;

import java.util.HashMap;

public class MonsterDict {

    private static HashMap dict;

    public MonsterDict(String type) {
        dict = new HashMap();
        if (type == "Ork Ship") {
            dict.put("Ork RuntMonster.png", new int[] {5, 5, 1, 5});
            dict.put("FlyboyzMonster.png", new int[] {10, 5, 3, 10});
            dict.put("Burna BoyzMonster.png", new int[] {20, 20, 1, 20});
            dict.put("WarbossMonster.png", new int[] {100, 40, 2, 100});
            dict.put("Library", new String[] {"Ork RuntMonster.png", "FlyboyzMonster.png",
                    "Burna BoyzMonster.png", "WarbossMonster.png"});
        } else if (type == "Necron Hulk") {
            dict.put("Canoptek WraithMonster.png", new int[] {50, 5, 2, 10});
            dict.put("Necron WarriorMonster.png", new int[] {100, 10, 1, 20});
            dict.put("Flayed OneMonster.png", new int[] {100, 20, 1, 40});
            dict.put("PhaeronMonster.png", new int[] {500, 30, 1, 200});
            dict.put("Library", new String[]
                    {"Canoptek WraithMonster.png", "Necron WarriorMonster.png",
                            "Flayed OneMonster.png", "PhaeronMonster.png"});
        } else if (type == "Tyranid Hive") {
            dict.put("GauntMonster.png", new int[] {1, 1, 1, 1});
            dict.put("RipperMonster.png", new int[] {5, 5, 2, 5});
            dict.put("Tyrant GuardMonster.png", new int[] {20, 10, 1, 20});
            dict.put("Hive TyrantMonster.png", new int[] {500, 25, 2, 50});
            dict.put("Library", new String[] {"GauntMonster.png", "Ripper,png",
                    "Tyrant GuardMonster.png", "Hive TyrantMonster.png"});
        }
    }

    public static HashMap getDict() {
        return dict;
    }
    public static int[] get(int key) {
        return (int[]) dict.get(key);
    }
}
