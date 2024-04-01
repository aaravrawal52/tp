package main;


import Hint.HintHandler;
import command.Command;
import map.BaseMap;
import map.FirstMap;
import map.BattleInterface.BattleInterface;
import map.ShopMap;
import parser.Parser;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;
import map.MapGenerator;


public class CalculaChroniclesOfTheAlgorithmicKingdom {
    public static int currentOn;
    public static ArrayList<BaseMap> storedMaps = new ArrayList<>();

    public static void main(String[] args) {
        new CalculaChroniclesOfTheAlgorithmicKingdom().startGame();
    }

    public void startGame() {
        Scanner in = new Scanner(System.in);

        PlayerStatus playerStatus = new PlayerStatus(100, 0, 0);
        TextBox textBox = new TextBox();
        Parser parser = new Parser();
        BaseMap map = new FirstMap();
        Ui ui = new Ui();
        HintHandler hints = new HintHandler(map, textBox);

        MapGenerator.getInstance().generateMap(map);
        textBox.initTextBox();
        currentOn = 0;
        storedMaps.add(map); //map for player traversal index 0
        storedMaps.add(new BattleInterface(null, null, null)); //map for battles to take place index 1
        storedMaps.add(new ShopMap(null, null, null)); //map for shop index 2
        assert storedMaps.size() == 3;

        ui.printPlayerStatus(playerStatus);
        ui.printMap(storedMaps.get(currentOn));
        ui.printTextBox(textBox);

        Command userCommand;
        do {
            String userCommandText = in.nextLine();
            hints.checkMapThenDisplayHint(); //handles invisible map triggers for hints
            userCommand = parser.parseCommand(userCommandText);
            setUserCommand(userCommand, storedMaps.get(currentOn), playerStatus, textBox);

            executeCommand(userCommand, in);

            printMessageUnderMap(userCommand, ui, playerStatus, textBox);

        } while (!userCommand.getCommandDescription().equals("TIRED"));
    }

    private static void printMessageUnderMap(Command userCommand, Ui ui, PlayerStatus playerStatus, TextBox textBox) {
        if (!userCommand.getCommandDescription().equals("HelpMe!!") &&
                !userCommand.getCommandDescription().equals("TIRED")) {
            ui.printPlayerStatus(playerStatus);
            if (storedMaps.get(currentOn) instanceof BattleInterface) {
                ui.printEnemy(storedMaps.get(currentOn));
            } else {
                ui.printMap(storedMaps.get(currentOn));
            }
            ui.printTextBox(textBox);
        }
    }

    private static void executeCommand(Command userCommand, Scanner in) {
        if (userCommand.getCommandDescription().equals("FIGHT!")) {
            userCommand.execute(in);
        } else {
            userCommand.execute();
        }
    }

    private static void setUserCommand(Command userCommand, BaseMap map, PlayerStatus playerStatus, TextBox textBox) {
        userCommand.setCurrentMap(map);
        userCommand.setPlayerStatus(playerStatus);
        userCommand.setTextBox(textBox);
    }
}
