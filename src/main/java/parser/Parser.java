package parser;

import command.CommandType;
import command.fight.FightingCommand;
import command.fight.RunningCommand;
import command.inventory.SellCommand;
import command.inventory.OpenInventoryCommand;
import command.inventory.CloseInventoryCommand;
import command.inventory.UseCommand;
import command.inventory.NextPageCommand;
import command.inventory.PrevPageCommand;
import command.mapmove.InteractingCommand;
import command.mapmove.MovingDownwardCommand;
import command.mapmove.MovingForwardCommand;
import command.mapmove.MovingLeftCommand;
import command.mapmove.MovingRightCommand;
import command.ErrorCommand;
import command.HelpCommand;
import command.QuitCommand;
import command.Command;
import command.mapmove.ExitShop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static map.BaseMap.currentMap;
import static map.BaseMap.mapIndex;
import static map.MapGenerator.FIRST_MAP_IDENTITY;
import static map.MapGenerator.SHOP;
import static map.MapGenerator.INVENTORY_IDENTITY;

public class Parser {

    private static final int FIRST_MAP = 0;

    public CommandType analyseCommand(String userCommand) {
        Pattern pattern;
        Matcher matcher;
        for (CommandType commandType : CommandType.values()) {
            pattern = Pattern.compile(commandType.getRegExpression());
            matcher = pattern.matcher(userCommand);
            if (matcher.matches()) {
                return commandType;
            }
        }
        return CommandType.ERROR;
    }

    public Command parseCommand(String userCommand) {
        Command command;
        CommandType commandType = analyseCommand(userCommand);

        switch (commandType) {
        case FIGHT:
            command = (currentMap != mapIndex.get(FIRST_MAP_IDENTITY)) ? new FightingCommand() : new ErrorCommand();
            break;
        case EXIT:
            try {
                command = (currentMap == mapIndex.get(SHOP)) ? new ExitShop() : new ErrorCommand();
            } catch (NullPointerException e){
                command = new ErrorCommand();
            }
            break;
        case RUN:
            command = (currentMap != mapIndex.get(FIRST_MAP_IDENTITY)) ? new RunningCommand() : new ErrorCommand();
            break;
        case MOVE_FORWARD:
            command = (currentMap == mapIndex.get(FIRST_MAP_IDENTITY)) ?
                    new MovingForwardCommand(userCommand) : new ErrorCommand();
            break;
        case MOVE_DOWNWARD:
            command = (currentMap == mapIndex.get(FIRST_MAP_IDENTITY)) ?
                    new MovingDownwardCommand(userCommand) : new ErrorCommand();
            break;
        case MOVE_LEFT:
            command = (currentMap == mapIndex.get(FIRST_MAP_IDENTITY)) ?
                    new MovingLeftCommand(userCommand) : new ErrorCommand();
            break;
        case MOVE_RIGHT:
            command = (currentMap == mapIndex.get(FIRST_MAP_IDENTITY)) ?
                    new MovingRightCommand(userCommand) : new ErrorCommand();
            break;
        case QUIT:
            command = new QuitCommand();
            break;
        case INTERACT:
            command = (currentMap == mapIndex.get(FIRST_MAP_IDENTITY)) ? new InteractingCommand() : new ErrorCommand();
            break;
        case HELP:
            command = new HelpCommand();
            break;
        case ERROR:
            command = new ErrorCommand();
            break;
        case INVENTORY:
            command = (currentMap == mapIndex.get(FIRST_MAP_IDENTITY)) ?
                    new OpenInventoryCommand() : new ErrorCommand();
            break;
        case INV_NEXT:
            command = (currentMap == mapIndex.get(INVENTORY_IDENTITY)) ? new NextPageCommand() : new ErrorCommand();
            break;
        case INV_PREV:
            command = (currentMap == mapIndex.get(INVENTORY_IDENTITY)) ? new PrevPageCommand() : new ErrorCommand();
            break;
        case USE_ITEM:
            command = (currentMap == mapIndex.get(INVENTORY_IDENTITY)) ?
                    new UseCommand(userCommand) : new ErrorCommand();
            break;
        case SELL_ITEM:
            command = (currentMap == mapIndex.get(INVENTORY_IDENTITY)) ?
                    new SellCommand(userCommand) : new ErrorCommand();
            break;
        case CLOSE_INV: // to delete aft sihan implements
            command = (currentMap == mapIndex.get(INVENTORY_IDENTITY)) ?
                    new CloseInventoryCommand() : new ErrorCommand();
            break;
        default:
            command = new ErrorCommand();
        }
        return command;
    }

}
