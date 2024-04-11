package command.inventory;

import command.Command;
import inventoryitems.Item;
import map.PlayerInventory;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class SellCommand extends Command {
    String userCommand;

    public SellCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    public void findItem(String item, ArrayList<Item> itemList) {
        PlayerInventory inventory = playerStatus.getPlayerInventory();
        Item itemToSell;
        try {
            itemToSell = itemList.stream().filter(x -> x.getName().equalsIgnoreCase(item))
                    .collect(Collectors.toList()).get(0);
            inventory.sellItem(itemToSell);
        } catch (Exception e) {
            textBox.setNextError("Please enter a valid item number or item name");
        }
    }

    @Override
    public void execute() {
        PlayerInventory inventory = playerStatus.getPlayerInventory();
        ArrayList<Item> list = playerStatus.getPlayerInventory().getGeneralItems();
        if (list.isEmpty()) {
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
            findItem(itemString, list);
            return;
        } catch (IndexOutOfBoundsException e) {
            textBox.setNextError("The item does not exist");
            return;
        }
        Item item;
        try {
            item = list.get(itemIndex - 1);
        } catch (Exception e) {
            textBox.setNextError("The item does not exist");
            return;
        }
        inventory.sellItem(item);
    }
}
