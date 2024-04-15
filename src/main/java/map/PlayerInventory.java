package map;

import inventoryitems.Consumable;
import inventoryitems.Item;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerInventory extends BaseMap {
    protected ArrayList<String> inventoryNames;
    protected ArrayList<Item> generalItems;
    protected Ui ui;
    protected TextBox currentTextBox;
    protected PlayerStatus playerStatus;

    public PlayerInventory() {
        this.generalItems = new ArrayList<>();
        this.inventoryNames = new ArrayList<>();
        inventoryNames.add("General");
        this.ui = new Ui();
        width = 59;
        height = 8;
    }

    public void loadInventory(Consumable item){
        generalItems.add(item);
    }

    public void addItems(Consumable item) {
        if (generalItems.isEmpty()) {
            item.setQuantity(1);
            generalItems.add(item);
            return;
        }
        List<Item> filteredList = generalItems.stream().filter(x -> x.getName().equalsIgnoreCase(
                item.getName())).collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            item.setQuantity(1);
            generalItems.add(item);
            return;
        }
        filteredList.get(0).addQuantity(1);
    }

    public void useItem(Consumable item) {
        item.use(playerStatus, item, generalItems);
        int leftover = item.getQuantity() - 1;
        if (leftover <= 0) {
            generalItems.remove(item);
        } else {
            item.setQuantity(item.getQuantity() - 1);
        }
        currentTextBox.setNextDialogue(item.getName() + " has been used.");
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
        int leftover = item.getQuantity() - 1;
        if (leftover <= 0) {
            generalItems.remove(item);
        } else {
            item.setQuantity(item.getQuantity() - 1);
        }
        playerStatus.addMoney(item.getSellPrice());
        currentTextBox.setNextDialogue("Congrats, you just sold a " + item.getName() + " for $" + item.getSellPrice());
    }

    public ArrayList<Item> getGeneralItems() {
        return generalItems;
    }

    public void setGeneralItems(ArrayList<Item> items) {
        this.generalItems = items;
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
