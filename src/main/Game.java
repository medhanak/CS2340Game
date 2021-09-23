package main;
////
public class Game {
    private static String shiptype;
    private static double multiplier;
    private static int mobsKilled;
    private static int moneyEarned;
    private static int potsDrank;
    private static int itemsCollected;

    public Game(String shiptype) {
        this.shiptype = shiptype;
    }
    public static String getType() {
        return shiptype;
    }
    public static void setType(String type) {
        shiptype = type;
        if (type == "Ork Ship") {
            multiplier = .75;
        } else if (type == "Necron Hulk") {
            multiplier = 1.25;
        } else {
            multiplier = 2;
        }
    }
    public static double getMultiplier() {
        return multiplier;
    }

    public static int getMobsKilled() {
        return mobsKilled;
    }
    public static int getMoneyEarned() {
        return moneyEarned;
    }
    public static int getPotsDrank() {
        return potsDrank;
    }
    public static int getItemsCollected() {
        return itemsCollected;
    }

    public static void killMob() { mobsKilled = mobsKilled + 1;}
    public static void earnMoney(int cash) { moneyEarned = moneyEarned + cash;}
    public static void drinkPot() { potsDrank = potsDrank + 1;}
    public static void collectItem() { itemsCollected = itemsCollected + 1;}


}
