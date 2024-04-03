package main;


import Hint.HintHandler;
import command.Command;
import map.BaseMap;
import map.FirstMap;
import map.BattleInterface.BattleInterface;
import parser.Parser;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;

import java.util.Scanner;

import map.MapGenerator;
import static map.BaseMap.mapIndex;
import static map.BaseMap.storedMaps;
import static map.BaseMap.currentMap;
import static map.MapGenerator.FIRST_MAP_IDENTITY;


public class CalculaChroniclesOfTheAlgorithmicKingdom {

    public static final int START_HEALTH = 100;
    public static final int START_MONEY = 0;
    public static final int START_EXP = 0;

    public static void main(String[] args) {
        new CalculaChroniclesOfTheAlgorithmicKingdom().startGame();
    }

    public void startGame() {
        Scanner in = new Scanner(System.in);

        PlayerStatus playerStatus = new PlayerStatus(START_HEALTH, START_MONEY, START_EXP);
        TextBox textBox = new TextBox();
        Parser parser = new Parser();
        BaseMap map = new FirstMap();
        Ui ui = new Ui();
        HintHandler hints = new HintHandler(map, textBox);

        MapGenerator.getInstance().generateMap(map);
        textBox.initTextBox();

        storedMaps.add(map);
        mapIndex.put(FIRST_MAP_IDENTITY, storedMaps.size() - 1);
        currentMap = mapIndex.get(FIRST_MAP_IDENTITY);


        ui.printPlayerStatus(playerStatus);
        ui.printMap(storedMaps.get(currentMap));
        ui.printTextBox(textBox);

        Command userCommand;
        do {
            String userCommandText = in.nextLine();
            hints.checkMapThenDisplayHint(); //handles invisible map triggers for hints
            userCommand = parser.parseCommand(userCommandText);
            setUserCommand(userCommand, storedMaps.get(currentMap), playerStatus, textBox);

            executeCommand(userCommand, in);

            printMessageUnderMap(userCommand, ui, playerStatus, textBox);

        } while (!userCommand.getCommandDescription().equals("TIRED"));
    }

    private static void printMessageUnderMap(Command userCommand, Ui ui, PlayerStatus playerStatus, TextBox textBox) {
        if (!userCommand.getCommandDescription().equals("HelpMe!!") &&
                !userCommand.getCommandDescription().equals("TIRED")) {
            ui.printPlayerStatus(playerStatus);
            if (storedMaps.get(currentMap) instanceof BattleInterface) {
                ui.printEnemy(storedMaps.get(currentMap));
            } else {
                ui.printMap(storedMaps.get(currentMap));
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
