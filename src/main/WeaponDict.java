package main;

import java.util.HashMap;

public class WeaponDict {

    private static HashMap dict;

    public WeaponDict() {
        dict = new HashMap();
        dict.put("BoltPistolWeapon.png", new int[] {3, 2, 3});
        dict.put("ReductorPistolWeapon.png", new int[] {5, 2, 2});
        dict.put("ChainswordWeapon.jpg", new int[] {10, 1, 2});
        dict.put("GravGunWeapon.png", new int[] {1, 2, 3});
        dict.put("InfernoPistolWeapon.png", new int[] {10, 2, 1});
        dict.put("LasgunWeapon.jpg", new int[] {5, 3, 3});
        dict.put("MeltaRifleWeapon.png", new int[] {15, 3, 1});
        dict.put("monster.jpg", new int[] {5, 1, 0});
        dict.put("PlasmaPistolWeapon.png", new int[] {10, 2, 2});
        dict.put("PulseRifleWeapon.jpg", new int[] {5, 2, 3});
        dict.put("StormSniperWeapon.png", new int[] {10, 4, 1});
        dict.put("WitchbladeWeapon.jpg", new int[] {20, 1, 1});
    }
    public static HashMap getDict() {
        return dict;
    }
    public static int[] get(int key) {
        return (int[]) dict.get(key);
    }
}
