package map;

import interactable.InteractableEntity;
import interactable.ShopKeeper;
import filereader.FileReader;
import textbox.PlayerStatus;
import textbox.TextBox;

import java.util.ArrayList;

public class ShopMap extends BaseMap{
    protected PlayerStatus currentPlayer;
    protected TextBox currentTextBox;
    protected ShopKeeper currentEntity;

    public ShopMap(PlayerStatus player, TextBox text, ShopKeeper entity){
        this.currentPlayer = player;
        this.currentTextBox = text;
        this.currentEntity = entity;
    }

    public void initMap(int givenWidth, int givenHeight) { //copied from battle interface
        this.width = givenWidth;
        this.height = givenHeight;
        this.currentMap = new ArrayList<>(height);

        FileReader fileReader = new FileReader(currentEntity.getFilePath());
        try {
            currentMap = fileReader.readEnemyDesign();
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
    public void fightLoop() {

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
