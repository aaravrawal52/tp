package InventoryItems;

public abstract class ShopItem {
    protected String description;
    protected String name;

    protected int price;

    public ShopItem(){

    }

    public int getPrice() {
        return price;
    }

    public String getDescription(){
        return description;
    }

    public String getName(){
        return name;
    }
}
