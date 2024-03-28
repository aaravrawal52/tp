package main;

import Hint.HintHandler;
import command.Command;
import map.*;
import map.BattleInterface.BattleInterface;
import parser.Parser;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;


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

        map.initMap(30, 10);
        map.initPlayerLocation(0, 0);
        map.placeMonsterInTheMap(2, 3);
        textBox.initTextBox();
        currentOn = 0;
        storedMaps.add(map);
        assert storedMaps.size() == 1;

        ui.printPlayerStatus(playerStatus);
        ui.printMap(storedMaps.get(currentOn));
        ui.printTextBox(textBox);

        Command userCommand;
        do {
            String userCommandText = in.nextLine();
            hints.checkMapThenDisplayHint(); //handles invisible map triggers for hints
            userCommand = parser.parseCommand(userCommandText);
            setUserCommand(userCommand, storedMaps.get(currentOn), playerStatus, textBox);

            if (userCommand.getCommandDescription().equals("FIGHT!")) {
                userCommand.execute(in);
            } else {
                userCommand.execute();
            }

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

        } while (!userCommand.getCommandDescription().equals("TIRED"));
    }

    private static void setUserCommand(Command userCommand, BaseMap map, PlayerStatus playerStatus, TextBox textBox) {
        userCommand.setCurrentMap(map);
        userCommand.setPlayerStatus(playerStatus);
        userCommand.setTextBox(textBox);
    }
}
