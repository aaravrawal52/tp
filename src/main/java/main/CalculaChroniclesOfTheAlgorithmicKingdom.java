package main;


import filereader.InventoryItemsStorage;
import filereader.MapStorage;
import filereader.PlayerStatusStorage;
import hint.HintHandler;
import command.Command;
import map.BaseMap;
import map.ShopMap;
import map.PlayerInventory;
import map.battleinterface.BattleInterface;
import parser.Parser;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static map.BaseMap.mapIndex;
import static map.BaseMap.storedMaps;
import static map.BaseMap.currentMap;
import static map.MapGenerator.FIRST_MAP_IDENTITY;
import static map.MapGenerator.INVENTORY_IDENTITY;


public class CalculaChroniclesOfTheAlgorithmicKingdom {

    public static final int START_HEALTH = 100;
    public static final int START_MONEY = 200;
    public static final int START_EXP = 0;
    public static final int START_DAMAGE = 5;
    public static final PlayerInventory PLAYER_INVENTORY = new PlayerInventory();

    public static void main(String[] args) {
        new CalculaChroniclesOfTheAlgorithmicKingdom().startGame();
    }

    public void startGame() {
        Scanner in = new Scanner(System.in);

        InventoryItemsStorage inventoryItemsStorage = new InventoryItemsStorage();
        MapStorage mapStorage = new MapStorage();
        PlayerStatusStorage playerStatusStorage = new PlayerStatusStorage();

        PlayerStatus playerStatus = null;
        try {
            playerStatus = playerStatusStorage.readPlayerStatus();
        } catch (IOException e) {
            System.out.println("Can not read playerStatus !!\n" + e.getMessage());
        }

        try {
            inventoryItemsStorage.readFile(PLAYER_INVENTORY);
        } catch (IOException e) {
            System.out.println("Can not read inventory file!\n" + e.getMessage());
        }
        storedMaps.add(PLAYER_INVENTORY);
        mapIndex.put(INVENTORY_IDENTITY, storedMaps.size() - 1);
        TextBox textBox = new TextBox();
        Parser parser = new Parser();
        Ui ui = new Ui();


        textBox.initTextBox();


        PLAYER_INVENTORY.setPlayerStatus(playerStatus);
        PLAYER_INVENTORY.setCurrentTextBox(textBox);

        BaseMap map = null;
        try {
            map = mapStorage.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("\tCan not find your file!!!\n" + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Timer error !!\n" + e.getMessage());
        }
        map.setTextBox(textBox); // so that first map can use the text box
        HintHandler hints = new HintHandler(map, textBox);
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
            saveAllGameFile(mapStorage, playerStatusStorage, playerStatus, userCommand, inventoryItemsStorage);
        } while (!userCommand.getCommandDescription().equals("TIRED"));
    }

    private void saveAllGameFile(MapStorage mapStorage, PlayerStatusStorage playerStatusStorage,
                                 PlayerStatus playerStatus, Command userCommand,
                                 InventoryItemsStorage inventoryItemsStorage) {
        try {
            mapStorage.saveMap(storedMaps.get(mapIndex.get(FIRST_MAP_IDENTITY)));
        } catch (IOException e) {
            System.out.println("Can not save the map!\n" + e.getMessage());
        }
        try {
            playerStatusStorage.savePlayerStatus(playerStatus);
        } catch (IOException e) {
            System.out.println("Can not save Player Status" + e.getMessage());
        }

        try {
            inventoryItemsStorage.saveFile(PLAYER_INVENTORY);
        } catch (IOException e) {
            System.out.println("Can not save inventory items!\n" + e.getMessage());
        }
    }

    private static void printMessageUnderMap(Command userCommand, Ui ui, PlayerStatus playerStatus, TextBox textBox) {
        if (!userCommand.getCommandDescription().equals("HelpMe!!") &&
                !userCommand.getCommandDescription().equals("TIRED")) {
            ui.printPlayerStatus(playerStatus);
            if (storedMaps.get(currentMap) instanceof BattleInterface ||
                    storedMaps.get(currentMap) instanceof ShopMap) {
                ui.printEnemy(storedMaps.get(currentMap));
            } else if (storedMaps.get(currentMap) instanceof PlayerInventory) {
                ui.printInventory(playerStatus.getPlayerInventory().getGeneralItems(),
                        playerStatus.getPlayerInventory().getInventoryNames().get(0),
                        storedMaps.get(currentMap).getWidth(), storedMaps.get(currentMap).getHeight());
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
