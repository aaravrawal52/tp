package parser;

import command.*;
import command.fight.FightingCommand;
import command.fight.RunningCommand;
import command.mapmove.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static map.BaseMap.*;
import static map.MapGenerator.*;

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
            command = (currentOn != mapIndex.get(FIRST_MAP_IDENTITY)) ? new FightingCommand() : new ErrorCommand();
            break;
        case RUN:
            command = (currentOn != mapIndex.get(FIRST_MAP_IDENTITY)) ? new RunningCommand() : new ErrorCommand();
            break;
        case MOVE_FORWARD:
            command = (currentOn == mapIndex.get(FIRST_MAP_IDENTITY)) ? new MovingForwardCommand(userCommand) : new ErrorCommand();
            break;
        case MOVE_DOWNWARD:
            command = (currentOn == mapIndex.get(FIRST_MAP_IDENTITY)) ? new MovingDownwardCommand(userCommand) : new ErrorCommand();
            break;
        case MOVE_LEFT:
            command = (currentOn == mapIndex.get(FIRST_MAP_IDENTITY)) ? new MovingLeftCommand(userCommand) : new ErrorCommand();
            break;
        case MOVE_RIGHT:
            command = (currentOn == mapIndex.get(FIRST_MAP_IDENTITY)) ? new MovingRightCommand(userCommand) : new ErrorCommand();
            break;
        case QUIT:
            command = new QuitCommand();
            break;
        case INTERACT:
            command = (currentOn == mapIndex.get(FIRST_MAP_IDENTITY)) ? new InteractingCommand() : new ErrorCommand();
            break;
        case HELP:
            command = new HelpCommand();
            break;
        case ERROR:
            command = new ErrorCommand();
            break;
        default:
            command = new ErrorCommand();
        }
        return command;
    }

}
