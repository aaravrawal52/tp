package command.inventory;

import command.Command;
import inventoryitems.Consumable;
import inventoryitems.Item;
import map.PlayerInventory;

import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class UseCommand extends Command {
    String userCommand;

    public UseCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    public void findItem(String item) {
        PlayerInventory inventory = playerStatus.getPlayerInventory();
        Item consumable;
        try {
            consumable = inventory.getConsumableItems().stream().filter(x -> x.getName().equalsIgnoreCase(item))
                    .collect(Collectors.toList()).get(0);
            inventory.useItem((Consumable) consumable);
        } catch (Exception e) {
            textBox.setNextError("Please enter a valid item number or item name");
        }
    }

    @Override
    public void execute() {
        if (playerStatus.getPlayerInventory().getInventoryNames().indexOf("Consumable") !=
                playerStatus.getPlayerInventory().getCurrentItemPageNumber()) {
            textBox.setNextInstruction("Enter [next] or [prev] to navigate the inventory page.");
            textBox.setNextError("You only can use an item in the consumables page");
            return;
        }
        if (playerStatus.getPlayerInventory().getConsumableItems().isEmpty()) {
            textBox.setNextError("The item does not exist");
            return;
        }
        String itemString = "";
        int itemIndex;
        try {
            itemString = userCommand.split(" ", 2)[1];
            itemIndex = parseInt(itemString);
        } catch (ArrayIndexOutOfBoundsException e) {
            textBox.setNextError("Please enter an item number or item name");
            return;
        } catch (NumberFormatException e) {
            findItem(itemString);
            return;
        }
        Item item;
        PlayerInventory inventory = playerStatus.getPlayerInventory();
        try {
            item = inventory.getConsumableItems().get(itemIndex - 1);
        } catch (Exception e) {
            textBox.setNextError("The item does not exist");
            return;
        }
        inventory.useItem((Consumable) item);
    }
}
