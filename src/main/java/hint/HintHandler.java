package hint;

import map.BaseMap;
import textbox.TextBox;

import java.util.ArrayList;

public class HintHandler {
    BaseMap currentMap;
    TextBox currentTextBox;
    ArrayList<Trigger> invisibleTriggers;


    public HintHandler(BaseMap map, TextBox text){
        currentMap = map;
        currentTextBox = text;
        invisibleTriggers = new ArrayList<Trigger>();

        addTrigger(0, 1, 3, 2, "You should visit the shop sometime. The owner sells many" +
                " useful items for your journey");     //add all triggers in the map here
    }


    /**
     * Creates an invisible trigger with a defined area using 2 coordinates such that when a player walks over the
     * defined area, a message is injected into the Text Box.
     *
     * @param xStart starting x coordinate
     * @param yStart starting y coordinate
     * @param xEnd ending x coordinate
     * @param yEnd ending y coordinate
     * @param message message to be printed
     */
    public void addTrigger(int xStart, int yStart, int xEnd, int yEnd, String message){
        Trigger newTrigger = new Trigger(xStart, yStart, xEnd, yEnd, message);
        invisibleTriggers.add(newTrigger);
    }

    private boolean isWithinArea(Trigger trigger){
        return currentMap.getPlayerX() >= trigger.getXInitial() && currentMap.getPlayerX() <= trigger.getXEnding() &&
                currentMap.getPlayerY() >= trigger.getYInitial() && currentMap.getPlayerX() <= trigger.getYEnding();
    }


    public void checkMapThenDisplayHint(){  //takes first hint in list within an area and pushes it to the text box
        for (Trigger trigger : invisibleTriggers) {
            if (isWithinArea(trigger)){
                currentTextBox.setNextInstruction(trigger.getMessage());
                return;
            }
        }
    }
}
