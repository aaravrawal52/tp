package command.inventory;

import command.Command;
import inventoryitems.Consumable;
import inventoryitems.Item;
import map.PlayerInventory;

import static java.lang.Integer.parseInt;

public class UseCommand extends Command {
    String userCommand;

    public UseCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    @Override
    public void execute() {
        if (playerStatus.getPlayerInventory().getGeneralItems().isEmpty()) {
            textBox.setNextError("The item does not exist");
            return;
        }
        String itemString = "";
        int itemIndex;
        try {
            itemString = userCommand.split(" ", 2)[1];
            itemIndex = parseInt(itemString);
        } catch (ArrayIndexOutOfBoundsException e) {
            textBox.setNextError("Please enter an item index");
            return;
        } catch (NumberFormatException e) {
            textBox.setNextError("Please enter a valid integer for the item index");
            return;
        }
        Item item;
        PlayerInventory inventory = playerStatus.getPlayerInventory();
        try {
            item = inventory.getGeneralItems().get(itemIndex - 1);
        } catch (Exception e) {
            textBox.setNextError("The item does not exist");
            return;
        }
        inventory.useItem((Consumable) item);
    }
}
