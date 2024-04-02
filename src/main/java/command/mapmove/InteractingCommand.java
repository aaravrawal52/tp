package command.mapmove;

import interactable.InteractableEntity;
import interactable.ShopKeeper;
import interactable.enemies.Centaur;
import map.BaseMap;
import map.ShopMap;
import map.BattleInterface.BattleInterface;

import java.util.Objects;

import static main.CalculaChroniclesOfTheAlgorithmicKingdom.storedMaps;
import static main.CalculaChroniclesOfTheAlgorithmicKingdom.currentOn;

public class InteractingCommand extends MapMoveCommand {

    public InteractingCommand(){
        commandDescription = "interact";
    }
    @Override
    public void execute() {
        String entityInteractedWith = currentMap.handleInteract();
        if (Objects.equals(entityInteractedWith, "no interaction")){
            textBox.setNextNarration("Nothing to interact with here");
        }
        else if (Objects.equals(entityInteractedWith, "@")){
            textBox.setNextNarration(entityInteractedWith + " appears in your path. What will you do?");
            textBox.setNextInstruction("Will you [fight] or will you [run]?");
        }

        BaseMap battleMap;
        switch (entityInteractedWith) {
        case "@": //centaur
            int xPos = currentMap.getInteractX();
            int yPos = currentMap.getInteractY();
            InteractableEntity monster = new Centaur(10, 10, 10, xPos, yPos, 10, 10);
            textBox.setNextDialogue("*the " + monster.getName() + " stares at you menacingly*");
            battleMap = new BattleInterface(playerStatus, textBox, monster);
            battleMap.initMap(30, 0);
            currentOn = 1;
            storedMaps.set(currentOn, battleMap);
            break;
        case "#":  //some shopkeeper
            ShopMap shopMap;
            ShopKeeper shopkeeper = new ShopKeeper("src/main/resources/ShopKeeper/ShopKeeper", "Hi welcome to my shop!");
            shopMap = new ShopMap(playerStatus, textBox, shopkeeper);
            shopMap.initMap(30, 0); // Set appropriate width and height
            currentOn = 2;
            storedMaps.set(currentOn, shopMap);

            //((ShopMap) battleMap).interact(textBox); // Interact with the shop
            textBox.setNextNarration(shopkeeper.getDefaultMessgage());
            break;

        default:
            battleMap = new BattleInterface(null, null, null);
            break;
        }

    }
}
