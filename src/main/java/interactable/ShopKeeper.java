package interactable;

import inventoryitems.Consumable;
import inventoryitems.ShopItem;

import java.util.ArrayList;

public class ShopKeeper extends InteractableEntity{
    protected ArrayList<ShopItem> shopItems;
    protected String defaultMessage; //whatever the guy says to introduce his items
    protected String filePath;


    public ShopKeeper(String filePathway, String message){
        //addConsumable();
        this.filePath = filePathway;
        this.defaultMessage = message;
        this.shopItems = new ArrayList<>();
    }

    public ArrayList<ShopItem> getShopItems() {
        return shopItems;
    }

    public void addConsumable(int heal, int damage, String itemDescription, String itemName, int price){
        Consumable newConsumable = new Consumable(heal, damage, itemDescription, itemName, price);
        shopItems.add(newConsumable);
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }


    public String formatShop() {
        if (shopItems != null) {
            StringBuilder formattedList = new StringBuilder();
            for (int i = 0; i < shopItems.size(); i += 1) {
                ShopItem item = shopItems.get(i);
                formattedList.append(i + 1).append(". ")
                        .append(item.getName())
                        .append(" - ")
                        .append(item.getDescription())
                        .append(" - ")
                        .append(" $")
                        .append(item.getSellPrice())
                        .append("\n");
            }
            return formattedList.toString();
        }
        return "The shop is empty";
    }


    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public int getDefence() {
        return 0;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

}
