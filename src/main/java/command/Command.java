package command;

import textbox.PlayerStatus;
import textbox.TextBox;
import map.BaseMap;

import java.io.FileNotFoundException;
import java.util.Scanner;


public abstract class Command {
    protected TextBox textBox;
    protected PlayerStatus playerStatus;
    protected String commandDescription;
    protected BaseMap currentMapForCommand;

    public Command() {
        commandDescription = "Impossible";
        currentMapForCommand = null;
    }

    public abstract void execute();

    public void execute(Scanner in) throws FileNotFoundException {
    }

    public void execute(PlayerStatus playerStatus) {
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public BaseMap getCurrentMapForCommand() {
        return currentMapForCommand;
    }

    public void setCurrentMapForCommand(BaseMap givenMap) {
        currentMapForCommand = givenMap;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public void setTextBox(TextBox textBox) {
        this.textBox = textBox;
    }
}
