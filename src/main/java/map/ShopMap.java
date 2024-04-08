package map;

import interactable.ShopKeeper;
import filereader.FileReader;
import inventoryitems.ShopItem;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;


public class ShopMap extends BaseMap{
    protected PlayerStatus currentPlayer;
    protected TextBox currentTextBox;
    protected ShopKeeper currentEntity;
    protected PlayerInventory inventory;

    public ShopMap(PlayerStatus player, TextBox text, ShopKeeper shopKeeper, PlayerInventory bag){
        this.currentPlayer = player;
        this.currentTextBox = text;
        this.currentEntity = shopKeeper;
        this.inventory = bag;
        //this.currentMap = new ArrayList<>(height);
        //loadShopMap();
    }
    @Override
    public void initMap(int givenWidth, int givenHeight) {
        this.width = givenWidth;
        this.height = givenHeight;

        this.mapData = new ArrayList<>(height);

        FileReader fileReader = new FileReader(currentEntity.getFilePath());
        try {
            mapData = fileReader.readShopMapDesign();
        } catch (Exception e) {
            // display exception, see how sihan wants to do.
        }
    }


    public void queueTextBox(){
        currentTextBox.setNextNarration("You are greeted by a cat with oddly small eyes.\n");
        currentTextBox.setNextDialogue(currentEntity.getDefaultMessage() + "\n" + currentEntity.formatShop());
        currentTextBox.setNextInstruction("Give the shop keeper an [INDEX] to view the item and purchase" +
                " or enter [exit]" +
                " to leave the shop.");
    }



    public void enableFight(){

    }


    @Override
    public void enableFight(Scanner in) {
        String answerCommand = "";
        Ui ui = new Ui();
        queueTextBox();
        while (!answerCommand.equalsIgnoreCase("exit")) {

            ui.printPlayerStatus(currentPlayer);
            ui.printShopKeeper(currentEntity);
            ui.printTextBox(currentTextBox);

            answerCommand = in.nextLine().trim();
            answerCommand = (answerCommand.length() > 10) ? answerCommand.substring(0, 10) : answerCommand;
            // Check if the input is numeric
            if (answerCommand.matches("\\d+")) {
                int index = Integer.parseInt(answerCommand) - 1;
                ArrayList<ShopItem> shopItems = currentEntity.getShopItems();

                if (index >= 0 && index < shopItems.size()) {
                    ShopItem item = shopItems.get(index);
                    if (currentPlayer.getPlayerMoney() >= item.getPrice()) {
                        int currentMoney = currentPlayer.getPlayerMoney();
                        currentPlayer.setPlayerMoney(currentMoney - item.getPrice());
                        currentTextBox.setNextNarration("NEW ITEM ADDED TO INVENTORY");
                        inventory.addItems(item);
                    } else {
                        currentTextBox.setNextNarration("The cat silently judged your broke ass.\n");
                    }
                } else {
                    currentTextBox.setNextError("Invalid index. Please enter a valid item index or 'exit'.");
                }
            } else if (!answerCommand.equalsIgnoreCase("exit")) {
                currentTextBox.setNextError("Invalid command. Please enter a valid item index or 'exit'.");
            }

            currentTextBox.setNextDialogue(currentEntity.getDefaultMessage() + "\n" + currentEntity.formatShop());
            currentTextBox.setNextInstruction("Give the shopkeeper an [INDEX] to view the item and purchase or " +
                    "enter [exit]" +
                    " to leave the shop.");
        }
        currentTextBox.clearAll();
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
