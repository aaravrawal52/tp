package command.mapmove;

import command.Command;
import map.BaseMap;
import map.ShopMap;

import static map.BaseMap.storedMaps;
import static map.MapGenerator.FIRST_MAP_IDENTITY;
import static map.BaseMap.mapIndex;

public class ExitShop extends Command {
    public ExitShop() {
        commandDescription = "RUN!";
    }
    @Override
    public void execute(){
        if(currentMapForCommand instanceof ShopMap) {
            textBox.setNextNarration("You exited the shop!!");
            BaseMap.currentMap = mapIndex.get(FIRST_MAP_IDENTITY);
            currentMapForCommand = storedMaps.get(BaseMap.currentMap);
        }
    }
}
