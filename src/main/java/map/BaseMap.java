package map;

import org.w3c.dom.Text;
import textbox.TextBox;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class BaseMap {

    public static int currentMap;
    public static ArrayList<BaseMap> storedMaps = new ArrayList<>();
    public static HashMap<Character, Integer> mapIndex = new HashMap<>();
    protected int width;
    protected int height;
    protected ArrayList<ArrayList<Character>> mapData;
    protected int playerX;
    protected int playerY;
    protected String mapName;
    protected TextBox textBox;

    public BaseMap() {
    }

    public abstract void enableFight();

    public void enableFight(Scanner in) {
        assert in != null;
    }

    public void initMap(int givenWidth, int givenHeight) {
        assert givenHeight != 0;
        assert givenWidth != 0;
    }

    public void setTextBox(TextBox box){
        textBox = box;
    }

    public ArrayList<ArrayList<Character>> getMapData() {
        return mapData;
    }

    public void initPlayerLocation(int x, int y) {

        if (x >= 0 && x < width && y >= 0 && y < height) {
            mapData.get(y).set(x, 'P');
            this.playerX = x;
            this.playerY = y;
        }

    }

    public void initShopLocation(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            mapData.get(y).set(x, '#');
        }
    }

    public void movePlayerUpOne() {
        Ui ui = new Ui();
        if (this.playerY - 1 >= 0) {
            if (mapData.get(playerY - 1).get(playerX) != '.') {
                ui.insertObjectObstructionMessage(textBox);
            } else {
                mapData.get(playerY).set(playerX, '.');
                mapData.get(playerY - 1).set(playerX, 'P');
                this.playerY -= 1;
            }
        } else {
            ui.insertOutOfBoundsMessage(textBox);
        }
    }

    public void movePlayerDownOne() {
        Ui ui = new Ui();
        if (this.playerY + 1 < height) {
            if (mapData.get(playerY + 1).get(playerX) != '.') {
                ui.insertObjectObstructionMessage(textBox);
            } else {
                mapData.get(playerY).set(playerX, '.');
                mapData.get(playerY + 1).set(playerX, 'P');
                this.playerY += 1;
            }
        } else {
            ui.insertOutOfBoundsMessage(textBox);
        }
    }

    public void movePlayerLeftOne() {
        Ui ui = new Ui();
        if (this.playerX - 1 >= 0) {
            if (mapData.get(playerY).get(playerX - 1) != '.') {
                ui.insertObjectObstructionMessage(textBox);
            } else {
                mapData.get(playerY).set(playerX, '.');
                mapData.get(playerY).set(playerX - 1, 'P');
                this.playerX -= 1;
            }
        } else {
            ui.insertOutOfBoundsMessage(textBox);
        }
    }

    public void movePlayerRightOne() {
        Ui ui = new Ui();
        if (this.playerX + 1 < width) {
            if (mapData.get(playerY).get(playerX + 1) != '.') {
                ui.insertObjectObstructionMessage(textBox);
            } else {
                mapData.get(playerY).set(playerX, '.');
                mapData.get(playerY).set(playerX + 1, 'P');
                this.playerX += 1;
            }
        } else {
            ui.insertOutOfBoundsMessage(textBox);
        }
    }


    public ArrayList<ArrayList<Character>> getMap() {
        return mapData;
    }

    public String handleInteract() {
        if (playerY > 0 && mapData.get(playerY - 1).get(playerX) != '.') {
            return String.valueOf(mapData.get(playerY - 1).get(playerX));
        }

        if (playerX < mapData.get(0).size() - 1 && mapData.get(playerY).get(playerX + 1) != '.') {
            return String.valueOf(mapData.get(playerY).get(playerX + 1));
        }

        if (playerY < mapData.size() - 1 && mapData.get(playerY + 1).get(playerX) != '.') {
            return String.valueOf(mapData.get(playerY + 1).get(playerX));
        }

        if (playerX > 0 && mapData.get(playerY).get(playerX - 1) != '.') {
            return String.valueOf(mapData.get(playerY).get(playerX - 1));
        }
        return "no interaction";
    }

    public int getInteractX() {
        if (playerY > 0 && mapData.get(playerY - 1).get(playerX) != '.') {
            return playerX;
        }
        if (playerX < mapData.get(0).size() - 1 && mapData.get(playerY).get(playerX + 1) != '.') {
            return playerX + 1;
        }
        if (playerY < mapData.size() - 1 && mapData.get(playerY + 1).get(playerX) != '.') {
            return playerX;
        }
        if (playerX > 0 && mapData.get(playerY).get(playerX - 1) != '.') {
            return playerX - 1;
        }
        return -1;
    }

    public int getInteractY() {
        if (playerY > 0 && mapData.get(playerY - 1).get(playerX) != '.') {
            return playerY - 1;
        }
        if (playerX < mapData.get(0).size() - 1 && mapData.get(playerY).get(playerX + 1) != '.') {
            return playerY;
        }
        if (playerY < mapData.size() - 1 && mapData.get(playerY + 1).get(playerX) != '.') {
            return playerY + 1;
        }
        if (playerX > 0 && mapData.get(playerY).get(playerX - 1) != '.') {
            return playerY;
        }
        return -1;
    }

    public void clearSpot(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            mapData.get(y).set(x, '.');
        }
    }

    public abstract boolean getEntityDeath();

    public abstract boolean getPlayerDeath();

    public abstract void handleDeath();

    public abstract void handleLootingByPlayer();

    public void placeMonsterInTheMap(int x, int y, char monsterType) {
        mapData.get(y).set(x, monsterType);
    }

    public Character getCurrentMapInfo(int x, int y) {
        return mapData.get(y).get(x);
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }
    public void printMap(){
        for(int i = 0; i < height; i++){
            for (int j = 0;  j < width; j++){
                System.out.print(mapData.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
