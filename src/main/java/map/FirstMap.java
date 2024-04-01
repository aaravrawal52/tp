package map;

import java.util.ArrayList;

public class FirstMap extends BaseMap {
    protected String DIFFICULTY_MODIFIER = "easy"; //can use to determine question difficulty

    @Override
    public void fightLoop() {
        System.out.println("lol");
    }
    public boolean getEntityDeath(){
        return false;
    }
    @Override
    public boolean getPlayerDeath(){
        return false;
    }
    @Override
    public void handleDeath() {
    }
    @Override
    public void handleLootingByPlayer(){

    }
    public void initMap(int givenWidth, int givenHeight) { //creates a box of "." of a given width and height and width
        this.width = givenWidth;
        this.height = givenHeight;
        this.currentMap = new ArrayList<>(height);

        for (int i = 0; i < height; i += 1) {
            ArrayList<Character> row = new ArrayList<>(width);
            for (int j = 0; j < width; j += 1) {
                row.add('.');
            }
            currentMap.add(row);
        }
    }
}
