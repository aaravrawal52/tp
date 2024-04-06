package command.inventory;

import command.Command;
import map.BaseMap;

import static map.BaseMap.mapIndex;
import static map.MapGenerator.FIRST_MAP_IDENTITY;

public class CloseInventoryCommand extends Command {

    public CloseInventoryCommand() {

    }
    @Override
    public void execute() {
        BaseMap.currentMap = mapIndex.get(FIRST_MAP_IDENTITY);
    }
}
