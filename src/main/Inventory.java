package main;

public class Inventory {

    private static Item[] array;
    private static int itemCount = 1;

    public Inventory(Weapon startWeap) {
        array = new Item[10];
        Player.setWeapon(startWeap);
    }

    public static void addItem(Item item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = item;
                itemCount++;
                GameController.clearItem();
                GameController.updateItem(item);
                return;
            }
        }
    }

    public static int removeItem(Item item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                array[i] = null;
                itemCount--;
                return i;
            }
        }
        return -1;
    }

    public static void useItem(int index) {
        Item item = array[index];
        if (null == item) {
            return;
        }
        String type = item.getType();
        if (type.contains("Potion")) {
            Potion potion = (Potion) item;
            if (type.contains("Health")) {
                Player.setHealth(Player.getHealth() + potion.getHealthBoost());
                GameController.updateHealth();
            } else if (type.contains("Attack")) {
                Player.setStrength(Player.getStrength() + potion.getDamageBoost());
                GameController.updateStats();
            } else if (type.contains("Speed")) {
                Player.setSpeed(Player.getSpeed() + potion.getSpeedBoost());
                GameController.updateStats();
            }
            Inventory.removeItem(item);
            Game.drinkPot();
        } else if (type.contains("Weapon")) {
            Inventory.removeItem(item);
            Inventory.addItem(Player.getWeapon());
            Player.setWeapon((Weapon) item);
            GameController.updateWeapon();
        }
        InventoryView.updateInventory();
        GameController.clearItem();
        GameController.updateItem(item);
    }

    public static Item[] getArray() {
        return array;
    }
    public static int getItemCount() {
        return itemCount;
    }
}
