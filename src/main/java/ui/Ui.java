package ui;

import map.BattleInterface.BattleInterface;
import map.AMap;
import textbox.PlayerStatus;
import textbox.TextBox;

import java.util.ArrayList;

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
        printDividingLine();
        if (box.getNextMessage() != null) {
            System.out.println(box.getNextMessage());
        } else {
            System.out.println(" ");
        }
        printDividingLine();
    }

    public void printMap(AMap map) {
        printDividingLine();
        for (ArrayList<Character> row : map.getCurrentMap()) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        printDividingLine();
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
}