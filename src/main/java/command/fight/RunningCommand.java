package command.fight;

import command.Command;
import map.BattleInterface.BattleInterface;

import static map.BaseMap.currentOn;
import static map.BaseMap.storedMaps;

public class RunningCommand extends Command {
    public RunningCommand() {
        commandDescription = "RUN!";
    }
    @Override
    public void execute(){
        if(currentMap instanceof BattleInterface) {
            textBox.setNextNarration("You decide to run and successfully got away");
            currentOn = 0;
            currentMap = storedMaps.get(currentOn);
        }
    }
}
