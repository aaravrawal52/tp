package map;

import interactable.ShopKeeper;
import filereader.FileReader;
import textbox.PlayerStatus;
import textbox.TextBox;

import java.util.ArrayList;
import InventoryItems.ShopItem;


public class ShopMap extends BaseMap{
    protected PlayerStatus currentPlayer;
    protected TextBox currentTextBox;
    protected ShopKeeper currentEntity;

    public ShopMap(PlayerStatus player, TextBox text, ShopKeeper shopKeeper){
        this.currentPlayer = player;
        this.currentTextBox = text;
        this.currentEntity = shopKeeper;
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
            mapData = fileReader.readEnemyDesign();
        } catch (Exception e) {
            // display exception, see how sihan wants to do.
        }
    }


    public void queueTextBox(){
        currentTextBox.setNextNarration("Welcome to " + currentEntity.getName() + "'s shop");
        currentTextBox.setNextDialogue(currentEntity.getDefaultMessgage() + "\n" + currentEntity.formatShop());
        currentTextBox.setNextInstruction("Enter the index of the item you wish to purchase");
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
