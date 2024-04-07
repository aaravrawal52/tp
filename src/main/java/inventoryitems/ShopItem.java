package inventoryitems;

public abstract class ShopItem extends Item{
    protected int price;

    public ShopItem() {

    }

    public int getPrice() {
        return price;
    }
}
