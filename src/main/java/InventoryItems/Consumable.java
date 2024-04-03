package inventoryitems;

public class Consumable extends ShopItem { //we assume all consumables are for 1 turn only
    protected int healAmt;
    protected int damageAmpAmt;

    public Consumable(int heal, int damage, String itemDescription, String itemName, int cost){
        super.description = itemDescription;
        healAmt = heal;
        damageAmpAmt = damage;
        super.name = itemName;
        super.price = cost;
    }

    public int getDamageAmpAmt() {
        return damageAmpAmt;
    }

    public int getHealAmt() {
        return healAmt;
    }
}
