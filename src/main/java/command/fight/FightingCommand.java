package command.fight;
import command.Command;
import map.BaseMap;

import java.util.Scanner;

import static map.BaseMap.mapIndex;
import static map.BaseMap.storedMaps;
import static map.MapGenerator.FIRST_MAP_IDENTITY;


public class FightingCommand extends Command {
    public FightingCommand() {
        commandDescription = "FIGHT!";
    }

    @Override
    public void execute() {
    }

    @Override
    public void execute(Scanner in) {
        currentMap.enableFight(in);
        BaseMap.currentMap = mapIndex.get(FIRST_MAP_IDENTITY);
        if (currentMap.getEntityDeath()){
            int xPos = storedMaps.get(BaseMap.currentMap).getInteractX();
            int yPos = storedMaps.get(BaseMap.currentMap).getInteractY();
            storedMaps.get(BaseMap.currentMap).clearSpot(xPos, yPos);
            currentMap.handleLootingByPlayer();
        } else if (currentMap.getPlayerDeath()){
            currentMap.handleDeath();
        }
    }
}
