package command.fight;

import command.Command;
import map.BaseMap;
import map.battleinterface.BattleInterface;
import map.ShopMap;

import static map.BaseMap.storedMaps;
import static map.MapGenerator.FIRST_MAP_IDENTITY;
import static map.BaseMap.mapIndex;

public class RunningCommand extends Command {
    public RunningCommand() {
        commandDescription = "RUN!";
    }
    @Override
    public void execute(){
        if(currentMapForCommand instanceof BattleInterface) {
            textBox.setNextNarration("You decide to run and successfully got away");
            BaseMap.currentMap = mapIndex.get(FIRST_MAP_IDENTITY);
            currentMapForCommand = storedMaps.get(BaseMap.currentMap);
        } else if(currentMapForCommand instanceof ShopMap){
            textBox.setNextNarration("You exited the shop!!");
            BaseMap.currentMap = mapIndex.get(FIRST_MAP_IDENTITY);
            currentMapForCommand = storedMaps.get(BaseMap.currentMap);
        }
    }
}
