package command;

import inventoryitems.Item;
import map.BaseMap;
import map.FirstMap;
import map.MapGenerator;
import textbox.PlayerStatus;

import java.util.ArrayList;

import static main.CalculaChroniclesOfTheAlgorithmicKingdom.PLAYER_INVENTORY;
import static map.BaseMap.mapIndex;
import static map.BaseMap.currentMap;
import static map.BaseMap.storedMaps;
import static map.MapGenerator.FIRST_MAP_IDENTITY;
import static map.MapGenerator.INVENTORY_IDENTITY;

public class ResetCommand extends Command {
    public ResetCommand() {
        commandDescription = "RESET!";
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(PlayerStatus playerStatus) {
        System.out.print("\n\n\n\t\t\t\t\tResetting the game\n\n\n");
        for (int i = 0; i < 201; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread.sleep function failed!\n" + e.getMessage());
            }
            System.out.print("Cleaning all maps         |" + "#".repeat(i / 6) +
                    " ".repeat(33 - i / 6)
                    + "|" + "%" + i / 2 + "\r");
        }
        System.out.println();

        storedMaps.clear();
        mapIndex.clear();
        for (int i = 0; i < 201; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread.sleep function failed!\n" + e.getMessage());
            }
            System.out.print("Cleaning player inventory |" + "#".repeat(i / 6) +
                    " ".repeat(33 - i / 6)
                    + "|" + "%" + i / 2 + "\r");
        }
        System.out.println();

        ArrayList<Item> generalItem = PLAYER_INVENTORY.getGeneralItems();
        generalItem.clear();

        for (int i = 0; i < 201; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread.sleep function failed!\n" + e.getMessage());
            }
            System.out.print("Resetting player status   |" + "#".repeat(i / 6) +
                    " ".repeat(33 - i / 6)
                    + "|" + "%" + i / 2 + "\r");
        }
        System.out.println();

        playerStatus.setPlayerHealth(100);
        playerStatus.setPlayerMoney(0);
        playerStatus.setPlayerExp(0);
        playerStatus.setPlayerDamage(5);

        for (int i = 0; i < 201; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread.sleep function failed!\n" + e.getMessage());
            }
            System.out.print("Generating new map        |" + "#".repeat(i / 6) +
                    " ".repeat(33 - i / 6)
                    + "|" + "%" + i / 2 + "\r");
        }
        System.out.println();

        BaseMap map = new FirstMap();
        MapGenerator.getInstance().generateMap(map);
        map.setTextBox(textBox);

        storedMaps.add(PLAYER_INVENTORY);
        mapIndex.put(INVENTORY_IDENTITY, storedMaps.size() - 1);
        PLAYER_INVENTORY.setPlayerStatus(playerStatus);
        PLAYER_INVENTORY.setCurrentTextBox(textBox);
        storedMaps.add(map);
        mapIndex.put(FIRST_MAP_IDENTITY, storedMaps.size() - 1);
        currentMap = mapIndex.get(FIRST_MAP_IDENTITY);
    }
}
