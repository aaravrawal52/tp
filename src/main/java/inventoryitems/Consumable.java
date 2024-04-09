package inventoryitems;

import textbox.PlayerStatus;

import java.util.ArrayList;

public class Consumable extends ShopItem { //we assume all consumables are for 1 turn only
    protected int healAmt;
    protected int damageAmpAmt;

    public Consumable(int heal, int damage, String itemDescription, String itemName, int cost) {
        super.description = itemDescription;
        healAmt = heal;
        damageAmpAmt = damage;
        super.name = itemName;
        super.price = cost;
        super.sellPrice = cost;
    }


    //use() assumes damage items heal for 0 and healing items do 0 damage
    public void use(PlayerStatus currentPlayer, Consumable item, ArrayList<Item> generalItems) {
        for (Item item1 : generalItems){
            if (item1.equals(item)){
                currentPlayer.setPlayerDamageAmp(item.getDamageAmpAmt());
                int currentHealth = currentPlayer.getPlayerHealth();
                currentPlayer.setPlayerHealth(currentHealth + item.getHealAmt());
                break;
            }
        }
    }

    public int getDamageAmpAmt() {
        return damageAmpAmt;
    }

    public int getHealAmt() {
        return healAmt;
    }
}
