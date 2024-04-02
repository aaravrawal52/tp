package command.mapmove;

import interactable.InteractableEntity;
import interactable.ShopKeeper;
import interactable.enemies.*;
import map.BaseMap;
import map.ShopMap;
import map.BattleInterface.BattleInterface;

import java.util.Objects;

import static map.BaseMap.*;
import static map.MapGenerator.*;
import ui.Ui;

public class InteractingCommand extends MapMoveCommand {

    public InteractingCommand() {
        commandDescription = "interact";
    }

    @Override
    public void execute() {
        String entityInteractedWith = currentMap.handleInteract();
        if (Objects.equals(entityInteractedWith, "no interaction")) {
            textBox.setNextNarration("Nothing to interact with here");
        } else {
            textBox.setNextNarration(entityInteractedWith + " appears in your path. What will you do?");
            textBox.setNextInstruction("Will you [fight] or will you [run]?");
        }

        char entity = entityInteractedWith.charAt(0);
        BaseMap battleMap;
        InteractableEntity monster;
        int xPos = currentMap.getInteractX();
        int yPos = currentMap.getInteractY();

        switch (entity) {
        case CENTAUR:
            monster = new Centaur(10, 10, 10, xPos, yPos, 10, 10);
            textBox.setNextDialogue("*the " + monster.getName() + " stares at you menacingly*");
            battleMap = new BattleInterface(playerStatus, textBox, monster);
            battleMap.initMap(30, monster.getHeight());
            storedMaps.add(battleMap);
            mapIndex.put(CENTAUR, storedMaps.size() - 1);
            currentOn = mapIndex.get(CENTAUR);
            break;
        case DEMON:
            monster = new Demon(15, 15, 15, xPos, yPos, 15, 15);
            textBox.setNextDialogue("*the " + monster.getName() + " stares at you menacingly*");
            battleMap = new BattleInterface(playerStatus, textBox, monster);
            battleMap.initMap(30, monster.getHeight());
            storedMaps.add(battleMap);
            mapIndex.put(DEMON, storedMaps.size() - 1);
            currentOn = mapIndex.get(DEMON);
            break;
        case DRAGON:
            monster = new Dragon(20, 20, 20, xPos, yPos, 20, 20);
            textBox.setNextDialogue("*the " + monster.getName() + " stares at you menacingly*");
            battleMap = new BattleInterface(playerStatus, textBox, monster);
            battleMap.initMap(30, monster.getHeight());
            storedMaps.add(battleMap);
            mapIndex.put(DRAGON, storedMaps.size() - 1);
            currentOn = mapIndex.get(DRAGON);
            break;
        case GOBLIN:
            monster = new Goblin(25, 25, 25, xPos, yPos, 25, 25);
            textBox.setNextDialogue("*the " + monster.getName() + " stares at you menacingly*");
            battleMap = new BattleInterface(playerStatus, textBox, monster);
            battleMap.initMap(30, monster.getHeight());
            storedMaps.add(battleMap);
            mapIndex.put(GOBLIN, storedMaps.size() - 1);
            currentOn = mapIndex.get(GOBLIN);
            break;
        case GRYPHON:
            monster = new Gryphon(30, 30, 30, xPos, yPos, 30, 30);
            textBox.setNextDialogue("*the " + monster.getName() + " stares at you menacingly*");
            battleMap = new BattleInterface(playerStatus, textBox, monster);
            battleMap.initMap(30, monster.getHeight());
            storedMaps.add(battleMap);
            mapIndex.put(GRYPHON, storedMaps.size() - 1);
            currentOn = mapIndex.get(GRYPHON);
            break;
        case SHOP:  //some shopkeeper
            ShopKeeper shopkeeper = new ShopKeeper("resources/ShopKeeper/ShopKeeper", "Hi welcome to my shop!");
            currentMap = new ShopMap(playerStatus, textBox, shopkeeper);
            storedMaps.add(currentMap);
            mapIndex.put(SHOP, storedMaps.size() - 1);
            currentOn = mapIndex.get(SHOP);
            break; //not done yet
        default:
            battleMap = new BattleInterface(null, null, null);
            break;
        }
    }
}
