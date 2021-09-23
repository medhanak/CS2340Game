package main;

import java.util.HashMap;

public class ShopDict {
    private static HashMap dict;

    public ShopDict() {
        dict = new HashMap();
        dict.put("BoltPistolWeapon.png", 5);
        dict.put("ChainswordWeapon.jpg", 100);
        dict.put("GravGunWeapon.png", 10);
        dict.put("GreenAttackPotion.png", 50);
        dict.put("InfernoPistolWeapon.png", 200);
        dict.put("LasgunWeapon.jpg", 50);
        dict.put("MeltaRifleWeapon.png", 200);
        dict.put("PlasmaPistolWeapon.png", 75);
        dict.put("PulseRifleWeapon.png", 50);
        dict.put("RedHealthPotion.png", 20);
        dict.put("ReductorPistolWeapon.png", 10);
        dict.put("StormSniperWeapon.png", 250);
        dict.put("WitchbladeWeapon.jpg", 100);
        dict.put("YellowSpeedPotion.png", 50);
    }
    public static HashMap getDict() {
        return dict;
    }
    public static int getPrice(String item) {
        return (int) dict.get(item);
    }
}
