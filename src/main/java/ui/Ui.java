package ui;

import interactable.Enemy;
import interactable.ShopKeeper;
import inventoryitems.Item;
import map.BaseMap;
import textbox.PlayerStatus;
import textbox.TextBox;
import math.MathQuestion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ui {
    private static final int DEFAULT_WIDTH_OF_BATTLE_INTERFACE = 50;
    private static final int DEFAULT_HEIGHT_OF_BATTLE_INTERFACE = 50;

    public void printDividingLine() {
        System.out.println("===========================================================");
    }

    public void printPlayerStatus(PlayerStatus statusBar) {
        printDividingLine();
        System.out.print("HEALTH: " + statusBar.getPlayerHealth() + "      ");
        System.out.print("MONEY: $" + statusBar.getPlayerMoney() + "      ");
        System.out.println("EXP: " + statusBar.getPlayerExp() + "      ");
        printDividingLine();
    }


    public void printTextBox(TextBox box) {
        assert box.getNextDialogue() != null : "next dialogue is null";
        assert box.getNextError() != null : "next error is null";
        assert box.getNextInstruction() != null : "next instruction is null";
        assert box.getNextNarration() != null : "next narration is null";


        printDividingLine();
        if (!box.getNextError().isEmpty()) {
            System.out.println(box.getNextError());
        }
        if (!box.getNextNarration().isEmpty()) {
            System.out.println(box.getNextNarration());
            System.out.println("\n");
        }
        if (!box.getNextDialogue().isEmpty()) {
            System.out.println(box.getNextDialogue());
        }
        if (!box.getNextInstruction().isEmpty()) {
            System.out.println(box.getNextInstruction());
        }
        printDividingLine();
        box.clearAll();
    }

    public void printTextbox(String message){ //for custom messages
        printDividingLine();
        System.out.println(message);
        printDividingLine();
    }

    private static StringBuilder getStringBuilder(ArrayList<Character> row, String healthInfo) {
        StringBuilder firstRowWithHealth = new StringBuilder();
        for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
            // Append the art character until reaching the position to overlay health info
            if (cellIndex < row.size() - healthInfo.length()) {
                firstRowWithHealth.append(row.get(cellIndex));
            } else {
                // Start overlaying health info onto the map
                int healthInfoIndex = cellIndex - (row.size() - healthInfo.length());
                firstRowWithHealth.append(healthInfo.charAt(healthInfoIndex));
            }
        }
        return firstRowWithHealth;
    }


    public void printMap(BaseMap map) {
        printDividingLine();
        for (ArrayList<Character> row : map.getMapData()) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        printDividingLine();
    }

    public void printMap(ArrayList<ArrayList<Character>> map, Enemy monster) {
        printDividingLine();
        String healthInfo = " Health: " + monster.getHealth(); // Health information as a string

        for (int rowIndex = 0; rowIndex < map.size(); rowIndex++) {
            ArrayList<Character> row = map.get(rowIndex);

            // Overlay the health information on the first row directly within the ASCII art
            if (rowIndex == 0) {
                StringBuilder firstRowWithHealth = getStringBuilder(row, healthInfo);
                System.out.println(firstRowWithHealth.toString());
            } else {
                for (char cell : row) {
                    System.out.print(cell);
                }
                System.out.println();
            }
        }
        printDividingLine();
    }


    public void printEnemy(BaseMap map) {
        printDividingLine();
        for (ArrayList<Character> row : map.getMapData()) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
        printDividingLine();
    }

    public void printShopKeeper(ShopKeeper cat){
        String filePath = cat.getFilePath();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file at " + filePath);
        }
    }

    public void printInventoryLine(String text, int quantity, int width) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|");
        stringBuilder.append(text);
        int itemQuantityCharacters = quantity <= 0 ? 0 : (String.valueOf(quantity).length() + 3);
        int length = width - text.length() - itemQuantityCharacters - 2;
        stringBuilder.append(" ".repeat(Math.max(0, length)));
        if (quantity > 0) {
            stringBuilder.append("x");
            stringBuilder.append(" ");
            stringBuilder.append(quantity);
            stringBuilder.append(" ");
        }

        stringBuilder.append("|");
        System.out.println(stringBuilder.toString());
    }

    public void printInventory(ArrayList<Item> inventory, String name, int width, int height) {
        printDividingLine();
        StringBuilder header = buildHeader(name, width);
        System.out.println(header);
        printInventoryLine("", 0, width);
        if (inventory.isEmpty()) {
            for (int i = 0; i < height - 1; i += 1) {
                System.out.print("|");
                for (int j = 0; j < width - 2; j += 1) {
                    System.out.print(" ");
                }
                System.out.println("|");
            }
            return;
        }
        for (Item item : inventory) {
            int itemIndex = inventory.indexOf(item) + 1;
            printInventoryLine(" " + itemIndex + ". " + item.getName(), item.getQuantity(), width);
        }
        if (inventory.size() < height - 1) {
            for (int i = 0; i < height - inventory.size() - 1; i += 1) {
                printInventoryLine(" ", 0, width);
            }
        }
        printDividingLine();
    }

    private static StringBuilder buildHeader(String inventoryName, int width) {
        StringBuilder header = new StringBuilder();
        int length = width - inventoryName.length() - 2;
        header.append("|");
        for (int i = 0; i <= length; i += 1) {
            if (i == 1) {
                header.append("<");
            } else if (i == length - 1) {
                header.append(">");
            } else if (i == length / 2 - 1) {
                header.append(inventoryName);
            } else {
                header.append(" ");
            }
        }
        header.append("|");
        return header;
    }

    public void printHelpMenu() {
        printDividingLine();
        System.out.println("'w' 'a' 's' 'd' to move around");
        System.out.println("'e' to interact");
        System.out.println("'q' to quit");
        System.out.println("'h' to print help menu");
        System.out.println("'run' to escape the battle interface");
        printDividingLine();
    }
    public void printQuestion(MathQuestion mathQuestion){
        System.out.println(mathQuestion.getQuestion());
    }


    public void printDeathMessage(){
        System.out.println("          _______             ______   ________  ______   _____      ");
        System.out.println("|\\     /|(  ___  )|\\     /|  (  __  \\ \\__   __/(  ____ \\(  __  \\ ");
        System.out.println("( \\   / )| (   ) || )   ( |  | (  \\  )   ) (   | (    \\/| (  \\  )");
        System.out.println(" \\ (_) / | |   | || |   | |  | |   ) |   | |   | (__    | |   ) |");
        System.out.println("  \\   /  | |   | || |   | |  | |   | |   | |   |  __)   | |   | |");
        System.out.println("   ) (   | |   | || |   | |  | |   ) |   | |   | (      | |   ) |");
        System.out.println("   | |   | (___) || (___) |  | (__/  )___) (___| (____/\\| (__/  )");
        System.out.println("   \\_/   (_______)(_______)  (______/ \\_______/(_______/(______/ ");
    }

    public void insertOutOfBoundsMessage(TextBox box){
        box.setNextNarration("You ran straight into a wall");
    }

    public void insertObjectObstructionMessage(TextBox box){
        System.out.println();box.setNextNarration("Something appears to be blocking your way");
    }
}
