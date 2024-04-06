package command.inventory;

import command.Command;

public class PrevPageCommand extends Command {
    @Override
    public void execute() {
        playerStatus.getPlayerInventory().listPreviousInventoryPage();
    }
}
