package map;

import inventoryitems.Consumable;
import inventoryitems.Gear;
import inventoryitems.Item;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;

import java.util.ArrayList;

public class PlayerInventory extends BaseMap {
    protected static final int FIRST_PAGE_INDEX = 0;
    protected static final int LAST_PAGE_INDEX = 2;
    protected ArrayList<ArrayList<Item>> allItemsList;
    protected ArrayList<String> inventoryNames;
    protected ArrayList<Item> generalItems;
    protected ArrayList<Item> consumableItems;
    protected ArrayList<Item> equippableItems;
    protected int currentItemPageNumber;
    protected Ui ui;
    protected TextBox currentTextBox;
    protected PlayerStatus playerStatus;

    public PlayerInventory() {
        this.currentItemPageNumber = 0;
        this.generalItems = new ArrayList<>();
        this.consumableItems = new ArrayList<>();
        this.equippableItems = new ArrayList<>();
        this.allItemsList = new ArrayList<>();
        this.inventoryNames = new ArrayList<>();
        allItemsList.add(generalItems);
        allItemsList.add(consumableItems);
        allItemsList.add(equippableItems);
        inventoryNames.add("General");
        inventoryNames.add("Consumable");
        inventoryNames.add("Equipment");
        this.ui = new Ui();
        width = 59;
        height = 8;
    }

    public void resetInventoryPage() {
        currentItemPageNumber = FIRST_PAGE_INDEX;
    }

    public void listNextInventoryPage() {
        int size = allItemsList.size();
        if (currentItemPageNumber + 1 >= size) {
            resetInventoryPage();
        } else {
            currentItemPageNumber += 1;
        }
    }

    public void listPreviousInventoryPage() {
        if (currentItemPageNumber <= 0) {
            currentItemPageNumber = LAST_PAGE_INDEX;
        } else {
            currentItemPageNumber -= 1;
        }
    }

    public void addItems(Item item) {
        if (generalItems.isEmpty()) {
            generalItems.add(item);
            return;
        }
        int indexOfItem = generalItems.indexOf(item);
        if (indexOfItem < 0) {
            generalItems.add(item);
            return;
        }
        generalItems.get(indexOfItem).addQuantity(item.getQuantity());
    }

    public void addItems(Consumable item) {
        if (consumableItems.isEmpty()) {
            consumableItems.add(item);
            return;
        }
        int indexOfItem = consumableItems.indexOf(item);
        if (indexOfItem < 0) {
            consumableItems.add(item);
            return;
        }
        consumableItems.get(indexOfItem).addQuantity(item.getQuantity());
    }

    public void addItems(Gear item) {
        if (equippableItems.isEmpty()) {
            equippableItems.add(item);
            return;
        }
        int indexOfItem = equippableItems.indexOf(item);
        if (indexOfItem < 0) {
            equippableItems.add(item);
            return;
        }
        equippableItems.get(indexOfItem).addQuantity(item.getQuantity());
    }

    public void useItem(Consumable item) {
        item.use(playerStatus, item, generalItems);
        int leftover = item.getQuantity() - 1;
        if (leftover <= 0) {
            consumableItems.remove(item);
        } else {
            item.setQuantity(item.getQuantity() - 1);
        }
    }

    public void sellItem(Item item) {
        try {
            if (item.getSellPrice() == 0) {
                currentTextBox.setNextError("This item cannot be sold");
                return;
            }
        } catch (Exception e) {
            currentTextBox.setNextError("This item cannot be sold");
        }
        ArrayList<Item> list = allItemsList.get(currentItemPageNumber);
        int leftover = item.getQuantity() - 1;
        if (leftover <= 0) {
            list.remove(item);
        } else {
            item.setQuantity(item.getQuantity() - 1);
        }
        playerStatus.addMoney(item.getSellPrice());
        currentTextBox.setNextDialogue("Congrats, you just sold a " + item.getName() + " for $" + item.getSellPrice());
    }

    public void loadInventory() { // for when loading game aft save

    }

    public ArrayList<Item> getGeneralItems() {
        return generalItems;
    }

    public void setGeneralItems(ArrayList<Item> items) {
        this.generalItems = items;
    }

    public ArrayList<Item> getConsumableItems() {
        return consumableItems;
    }

    public void setConsumableItems(ArrayList<Item> consumableItems) {
        this.consumableItems = consumableItems;
    }

    public ArrayList<Item> getEquippableItems() {
        return equippableItems;
    }

    public void setEquippableItems(ArrayList<Item> equippableItems) {
        this.equippableItems = equippableItems;
    }

    public ArrayList<ArrayList<Item>> getAllItemsList() {
        return allItemsList;
    }

    public int getCurrentItemPageNumber() {
        return currentItemPageNumber;
    }

    public void setCurrentItemPageNumber(int currentItemPageNumber) {
        this.currentItemPageNumber = currentItemPageNumber;
    }

    public ArrayList<String> getInventoryNames() {
        return inventoryNames;
    }

    public void setInventoryNames(ArrayList<String> inventoryNames) {
        this.inventoryNames = inventoryNames;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public void setCurrentTextBox(TextBox currentTextBox) {
        this.currentTextBox = currentTextBox;
    }
    @Override
    public void enableFight() {

    }

    @Override
    public boolean getEntityDeath() {
        return false;
    }

    @Override
    public boolean getPlayerDeath() {
        return false;
    }

    @Override
    public void handleDeath() {

    }

    @Override
    public void handleLootingByPlayer() {

    }
}
