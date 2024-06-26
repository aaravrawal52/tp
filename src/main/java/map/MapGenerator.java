package map;

import java.util.concurrent.ThreadLocalRandom;

public class MapGenerator {
    public static final char FIRST_MAP_IDENTITY = '!';
    public static final char SHOP = '#';
    public static final char CENTAUR = '@';
    public static final char DEMON = '$';
    public static final char DRAGON= '%';
    public static final char GOBLIN = '^';
    public static final char GRYPHON = '&';
    public static final char INVENTORY_IDENTITY = 'i';

    private static final int MIN = 0;
    private static final int MAX = 999;
    private static MapGenerator mapGen = null;

    private MapGenerator() {

    }

    public static MapGenerator getInstance() {
        if (mapGen == null) {
            mapGen = new MapGenerator();
        }
        return mapGen;
    }

    public void generateMap(BaseMap map) {
        int mapWidth = 30;
        int mapHeight = 10;
        int initPlayerX = 0;
        int initPlayerY = 0;
        int shopX = ThreadLocalRandom.current().nextInt(0, mapWidth);
        int shopY = ThreadLocalRandom.current().nextInt(0, mapHeight);

        map.initMap(mapWidth, mapHeight);
        map.initPlayerLocation(initPlayerX, initPlayerY);
        map.initShopLocation(shopX, shopY);

        int generateFactor;
        for (int i = 0; i < mapWidth; i++) {
            for (int j = 0; j < mapHeight; j++) {
                generateFactor = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
                if(map.getCurrentMapInfo(i, j) == '.'){
                    if(generateFactor == 0){
                        map.placeMonsterInTheMap(i, j, GRYPHON);
                    } else if (generateFactor >= 0 && generateFactor <= 4){
                        map.placeMonsterInTheMap(i, j, GOBLIN);
                    } else if (generateFactor >= 0 && generateFactor <= 9){
                        map.placeMonsterInTheMap(i, j, DRAGON);
                    } else if (generateFactor >= 0 && generateFactor <= 29){
                        map.placeMonsterInTheMap(i, j, DEMON);
                    } else if (generateFactor >= 0 && generateFactor <= 49){
                        map.placeMonsterInTheMap(i, j, CENTAUR);
                    }
                }
            }
        }

    }

    public void setMap(BaseMap mapToGenerate) {
        getInstance().generateMap(mapToGenerate);
    }
}
