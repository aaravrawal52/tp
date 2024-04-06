package command.inventory;

import command.Command;
import map.BaseMap;

import static map.BaseMap.mapIndex;
import static map.MapGenerator.INVENTORY_IDENTITY;

public class OpenInventoryCommand extends Command {
    public OpenInventoryCommand() {
        commandDescription = "Inventory";
    }

    @Override
    public void execute() {
        BaseMap.currentMap = mapIndex.get(INVENTORY_IDENTITY);
        textBox.setNextNarration("Here's your inventory. Navigate the pages using [next] or [prev]");
    }
}
