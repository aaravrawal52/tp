package command.fight;
import command.Command;
import command.ResetCommand;
import map.BaseMap;
import map.ShopMap;

import java.io.FileNotFoundException;
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
    public void execute(Scanner in) throws FileNotFoundException {
        if (currentMapForCommand instanceof map.BattleInterface.BattleInterface) {
            currentMapForCommand.enableFight(in);
            BaseMap.currentMap = mapIndex.get(FIRST_MAP_IDENTITY);
            if (currentMapForCommand.getEntityDeath()) {
                int xPos = storedMaps.get(BaseMap.currentMap).getInteractX();
                int yPos = storedMaps.get(BaseMap.currentMap).getInteractY();
                storedMaps.get(BaseMap.currentMap).clearSpot(xPos, yPos);
                currentMapForCommand.handleLootingByPlayer();
            } else if (currentMapForCommand.getPlayerDeath()) {
                currentMapForCommand.handleDeath();
                Command deathReset = new ResetCommand();
                deathReset.execute(playerStatus);
            }
        }

        if (currentMapForCommand instanceof ShopMap) {
            currentMapForCommand.enableFight(in);
            BaseMap.currentMap = mapIndex.get(FIRST_MAP_IDENTITY);
        }
    }
}
