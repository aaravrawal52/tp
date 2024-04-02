package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class BaseMap {

    public static int currentOn;
    public static ArrayList<BaseMap> storedMaps = new ArrayList<>();
    public static HashMap<Character, Integer> mapIndex = new HashMap<>();
    protected int width;
    protected int height;
    protected ArrayList<ArrayList<Character>> currentMap;
    protected int playerX;
    protected int playerY;
    protected String mapName;

    public BaseMap() {

    }

    public abstract void fightLoop();

    public void fightLoop(Scanner in) {
        assert in != null;
    }

    public void initMap(int givenWidth, int givenHeight) {
        assert givenHeight != 0;
        assert givenWidth != 0;
    }

    public ArrayList<ArrayList<Character>> getCurrentMap() {
        return currentMap;
    }

    public void initPlayerLocation(int x, int y) {

        if (x >= 0 && x < width && y >= 0 && y < height) {
            currentMap.get(y).set(x, 'P');
            this.playerX = x;
            this.playerY = y;
        }

    }

    public void initShopLocation(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            currentMap.get(y).set(x, '#');
        }
    }

    public void movePlayerUpOne() {
        if (this.playerY - 1 >= 0) {
            if (currentMap.get(playerY - 1).get(playerX) == '@') {
                System.out.println("MONSTER HERE\n");
            } else {
                currentMap.get(playerY).set(playerX, '.');
                currentMap.get(playerY - 1).set(playerX, 'P');
                this.playerY -= 1;
            }
        }
    }

    public void movePlayerDownOne() {
        if (this.playerY + 1 < height) {
            if (currentMap.get(playerY + 1).get(playerX) == '@') {
                System.out.println("MONSTER HERE\n");
            } else {
                currentMap.get(playerY).set(playerX, '.');
                currentMap.get(playerY + 1).set(playerX, 'P');
                this.playerY += 1;
            }
        }
    }

    public void movePlayerLeftOne() {
        if (this.playerX - 1 >= 0) {
            if (currentMap.get(playerY).get(playerX - 1) == '@') {
                System.out.println("MONSTER HERE\n");
            } else {
                currentMap.get(playerY).set(playerX, '.');
                currentMap.get(playerY).set(playerX - 1, 'P');
                this.playerX -= 1;
            }
        }
    }

    public void movePlayerRightOne() {
        if (this.playerX + 1 < width) {
            if (currentMap.get(playerY).get(playerX + 1) == '@') {
                System.out.println("MONSTER HERE\n");
            } else {
                currentMap.get(playerY).set(playerX, '.');
                currentMap.get(playerY).set(playerX + 1, 'P');
                this.playerX += 1;
            }
        }
    }


    public ArrayList<ArrayList<Character>> getMap() {
        return currentMap;
    }

    public String handleInteract() {
        if (playerY > 0 && currentMap.get(playerY - 1).get(playerX) != '.') {
            return String.valueOf(currentMap.get(playerY - 1).get(playerX));
        }

        if (playerX < currentMap.get(0).size() - 1 && currentMap.get(playerY).get(playerX + 1) != '.') {
            return String.valueOf(currentMap.get(playerY).get(playerX + 1));
        }

        if (playerY < currentMap.size() - 1 && currentMap.get(playerY + 1).get(playerX) != '.') {
            return String.valueOf(currentMap.get(playerY + 1).get(playerX));
        }

        if (playerX > 0 && currentMap.get(playerY).get(playerX - 1) != '.') {
            return String.valueOf(currentMap.get(playerY).get(playerX - 1));
        }
        return "no interaction";
    }

    public int getInteractX() {
        if (playerY > 0 && currentMap.get(playerY - 1).get(playerX) != '.') {
            return playerX;
        }
        if (playerX < currentMap.get(0).size() - 1 && currentMap.get(playerY).get(playerX + 1) != '.') {
            return playerX + 1;
        }
        if (playerY < currentMap.size() - 1 && currentMap.get(playerY + 1).get(playerX) != '.') {
            return playerX;
        }
        if (playerX > 0 && currentMap.get(playerY).get(playerX - 1) != '.') {
            return playerX - 1;
        }
        return -1;
    }

    public int getInteractY() {
        if (playerY > 0 && currentMap.get(playerY - 1).get(playerX) != '.') {
            return playerY - 1;
        }
        if (playerX < currentMap.get(0).size() - 1 && currentMap.get(playerY).get(playerX + 1) != '.') {
            return playerY;
        }
        if (playerY < currentMap.size() - 1 && currentMap.get(playerY + 1).get(playerX) != '.') {
            return playerY + 1;
        }
        if (playerX > 0 && currentMap.get(playerY).get(playerX - 1) != '.') {
            return playerY;
        }
        return -1;
    }

    public void clearSpot(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            currentMap.get(y).set(x, '.');
        }
    }

    public abstract boolean getEntityDeath();

    public abstract boolean getPlayerDeath();

    public abstract void handleDeath();

    public abstract void handleLootingByPlayer();

    public void placeMonsterInTheMap(int x, int y, char monsterType) {
        currentMap.get(y).set(x, monsterType);
    }

    public Character getCurrentMapInfo(int x, int y) {
        return currentMap.get(y).get(x);
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
                System.out.print(currentMap.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
