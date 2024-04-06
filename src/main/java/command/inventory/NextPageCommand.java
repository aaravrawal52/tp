package command.inventory;

import command.Command;

public class NextPageCommand extends Command {
    @Override
    public void execute() {
        playerStatus.getPlayerInventory().listNextInventoryPage();
    }
}
